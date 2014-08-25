package com.elvin.atmosphere.common;

import java.util.HashMap;
import java.util.Map;

public class Statistic {

    private static Map<String, Float> mapName2Avg = new HashMap<String, Float>();
    private static Map<String, Long> mapName2Time = new HashMap<String, Long>();
    
    public static void calcAvg(String name, float time){
        Float avg = mapName2Avg.get(name);
        if(avg == null){
            avg = time;
        }
        avg = (avg + time) / 2.0f;
        mapName2Avg.put(name, avg);
    }
    
    public static void report(){
        for(Map.Entry<String, Float> entry : mapName2Avg.entrySet()){
            System.out.println(" Avg: "+ entry.getKey() + " - " + entry.getValue());
        }
    }
    
    public static void start(String name){
        mapName2Time.put(name, System.currentTimeMillis());
    }
    public static void end(String name){
        long spent = System.currentTimeMillis() - mapName2Time.get(name);
        calcAvg(name, spent);
    }
    
}
