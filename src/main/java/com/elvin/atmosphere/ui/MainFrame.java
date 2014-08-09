package com.elvin.atmosphere.ui;

import java.awt.event.ActionEvent;

public class MainFrame extends AbstractMainFrame {

    
    public static void main(String[] args) {
        AbstractMainFrame mf = new MainFrame();
        mf.init();
        mf.setVisible(true);
    }

    @Override
    protected void onOffButtonClicked(ActionEvent e) {
        this.onOffButton.setText( this.onOffButton.isSelected() ? "ON" : "OFF");
        
    }
    
}
