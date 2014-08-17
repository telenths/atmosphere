package com.elvin.atmosphere.common;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static Robot robot;
    
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    public static Dimension getScreenDimension(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return  screenSize;
    }
    
    public static BufferedImage captureScreen(Rectangle rec) {

        long start = System.currentTimeMillis();
        BufferedImage image = robot.createScreenCapture(rec);
        Statistic.calcAvg("CaptureScreen_" + rec.width + "_" + rec.height, System.currentTimeMillis() - start);
        
        return image;
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
