package me.jeonwooyoung.synth.grain;

/*import java.io.IOException;

public class GrainSynth implements Osc1 {
    private Synthesizer synth;
    private LineOut lineOut;
    private AudioScope scope;
    private GrainFarm grainFarm;
    private ContinuousRamp ramp;
    private static final int NUM_GRAINS = 32;
    private FloatSample sample;
    private WaveRecorder recorder;
    private static boolean useRecorder = false;
    private static boolean playingRecoder;

    public double rate;
    public double rateRange;
    public double amplitude;
    public double amplitudeRange;
    public double density;
    public double duration;
    public double durationRange;

    private static final boolean useSample = true;
    // If you enable useSample then you will need to replace the file name below with a valid
    // file name on your computer.
    private static final File sampleFile = new File("notes_to_tone.wav");
    SampleGrainFarm sampleGrainFarm = (SampleGrainFarm) grainFarm;

    public GrainSynth(){
        synth = JSyn.createSynthesizer();


    }

    @Override
    public void startAudio() {
        synth = JSyn.createSynthesizer();

        try {

            if (useRecorder) {
                File waveFile = new File("temp_recording.wav");
                // Record mono 16 bits.
                recorder = new WaveRecorder(synth, waveFile, 1);
                System.out.println("Writing to WAV file " + waveFile.getAbsolutePath());
            }

            if (useSample) {
                sample = SampleLoader.loadFloatSample(sampleFile);
                SampleGrainFarm sampleGrainFarm = new SampleGrainFarm();
                synth.add(ramp = new ContinuousRamp());
                sampleGrainFarm.setSample(sample);
                ramp.output.connect(sampleGrainFarm.position);
                grainFarm = sampleGrainFarm;
            } else {
                grainFarm = new GrainFarm();
            }

            synth.add(grainFarm);

            grainFarm.allocate(NUM_GRAINS);

            // Add an output so we can hear the grains.
            synth.add(lineOut = new LineOut());

            grainFarm.getOutput().connect(0, lineOut.input, 0);
            grainFarm.getOutput().connect(0, lineOut.input, 1);

            // Start synthesizer using default stereo output at 44100 Hz.
            synth.start();

            if (useRecorder) {
                grainFarm.output.connect(0, recorder.getInput(), 0);
                // When we start the recorder it will pull data from the
                // oscillator
                // and sweeper.
                recorder.start();
            }

            // We only need to start the LineOut. It will pull data from the
            // oscillator.
            lineOut.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void stopAudio() {
        try {
            if (recorder != null) {
                recorder.stop();
                recorder.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scope.stop();
        synth.stop();

    }
}*/
