package me.jeonwooyoung;

import me.jeonwooyoung.Gui.TestFrame;
import me.jeonwooyoung.Imagetoaudio.ImagetoAduioConverter;
import me.jeonwooyoung.readimage.ReadImages;
import me.jeonwooyoung.synth.play.PlaySequence;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.IOException;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {

    PlaySequence playSequence;
    ImagetoAduioConverter imagetoAduioConverter;
    ReadImages readImages;
    BufferedImage bufferedImage;


    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        TestFrame tf = new TestFrame();
//

    }
}