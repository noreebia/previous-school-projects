/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 *
 * @author Soo-Young
 */
public class PCM extends javax.swing.JPanel {

	int grid = 30;

	public PCM() {
		initComponents();
		jTextField1.setText("");
		jTextField1.setColumns(6);
		setText();
	}
	
	public void displayInfo(Graphics g) {
		int i, temp;
		int graphVal;
		double tempVal;
		String tempString;
		DecimalFormat df = new DecimalFormat("###.#");
		DecimalFormat df2 = new DecimalFormat("###");
		df2.setRoundingMode(java.math.RoundingMode.DOWN);     
		StringBuilder sb;
		for (i = 0; i <= 340; i = i + grid) {
			graphVal = 140 - (int) (50 * getCos((i / 340.0) * 2 * Math.PI));
			tempVal = 200- (140 - (50 * getCos((i / 340.0) * 2 * Math.PI)));
			temp = Integer.parseInt(df2.format(((tempVal / 160) * 15)));
			g.drawLine(i + 80, 200, i + 80, 210);
			g.drawRect(i + 80, graphVal, grid, 200 - graphVal);
			g.drawString(df.format(((tempVal / 160) * 16)), i + 75, 225);
			g.drawString(df2.format(((tempVal / 160) * 16)), i + 75, 245);
			tempString = Integer.toBinaryString(temp);
			sb = new StringBuilder(tempString);
			if (sb.length() < 4) {
				while (sb.length() < 4) {
					sb.insert(0, "0");
				}
			}
			g.drawString(sb.toString(), i + 75, 265);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("정수를 입력하시오", 90, 295);
		g.drawString("(되도록이면 30에서 100사이)", 30, 310);
		drawAnalogInput(g);
		int k;
		int j = 0;

		for (k = 0; k < 16; k++) {
			if (k == 0) {
				g.drawLine(80, 200 - j, 85, 200 - j);
			} else {
				g.drawLine(75, 200 - j, 85, 200 - j);
			}
			g.drawString(String.valueOf(k), 60, 205 - j);
			j = j + 10;
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(0.1f));
		displayInfo(g);
	}

	public void drawAnalogInput(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2f));
		g.drawLine(80, 200, 440, 200);
		g.drawLine(80, 30, 80, 200);
		g.drawString("Time", 420, 215);

		int i;
		g.setColor(Color.black);
		for (i = 0; i <= 340; i++) {
			g.drawLine(i + 80,  (int) (140 - (50 * getCos((i / 340.0) * 2 * Math.PI))), i + 81,  (int) (140 - (50 * getCos(((i+1) / 340.0) * 2 * Math.PI))));
		}
	}

	

	double getCos(double x) {
		return Math.cos(x);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {

		jTextField1 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();

		jTextField1.setText("jTextField1");
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jButton1.setText("Set Grid");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton3.setText("Exit");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jLabel1.setText("PAM→");

		jLabel2.setText("←Quantization");

		jLabel3.setText("PCM→");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(194, Short.MAX_VALUE)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(170, 170, 170))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addGap(23, 23, 23)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1)
												.addComponent(jLabel3))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel2)
												.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(212, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addComponent(jLabel2)
										.addGap(32, 32, 32))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addComponent(jLabel1)
												.addGap(26, 26, 26)
												.addComponent(jLabel3)
												.addGap(11, 11, 11)))
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jButton1)
														.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(jButton3)
														.addGap(31, 31, 31))
				);
	}// </editor-fold>                        

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            

	}                                           

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {         
		try{
			grid=Integer.parseInt((jTextField1.getText()));   
			this.repaint();
		}catch(NumberFormatException e){
			
		}
	}                                        

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
		System.exit(0);        // TODO add your handling code here:
	}                                        
	public void setText() {
		jTextField1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jTextField1.setText("");
			}
		});
	}

	// Variables declaration - do not modify                     
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration                   
}
