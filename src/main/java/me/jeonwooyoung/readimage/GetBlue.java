package me.jeonwooyoung.readimage;

public class GetBlue implements GetAverage {

    Pixel[][] pixels;
    int width;
    int height;
    int currentSum;

    public GetBlue(ReadImages readImages){
        this.pixels = readImages.imagetopixel();
        this.width = readImages.getWidth();
        this.height = readImages.getHeight();
    }

    @Override
    public double GetAverageLevel() {

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++){
                if (pixels[j][i] != null) {
                    currentSum += pixels[j][i].getBlue(); // getBrightness() 호출 (Pixel 클래스에 이 메서드가 있어야 함)
                } else {
                    System.err.println("경고: 픽셀 (" + i + ", " + j + ")이 null입니다. 계산에서 제외됩니다.");
                }
            }
        }
        currentSum /= (height*width);
        return currentSum;
    }


}
