package me.jeonwooyoung.Gui;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;


public class TestFrame extends JFrame {


    public TestFrame() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        setTitle("Sandwitch Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        Container contentPane = getContentPane();

        JLabel lblNewLabel = new JLabel("Sandwitch Player!");
        lblNewLabel.setBounds(0, 0, 124, 15);
        contentPane.add(lblNewLabel);

        JLabel backgroundimagesandwitch = new JLabel(" ", JLabel.CENTER);
        ImageIcon icon = new ImageIcon("C:\\java project\\ImagetoAudioSwing\\src\\main\\resources\\peanut.jpg");
        backgroundimagesandwitch.setIcon(icon);
        contentPane.add(backgroundimagesandwitch);

        new DropListener(backgroundimagesandwitch);



        setVisible(true);
    }
}
