package com.elvin.atmosphere.core;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorPicker {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(2000L);
    }
    
    public static Color getAverageColor(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        
        int r=0, g=0, b=0, a=0;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int[] rgbArr = getRGBArr(image.getRGB(i, j));
                r += rgbArr[0];
                g += rgbArr[1];
                b += rgbArr[2];
                a += rgbArr[3];
            }
        }
        
        int totalPix = height * width;
        r = r / totalPix;
        g = g / totalPix;
        b = b / totalPix;
        a = a / totalPix;
        
        return new Color(r, g, b, a) ;
    }
    
    public static int[] getRGBArr(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        int alpha = (pixel >> 24) & 0xff;
        return new int[] { red, green, blue, alpha };
    }

    public static boolean isGray(int[] rgbArr) {
        
        int rgDiff = rgbArr[0] - rgbArr[1];
        int rbDiff = rgbArr[0] - rgbArr[2];
        // Filter out black, white and grays...... (tolerance within 10 pixels)
        int tolerance = 10;
        if (rgDiff > tolerance || rgDiff < -tolerance)
            if (rbDiff > tolerance || rbDiff < -tolerance) {
                return false;
            }
        return true;
    }

}
