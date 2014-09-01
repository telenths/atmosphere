package com.elvin.atmosphere.common;

public class AtLog {

    
    private static int dots = 0;
    
    public static void logDots(){
        if(dots ++ % 10 == 0){
            System.out.println("+");
        }else{
            System.out.print(".");
        }
        
    }
    
}
