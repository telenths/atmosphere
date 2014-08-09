package com.elvin.atmosphere.core;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.elvin.atmosphere.common.Utils;

public class Atmosphere {


    BorderDimension topDimension ;
    BorderDimension botomDimension;
    BorderDimension leftDimension ;
    BorderDimension rightDimension ;
    
    
    /**
     * =====================
     * |...................|
     * |...................|
     * |...................|
     * |...................|
     * =====================
     * 
     * @throws AWTException
     * 
     */
    public void makeAtmo(int vHeight, int hWidth, int tbCut, int lrCut, int topAdjust, int bottomAdjust, int leftAdjust, int rightAdjust) {
        Dimension screenSize = Utils.getScreenDimension();

        double height = vHeight;
        double width = hWidth;

        Rectangle top = new Rectangle(  0, 0,                                screenSize.width, (int) height);
        Rectangle botom = new Rectangle(0, screenSize.height - (int) height, screenSize.width, (int) height);
        Rectangle left = new Rectangle( 0,                              (int) height, (int) width, screenSize.height - 2 * (int) height);
        Rectangle right = new Rectangle(screenSize.width - (int) width, (int) height, (int) width, screenSize.height - 2 * (int) height);

        topDimension = new BorderDimension(top);
        topDimension.setSplitH(tbCut);
        topDimension.setAdjustY(topAdjust);
        
        botomDimension = new BorderDimension(botom);
        botomDimension.setSplitH(tbCut);
        botomDimension.setAdjustY(-bottomAdjust);
        
        leftDimension = new BorderDimension(left);
        leftDimension.setSplitV(lrCut);
        leftDimension.setAdjustX(leftAdjust);
        
        rightDimension = new BorderDimension(right);
        rightDimension.setSplitV(lrCut);
        rightDimension.setAdjustX(-rightAdjust);
    }
    
    public BorderColor getColors(){

        BorderColor borderColor = new BorderColor();
        borderColor.setTop(topDimension.getColors());
        borderColor.setBottom(botomDimension.getColors());
        borderColor.setLeft(leftDimension.getColors());
        borderColor.setRight(rightDimension.getColors());
        
        return borderColor;
        
    }

    /**
     * 
     * right | top | left | bottom
     * 
     * @param borderColor
     */
    private void getColorCode(BorderColor borderColor) {

    }

    public static void main(String[] args) throws AWTException, InterruptedException {
        Thread.sleep(2000L);
        Atmosphere atm = new Atmosphere();
        atm.makeAtmo(100, 50, 2, 1, 200, 200, 0, 0);
        int times = 1;
        long count = 0;
        for (int i = 0; i < times; i++) {
            long start = System.currentTimeMillis();
            BorderColor borderColor = atm.getColors();
            long spent = System.currentTimeMillis() - start;
            System.out.println("time: " + spent + "ms");
            count += spent;
            
            try {
                FileWriter fw = new FileWriter(new File("c.html"));
                fw.write(borderColor.toHtml());
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("avg: " + ( count / times) + "ms");

    }

}
