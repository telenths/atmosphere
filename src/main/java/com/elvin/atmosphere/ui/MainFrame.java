package com.elvin.atmosphere.ui;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.elvin.atmosphere.clients.PiColorUtil;
import com.elvin.atmosphere.clients.RaspberryClient;
import com.elvin.atmosphere.common.Utils;
import com.elvin.atmosphere.core.BorderColor;

public class MainFrame extends AbstractMainFrame {

    private WorkingThread workingThread;
    private String propertyFile = "atmosphere.prop";
    private RaspberryClient raspClient;
    
    public static void main(String[] args) {
        AbstractMainFrame mf = new MainFrame();
        mf.init();
        
        int x = (Utils.getScreenDimension().width - mf.getWidth()) / 2;
        int y = (Utils.getScreenDimension().height - mf.getHeight()) / 2 ;
        mf.setLocation(x, y);
        
        mf.setVisible(true);
    }

    @Override
    protected void onOffButtonClicked(ActionEvent e) {
        this.onOffButton.setText( this.onOffButton.isSelected() ? "ON" : "OFF");
        if(!onOffButton.isSelected() && workingThread != null){
            workingThread.stop();
            try {
                raspClient.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return;
        }

        raspClient = new RaspberryClient();
        try {
            raspClient.connect();
        } catch (Exception e1) {
            e1.printStackTrace();
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
        workingThread.addBorderColorRetrievedListener(new BorderColorRetrievedListener() {
            public void BorderColorRetrieved(BorderColor borderColor) {
                //send to raspberry pi

                String piColorString = PiColorUtil.getPiColorString(borderColor);
                System.out.println(piColorString);
                System.out.println(piColorString == null ? "" : piColorString.length());
                raspClient.sendToRpi(piColorString);
                
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
        RedBoxes.getInstance().showWith(workingThread);
    }

    @Override
    protected void redBoxButtonReleased(MouseEvent e) {
        RedBoxes.getInstance().hideAll();
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

    @Override
    protected void saveSettings() {
        
        Properties atomProperties = new Properties();
        atomProperties.put(tbHeight.getName(), Integer.toString(tbHeight.getValue()));
        atomProperties.put(lrWidth.getName(), Integer.toString(lrWidth.getValue()));
        atomProperties.put(tbSplit.getName(), Integer.toString(tbSplit.getValue()));
        atomProperties.put(lrSplit.getName(), Integer.toString(lrSplit.getValue()));
        atomProperties.put(tAdjust.getName(), Integer.toString(tAdjust.getValue()));
        atomProperties.put(bAdjust.getName(), Integer.toString(bAdjust.getValue()));
        atomProperties.put(lAdjust.getName(), Integer.toString(lAdjust.getValue()));
        atomProperties.put(rAdjust.getName(), Integer.toString(rAdjust.getValue()));
        atomProperties.put(interval.getName(), Integer.toString(interval.getValue()));
        atomProperties.put(targetIp.getName(), targetIp.getText());
        atomProperties.put(targetPort.getName(), targetPort.getText());
        
        try {
            atomProperties.store(new FileWriter(new File(propertyFile)), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    protected void loadSettings() {
        Properties atomProperties = new Properties();
        try {
            atomProperties.load(new FileReader(new File(propertyFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        tbHeight.setValue(getIntProperty(atomProperties, tbHeight.getName()));
        lrWidth.setValue(getIntProperty(atomProperties, lrWidth.getName()));
        tbSplit.setValue(getIntProperty(atomProperties, tbSplit.getName()));
        lrSplit.setValue(getIntProperty(atomProperties, lrSplit.getName()));
        tAdjust.setValue(getIntProperty(atomProperties, tAdjust.getName()));
        bAdjust.setValue(getIntProperty(atomProperties, bAdjust.getName()));
        lAdjust.setValue(getIntProperty(atomProperties, lAdjust.getName()));
        rAdjust.setValue(getIntProperty(atomProperties, rAdjust.getName()));
        interval.setValue(getIntProperty(atomProperties, interval.getName()));

        targetIp.setText(getStringProperty(atomProperties, targetIp.getName()));
        targetPort.setText(getStringProperty(atomProperties, targetPort.getName()));
        
    }
    
    private int getIntProperty(Properties prop, String key){
        Object obj = prop.get(key);
        if(obj == null)
            return -1;
        try {
            return Integer.parseInt((String)obj);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private String getStringProperty(Properties prop, String key){
        Object obj = prop.get(key);
        if(obj == null)
            return null;
        return (String)obj;
    }
    
}


