
package me.jeonwooyoung.synth.JsOsc;

import com.jsyn.engine.MultiTable;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.SawtoothOscillatorBL;

public class JsPulse extends SawtoothOscillatorBL {
    public UnitInputPort width;
    private static JsPulse singletonObject;

    public static JsPulse getInstance(){
        if(singletonObject == null){
            singletonObject = new JsPulse();
        }

        return singletonObject;
    }

    public JsPulse() {
        this.addPort(this.width = new UnitInputPort("Width"));
    }

    public double generate(MultiTable multiTable, double currentPhase, double positivePhaseIncrement, double flevel, int i) {
        double[] widths = this.width.getValues();
        double width = widths[i];
        width = width > 0.999 ? 0.999 : (width < -0.999 ? -0.999 : width);
        double val1 = multiTable.calculateSawtooth(currentPhase, positivePhaseIncrement, flevel);
        double phase2 = currentPhase + (double)1.0F - width;
        if (phase2 >= (double)1.0F) {
            phase2 -= (double)2.0F;
        }

        double val2 = multiTable.calculateSawtooth(phase2, positivePhaseIncrement, flevel);
        double scale = (double)1.0F - positivePhaseIncrement;
        return scale * (val1 - val2 - width);
    }
}
