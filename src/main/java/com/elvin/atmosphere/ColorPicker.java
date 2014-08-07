package com.elvin.atmosphere;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ColorPicker {

    private Robot robot;

    public static void main(String[] args) throws AWTException {
        ColorPicker cp = new ColorPicker();
        cp.pickColor(55, 40, 10);
    }
    
    public ColorPicker() throws AWTException {
        robot = new Robot();
    }

    public Color pickColor(int x, int y, int delta) {

        Rectangle rec = new Rectangle(x - delta, y - delta, delta * 2, delta * 2);

        BufferedImage image = robot.createScreenCapture(rec);

        int height = image.getHeight();
        int width = image.getWidth();

        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                int[] rgbArr = getRGBArr(rgb);
                // Filter out grays....
                if (!isGray(rgbArr)) {
                    Integer counter = (Integer) m.get(rgb);
                    if (counter == null)
                        counter = 0;
                    counter++;
                    m.put(rgb, counter);
                }
            }
        }
        
        Color colour = getMostCommonColour(m);

        System.out.println(colour);
        return colour;
    }

    public Color pickColor(int x, int y) {
        return robot.getPixelColor(x, y);
    }

    public static Color getMostCommonColour(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Integer, Integer>) (o1)).getValue()).compareTo(((Map.Entry<Integer, Integer>) (o2)).getValue());
            }
        });
        Map.Entry<Integer, Integer> me = (Map.Entry<Integer, Integer>) list.get(list.size() - 1);
        int[] rgb = getRGBArr((Integer) me.getKey());
        
        
        System.out.println(Integer.toHexString(rgb[0]) + " " + Integer.toHexString(rgb[1]) + " " + Integer.toHexString(rgb[2]));
        return new Color(rgb[0], rgb[1], rgb[2], rgb[3]);
//        return Integer.toHexString(rgb[0]) + " " + Integer.toHexString(rgb[1]) + " " + Integer.toHexString(rgb[2]);
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
