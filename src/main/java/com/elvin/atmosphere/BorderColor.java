package com.elvin.atmosphere;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class BorderColor {

    private List<Color> top;
    private List<Color> bottom;
    private List<Color> left;
    private List<Color> right;
    
    public List<Color> getTop() {
        if(top == null)
            top = new ArrayList<Color>();
        return top;
    }
    public List<Color> getBottom() {
        if(bottom == null)
            bottom = new ArrayList<Color>();
        return bottom;
    }
    public List<Color> getLeft() {
        if(left == null)
            left = new ArrayList<Color>();
        return left;
    }
    public List<Color> getRight() {
        if(right == null)
            right = new ArrayList<Color>();
        return right;
    }
    
    public String toHtml(){
        StringBuilder sb = new StringBuilder();
        
        int borderSize = 30;
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
            sb.append(getDiv(i * borderSize, top.size() * borderSize, borderSize, getHex(color),"R"+(i-1)));
        }

        return sb.toString();
    }
    
    private String getHex(Color color){
        return Integer.toHexString(color.getRed()) +  Integer.toHexString(color.getGreen()) +  Integer.toHexString(color.getBlue());
    }
    
    private String getDiv(int top, int left, int borderSize, String color, String t) {

        return "<div style=\" position:absolute; top: " + top + "; left: " + left + "; background-color:#" + color + "; width:" + borderSize + "px; height:" + borderSize + "px \" >"+t+"</div>";

    }
}
