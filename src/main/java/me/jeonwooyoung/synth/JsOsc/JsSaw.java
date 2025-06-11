//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.jeonwooyoung.synth.JsOsc;

import com.jsyn.engine.MultiTable;
import com.jsyn.unitgen.UnitOscillator;

public class JsSaw extends UnitOscillator {
    private static JsSaw singletonObject;

    public JsSaw() {
    }

    public static JsSaw getInstance(){
        if(singletonObject == null){
            singletonObject = new JsSaw();
        }

        return singletonObject;
    }

    public void generate(int start, int limit) {
        MultiTable multiTable = MultiTable.getInstance();
        double[] frequencies = this.frequency.getValues();
        double[] amplitudes = this.amplitude.getValues();
        double[] outputs = this.output.getValues();
        double currentPhase = this.phase.getValue();
        double phaseIncrement = this.convertFrequencyToPhaseIncrement(frequencies[0]);
        double positivePhaseIncrement = Math.abs(phaseIncrement);
        double flevel = multiTable.convertPhaseIncrementToLevel(positivePhaseIncrement);

        for(int i = start; i < limit; ++i) {
            phaseIncrement = this.convertFrequencyToPhaseIncrement(frequencies[i]);
            currentPhase = this.incrementWrapPhase(currentPhase, phaseIncrement);
            positivePhaseIncrement = Math.abs(phaseIncrement);
            double val = this.generateBL(multiTable, currentPhase, positivePhaseIncrement, flevel, i);
            outputs[i] = val * amplitudes[i];
        }

        this.phase.setValue(currentPhase);
    }

    protected double generateBL(MultiTable multiTable, double currentPhase, double positivePhaseIncrement, double flevel, int i) {
        return multiTable.calculateSawtooth(currentPhase, positivePhaseIncrement, flevel);
    }
}
