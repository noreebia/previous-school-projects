import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;

public class Main extends JFrame{
	
	public static void main(String[] args) {
		Main m=new Main();
		MainPanel panel=new MainPanel();
		m.setSize(250,150);
		m.setLocationRelativeTo(null);
		m.add(panel, BorderLayout.CENTER);
		m.setVisible(true);
	}
}

class MainPanel extends JPanel implements ActionListener{
	static JButton pcmButton=new JButton("PCM");
	static JButton deltaButton=new JButton("DELTA");
	static JLabel clickPrompt=new JLabel("Select modulation type");

	MainPanel(){
		this.setLayout(null);
		pcmButton.setBounds(30, 50, 80, 25);
		deltaButton.setBounds(120, 50, 80, 25);
		clickPrompt.setBounds(50,10, 200,50);
		pcmButton.addActionListener(this);
		deltaButton.addActionListener(this);;
		this.add(pcmButton);
		this.add(deltaButton);
		this.add(clickPrompt);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == pcmButton){
			PCM pcm=new PCM();
		}
		else if(e.getSource()==deltaButton){
			Delta delta=new Delta();
		}
	}
}

