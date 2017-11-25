import java.awt.*;
import javax.swing.*;

public class PCM extends JFrame{
	static PCMpanel panel=new PCMpanel();
	
	PCM(){
		this.add(panel, BorderLayout.CENTER);
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}

class PCMpanel extends JPanel{

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
	}
	
	public double getCos(double x)
	{
		return Math.cos(x);
	}
}
