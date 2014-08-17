package com.elvin.atmosphere.clients;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.elvin.atmosphere.core.BorderColor;

public class PiColorUtil {

    private static List<String> priviousColorList = new ArrayList<String>();
    
    public static String getPiColorString(BorderColor borderColor){
        List<String> colorList = calcDiff(borderColor);
        if(colorList.size() <= 0)
            return null;
        
        StringBuffer sBuf = new StringBuffer();
        sBuf.append("LED ");
        for(String str : colorList){
            sBuf.append(str).append(",");
        }
        return sBuf.toString();
    }
    
    private static List<String> calcDiff(BorderColor borderColor){

        List<String> colorList = new ArrayList<String>();
        colorList.addAll(getPiColorList(borderColor.getRight(), 0, true));
        colorList.addAll(getPiColorList(borderColor.getTop(), 18, true));
        colorList.addAll(getPiColorList(borderColor.getLeft(), 18 + 32, false));
        
        if(priviousColorList == null){
            priviousColorList = colorList;
            return colorList;
        }
        List<String> tempList = new ArrayList<String>(colorList);
        colorList.removeAll(priviousColorList);
        priviousColorList = tempList;
        return colorList;
    }
    
    private static List<String> getPiColorList(List<Color> colorList, int indexFrom, boolean reverse){
        if(reverse)
            Collections.reverse(colorList);

        List<String> colorString = new ArrayList<String>();
        for(int i = 0 ; i < colorList.size() ; i++){
            Color color = colorList.get(i);
            colorString.add(color.getRed() + " " + color.getGreen() + " " + color.getBlue() + " " + (i + indexFrom));
        }
        return colorString;
    }
    
}
