package first_matlab_test;

import processing.core.*;
import java.util.*;

public class Enemy extends PApplet{
	PImage img;
	float x;
	float y;
	int i;
	double speed;
	Random rand=new Random();
	Enemy(){
	}
	
	Enemy(float x, float y,int i){
		this.x = x;
		this.y = y;
		this.i=i;
		setSpeed();
	}
	
	public void setSpeed()
	{
		this.speed= (rand.nextFloat() / 0.2)+ 1;
	}
	
	Enemy(float x,float y,PImage img){
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public void move(){
		if(i ==0)
		{
			this.x+=speed;	
			x=x% 1500;
		}
		else
		{
			this.x-=speed;
			if(x <= -30)
			{
				reset();
			}
		}
		
	}
	
	public void reset(){
		if(i==0)
		{
			this.x=-10;	
			
		}
		else
		{
			this.x=1500;
			
		}
		setSpeed();
	}
}
