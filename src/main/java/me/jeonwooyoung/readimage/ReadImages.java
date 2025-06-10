package me.jeonwooyoung.readimage;

import java.awt.image.BufferedImage;

public class ReadImages {

    public Pixel pixels;
    public BufferedImage Image;

    public int width;  // 선언만 하고 초기화는 생성자에서
    public int height; // 선언만 하고 초기화는 생성자에서

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // You can remove this private static BufferedImage pic; if it's not used elsewhere.
    // private static BufferedImage pic;

    public ReadImages(BufferedImage Image) {
        this.Image = Image;
        this.width = Image.getWidth();   // 생성자에서 초기화
        this.height = Image.getHeight(); // 생성자에서 초기화
    }

    void printHello() {
        System.out.println("Hello");
    }

    public Pixel[][] imagetopixel() {

        // Consider using a List<List<Pixel>> or similar if the image dimensions are not fixed
        // or if you prefer dynamic resizing.
        Pixel[][] pixels = new Pixel[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = Image.getRGB(x, y);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Calculate brightness
                // A common formula for perceived brightness/luminance:
                // Y = 0.299 * R + 0.587 * G + 0.114 * B
                // The result will be between 0 and 255.
                double brightness = (0.299 * red + 0.587 * green + 0.114 * blue);

                // Cast to int, or store as double if more precision is needed.
                pixels[y][x] = new Pixel(red, green, blue, (int) brightness);
            }
        }
        System.out.println(pixels[0][0].toString());
        return pixels;
    }
}