package first_matlab_test;

public class UnderwaterE extends Enemy {

	UnderwaterE() {

	}

	UnderwaterE(float x, int i) {
		this.x = x;
		this.y = 280 + rand.nextInt(400);
		this.i = i;
		setSpeed();
		if (i == 0) {
			img = loadImage("data\\scubaleft.png");
		} else {
			img = loadImage("data\\scubaright.png");
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
		this.y = 280 + rand.nextInt(400);
	}

}
