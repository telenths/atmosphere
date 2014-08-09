package com.elvin.atmosphere.common;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    
    public static Dimension getScreenDimension(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return  screenSize;
    }
    
    
    public static List<Rectangle> splitV(Rectangle rec, int n) {
        List<Rectangle> resultList = new ArrayList<Rectangle>();
        if (n <= 1) {
            resultList.add(rec);
            return resultList;
        }
        int height = (int) (rec.getHeight() / n);

        for (int i = 0; i < n; i++) {

            Rectangle recCut = new Rectangle(rec.x, rec.y + i * height, rec.width, height);
            resultList.add(recCut);
        }
        return resultList;
    }

    public static List<Rectangle> splitH(Rectangle rec, int n) {
        List<Rectangle> resultList = new ArrayList<Rectangle>();
        if (n <= 1) {
            resultList.add(rec);
            return resultList;
        }
        int width = (int) (rec.getWidth() / n);

        for (int i = 0; i < n; i++) {
            Rectangle recCut = new Rectangle(rec.x + i * width, rec.y, width, rec.height);
            resultList.add(recCut);
        }
        return resultList;
    }
}
