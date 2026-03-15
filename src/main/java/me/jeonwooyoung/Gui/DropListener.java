package me.jeonwooyoung.Gui;

import me.jeonwooyoung.Istft.ISTFT;
import me.jeonwooyoung.Istft.IstrefReadImages;
import me.jeonwooyoung.Istft.MakeImaArray;
import me.jeonwooyoung.Istft.Player;

import javax.naming.directory.InvalidSearchFilterException;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.image.BufferedImage; // BufferedImage 임포트
import java.io.File;
import java.io.IOException; // IOException 임포트
import java.util.Arrays;
import java.util.List; // List 임포트
import javax.imageio.ImageIO; // ImageIO 임포트

public class DropListener extends JFrame implements DropTargetListener {
    JTextArea jta_log; // 이 필드는 현재 코드에서 사용되지 않지만, 필요에 따라 로그 출력 등에 활용 가능합니다.
    JLabel lblNewLabel; // 이 필드는 드롭된 이미지를 표시하는 데 사용될 수 있습니다.
    private Component targetComponent; // 드롭 대상이 되는 컴포넌트

    private Clip clip;
    String pathName = "C:\\java project\\ImagetoAudioSwing\\src\\main\\resources\\whats up 200 + reverb.wav";


    public DropListener(Component component) {
        this.targetComponent = component;
        new DropTarget(targetComponent, this);


        try {
            clip= AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        File audioFile=new File(pathName);
        AudioInputStream audioStream= null;
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(audioStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

        if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
        } else {
            dtde.rejectDrag();
        }

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent dtde){


        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
            dtde.acceptDrop(dtde.getDropAction());
            Transferable tr = dtde.getTransferable();
            try {

                if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    List<File> files = (List<File>) tr.getTransferData(DataFlavor.javaFileListFlavor);
                    if (!files.isEmpty()) {
                        File droppedFile = files.get(0);

                        clip.stop();

                        if (isImageFile(droppedFile)) {
                            BufferedImage originalImage = ImageIO.read(droppedFile);
                            if (originalImage != null) {

                            } else {
                                System.out.println("Failed to load image from file: " + droppedFile.getAbsolutePath());
                            }

                            IstrefReadImages readIma = new IstrefReadImages(originalImage);
                            double[][] magnitude = readIma.getImageBrightness();
                            MakeImaArray makeImaArray = new MakeImaArray(magnitude);
                            ISTFT istft = new ISTFT(makeImaArray.MakestftData(), 1024, 256);
                            System.out.println("Success!");

                            double[] outputSignal = istft.Makeistft();
                            Player player = new Player();
                            Player.play(outputSignal, 44100f);





                        } else {
                            System.out.println("Dropped file is not an image: " + droppedFile.getAbsolutePath());
                        }
                    }
                } else {
                    System.out.println("Dropped data flavor not supported for files.");
                }

            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } finally {
                dtde.dropComplete(true);
            }
        } else {
            dtde.rejectDrop();
        }

    }

    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                name.endsWith(".png") || name.endsWith(".gif") ||
                name.endsWith(".bmp");
    }
}