package me.jeonwooyoung.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DropListener extends JFrame implements DropTargetListener {
    JTextArea jta_log;

    private Component targetComponent; // 드롭 대상이 되는 컴포넌트
    JLabel lblNewLabel;

    public DropListener(Component component) {
        this.targetComponent = component;
        // DropTarget을 생성하고 컴포넌트와 리스너를 연결
        new DropTarget(targetComponent, this);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

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
        System.out.println("drop it !!!");
        //액션이 copy or move인 경우에 읽어들인다.
        if((dtde.getDropAction() &
                DnDConstants.ACTION_COPY_OR_MOVE)!=0){
            dtde.acceptDrop(dtde.getDropAction());
            Transferable tr = dtde.getTransferable();
            try {
                System.out.println("Dropped !!!");

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


