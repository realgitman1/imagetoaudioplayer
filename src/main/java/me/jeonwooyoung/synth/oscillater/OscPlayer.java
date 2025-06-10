package me.jeonwooyoung.synth.oscillater;

public class OscPlayer {
    private Osc1 osc1;

    public void setOsc1(Osc1 osc1){
        this.osc1 = osc1;
    }

    public void startAudio(){
        osc1.startAudio();
    }

    public void stopAudio(){
        osc1.stopAudio();
    }
}
