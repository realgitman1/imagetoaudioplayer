//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.jeonwooyoung.synth.JsOsc;

import com.jsyn.engine.MultiTable;
import com.jsyn.unitgen.SawtoothOscillatorBL;

public class JsSq extends SawtoothOscillatorBL {
    static JsSq singletonObject;

    public JsSq() {
    }

    public static JsSq getInstance(){
        if(singletonObject == null){
            singletonObject = new JsSq();
        }

        return singletonObject;
    }
    protected double generateBL(MultiTable multiTable, double currentPhase, double positivePhaseIncrement, double flevel, int i) {
        double val1 = multiTable.calculateSawtooth(currentPhase, positivePhaseIncrement, flevel);
        double phase2 = currentPhase + (double)1.0F;
        if (phase2 >= (double)1.0F) {
            phase2 -= (double)2.0F;
        }

        double val2 = multiTable.calculateSawtooth(phase2, positivePhaseIncrement, flevel);
        double STARTAMP = 0.92;
        double scale = 0.92 - positivePhaseIncrement;
        return scale * (val1 - val2);
    }
}
