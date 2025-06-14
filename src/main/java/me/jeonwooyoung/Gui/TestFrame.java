package me.jeonwooyoung.Gui;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;


public class TestFrame extends JFrame {


    public TestFrame() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        setTitle("Sandwitch Player"); // 프레임 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // 프레임 크기 설정
        Container contentPane = getContentPane(); //프레임에서 컨텐트팬 받아오기

        JLabel lblNewLabel = new JLabel("Sandwitch Player!");
        lblNewLabel.setBounds(0, 0, 124, 15); // 레이블 위치 설정
        contentPane.add(lblNewLabel); // 콘텐트팬에 레이블 붙이기

        JLabel backgroundimagesandwitch = new JLabel(" ", JLabel.CENTER); //가운데로 수평정렬
        ImageIcon icon = new ImageIcon("C:\\java project\\ImagetoAudioSwing\\src\\main\\resources\\peanut.jpg");
        backgroundimagesandwitch.setIcon(icon);
        contentPane.add(backgroundimagesandwitch);

        new DropListener(backgroundimagesandwitch);



        setVisible(true); //화면에 프레임 출력
    }
}
