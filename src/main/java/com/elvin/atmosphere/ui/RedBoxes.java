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
        
        top.setLocation(atmosphere.getTopDimension().getOriginRectangle().x, atmosphere.getTopDimension().getOriginRectangle().y);
        top.setSize(atmosphere.getTopDimension().getOriginRectangle().width, atmosphere.getTopDimension().getOriginRectangle().height);

        bottom.setLocation(atmosphere.getBottomDimension().getOriginRectangle().x, atmosphere.getBottomDimension().getOriginRectangle().y);
        bottom.setSize(atmosphere.getBottomDimension().getOriginRectangle().width, atmosphere.getBottomDimension().getOriginRectangle().height);
        
        left.setLocation(atmosphere.getLeftDimension().getOriginRectangle().x, atmosphere.getLeftDimension().getOriginRectangle().y);
        left.setSize(atmosphere.getLeftDimension().getOriginRectangle().width, atmosphere.getLeftDimension().getOriginRectangle().height);
        
        right.setLocation(atmosphere.getRightDimension().getOriginRectangle().x, atmosphere.getRightDimension().getOriginRectangle().y);
        right.setSize(atmosphere.getRightDimension().getOriginRectangle().width, atmosphere.getRightDimension().getOriginRectangle().height);
        
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