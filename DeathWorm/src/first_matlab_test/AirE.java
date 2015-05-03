package first_matlab_test;

import processing.core.*;
import java.util.*;

public class AirE extends Enemy{ 
	
	AirE(){
		
	}
	
	AirE(float x,int i){
		this.x = x;
		this.y =80 + rand.nextInt(100);
		this.i=i;
		setSpeed();
		if(i==0)
		{
			img = loadImage("data\\left.png");	
		}
		else
		{
			img = loadImage("data\\right.png");
		}
		
	}

}
