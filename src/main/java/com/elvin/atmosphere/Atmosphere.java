package com.elvin.atmosphere;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Atmosphere {

    /**
     *   =====================
     *   |                   |
     *   |                   |
     *   |                   |
     *   |                   |
     *   =====================
     * @throws AWTException 
     *   
     * 
     */
    public void makeAtmo(int percentageV, int percentageH) throws AWTException{
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        double height = screenSize.getHeight() * (percentageV / 100d);
        double width = screenSize.getWidth() * (percentageH / 100d);
        
        Rectangle top = new Rectangle(0, 0, screenSize.width, (int)height);
        Rectangle botom = new Rectangle(0, screenSize.height- (int)height, screenSize.width, (int)height);
        Rectangle left = new Rectangle(0, (int)height, (int)width,  screenSize.height - 2 *(int)height);
        Rectangle right = new Rectangle(screenSize.width - (int)width, (int)height, (int)width,  screenSize.height - 2 *(int)height);
        

        List<Rectangle> topSplit = splitH(top, 6);
        List<Rectangle> botomSplit = splitH(botom, 6);
        List<Rectangle> leftSplit = splitV(left, 4);
        List<Rectangle> rightSplit = splitV(right, 4);

        BorderColor border = new BorderColor();
        ColorPicker colorPicker = new ColorPicker();
        for(Rectangle rec : topSplit){
            border.getTop().add(colorPicker.pickColor(rec));
        }
        for(Rectangle rec : botomSplit){
            border.getBottom().add(colorPicker.pickColor(rec));
        }
        for(Rectangle rec : leftSplit){
            border.getLeft().add(colorPicker.pickColor(rec));
        }
        for(Rectangle rec : rightSplit){
            border.getRight().add(colorPicker.pickColor(rec));
        }
        
        try {
            FileWriter fw = new FileWriter(new File("c.html"));
            fw.write(border.toHtml());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private List<Rectangle> splitV(Rectangle rec, int n){
        int height = (int)(rec.getHeight() / n);

        List<Rectangle> resultList = new ArrayList<Rectangle>();
        for(int i = 0; i < n; i++){

            Rectangle recCut = new Rectangle(rec.x, rec.y + i * height, rec.width, height);
            resultList.add(recCut);
        }
        return resultList;
    }
    
    private List<Rectangle> splitH(Rectangle rec, int n){
        int width = (int)(rec.getWidth() / n);
        
        List<Rectangle> resultList = new ArrayList<Rectangle>();
        for(int i = 0; i < n; i++){
            Rectangle recCut = new Rectangle(rec.x + i * width, rec.y, width, rec.height);
            resultList.add(recCut);
        }
        return resultList;
    }
    
    public static void main(String[] args) throws AWTException {
        new Atmosphere().makeAtmo(9, 6);
    }
    
}
