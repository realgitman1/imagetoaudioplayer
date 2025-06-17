package me.jeonwooyoung.Istft;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;

public class Player {

    public static void play(double[] samples, float sampleRate) throws LineUnavailableException {
        byte[] audioBytes = doubleToByteArray(samples);

        AudioFormat format = new AudioFormat(
                sampleRate, 16, 1, true, false
        );

        SourceDataLine line = AudioSystem.getSourceDataLine(format);
        line.open(format);
        line.start();
        line.write(audioBytes,0,audioBytes.length);
        line.drain();
        line.stop();
        line.close();
    }

    private static byte[] doubleToByteArray(double[] samples){
        byte[] bytes = new byte[samples.length*2];
        for (int i=0; i<samples.length; i++){
            int value = (int)Math.max(-32768,Math.min(32768,samples[i]*32768));
            bytes[2*i] = (byte) (value&0xff);
            bytes[2*i+1] = (byte)((value >> 8) & 0xff);
        }

        return bytes;
    }
}
