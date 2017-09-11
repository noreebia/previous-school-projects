import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnalogGraph extends JFrame{

	public AnalogGraph(){
		setLayout(new BorderLayout());
		add(new DeltaPanel(), BorderLayout.CENTER);
	}
}

class DeltaPanel extends JPanel implements ActionListener{

	JButton deltaButton;
	int state;
	int stateHelper;
	DeltaPanel(){
		this.stateHelper=0;
		this.state=stateHelper %2;
		this.setLayout(null);
		deltaButton= new JButton("Delta On/Off");
		deltaButton.setBounds(190, 270, 120, 25);
		this.add(deltaButton);
		deltaButton.addActionListener(this);
	}

	double valofCos(double x)
	{
		return Math.cos(x);
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g;
		g2.setStroke(new BasicStroke(2f));
		g.drawLine(80, 200, 440, 200);
		g.drawLine(80, 30, 80, 200);
		g.drawString("시간", 420, 215);

		int i;
		for ( i = 0; i <= 340; i++) 
		{
			g.drawLine(i + 80, (int)(140 - (50 * valofCos((i / 340.0) * 2* Math.PI))),i + 81, (int)(140 - (50 * valofCos(((i+1) / 340.0) * 2* Math.PI))) );
		}
	}

	public void actionPerformed(ActionEvent e) {
		Delta delta=new Delta(this.getGraphics());		
		if(e.getSource()== deltaButton)
		{
			if(state==0){
				delta.paint(this.getGraphics());
			}
			else{
				this.repaint();
			}
			stateHelper++;
			setState();
		}
	}
	
	public void setState(){
		this.state=stateHelper %2;
		
	}
}