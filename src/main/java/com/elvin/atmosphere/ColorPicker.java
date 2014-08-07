package com.elvin.atmosphere;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ColorPicker {

    private Robot robot;

    public static void main(String[] args) throws AWTException, InterruptedException {
        
        Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();
                
        ColorPicker cp = new ColorPicker();
        Thread.currentThread().sleep(2000L);
        cp.pickColor(55, 30, 5);
    }
    
    public ColorPicker() throws AWTException {
        robot = new Robot();
    }

    public Color pickColor(int x, int y, int delta) {

        Rectangle rec = new Rectangle(x - delta, y - delta, delta * 2, delta * 2);

        BufferedImage image = robot.createScreenCapture(rec);
        try {
            ImageIO.write(image, "jpeg", new File("a.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int height = image.getHeight();
        int width = image.getWidth();

        Map<Integer, Integer> mapRgb2Count = new HashMap<Integer, Integer>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                int[] rgbArr = getRGBArr(rgb);
                // Filter out grays....
                if (!isGray(rgbArr)) {
                    Integer counter = (Integer) mapRgb2Count.get(rgb);
                    counter = counter == null ? 1 : ++counter;
                    mapRgb2Count.put(rgb, counter);
                }
            }
        }
        
        Color colour = getMostCommonColour(mapRgb2Count);

        System.out.println(colour);
        return colour;
    }

    public Color pickColor(int x, int y) {
        return robot.getPixelColor(x, y);
    }

    public static Color getMostCommonColour(Map<Integer, Integer> map) {
        
        Map.Entry<Integer, Integer> maxEntry = null;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(maxEntry == null){
                maxEntry = entry;
                continue;
            }
            if(entry.getValue() >= maxEntry.getValue()){
                maxEntry = entry;
            }
        }
        
        int[] rgb = getRGBArr((Integer) maxEntry.getKey());
        
        System.out.println(Integer.toHexString(rgb[0]) +  Integer.toHexString(rgb[1]) +  Integer.toHexString(rgb[2]));
        return new Color(rgb[0], rgb[1], rgb[2], rgb[3]);
    }

    public static int[] getRGBArr(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
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
