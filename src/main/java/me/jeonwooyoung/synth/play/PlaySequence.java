package me.jeonwooyoung.synth.play;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.UnitOscillator;
import me.jeonwooyoung.synth.JsOsc.JsPulse;
import me.jeonwooyoung.synth.JsOsc.JsSaw; // Assuming JsSaw is your custom Sawtooth Oscillator
import me.jeonwooyoung.synth.JsOsc.JsSine;

import java.util.ArrayList;

/**
 * Use time stamps to change the frequency of an oscillator at precise times.
 *
 * @author Phil Burk (C) 2009 Mobileer Inc
 */
public class PlaySequence {
    double noteDuration; // Duration of each individual note in seconds.
    double baseFreq;     // Starting frequency for the first note.
    int numberOfNotes;
    double degree;// Total number of notes to play.

    Synthesizer synth;
    UnitOscillator osc; // This will hold the instance of JsSaw.
    LineOut lineOut;

    public PlaySequence(ArrayList<Double> generateAudioParametersArrayList) {
        this.baseFreq = generateAudioParametersArrayList.get(0) * 10;
        this.noteDuration = generateAudioParametersArrayList.get(1);
        this.numberOfNotes = (int) Math.round(generateAudioParametersArrayList.get(2));
        this.degree = generateAudioParametersArrayList.get(3);
    }

    // Plays a sequence of notes with varying frequencies based on brightness values (implied).
    public void playaudio() {
        // Create a synthesizer context.
        synth = JSyn.createSynthesizer();

        // Add your custom tone generator (JsSaw).
        synth.add(osc = new JsPulse()); // Make sure JsSaw has a public constructor or a static factory method you can call.
        // If JsSaw.getInstance() is preferred, then: synth.add(osc = JsSaw.getInstance());
        // Add an output mixer.
        synth.add(lineOut = new LineOut());

        // Connect the oscillator's output to both left and right audio channels.
        osc.output.connect(0, lineOut.input, 0);
        osc.output.connect(0, lineOut.input, 1);

        // Start the synthesizer. This initializes the audio engine.
        synth.start();
        // Start the LineOut. It will pull audio data from the connected units.
        lineOut.start();

        // Get the current time from the synthesizer. All note events will be scheduled relative to this.
        double currentTime = synth.getCurrentTime();
        double currentFreq = baseFreq;

        // Loop to schedule each note.
        for (int i = 0; i < numberOfNotes; i++) {
            // Calculate the frequency for the current note.
            // This example increases the frequency exponentially (like a musical scale).
            // Adjust '1.1' or the formula if you need a different frequency progression.

            // Schedule the note to start:
            // Set the frequency for this note at its start time.
            osc.frequency.set(currentFreq, currentTime);
            // Set the amplitude (volume) to 1.0 (full volume) at its start time.
            osc.amplitude.set(1.0, currentTime);

            // Schedule the note to end:
            // Set the amplitude to 0.0 (silent) at the note's end time.
            // We subtract a very small value (0.001) to ensure the note fully stops before the next one starts,
            // preventing tiny overlaps due to floating-point precision or scheduling nuances.
            osc.amplitude.set(0.0, currentTime + noteDuration - 0.001);

            // Advance the current time to the start of the next note.
            currentTime += noteDuration;
            currentFreq *= 1.5 * Math.sin(degree*100);
            degree += 120;


        }

        // After scheduling all notes, wait until the last note has finished playing.
        // currentTime now holds the timestamp for when the very last note is supposed to end.
        // We add a small buffer (0.1 seconds) just to be absolutely sure all sound has faded out
        // and internal audio buffers are cleared before stopping the synthesizer.
        try {
            synth.sleepUntil(currentTime + 0.1);
        } catch (InterruptedException e) {
            // If the thread is interrupted while sleeping, print the stack trace.
            e.printStackTrace();
            // Re-interrupt the current thread to signal that it was interrupted.
            Thread.currentThread().interrupt();
        } finally {
            // Always stop the synthesizer and release resources, even if an exception occurred.
            if (synth != null) {
                synth.stop();
            }
        }
    }
}