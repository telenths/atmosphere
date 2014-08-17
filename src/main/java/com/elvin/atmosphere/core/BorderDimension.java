package com.elvin.atmosphere.core;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.elvin.atmosphere.common.Utils;

public class BorderDimension {
    
    private int originalX;
    private int originalY;
    
    private int adjustX;
    private int adjustY;
    
    private int splitH = -1;
    private int splitV = -1;
    
    private Rectangle dimensionRectangle;
    
    public BorderDimension(Rectangle dimensionRectangle){
        this.dimensionRectangle = dimensionRectangle;
        this.originalX = dimensionRectangle.x;
        this.originalY = dimensionRectangle.y;
    }
    
    private void adjust(){
        if(dimensionRectangle == null){
            return;
        }

        dimensionRectangle.setLocation(originalX + adjustX, originalY + adjustY);
    }
    
    public void setAdjustX(int adjustX) {
        this.adjustX = adjustX;
        adjust();
    }

    public void setAdjustY(int adjustY) {
        this.adjustY = adjustY;
        adjust();
    }

    public void setSplitH(int splitH) {
        this.splitH = splitH;
        this.splitV = -1;
    }
    public void setSplitV(int splitV) {
        this.splitV = splitV;
        this.splitH = -1;
    }
    public List<Color> getColors() {

        BufferedImage wholeImage = Utils.captureScreen(dimensionRectangle);
        List<Color> colors = new ArrayList<Color>();
        
        if(splitH == 0 || splitV == 0){
            colors.add(ColorPicker.getAverageColor(wholeImage));
            return colors;
        }
        
        if( splitH >= 0 ){
            int width = dimensionRectangle.width / splitH;
            int height = dimensionRectangle.height;
            
            for (int i = 0 ; i < splitH; i++) {
                if(width * i + width > wholeImage.getWidth())
                    continue;
                BufferedImage splitImg = wholeImage.getSubimage(width * i, 0, width, height); 
                colors.add(ColorPicker.getAverageColor(splitImg));
            }
            return colors;
        }
        
        if( splitV >= 0 ){
            int width = dimensionRectangle.width;
            int height = dimensionRectangle.height / splitV;
            
            for (int i = 0 ; i < splitV; i++) {
                if(height * i + height > wholeImage.getHeight())
                    continue;
                BufferedImage splitImg = wholeImage.getSubimage(0, height * i, width, height); 
                colors.add(ColorPicker.getAverageColor(splitImg));
            }
            return colors;
        }
        
        colors.add(ColorPicker.getAverageColor(wholeImage));
        return colors;
    }

    public Rectangle getDimensionRectangle() {
        return dimensionRectangle;
    }
    
}
