import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;


class PCM
{
	Graphics g;
	String spacingString;
	int spacing;
	PCM(Graphics g, Object spacing){
		this.g=g;
		this.spacingString=(String)spacing;
		this.spacing=Integer.parseInt(spacingString);
		g.drawString("PAM Value:", 10, 225);
		g.drawString("Quantization:", 0, 245);
		g.drawString("PCM Code:", 10, 265);
		g.drawString("Code Number", 45, 25);
	}

	public void paintComponent(Graphics g)
	{
		int k;
		int j=0;
		
		for(k=0;k<16;k++)
		{
			if(k == 0)
			{
				g.drawLine(80, 200-j, 85, 200-j);
			}
			else
			{
				g.drawLine(75, 200-j, 85, 200-j);
			}
			g.drawString(String.valueOf(k), 60, 205-j);
			j=j+10;
		}
		g.drawString("Current spacing: "+ spacingString+"pixels", 200, 50);
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(0.1f));
		showValues(g);
		
	}
	
	public void showValues(Graphics g){
		int i,temp;
		String strtemp;
		int valueofgraph;
		double doublevalue;
		DecimalFormat df = new DecimalFormat("###.#");
		DecimalFormat df2 = new DecimalFormat("###");
		df2.setRoundingMode(java.math.RoundingMode.DOWN);
		int p;
		StringBuilder sb;
		for(i = 0; i <= 340; i=i+spacing)
		{
			valueofgraph=140 - (int)(50 * getSineVal((i / 340.0) * 2* Math.PI));
			doublevalue=190-(125-(50 * getSineVal((i / 340.0) * 2* Math.PI)));
			temp=Integer.parseInt(df2.format( ((doublevalue/160)*15) ));
			g.drawLine(i + 80,200,i+80, 210);
			g.drawRect(i+80, valueofgraph, spacing, 200-valueofgraph);		
			g.drawString(df.format( ((doublevalue/160)*15) ), i+75, 225);
			g.drawString(df2.format( ((doublevalue/160)*15) ), i+75, 245);
			strtemp=Integer.toBinaryString(temp);
			sb=new StringBuilder(strtemp);
			if(sb.length() < 4){
				while(sb.length() <4 ){
					sb.insert(0, "0");
				}
			}
			g.drawString(   sb.toString(), i+75, 265);
		}
	}
	
	double getSineVal(double x) 
	{
		return Math.sin(x);
	}
}