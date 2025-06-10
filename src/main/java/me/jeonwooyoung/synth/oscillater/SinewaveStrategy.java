package me.jeonwooyoung.synth.oscillater;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;

public class SinewaveStrategy implements Osc1 {

    private final double frequency;
    private Synthesizer synth; // 멤버 변수로 선언하고 private으로 캡슐화
    private SineOscillator sineOsc; // 사인파 오실레이터
    private LineOut lineOut; // 오디오 출력

    public SinewaveStrategy(double frequency) {
        // 1. JSyn Synthesizer 생성
        // 클래스 멤버 변수 synth를 초기화합니다.
        synth = JSyn.createSynthesizer();
        int numInputChannels = 0; // 이 예제에서는 입력 채널이 필요 없습니다.
        int numOutputChannels = 2;
        this.frequency = frequency;

        // 2. UnitGenerator 생성
        sineOsc = new SineOscillator();
        lineOut = new LineOut();

        // 3. 유닛 제너레이터를 신디사이저에 추가
        synth.add(sineOsc);
        synth.add(lineOut);

        // 4. 오실레이터의 출력을 라인 출력에 연결
        // 오실레이터의 출력을 라인 출력의 왼쪽(0)과 오른쪽(1) 입력에 연결
        sineOsc.output.connect(0, lineOut.input, 0);
        sineOsc.output.connect(0, lineOut.input, 1); // 스테레오로 들리게 양쪽 채널에 연결
    }


    @Override
    public void startAudio() {
        // 5. JSyn 시스템 시작
        // 오디오 장치와의 통신을 시작합니다.
        synth.start();

        // 6. 오실레이터의 주파수와 진폭 설정 (예시)
        sineOsc.frequency.set(((frequency * 78.4156) + 40)/4); //
        sineOsc.amplitude.set(0.8);   //

        // 7. 라인 출력 활성화
        lineOut.start();
        System.out.println("오디오 재생 시작. 5초 후 종료됩니다.");

    }

    @Override
    public void stopAudio() {
        // 8. JSyn 시스템 정지 (사용 후)
        lineOut.stop();
        synth.stop();
        System.out.println("오디오 재생 종료.");
    }

}
