package me.jeonwooyoung.Istft;

import static me.jeonwooyoung.Istft.IstrefReadImages.height;
import static me.jeonwooyoung.Istft.IstrefReadImages.width;

public class MakeImaArray {

    private double[][] magnitude;
    private double[][][] stftData = new double[width][height][2];

    private static final int NUM_ITERATIONS = 50;

    public MakeImaArray(double[][] magnitude) {
        this.magnitude = magnitude;
    }

    public double[][][] MakestftData() {

        for (int t = 0; t < width; t++) {
            for (int f = 0; f < height; f++) {
                double mag = magnitude[t][f];
                stftData[t][f][0] = mag;
                stftData[t][f][1] = 0;
            }
        }


        for (int i = 0; i < NUM_ITERATIONS; i++) {

            double[] reconstructedSignal = performISTFT(stftData);


            double[][][] newStft = performSTFT(reconstructedSignal);


            for (int t = 0; t < width; t++) {
                for (int f = 0; f < height; f++) {
                    double real = newStft[t][f][0];
                    double imag = newStft[t][f][1];
                    double newPhase = Math.atan2(imag, real);


                    stftData[t][f][0] = magnitude[t][f] * Math.cos(newPhase);
                    stftData[t][f][1] = magnitude[t][f] * Math.sin(newPhase);
                }
            }
        }

        return stftData;
    }

    private double[] performISTFT(double[][][] stftData) {

        return new double[0];
    }


    private double[][][] performSTFT(double[] signal) {

        return new double[width][height][2];
    }
}