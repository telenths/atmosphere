package com.elvin.atmosphere.core;

import java.awt.Color;
import java.awt.Rectangle;
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
    
    private Rectangle originRectangle;
    private List<Rectangle> splitedRectangles;
    
    public BorderDimension(Rectangle originRectangle){
        this.originRectangle = originRectangle;
        this.originalX = originRectangle.x;
        this.originalY = originRectangle.y;
    }
    
    private void split(){
        if(originRectangle == null){
            return;
        }

        if(splitH >= 0 ){
            splitedRectangles = Utils.splitH(originRectangle, splitH);
        }
        
        if(splitV >= 0){
            splitedRectangles = Utils.splitV(originRectangle, splitV);
        }
    }
    
    private void adjust(){
        if(originRectangle == null){
            return;
        }

        originRectangle.setLocation(originalX + adjustX, originalY + adjustY);
        split();
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
        split();
    }
    public void setSplitV(int splitV) {
        this.splitV = splitV;
        this.splitH = -1;
        split();
    }
    public List<Color> getColors() {
        List<Color> colors = new ArrayList<Color>();
        for (Rectangle rec : splitedRectangles) {
            colors.add(ColorPicker.pickColor(rec));
        }
        return colors;
    }

    public Rectangle getOriginRectangle() {
        return originRectangle;
    }
    
}
