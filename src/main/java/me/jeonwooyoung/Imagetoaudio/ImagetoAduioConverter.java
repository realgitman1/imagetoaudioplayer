package me.jeonwooyoung.Imagetoaudio;

import me.jeonwooyoung.readimage.ReadImages;
import me.jeonwooyoung.readimage.Pixel;

import java.util.ArrayList;

public class ImagetoAduioConverter {
    // ReadImages readImages; // 이 필드는 필요 없을 수 있습니다.
    Pixel[][] readhandsome;
    double averagebright;
    int height;
    int width;
    double freq;
    double time;

    public ImagetoAduioConverter(ReadImages readImages) {
        this.readhandsome = readImages.imagetopixel();
        this.width = readImages.getWidth();
        this.height = readImages.getHeight();
        this.averagebright = averagebright;

        System.out.println("ImageToAudioConvertor conductor!");
    }
    
    //여기에 공식을 적용해서 red blue green brightness 네가지 변수로 time melody 등 여러가지 변수 배열 생성
    public ArrayList<Double> generateAudioParameters() {

        ArrayList<Double> generateAudioParametersArrayList;
        generateAudioParametersArrayList = new ArrayList<Double>();

        //첫번째 변수 freq
        generateAudioParametersArrayList.add(freq);

        //두번째 변수 time
        generateAudioParametersArrayList.add(time);

        //세번째 변수 음색 (averageBright);
        generateAudioParametersArrayList.add(averagebright);

        System.out.println("here is array!" + generateAudioParametersArrayList);
        return generateAudioParametersArrayList;

    }
}