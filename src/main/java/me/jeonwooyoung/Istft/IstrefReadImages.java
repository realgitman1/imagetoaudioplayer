package me.jeonwooyoung.Istft;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IstrefReadImages {
    private BufferedImage originalImage; // 원본 이미지를 저장할 인스턴스 변수
    static BufferedImage resizedImage;// 리사이즈된 이미지를 저장할 인스턴스 변수
    public static int width;                   // 리사이즈된 이미지의 너비
    public static int height;                  // 리사이즈된 이미지의 높이
    private double[][] magnitude;        // 밝기 값을 저장할 배열

    // 생성자
    public IstrefReadImages(BufferedImage image) {
        this.originalImage = image; // 원본 이미지 저장

        // 이미지 리사이즈 및 타입 변환 (흑백)
        // 리사이즈된 이미지는 인스턴스 변수 resizedImage에 할당
        this.resizedImage = new BufferedImage(512, 512, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = this.resizedImage.createGraphics();
        g.drawImage(this.originalImage, 0, 0, 512, 512, null); // 원본 이미지를 리사이즈하여 그리기
        g.dispose(); // 그래픽 컨텍스트 해제

        // 리사이즈된 이미지의 너비와 높이 설정
        this.width = this.resizedImage.getWidth();
        this.height = this.resizedImage.getHeight();

        // 밝기 값을 저장할 배열을 리사이즈된 이미지 크기에 맞게 초기화
        this.magnitude = new double[this.width][this.height];
    }

    // 이미지의 밝기 값을 계산하여 2D 배열로 반환하는 메서드 (인스턴스 메서드)
    public double[][] getImageBrightness() {
        // 이중 반복문을 사용하여 각 픽셀의 밝기 계산
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // resizedImage에서 픽셀 RGB 값 가져오기
                // y축 반전: BufferedImage의 (0,0)은 좌상단, 일반적으로 그래프는 좌하단이 (0,0)이므로 반전
                int rgb = resizedImage.getRGB(x, y);
                // 흑백 이미지이므로 R, G, B 값이 모두 동일. R값만 사용해도 됨.
                // TYPE_BYTE_GRAY는 0-255의 그레이스케일 값을 가짐.
                int gray = (rgb >> 16) & 0xFF; // Red 채널 값 추출 (흑백 이미지에서는 R=G=B)

                // 0.0 ~ 1.0 범위로 정규화하여 magnitude 배열에 저장
                magnitude[x][y] = gray / 255.0;
            }
        }
        return magnitude; // 계산된 밝기 배열 반환
    }

    // 필요하다면 리사이즈된 이미지를 반환하는 getter 메서드 추가 (옵션)
    public BufferedImage getResizedImage() {
        return resizedImage;
    }

    // 필요하다면 원본 이미지를 반환하는 getter 메서드 추가 (옵션)
    public BufferedImage getOriginalImage() {
        return originalImage;
    }
}
    // 테스트를 위한 main 메서드 (실제 사용 시에는 다른 클래스에서 객체 생성 후 호출)
