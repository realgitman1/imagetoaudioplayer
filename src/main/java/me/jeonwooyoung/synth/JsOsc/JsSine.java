//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.jeonwooyoung.synth.JsOsc;

import com.jsyn.unitgen.UnitOscillator;

public class JsSine extends UnitOscillator {
    private static JsSine singletonObject;

    public JsSine() {
    }

    public static JsSine getInstance(){
        if(singletonObject == null){
            singletonObject = new JsSine();
        }

        return singletonObject;
    }

    public void SineOscillator(double freq) {
        this.frequency.set(freq);
    }

    public void SineOscillator(double freq, double amp) {
        this.frequency.set(freq);
        this.amplitude.set(amp);
    }

    public void generate(int start, int limit) {
        double[] frequencies = this.frequency.getValues();
        double[] amplitudes = this.amplitude.getValues();
        double[] outputs = this.output.getValues();
        double currentPhase = this.phase.getValue();

        for(int i = start; i < limit; ++i) {
            double phaseIncrement = this.convertFrequencyToPhaseIncrement(frequencies[i]);
            currentPhase = this.incrementWrapPhase(currentPhase, phaseIncrement);
            double value = fastSin(currentPhase);
            outputs[i] = value * amplitudes[i];
        }

        this.phase.setValue(currentPhase);
    }

    public static double fastSin(double currentPhase) {
        double IF3 = 0.16666666666666666;
        double IF5 = 0.008333333333333333;
        double IF7 = 1.984126984126984E-4;
        double IF9 = 2.7557319223985893E-6;
        double IF11 = 2.505210838544172E-8;
        double yp = currentPhase > (double)0.5F ? (double)1.0F - currentPhase : (currentPhase < (double)-0.5F ? (double)-1.0F - currentPhase : currentPhase);
        double x = yp * Math.PI;
        double x2 = x * x;
        return x * (x2 * (x2 * (x2 * (x2 * (x2 * -2.505210838544172E-8 + 2.7557319223985893E-6) - 1.984126984126984E-4) + 0.008333333333333333) - 0.16666666666666666) + (double)1.0F);
    }
}
