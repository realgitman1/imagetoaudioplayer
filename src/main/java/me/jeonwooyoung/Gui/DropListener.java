package me.jeonwooyoung.Gui;

import me.jeonwooyoung.Imagetoaudio.ImagetoAduioConverter;
import me.jeonwooyoung.readimage.*;
import me.jeonwooyoung.synth.play.PlaySequence;
import me.jeonwooyoung.synth.play.Playexample;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.image.BufferedImage; // BufferedImage 임포트
import java.io.File;
import java.io.IOException; // IOException 임포트
import java.util.List; // List 임포트
import javax.imageio.ImageIO; // ImageIO 임포트

public class DropListener extends JFrame implements DropTargetListener {
    JTextArea jta_log; // 이 필드는 현재 코드에서 사용되지 않지만, 필요에 따라 로그 출력 등에 활용 가능합니다.
    JLabel lblNewLabel; // 이 필드는 드롭된 이미지를 표시하는 데 사용될 수 있습니다.
    private Component targetComponent; // 드롭 대상이 되는 컴포넌트

    public DropListener(Component component) {
        this.targetComponent = component;
        new DropTarget(targetComponent, this);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        // 드래그 진입 시 커서 변경 등의 시각적 피드백을 제공할 수 있습니다.
        if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
        } else {
            dtde.rejectDrag();
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        // 드래그 오버 시 특별한 동작이 필요 없다면 비워둘 수 있습니다.
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        // 드롭 액션 변경 시 (예: Ctrl 키를 눌러 복사 -> 이동)
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        // 드래그 영역 이탈 시
    }

    @Override
    public void drop(DropTargetDropEvent dtde){
        System.out.println("drop it !!!");
        // 액션이 copy or move인 경우에 읽어들인다.
        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
            dtde.acceptDrop(dtde.getDropAction());
            Transferable tr = dtde.getTransferable();
            try {
                // 드롭된 데이터가 파일 리스트인지 확인
                if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    List<File> files = (List<File>) tr.getTransferData(DataFlavor.javaFileListFlavor);
                    if (!files.isEmpty()) {
                        File droppedFile = files.get(0); // 첫 번째 파일만 처리
                        System.out.println("Dropped file: " + droppedFile.getAbsolutePath());

                        // 파일이 이미지인지 확인하고 BufferedImage로 로드
                        if (isImageFile(droppedFile)) {
                            BufferedImage originalImage = ImageIO.read(droppedFile);
                            if (originalImage != null) {
                                System.out.println("Image successfully loaded: " + originalImage.getWidth() + "x" + originalImage.getHeight());
                            } else {
                                System.out.println("Failed to load image from file: " + droppedFile.getAbsolutePath());
                            }

                            ReadImages readImages = new ReadImages(originalImage);

                            GetRed getRed = new GetRed(readImages);
                            double redaver = getRed.GetAverageLevel();

                            GetGreen getGreen = new GetGreen((readImages));
                            double greenaver = getGreen.GetAverageLevel();

                            GetBlue getBlue = new GetBlue((readImages));
                            double blueaver = getGreen.GetAverageLevel();

                            GetBrightness getBrightness = new GetBrightness((readImages));
                            double brightnessaver = getBrightness.GetAverageLevel();

                            ImagetoAduioConverter imagetoAduioConverter = new ImagetoAduioConverter(redaver, greenaver, blueaver, brightnessaver);

                            PlaySequence playSequence = new PlaySequence(imagetoAduioConverter.generateAudioParameters());
                            //Playexample playexample = new Playexample();
                            //playexample.test();
                            playSequence.playaudio();

                        } else {
                            System.out.println("Dropped file is not an image: " + droppedFile.getAbsolutePath());
                        }
                    }
                } else {
                    System.out.println("Dropped data flavor not supported for files.");
                }

            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            } finally {
                dtde.dropComplete(true); // 드롭 완료
            }
        } else {
            dtde.rejectDrop();
        }

    }

    // 파일 확장자를 통해 이미지 파일인지 간단히 확인하는 헬퍼 메서드
    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                name.endsWith(".png") || name.endsWith(".gif") ||
                name.endsWith(".bmp");
    }
}