/*
 * Copyright 2009 Phil Burk, Mobileer Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.jeonwooyoung.synth.play;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SawtoothOscillatorBL;
import com.jsyn.unitgen.UnitOscillator;
import me.jeonwooyoung.synth.oscillater.OscPlayer;
import me.jeonwooyoung.synth.oscillater.SawwaveStrategy;
import me.jeonwooyoung.synth.oscillater.SinewaveStrategy;

import java.util.ArrayList;


/**
 * Use time stamps to change the frequency of an oscillator at precise times.
 *
 * @author Phil Burk (C) 2009 Mobileer Inc
 */
public class PlaySequence {
    double time;
    double freq;
    double averagebright;
    OscPlayer oscplayer;

    Synthesizer synth;
    UnitOscillator osc;
    LineOut lineOut;
    public PlaySequence(ArrayList<Double> generateAudioParametersArrayList) {
        this.freq = generateAudioParametersArrayList.get(0);
        this.time = generateAudioParametersArrayList.get(1);
        this.averagebright = generateAudioParametersArrayList.get(2);
    }
    //밝기 값에 따른 음색
    private void playAudio() {
        // Create a context for the synthesizer.

        synth = JSyn.createSynthesizer();
        synth.add(osc = new SawtoothOscillatorBL());
        synth.add(lineOut = new LineOut());
        osc.output.connect(0, lineOut.input, 0);
        osc.output.connect(0, lineOut.input, 1);
        synth.start();
        lineOut.start();

        osc.frequency.set(freq, time);

        freq *= 1.5;
        time += 0.5;
        osc.frequency.set(freq, time);

        freq *= 4;
        time += 0.5;
        osc.frequency.set(freq, time);

        try {
            synth.sleepUntil(time + 0.5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop everything.
        synth.stop();
    }

}