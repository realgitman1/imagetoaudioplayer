package me.jeonwooyoung.Istft;


import static me.jeonwooyoung.Istft.IstrefReadImages.height;
import static me.jeonwooyoung.Istft.IstrefReadImages.width;

public class MakeImaArray {

    double[][] magnitude;
    double[][][] stftData = new double[width][height][2];

    public MakeImaArray(double[][] magnitude) {
        this.magnitude = magnitude;

    }

    public double[][][] MakestftData() {
        for (int t = 0; t < width; t++) {
            for (int f = 0; f < height; f++) {
                double mag = magnitude[t][f];

                // 위상 무작위 or 0으로 설정
                double phase = 0; // 무작위 위상

                stftData[t][f][0] = mag * Math.cos(phase); // real
                stftData[t][f][1] = mag * Math.sin(phase); // imag
            }
        }
        return stftData;
    }

}

//witdh를 frame으로 height를 프레임내에서의 인덱스
