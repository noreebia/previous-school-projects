
public class Point {
	int x,y;
	
	Point()
	{
		
	}

	public void updatePoint(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void updatePointX(int x)
	{
		this.x=x;
	}
	
	public void updatePointY(int y)
	{
		this.y=y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
}
