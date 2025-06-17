package me.jeonwooyoung.Istft;

import org.jtransforms.fft.DoubleFFT_1D;

public class ISTFT {
    static int numFrames;
    static int signalLength;
    static double[][][] stftData;

    static double[] outputSignal;
    static double[] window;
    static double[] normalization;

    static int windowSize;
    static int hopSize;

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

    public static double[] Makeistft() {


        DoubleFFT_1D fft = new DoubleFFT_1D(windowSize);

        for (int frame = 0; frame < numFrames; frame++) {

            double[] complex = new double[windowSize * 2];
            //1
            // 채우기: [real0, imag0, real1, imag1, ...]
            for (int k = 0; k < stftData[frame].length; k++) {
                complex[2 * k]     = stftData[frame][k][0]; // real
                complex[2 * k + 1] = stftData[frame][k][1]; // imag
            }
            //System.out.println("Hello1");
            // 나머지 대칭 채우기 (Hermitian syammetry)
            for (int k = windowSize / 2 + 1; k < windowSize; k++) {

                int stftBins = stftData[0].length;

                int mirrorIdx = k < windowSize ? windowSize - k : 0;
                if (mirrorIdx >= stftBins) continue;  // ✅ 안전 체크


                complex[2 * k]     = stftData[frame][mirrorIdx][0]; // real
                complex[2 * k + 1] = -stftData[frame][mirrorIdx][1]; // -imag
            }

            // iFFT
            fft.complexInverse(complex, true);

            // 창 곱하고 오버랩-애드
            for (int n = 0; n < windowSize; n++) {
                int idx = frame * hopSize + n;
                outputSignal[idx] += complex[2 * n] * window[n]; // real part
                normalization[idx] += window[n] * window[n];
            }
        }

        // Normalize to avoid amplitude distortion
        for (int i = 0; i < outputSignal.length; i++) {
            if (normalization[i] > 0) {
                outputSignal[i] /= normalization[i];
            }
        }

        return outputSignal;
    }

    // Hann window 생성
    private static double[] hannWindow(int size) {
        double[] window = new double[size];
        for (int i = 0; i < size; i++) {
            window[i] = 0.5 * (1 - Math.cos(2 * Math.PI * i / (size - 1)));
        }
        return window;
    }
}
