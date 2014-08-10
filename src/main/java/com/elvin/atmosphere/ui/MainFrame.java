package com.elvin.atmosphere.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.Border;

import com.elvin.atmosphere.core.BorderColor;

public class MainFrame extends AbstractMainFrame {

    private WorkingThread workingThread;
    
    private RedBoxFrame top ;
    private RedBoxFrame bottom;
    private RedBoxFrame left;
    private RedBoxFrame right;
    
    public static void main(String[] args) {
        AbstractMainFrame mf = new MainFrame();
        mf.init();
        mf.setVisible(true);
    }

    @Override
    protected void onOffButtonClicked(ActionEvent e) {
        this.onOffButton.setText( this.onOffButton.isSelected() ? "ON" : "OFF");
        if(!onOffButton.isSelected() && workingThread != null){
            workingThread.stop();
            return;
        }

        int vHeight = tbHeight.getValue();
        int hWidth = lrWidth.getValue();
        int tbCut = tbSplit.getValue();
        int lrCut = lrSplit.getValue();
        int topAdjust = tAdjust.getValue();
        int bottomAdjust = bAdjust.getValue();
        int leftAdjust = lAdjust.getValue();
        int rightAdjust = rAdjust.getValue();
        
        workingThread = new WorkingThread(vHeight, hWidth, tbCut, lrCut, topAdjust, bottomAdjust, leftAdjust, rightAdjust);
        
        workingThread.addBorderColorRetrievedListener(new BorderColorRetrievedListener() {
            
            public void BorderColorRetrieved(BorderColor borderColor) {
                colorDemoPanel.setBorderColor(borderColor);
            }
        });
        workingThread.setInterval(interval.getValue());
        new Thread(workingThread).start();
    }

    @Override
    protected void redBoxButtonPressed(MouseEvent e) {
        if(workingThread == null){
            return;
        }
        if(top == null){
            top = new RedBoxFrame();
        }
        if(bottom == null){
            bottom = new RedBoxFrame();
        }
        if(left == null){
            left = new RedBoxFrame();
        }
        if(right == null){
            right = new RedBoxFrame();
        }
        
        top.setLocation(workingThread.getTopDimension().getOriginRectangle().x, workingThread.getTopDimension().getOriginRectangle().y);
        top.setSize(workingThread.getTopDimension().getOriginRectangle().width, workingThread.getTopDimension().getOriginRectangle().height);

        bottom.setLocation(workingThread.getBottomDimension().getOriginRectangle().x, workingThread.getBottomDimension().getOriginRectangle().y);
        bottom.setSize(workingThread.getBottomDimension().getOriginRectangle().width, workingThread.getBottomDimension().getOriginRectangle().height);
        
        left.setLocation(workingThread.getLeftDimension().getOriginRectangle().x, workingThread.getLeftDimension().getOriginRectangle().y);
        left.setSize(workingThread.getLeftDimension().getOriginRectangle().width, workingThread.getLeftDimension().getOriginRectangle().height);
        
        right.setLocation(workingThread.getRightDimension().getOriginRectangle().x, workingThread.getRightDimension().getOriginRectangle().y);
        right.setSize(workingThread.getRightDimension().getOriginRectangle().width, workingThread.getRightDimension().getOriginRectangle().height);
        
        top.setVisible(true);
        bottom.setVisible(true);
        left.setVisible(true);
        right.setVisible(true);
    
    }

    @Override
    protected void redBoxButtonReleased(MouseEvent e) {
        if(top != null)
            top.setVisible(false);
        if(bottom != null)
            bottom.setVisible(false);
        if(left != null)
            left.setVisible(false);
        if(right != null)
            right.setVisible(false);
    }

    @Override
    protected void intervalSliderValueChanged(int value) {
        if(workingThread != null)
            workingThread.setInterval(value);
    }

    @Override
    protected void rAdjustSliderValueChanged(int value) {
        if(workingThread != null)
            workingThread.setRightAdjust(value);
    }

    @Override
    protected void lAdjustSliderValueChanged(int value) {
        if(workingThread != null)
            workingThread.setLeftAdjust(value);
    }

    @Override
    protected void bAdjustSliderValueChanged(int value) {
        if(workingThread != null)
            workingThread.setBottomAdjust(value);
    }

    @Override
    protected void tAdjustSliderValueChanged(int value) {
        if(workingThread != null)
            workingThread.setTopAdjust(value);
        
    }

    @Override
    protected void lrSplitSliderValueChanged(int value) {
        if(workingThread != null)
            workingThread.setLrSplit(value);
    }

    @Override
    protected void tbSplitSliderValueChanged(int value) {
        if(workingThread != null)
            workingThread.setTbSplit(value);
        
    }

    @Override
    protected void lrWidthSliderValueChanged(int value) {
        if(workingThread != null){
            int width = lrWidth.getValue();
            int height = tbHeight.getValue();
            workingThread.refreshDimensions(height, width);
        }
    }

    @Override
    protected void tbHeightSliderValueChanged(int value) {
        if(workingThread != null){
            int width = lrWidth.getValue();
            int height = tbHeight.getValue();
            workingThread.refreshDimensions(height, width);
        }
    }
    
}


class RedBoxFrame extends JWindow {
    
    public RedBoxFrame(){
        this.setOpacity(0.4F);
        
        Border border = BorderFactory.createLineBorder(Color.RED);
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setOpaque(false);
        panel.setBorder(border);
        this.getContentPane().add(panel, BorderLayout.CENTER);
    }
    
    
}
