package com.elvin.atmosphere.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.elvin.atmosphere.core.BorderColor;

public class ColorDemoPanel extends JPanel {

    ColorPanel top = new ColorPanelH();
    ColorPanel bottom = new ColorPanelH();

    ColorPanel left = new ColorPanelV();
    ColorPanel right = new ColorPanelV();
    
    JCheckBox onOffCheckBox = new JCheckBox("ShowDemo");
    
    public ColorDemoPanel(){
        this.setLayout(new BorderLayout());

        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        
        this.add(onOffCheckBox, BorderLayout.CENTER);
        
    }
    
    public void setBorderColor(final BorderColor borderColor){
        if(!onOffCheckBox.isSelected()){
            return;
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PaintNewComp(borderColor);
            }
        });
    }
    
    private void PaintNewComp(BorderColor borderColor){
        top.setBorderColor(borderColor.getTop());
        bottom.setBorderColor(borderColor.getBottom());
        left.setBorderColor(borderColor.getLeft());
        right.setBorderColor(borderColor.getRight());
    }
    
}


class ColorPanel extends JPanel {
    
    public void setBorderColor(List<Color> colors){
        if(colors.size() != this.getComponentCount()){
            this.removeAll();
            for(Color c : colors){
                JPanel innerPanel = new JPanel();
                innerPanel.setPreferredSize(new Dimension(50, 50));
                innerPanel.setMinimumSize(new Dimension(50, 50));
                this.add(innerPanel);
            }
        }
        
        Component[] components = this.getComponents();
        for(int i = 0 ; i < colors.size(); i++){
            components[i].setBackground(colors.get(i));
        }
    }
}


class ColorPanelV extends ColorPanel{
    public ColorPanelV(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}

class ColorPanelH extends ColorPanel{
    public ColorPanelH(){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
}

