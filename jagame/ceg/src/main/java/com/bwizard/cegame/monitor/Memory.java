package com.bwizard.cegame.monitor;

public class Memory {
	
    long memoryTotal;
    long memoryFreePriv;
    long memoryFreeCurr;
   
    public Memory(){
            memoryTotal = Runtime.getRuntime().totalMemory();
            memoryFreePriv = Runtime.getRuntime().freeMemory();
           
            System.out.println("MemoryTotal : " + memoryTotal + " MemoryFree: " + memoryFreePriv);
    }
   
    public void Reset(){
            memoryFreePriv = Runtime.getRuntime().freeMemory();
    }

    public long getUsedMemory()
    {
            memoryFreeCurr = Runtime.getRuntime().freeMemory();
            return memoryFreePriv - memoryFreeCurr;
    }
}
