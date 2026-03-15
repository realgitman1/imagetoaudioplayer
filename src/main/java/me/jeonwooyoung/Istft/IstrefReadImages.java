package me.jeonwooyoung.Istft;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IstrefReadImages {
    private BufferedImage originalImage; // 원본 이미지를 저장할 인스턴스 변수
    static BufferedImage resizedImage;// 리사이즈된 이미지를 저장할 인스턴스 변수
    public static int width;                   // 리사이즈된 이미지의 너비
    public static int height;                  // 리사이즈된 이미지의 높이
    private double[][] magnitude;        // 밝기 값을 저장할 배열


    public IstrefReadImages(BufferedImage image) {
        this.originalImage = image;



        this.resizedImage = new BufferedImage(512, 512, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = this.resizedImage.createGraphics();
        g.drawImage(this.originalImage, 0, 0, 512, 512, null);
        g.dispose();

        this.width = this.resizedImage.getWidth();
        this.height = this.resizedImage.getHeight();


        this.magnitude = new double[this.width][this.height];
    }


    public double[][] getImageBrightness() {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int rgb = resizedImage.getRGB(x, y);

                int gray = (rgb >> 16) & 0xFF;

                magnitude[x][y] = gray / 255.0;
            }
        }
        return magnitude; // 계산된 밝기 배열 반환
    }


    public BufferedImage getResizedImage() {
        return resizedImage;
    }


    public BufferedImage getOriginalImage() {
        return originalImage;
    }
}
