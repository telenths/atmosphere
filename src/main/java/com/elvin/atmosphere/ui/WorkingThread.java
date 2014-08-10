package com.elvin.atmosphere.ui;

import java.util.ArrayList;
import java.util.List;

import com.elvin.atmosphere.core.Atmosphere;
import com.elvin.atmosphere.core.BorderColor;

public class WorkingThread extends Atmosphere implements Runnable {

    private boolean run = true;
    private long interval = 200L;
    private List<BorderColorRetrievedListener> listener = new ArrayList<BorderColorRetrievedListener>();
    
    public WorkingThread(int vHeight, int hWidth, int tbSplit, int lrSplit, int topAdjust, int bottomAdjust, int leftAdjust, int rightAdjust){
        this.init(vHeight, hWidth, tbSplit, lrSplit, topAdjust, bottomAdjust, leftAdjust, rightAdjust);
    }
    
    public void run() {
        while(run){
            
            BorderColor borderColor = getColors();
            for(BorderColorRetrievedListener l : listener){
                l.BorderColorRetrieved(borderColor);
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
            }
        }
    }

    public void stop(){
        this.run = false;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void addBorderColorRetrievedListener(BorderColorRetrievedListener e){
        listener.add(e);
    }
    
}

interface BorderColorRetrievedListener {
    public void BorderColorRetrieved(BorderColor borderColor);
}
