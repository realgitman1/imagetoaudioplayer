package me.jeonwooyoung.readimage;

public class Pixel {
    private int red, green, blue;
    private int brightness; // New field for brightness

    public Pixel(int r, int g, int b, int br) { // Modified constructor
        red = r;
        green = g;
        blue = b;
        brightness = br; // Initialize brightness
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getBrightness() { // New getter for brightness
        return brightness;
    }

    @Override // Good practice to use @Override for toString()
    public String toString() {
        return "[" + this.getRed() + "," + this.getGreen() + "," + this.getBlue() + " | Brightness: " + this.getBrightness() + "]";
    }
}
