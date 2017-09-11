import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;


public class Delta {
	
	Graphics g;
	int stateTransitor;
	int state;
	Coordinate crawlerCoord=new Coordinate();
	Coordinate bitCoord=new Coordinate();
	
	Delta(Graphics g)
	{
		this.g=g;
		this.stateTransitor=0;
		this.state=stateTransitor%2;
		crawlerCoord.setCoordinates(80, 140);
		bitCoord.setCoordinates(80, 250);
		g.drawString("진폭", 65, 25);
		g.drawString("결과", 20, 245);
	}

	public void paint(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(0.1f));
		

		g2.setStroke(new BasicStroke(2f));
		g2.setColor(Color.BLUE);
		draw(g);
	}

	public void draw(Graphics g)
	{
		int graphHeight;
		int i=0;
		while(crawlerCoord.getX()< 420 && bitCoord.getX()<420 )
		{	
			if(bitCoord.getX()==80)
			{
				straight(g);
			}
			g.drawLine(crawlerCoord.getX(), crawlerCoord.getY(), crawlerCoord.getX()+10, crawlerCoord.getY());
			crawlerCoord.setCoordinates( crawlerCoord.getX()+10, crawlerCoord.getY());
			i=i+10;
			graphHeight = (int)(140 - (50 * valueofCos((i / 340.0) * 2* Math.PI)));
			if(crawlerCoord.getY() < graphHeight)
			{
				downwards(g);
			}
			else
			{
				upwards(g);
			}
		}
	}
	
	double valueofCos(double x) 
	{
		return Math.cos(x);
	}

	public void upwards(Graphics g)
	{
		g.drawLine(crawlerCoord.getX(), crawlerCoord.getY(), crawlerCoord.getX(), crawlerCoord.getY()-10);
		crawlerCoord.setCoordinates( crawlerCoord.getX(), crawlerCoord.getY()-10);
		
		if(state==0)
		{
			lowToHigh(g);
			setStatus();
		}
		else if(state==1)
		{
			straight(g);
		}
	}

	public void downwards(Graphics g)
	{
		g.drawLine(crawlerCoord.getX(), crawlerCoord.getY(), crawlerCoord.getX(), crawlerCoord.getY()+10);
		crawlerCoord.setCoordinates( crawlerCoord.getX(), crawlerCoord.getY()+10);
		if(state==1)
		{
			highToLow(g);
			setStatus();
		}
		else if(state==0)
		{
			straight(g);
		}
	}
	
	public void straight(Graphics g)
	{
		g.drawLine(bitCoord.getX(), bitCoord.getY(), bitCoord.getX()+10, bitCoord.getY());
		bitCoord.setX(bitCoord.getX()+10);
	}

	public void lowToHigh(Graphics g)
	{
		g.drawLine(bitCoord.getX(), bitCoord.getY(), bitCoord.getX(), bitCoord.getY()-20);
		bitCoord.setY(bitCoord.getY()-20);
		g.drawLine(bitCoord.getX(), bitCoord.getY(), bitCoord.getX()+10, bitCoord.getY());
		bitCoord.setX(bitCoord.getX()+10);
	}
	public void highToLow(Graphics g)
	{
		g.drawLine(bitCoord.getX(), bitCoord.getY(), bitCoord.getX(), bitCoord.getY()+20);
		bitCoord.setY(bitCoord.getY()+20);
		g.drawLine(bitCoord.getX(), bitCoord.getY(), bitCoord.getX()+10, bitCoord.getY());
		bitCoord.setX(bitCoord.getX()+10);
	}
	
	public void setStatus()
	{
		stateTransitor++;
		state=stateTransitor%2;
	}
	
	
}
