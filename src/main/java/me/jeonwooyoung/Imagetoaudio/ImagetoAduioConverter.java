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
    
    //여기에 공식을 적용해서 red blue green brightness 네가지 변수로 time melody 등 여러가지 변수 배열 생성
    public ArrayList<Double> generateAudioParameters() {

        ArrayList<Double> generateAudioParametersArrayList;
        generateAudioParametersArrayList = new ArrayList<Double>();

        //첫번째 변수 freq
        generateAudioParametersArrayList.add(freq);

        //두번째 변수 time
        generateAudioParametersArrayList.add(time);

        //세번째 변수 노트수
        generateAudioParametersArrayList.add((double) notes);

        //degree
        generateAudioParametersArrayList.add(degree);

        System.out.println("here is array!" + generateAudioParametersArrayList);
        return generateAudioParametersArrayList;

    }
}