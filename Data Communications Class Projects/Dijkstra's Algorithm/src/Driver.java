import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.*;

public class Driver extends JFrame{

	public Driver() 
	{
		setLayout(new BorderLayout());
		add(new Panel(), BorderLayout.CENTER);
	}

	public static void main(String[] args) 
	{
		Driver frame = new Driver();
		frame.setSize(800, 600);
		frame.setTitle("Dijkstra's Algorithm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class Panel extends JPanel implements ActionListener{

	JButton v1;
	JButton v2;
	JButton v3;
	JButton v4;
	JButton v5;
	JButton v6;

	Dijkstra dijkstra;
	RoutingTable[] table;
	int[] xPoint;
	int[] yPoint;

	Panel(){
		this.setLayout(null);

		v1=new JButton("v1");
		v2=new JButton("v2");
		v3=new JButton("v3");
		v4=new JButton("v4");
		v5=new JButton("v5");
		v6=new JButton("v6");

		v1.setBounds(50, 250, 50, 25);
		v2.setBounds(120, 250, 50, 25);
		v3.setBounds(50, 300, 50, 25);
		v4.setBounds(120, 300, 50, 25);
		v5.setBounds(50, 350, 50, 25);
		v6.setBounds(120, 350, 50, 25);

		this.add(v1);
		this.add(v2);
		this.add(v3);
		this.add(v4);
		this.add(v5);
		this.add(v6);

		v1.addActionListener(this);
		v2.addActionListener(this);
		v3.addActionListener(this);
		v4.addActionListener(this);
		v5.addActionListener(this);
		v6.addActionListener(this);

		table=new RoutingTable[6];
		dijkstra=new Dijkstra();
		xPoint=new int[6];
		yPoint=new int[6];

		xPoint[0] = 300;
		yPoint[0] = 50;
		xPoint[1] = 450;
		yPoint[1] = yPoint[0];
		xPoint[2] = 600;
		yPoint[2] = yPoint[0];
		xPoint[3] = 300;
		yPoint[3] = yPoint[0]+150;
		xPoint[4] = 450;
		yPoint[4] = yPoint[3];
		xPoint[5] = 600;
		yPoint[5] = yPoint[3];
		int i;
		for(i=0;i<6;i++)
		{
			table[i]=new RoutingTable(i+1);
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2d= (Graphics2D)g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawOval(dijkstra.node1.xCoordinate, dijkstra.node1.yCoordinate, 20, 20);
		g2d.drawString("V1", dijkstra.node1.xCoordinate+5, dijkstra.node1.yCoordinate+15);
		g2d.drawOval(dijkstra.node2.xCoordinate, dijkstra.node2.yCoordinate, 20, 20);
		g2d.drawString("V2", dijkstra.node2.xCoordinate+5, dijkstra.node2.yCoordinate+15);
		g2d.drawOval(dijkstra.node3.xCoordinate, dijkstra.node3.yCoordinate, 20, 20);
		g2d.drawString("V3", dijkstra.node3.xCoordinate+5, dijkstra.node3.yCoordinate+15);
		g2d.drawOval(dijkstra.node4.xCoordinate, dijkstra.node4.yCoordinate, 20, 20);
		g2d.drawString("V4", dijkstra.node4.xCoordinate+5, dijkstra.node4.yCoordinate+15);
		g2d.drawOval(dijkstra.node5.xCoordinate, dijkstra.node5.yCoordinate, 20, 20);
		g2d.drawString("V5", dijkstra.node5.xCoordinate+5, dijkstra.node5.yCoordinate+15);
		g2d.drawOval(dijkstra.node6.xCoordinate, dijkstra.node6.yCoordinate, 20, 20);
		g2d.drawString("V6", dijkstra.node6.xCoordinate+5, dijkstra.node6.yCoordinate+15);
		
		g2d.drawString("Routing Tables", 450, 350);

		int k,p;
		for(p=0;p<6;p++){
			g2d.drawString("Node"+(p+1), xPoint[p], yPoint[p]);
			yPoint[p]+=20;
			g2d.drawString("Destination  Next Node", xPoint[p]-30, yPoint[p]);
			yPoint[p]+=20;
			for(k=0;k<5;k++){
				g2d.drawString(Integer.toString(table[p].data[k][0]), xPoint[p], yPoint[p]);
				xPoint[p]=xPoint[p]+50;
				g2d.drawString(Integer.toString(table[p].data[k][1]), xPoint[p], yPoint[p]);
				xPoint[p]=xPoint[p]-50;
				yPoint[p]=yPoint[p]+20;
			}
		}
		xPoint[0] = 300;
		yPoint[0] = 50;
		xPoint[1] = 450;
		yPoint[1] = yPoint[0];
		xPoint[2] = 600;
		yPoint[2] = yPoint[0];
		xPoint[3] = 300;
		yPoint[3] = yPoint[0]+150;
		xPoint[4] = 450;
		yPoint[4] = yPoint[3];
		xPoint[5] = 600;
		yPoint[5] = yPoint[3];
		g2d.drawString("Path: "+dijkstra.path, 50, 200);
		dijkstra.path="";
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== v1)
		{
			dijkstra.setSource(dijkstra.node1);
			dijkstra.performDijkstra();
			repaint();
		}
		else if(e.getSource()== v2){
			dijkstra.setSource(dijkstra.node2);
			dijkstra.performDijkstra();
			repaint();
		}
		else if(e.getSource()== v3){
			dijkstra.setSource(dijkstra.node3);
			dijkstra.performDijkstra();
			repaint();
		}
		else if(e.getSource()== v4){
			dijkstra.setSource(dijkstra.node4);
			dijkstra.performDijkstra();
			repaint();
		}
		else if(e.getSource()== v5){
			dijkstra.setSource(dijkstra.node5);
			dijkstra.performDijkstra();
			repaint();
		}
		else if(e.getSource()== v6){
			dijkstra.setSource(dijkstra.node6);
			dijkstra.performDijkstra();
			repaint();
		}
	}
}
