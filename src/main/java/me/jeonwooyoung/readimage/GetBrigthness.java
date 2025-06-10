import me.jeonwooyoung.readimage.ReadImages;/*package me.jeonwooyoung.readimage;

public class GetBrigthness {

    ReadImages readImages;
    Pixel[][] pixels;
    public int height;
    public int width;
    int sum = 0; // sum은 평균을 계산하기 전에 초기화되어야 합니다.

    public GetBrigthness(ReadImages readImages) {
        this.readImages = readImages;
        this.pixels = readImages.readhandsomeimage();
        this.width = readImages.getWidth();
        this.height = readImages.getHeight();
    }

    public double getAverage() {
        int currentSum = 0; // 지역 변수를 사용하여 누적

        if (width == 0 || height == 0) {
            System.out.println("이미지 크기가 0이므로 평균 밝기를 계산할 수 없습니다.");
            return 0.0;
        }

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++){
                if (pixels[j][i] != null) {
                    currentSum += pixels[j][i].getBrightness(); // getBrightness() 호출 (Pixel 클래스에 이 메서드가 있어야 함)
                } else {
                    System.err.println("경고: 픽셀 (" + i + ", " + j + ")이 null입니다. 계산에서 제외됩니다.");
                }
            }
        }

        double average = (double) currentSum / (width * height); // 정수 나눗셈 방지를 위해 형변환
        System.out.println("get Average!!! ->>>" + average);
        return average;
    }
}*/