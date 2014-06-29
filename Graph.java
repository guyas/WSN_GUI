import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class Graph extends JPanel {
	
	int[] centX, centY, p1x, p1y;
	float[] t;
	int[] c;
	int[] id;
	public static final int line_length=130;
	final int width=60;
	final int height=40;
	final int red=1;
	final int green=2;
	final int blue=3;
	final int diff=height/5;
	Font tempF=new Font("Arial",Font.PLAIN,14);
	Font numF=new Font("Arial",Font.BOLD,12);
	
	public Graph(int[] i,float[] data, int[] cluster){
		id=i;
		t=data;
		c=cluster;
	}
	
	public void update(int[] i,float[] data, int[] cluster){
		id=i;
		t=data;
		c=cluster;
		paintComponent(getGraphics());
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		//setting locations
		g.setFont(tempF);
		int l=id.length-1;
		double angle=((2*Math.PI)/(l));
		centX=new int[l+1];
		centY=new int[l+1];
		p1x=new int[l+1];
		p1y=new int[l+1];
		centX[0]=(int) ((this.getSize().getWidth())/2);
		centY[0]=(int) ((this.getSize().getHeight())/2);
		for(int i=1;i<=l;i++){
			double a=i*angle;
			centX[i]=(int)(line_length*Math.cos(a))+centX[0];
			centY[i]=(int)(line_length*Math.sin(a))+centY[0];
		}
		//drawing
		for(int i=0;i<=l;i++){
			//lines
			g.setColor(Color.DARK_GRAY);
			g.drawLine(centX[0], centY[0], centX[i], centY[i]);
			//squares
			p1x[i]=centX[i]-width/2;
			p1y[i]=centY[i]-height/2;
			if(c[i]==red){
				g.setColor(Color.RED);				
			}else if(c[i]==green){
				g.setColor(Color.GREEN);
			}else{
				g.setColor(Color.BLUE);
			}
			g.fillRoundRect(p1x[i], p1y[i], width, height, diff, diff);
			if(i>0){
				float temp=(float) (((int)(100*t[i]))/100.0);
				String s=String.valueOf(temp);
				if(c[i]==green){
					g.setColor(Color.BLACK);
				}else g.setColor(Color.WHITE);
				g.drawString(s, centX[i]-3*diff/2, centY[i]+diff/2);
			}
		}

		if(c[0]==red){
			g.setColor(Color.RED);				
		}else if(c[0]==green){
			g.setColor(Color.GREEN);
		}else{
			g.setColor(Color.BLUE);
		}
		g.fillRoundRect(p1x[0], p1y[0], width, height, width/5, height/5);
		float temp=(float) (((int)(100*t[0]))/100.0);
		String s=String.valueOf(temp);

		if(c[0]==green){
			g.setColor(Color.BLACK);
		}else g.setColor(Color.WHITE);
		g.drawString(s, centX[0]-3*diff/2, centY[0]+diff/2);
		//drawing IDs
		for(int i=0;i<=l;i++){
			g.setColor(Color.BLACK);
			g.setFont(numF);
			int j=(int)(id[i]);
			s=String.valueOf(j);
			g.drawString(s, centX[i], p1y[i]);
		}

		this.setSize(GUI.WIDTH,320);
	}
}
