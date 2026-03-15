package me.jeonwooyoung.Istft;

import org.jtransforms.fft.DoubleFFT_1D;

public class ISTFT {
    private int numFrames;
    private int signalLength;
    private double[][][] stftData;

    private double[] outputSignal;
    private double[] window;
    private double[] normalization;

    private int windowSize;
    private int hopSize;


    public ISTFT(double[][][] stftData, int windowSize, int hopSize) {
        this.stftData = stftData;
        this.windowSize = windowSize;
        this.hopSize = hopSize;

        this.numFrames = stftData.length;
        this.signalLength = (numFrames - 1) * hopSize + windowSize;

        this.outputSignal = new double[signalLength];
        this.window = hannWindow(windowSize);
        this.normalization = new double[signalLength];
    }

    public double[] Makeistft() {
        DoubleFFT_1D fft = new DoubleFFT_1D(windowSize);

        for (int frame = 0; frame < numFrames; frame++) {
            double[] complex = new double[windowSize * 2];


            for (int k = 0; k < stftData[frame].length; k++) {
                complex[2 * k]     = stftData[frame][k][0]; // real
                complex[2 * k + 1] = stftData[frame][k][1]; // imag
            }


            for (int k = stftData[0].length; k < windowSize / 2 + 1; k++) {
                complex[2 * k] = 0;
                complex[2 * k + 1] = 0;
            }


            for (int k = windowSize / 2 + 1; k < windowSize; k++) {
                int mirrorIdx = windowSize - k;
                complex[2 * k]     = stftData[frame][mirrorIdx][0];
                complex[2 * k + 1] = -stftData[frame][mirrorIdx][1];
            }


            fft.complexInverse(complex, true);


            for (int n = 0; n < windowSize; n++) {
                int idx = frame * hopSize + n;
                if (idx < signalLength) {
                    outputSignal[idx] += complex[2 * n] * window[n];
                    normalization[idx] += window[n] * window[n];
                }
            }
        }


        for (int i = 0; i < outputSignal.length; i++) {
            if (normalization[i] > 0) {
                outputSignal[i] /= normalization[i];
            }
        }

        return outputSignal;
    }

    private double[] hannWindow(int size) {
        double[] window = new double[size];
        for (int i = 0; i < size; i++) {
            window[i] = 0.5 * (1 - Math.cos(2 * Math.PI * i / (size - 1)));
        }
        return window;
    }
}