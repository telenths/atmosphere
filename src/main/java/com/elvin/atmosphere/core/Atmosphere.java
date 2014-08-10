package com.elvin.atmosphere.core;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.elvin.atmosphere.common.Utils;

public class Atmosphere {

    private BorderDimension topDimension ;
    private BorderDimension bottomDimension;
    private BorderDimension leftDimension ;
    private BorderDimension rightDimension ;
    
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
    public void init(int vHeight, int hWidth, int tbSplit, int lrSplit, int topAdjust, int bottomAdjust, int leftAdjust, int rightAdjust) {
        Dimension screenSize = Utils.getScreenDimension();

        double height = vHeight;
        double width = hWidth;

        Rectangle top = new Rectangle(  0, 0,                                screenSize.width, (int) height);
        Rectangle botom = new Rectangle(0, screenSize.height - (int) height, screenSize.width, (int) height);
        Rectangle left = new Rectangle( 0,                              (int) height, (int) width, screenSize.height - 2 * (int) height);
        Rectangle right = new Rectangle(screenSize.width - (int) width, (int) height, (int) width, screenSize.height - 2 * (int) height);

        topDimension = new BorderDimension(top);
        topDimension.setSplitH(tbSplit);
        topDimension.setAdjustY(topAdjust);
        
        bottomDimension = new BorderDimension(botom);
        bottomDimension.setSplitH(tbSplit);
        bottomDimension.setAdjustY(-bottomAdjust);
        
        leftDimension = new BorderDimension(left);
        leftDimension.setSplitV(lrSplit);
        leftDimension.setAdjustX(leftAdjust);
        
        rightDimension = new BorderDimension(right);
        rightDimension.setSplitV(lrSplit);
        rightDimension.setAdjustX(-rightAdjust);
    }
    
    public BorderColor getColors(){

        BorderColor borderColor = new BorderColor();
        borderColor.setTop(topDimension.getColors());
        borderColor.setBottom(bottomDimension.getColors());
        borderColor.setLeft(leftDimension.getColors());
        borderColor.setRight(rightDimension.getColors());
        
        return borderColor;
    }

    public void setTbSplit(int split){
        topDimension.setSplitH(split);
        bottomDimension.setSplitH(split);
    }
    public void setLrSplit(int split){
        leftDimension.setSplitV(split);
        rightDimension.setSplitV(split);
    }
    
    public void setTopAdjust(int adjust){
        topDimension.setAdjustY(adjust);
    }
    public void setBottomAdjust(int adjust){
        bottomDimension.setAdjustY(-adjust);
    }
    public void setLeftAdjust(int adjust){
        leftDimension.setAdjustX(adjust);
        
    }
    public void setRightAdjust(int adjust){
        rightDimension.setAdjustX(-adjust);
    }
    

    public BorderDimension getTopDimension() {
        return topDimension;
    }

    public BorderDimension getBottomDimension() {
        return bottomDimension;
    }

    public BorderDimension getLeftDimension() {
        return leftDimension;
    }

    public BorderDimension getRightDimension() {
        return rightDimension;
    }

    public static void main(String[] args) throws AWTException, InterruptedException {
        Thread.sleep(2000L);
        Atmosphere atm = new Atmosphere();
        atm.init(100, 50, 2, 1, 200, 200, 0, 0);
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
