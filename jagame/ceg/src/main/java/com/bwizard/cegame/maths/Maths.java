package com.bwizard.cegame.maths;

public class Maths {
	
	//rounding decimal places
	public static float round(double f, int places) {  
		float temp = (float)(f*(Math.pow(10, places)));
        temp = (Math.round(temp));
        temp = temp/(int)(Math.pow(10, places));
        return temp;
	}
	
	
	/**
	 * This method calculate the number of power. e.g. 128(binary: 10000000) method return (8)
	 * @param number This variable provide number
	 * @return number of power
	 */
	public static int expBin(int number)
	{
		//it can be quickly perform mathematical operations like multiply/divide
		int i=0;
		int[] tab = new int[31];
 
		while(number != 0)
		{
			tab[i++]=number%2;
			number/=2;
		}
  
		return i-1;
	}
}
