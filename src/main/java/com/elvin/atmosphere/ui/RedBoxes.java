package com.elvin.atmosphere.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.Border;

import com.elvin.atmosphere.core.Atmosphere;

public class RedBoxes {

    private RedBoxFrame top ;
    private RedBoxFrame bottom;
    private RedBoxFrame left;
    private RedBoxFrame right;
    
    private static RedBoxes instance = new RedBoxes();
    public static RedBoxes getInstance(){
        return instance;
    }
    
    public void showWith(Atmosphere atmosphere){
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
        
        top.setLocation(atmosphere.getTopDimension().getDimensionRectangle().x, atmosphere.getTopDimension().getDimensionRectangle().y);
        top.setSize(atmosphere.getTopDimension().getDimensionRectangle().width, atmosphere.getTopDimension().getDimensionRectangle().height);

        bottom.setLocation(atmosphere.getBottomDimension().getDimensionRectangle().x, atmosphere.getBottomDimension().getDimensionRectangle().y);
        bottom.setSize(atmosphere.getBottomDimension().getDimensionRectangle().width, atmosphere.getBottomDimension().getDimensionRectangle().height);
        
        left.setLocation(atmosphere.getLeftDimension().getDimensionRectangle().x, atmosphere.getLeftDimension().getDimensionRectangle().y);
        left.setSize(atmosphere.getLeftDimension().getDimensionRectangle().width, atmosphere.getLeftDimension().getDimensionRectangle().height);
        
        right.setLocation(atmosphere.getRightDimension().getDimensionRectangle().x, atmosphere.getRightDimension().getDimensionRectangle().y);
        right.setSize(atmosphere.getRightDimension().getDimensionRectangle().width, atmosphere.getRightDimension().getDimensionRectangle().height);
        
        top.setVisible(true);
        bottom.setVisible(true);
        left.setVisible(true);
        right.setVisible(true);
    }
    
    public void hideAll(){
        if(top != null)
            top.setVisible(false);
        if(bottom != null)
            bottom.setVisible(false);
        if(left != null)
            left.setVisible(false);
        if(right != null)
            right.setVisible(false);
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