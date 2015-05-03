package first_matlab_test;

import processing.core.*;

public class WaterSurfE extends Enemy{ 
	
	WaterSurfE(){
	}
	
	WaterSurfE(float x, int i){
		this.x = x;
		this.y = 220;
		this.i=i;
		setSpeed();
		
		img = loadImage("data\\boat.png");
	}

}
