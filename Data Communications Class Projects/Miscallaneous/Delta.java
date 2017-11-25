import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public class Delta extends JFrame{

	DeltaPanel panel=new DeltaPanel();

	Delta(){
		this.add(panel, BorderLayout.CENTER);
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}

class DeltaPanel extends JPanel{

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		g.drawLine(40, 50, 40, 200);
		g.drawLine(40, 200, 400, 200);

		int i;
		for(i=0;i<350;i++)
		{
			g.drawLine(i+40, (int)(120 - ( 40* getCos(((i+40)/50.0) ))) , i+41,(int)(120 - ( 40* getCos(( (i+41)/50.0)  )  )));
		}
		int k,p;
		for(k=50;k<200;k=k+10){
			g.drawLine(40 , k, 400, k);
		}
		for(p=40;p<400;p=p+10){
			g.drawLine(p, 50, p, 200);
		}
	}

	public double getCos(double x)
	{
		return Math.cos(x);
	}

}