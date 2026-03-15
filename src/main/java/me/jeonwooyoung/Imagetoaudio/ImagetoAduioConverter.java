package me.jeonwooyoung.Imagetoaudio;

import me.jeonwooyoung.readimage.*;

import java.util.ArrayList;

public class ImagetoAduioConverter {
    // ReadImages readImages; // 이 필드는 필요 없을 수 있습니다.
    Pixel[][] readimagetopixel;
    double averagebright;
    int height;
    int width;

    int red;
    int blue;
    int green;
    int averagebrightness;

    double freq;
    double time;
    int notes;
    double degree;

    double getred;
    double getGreen;
    double getBlue;
    double getBrightness;

    public ImagetoAduioConverter(double getred, double getGreen,double getBlue,double getBrightness) {
        this.getred = getred;
        this.getGreen = getGreen;
        this.getBlue = getBlue;
        this.getBrightness = getBrightness;

        this.freq = getBrightness / 0.784313;
        this.time = getBrightness / 80;
        this.notes = ((int) getBrightness / 50) + 2;
        this.degree = (double) (getred + getGreen + getred) / 128;
    }
    

    public ArrayList<Double> generateAudioParameters() {

        ArrayList<Double> generateAudioParametersArrayList;
        generateAudioParametersArrayList = new ArrayList<Double>();


        generateAudioParametersArrayList.add(freq);

        generateAudioParametersArrayList.add(time);

        generateAudioParametersArrayList.add((double) notes);

        generateAudioParametersArrayList.add(degree);

        System.out.println("here is array!" + generateAudioParametersArrayList);
        return generateAudioParametersArrayList;

    }
}