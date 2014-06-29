import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AlertPanel extends JPanel {
	
	private TextPanel t;
	
	private float[] temp;
	private int[] id;
	
	private JPanel[] aPanel;
	private JLabel[] aIDLabels;
	private JLabel[] aTempLabels;

	private String title="Alerted Sensors:";
	private String sID="Sensor ID:";
	private String sTemp="Temperature:";
	
	
	public AlertPanel(int[] aID, float[] aTemp){
		id=aID;
		temp=aTemp;
		t=new TextPanel(title, 80, 40);
		add(t);
		this.setSize(GUI.WIDTH, 40);
		int n=id.length;
		aPanel=new JPanel[n];
		aIDLabels=new JLabel[n];
		aTempLabels=new JLabel[n];
		for(int i=0;i<n;i++){
			aPanel[i]=new JPanel();
			aPanel[i].setSize(100, 40);
			aPanel[i].setLayout(new BoxLayout(aPanel[i],BoxLayout.PAGE_AXIS));
			aIDLabels[i]=new JLabel(sID+String.valueOf(id[i]));
			aIDLabels[i].setVisible(true);
			aPanel[i].add(aIDLabels[i]);
			aTempLabels[i]=new JLabel(sTemp+String.valueOf(trim(temp[i])));
			aTempLabels[i].setVisible(true);
			aPanel[i].add(aTempLabels[i]);
			aPanel[i].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
			add(aPanel[i]);
		}
	}
	
	private boolean find(int n){
		int x=-1;
		for(int i=0;i<id.length;i++)
			if id
	}
	
	
	
	
	private float trim(float x){
		int i=(int)(x*1000);
		int j=(int)(x);
		float k=(float) ((i%1000)/1000.0);
		float l=j+k;
		return l;
	}
}
