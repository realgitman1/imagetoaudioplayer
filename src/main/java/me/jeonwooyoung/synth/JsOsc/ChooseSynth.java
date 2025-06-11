package me.jeonwooyoung.synth.JsOsc;

import me.jeonwooyoung.Imagetoaudio.ImagetoAduioConverter;

public class ChooseSynth {

    ImagetoAduioConverter imagetoAduioConverter;
    double Brighness;
    public ChooseSynth(ImagetoAduioConverter imagetoAduioConverter) {
        this.imagetoAduioConverter = imagetoAduioConverter;
        this.Brighness = imagetoAduioConverter.generateAudioParameters().get(3);
    }
}
