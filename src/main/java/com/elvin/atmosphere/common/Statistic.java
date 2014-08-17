package com.elvin.atmosphere.common;

import java.util.HashMap;
import java.util.Map;

public class Statistic {

    private static Map<String, Float> mapName2Avg = new HashMap<String, Float>();
    
    public static void calcAvg(String name, float time){
        Float avg = mapName2Avg.get(name);
        if(avg == null){
            avg = time;
        }
        avg = avg + time / 2.0f;
        mapName2Avg.put(name, avg);
    }
    
    public static void report(){
        for(Map.Entry<String, Float> entry : mapName2Avg.entrySet()){
            System.out.println(" Avg: "+ entry.getKey() + " - " + entry.getValue());
        }
    }
    
}
