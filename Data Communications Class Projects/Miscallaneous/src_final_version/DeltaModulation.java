import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;


public class DeltaModulation {
	
	Graphics g;
	int modeHelper;
	int mode;
	Point p=new Point();
	Point lastDeltaPoint=new Point();
	
	DeltaModulation(Graphics g)
	{
		this.g=g;
		this.modeHelper=0;
		this.mode=modeHelper%2;
		p.updatePoint(80, 140);
		lastDeltaPoint.updatePoint(80, 250);
		g.drawString("Signal Amplitude", 45, 25);
		g.drawString("Delta", 25, 235);
		g.drawString("Modulation", 5, 245);
		g.drawString("Output", 20, 255);
		g.drawString("0", 70, 255);
		g.drawString("1", 70, 235);
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(0.1f));
		int i,j,k,p;
		k=80;
		p=40;
		for(i=0;i<16;i++)
		{
			g.drawLine(80, p, 420, p);
			p=p+10;
		}
		for(j=0;j<35;j++)
		{
			g.drawLine(k, 40, k, 200);
			k=k+10;
		}
		float dash1[] = {2.5f};
		BasicStroke dashed =new BasicStroke(1.0f,BasicStroke.CAP_BUTT,	BasicStroke.JOIN_MITER,	2.5f, dash1, 0.0f);
		g2.setStroke(dashed);
		k=80;
		for(j=0;j<35;j++)
		{
			g.drawLine(k, 200, k, 250);
			k=k+10;
		}
		g2.setStroke(new BasicStroke(2f));
		process(g);
	}

	public void process(Graphics g)
	{
		int valueofgraph;
		int i=0;
		while(p.getX()< 420 && lastDeltaPoint.getX()<420 )
		{	
			if(lastDeltaPoint.getX()==80)
			{
				maintain(g);
			}
			g.drawLine(p.getX(), p.getY(), p.getX()+10, p.getY());
			p.updatePoint( p.getX()+10, p.getY());
			i=i+10;
			valueofgraph = (int)(140 - (50 * getSineVal((i / 340.0) * 2* Math.PI)));
			if(p.getY() < valueofgraph)
			{
				goDown(g);
			}
			else
			{
				goUp(g);
			}
		}
	}

	public void goUp(Graphics g)
	{
		g.drawLine(p.getX(), p.getY(), p.getX(), p.getY()-10);
		p.updatePoint( p.getX(), p.getY()-10);
		
		if(mode==0)
		{
			transitiontoHigh(g);
			updateMode();
		}
		else if(mode==1)
		{
			maintain(g);
		}
	}

	public void goDown(Graphics g)
	{
		g.drawLine(p.getX(), p.getY(), p.getX(), p.getY()+10);
		p.updatePoint( p.getX(), p.getY()+10);
		if(mode==1)
		{
			transitiontoLow(g);
			updateMode();
		}
		else if(mode==0)
		{
			maintain(g);
		}
	}
	
	public void maintain(Graphics g)
	{
		g.drawLine(lastDeltaPoint.getX(), lastDeltaPoint.getY(), lastDeltaPoint.getX()+10, lastDeltaPoint.getY());
		lastDeltaPoint.updatePointX(lastDeltaPoint.getX()+10);
	}

	public void transitiontoHigh(Graphics g)
	{
		g.drawLine(lastDeltaPoint.getX(), lastDeltaPoint.getY(), lastDeltaPoint.getX(), lastDeltaPoint.getY()-20);
		lastDeltaPoint.updatePointY(lastDeltaPoint.getY()-20);
		g.drawLine(lastDeltaPoint.getX(), lastDeltaPoint.getY(), lastDeltaPoint.getX()+10, lastDeltaPoint.getY());
		lastDeltaPoint.updatePointX(lastDeltaPoint.getX()+10);
	}
	public void transitiontoLow(Graphics g)
	{
		g.drawLine(lastDeltaPoint.getX(), lastDeltaPoint.getY(), lastDeltaPoint.getX(), lastDeltaPoint.getY()+20);
		lastDeltaPoint.updatePointY(lastDeltaPoint.getY()+20);
		g.drawLine(lastDeltaPoint.getX(), lastDeltaPoint.getY(), lastDeltaPoint.getX()+10, lastDeltaPoint.getY());
		lastDeltaPoint.updatePointX(lastDeltaPoint.getX()+10);
	}
	
	public void updateMode()
	{
		modeHelper++;
		mode=modeHelper%2;
	}
	
	double getSineVal(double x) 
	{
		return Math.sin(x);
	}
}
