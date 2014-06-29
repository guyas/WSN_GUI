import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class TextPanel extends JPanel {
	String s;
	Color c;
	JLabel j;
	int w,h;
	
	public TextPanel(String st, Color clr,int width,int height){
		c=clr;
		setBackground(c);
		s=st;
		w=width;
		h=height;
		
		j=new JLabel(s,JLabel.CENTER);
		add(j);
		setSize(w,h);
	}


	public TextPanel(String st,int width,int height){
		s=st;
		w=width;
		h=height;

		j=new JLabel(s,JLabel.CENTER);
		add(j);
		setSize(w,h);
	}
	
	public void update(String s){
		j.setText(s);
		j.setVisible(true);
		setVisible(true);
	}
	
}
