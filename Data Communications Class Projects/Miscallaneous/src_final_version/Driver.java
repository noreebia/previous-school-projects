import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.*;

public class Driver extends JFrame {

	public Driver() 
	{
		setLayout(new BorderLayout());
		add(new DrawSine(), BorderLayout.CENTER);
	}

	public static void main(String[] args) 
	{
		Driver frame = new Driver();
		frame.setSize(570, 400);
		frame.setTitle("PCM & Delta Modulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class DrawSine extends JPanel implements ActionListener
{
	String[] comboList={"30","35","40","45","50"};
	JComboBox pcmSpacingBox;

	JButton pcmButton;
	JButton dmButton;
	JButton clearButton;
	JLabel explainComboBox;
	JLabel explainSpacingProcedure;
	PCM pcm;

	DrawSine()
	{
		this.setLayout(null);
		explainComboBox=new JLabel("<html>Adjust Spacing</html>",SwingConstants.CENTER);
		explainComboBox.setBounds(460, 150, 80, 30);
		explainSpacingProcedure=new JLabel("<html>Click 'Clear' and press 'PCM' again</html>",SwingConstants.CENTER);
		explainSpacingProcedure.setBounds(460, 140, 80, 65);
		pcmSpacingBox=new JComboBox(comboList);
		pcmSpacingBox.setBounds(460, 120, 80, 25);	
		pcmSpacingBox.setSelectedIndex(0);
		pcmButton= new JButton("PCM");
		pcmButton.setBounds(460, 90, 80, 25);
		dmButton= new JButton("Delta");
		dmButton.setBounds(460, 60, 80, 25);
		clearButton= new JButton("Clear");
		clearButton.setBounds(460, 30, 80, 25);
		this.add(pcmSpacingBox);
		this.add(dmButton);	
		this.add(pcmButton);	
		this.add(clearButton);
		this.add(explainComboBox);
		this.add(explainSpacingProcedure);
		pcmButton.addActionListener(this);
		dmButton.addActionListener(this);
		clearButton.addActionListener(this);
		pcmSpacingBox.addActionListener(this);
		pcmSpacingBox.setVisible(false);
		explainComboBox.setVisible(false);
		explainSpacingProcedure.setVisible(false);
	}

	double getSineVal(double x)
	{
		return Math.sin(x);
	}

	double getCosVal(double y)
	{
		return Math.cos(y);
	}

	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g;
		g2.setStroke(new BasicStroke(2f));
		g.drawLine(80, 200, 440, 200);
		g.drawLine(80, 30, 80, 200);
		g.drawLine(80, 30, 85, 35);
		g.drawLine(80, 30, 75, 35);
		g.drawLine(440, 200, 435, 195);
		g.drawLine(440, 200, 435, 205);
		g.drawString("Time", 420, 215);

		int i;
		Polygon p = new Polygon();
		Polygon p2 = new Polygon();

		for ( i = 0; i <= 340; i++) 
		{
			p.addPoint(i + 80, (int)(140 - (50 * getSineVal((i / 340.0) * 2* Math.PI))));
		}

		for ( i = 0; i <= 340; i++) 
		{
			p2.addPoint(i + 80, (int)(140 - (50 * getCosVal((i / 340.0) * 2 * Math.PI))));
		}

		g.setColor(Color.black);
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		g.setColor(Color.black);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==pcmButton)
		{
			pcm=new PCM(this.getGraphics(),pcmSpacingBox.getSelectedItem());
			pcm.paintComponent(this.getGraphics());
			pcmSpacingBox.setVisible(true);
			explainSpacingProcedure.setVisible(false);
			explainComboBox.setVisible(true);
		}
		else if(e.getSource()== dmButton)
		{
			DeltaModulation dmod=new DeltaModulation(this.getGraphics());		
			dmod.paintComponent(this.getGraphics());
		}	
		else if(e.getSource()== clearButton)
		{
			this.repaint();
			pcmSpacingBox.setVisible(false);
			explainComboBox.setVisible(false);
			explainSpacingProcedure.setVisible(false);
		}	
		else if(e.getSource()== pcmSpacingBox)
		{
			//
			explainComboBox.setVisible(false);
			explainSpacingProcedure.setVisible(true);
		}	
	}
}
