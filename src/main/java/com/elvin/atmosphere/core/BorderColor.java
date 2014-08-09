package com.elvin.atmosphere.core;

import java.awt.Color;
import java.util.List;

public class BorderColor {

    private List<Color> top;
    private List<Color> bottom;
    private List<Color> left;
    private List<Color> right;
    
    public List<Color> getTop() {
        return top;
    }

    public void setTop(List<Color> top) {
        this.top = top;
    }

    public List<Color> getBottom() {
        return bottom;
    }

    public void setBottom(List<Color> bottom) {
        this.bottom = bottom;
    }

    public List<Color> getLeft() {
        return left;
    }

    public void setLeft(List<Color> left) {
        this.left = left;
    }

    public List<Color> getRight() {
        return right;
    }

    public void setRight(List<Color> right) {
        this.right = right;
    }

    public String toHtml(){
        StringBuilder sb = new StringBuilder();
        
        int borderSize = 50;
        int i = 0 ; 
        for(Color color : top){
            sb.append(getDiv(0, i * borderSize, borderSize, getHex(color), "T"+i));
            i++;
        }

        i = 0;
        for(Color color : bottom){
            sb.append(getDiv( (left.size() + 1) * borderSize, i * borderSize, borderSize, getHex(color), "B"+i));
            i++;
        }

        i = 0;
        for(Color color : left){
            i++;
            sb.append(getDiv(i * borderSize, 0, borderSize, getHex(color),"L"+(i-1)));
        }

        i = 0;
        for(Color color : right){
            i++;
            sb.append(getDiv(i * borderSize, (top.size() - 1 == 0 ? 1 : (top.size() - 1)) * borderSize, borderSize, getHex(color),"R"+(i-1)));
        }

        return sb.toString();
    }
    
    private String getHex(Color color){
        String red = Integer.toHexString(color.getRed());
        red = red.length() <= 1 ? "0"+red : red;
        
        String green = Integer.toHexString(color.getGreen());
        green = green.length() <= 1 ? "0"+green : green;
        
        String blue = Integer.toHexString(color.getBlue());
        blue = blue.length() <= 1 ? "0"+blue : blue;
        
        return red + green + blue;
    }
    
    private String getDiv(int top, int left, int borderSize, String color, String t) {

        return "<div style=\" position:absolute; top: " + top + "; left: " + left + "; background-color:#" + color + "; width:" + borderSize + "px; height:" + borderSize + "px \" >"+t+"</div>";

    }
}
