import javax.swing.JPanel;


public class SensorPanel extends JPanel {
	
	float[] d;
	TextPanel t, c1,c2,c3,c4,c5;
	
	public SensorPanel(float[] data){
		d=data;
		t=new TextPanel("Sensor Additional data:",GUI.WIDTH,30);
		c1=new TextPanel("<html><FONT COLOR=NAVY>Average:<br/>"+trim(d[DataSet.AVG])+"</FONT></html>", GUI.WIDTH/3,40);
		c2=new TextPanel("<html><FONT COLOR=NAVY>Median:<br/>"+trim(d[DataSet.MED])+"</FONT></html>", GUI.WIDTH/3,40);
		c3=new TextPanel("<html><FONT COLOR=NAVY>Cluster #1:<br/>"+trim(d[DataSet.CL1])+"</FONT></html>", GUI.WIDTH/3,40);
		c4=new TextPanel("<html><FONT COLOR=NAVY>Cluster #2:<br/>"+trim(d[DataSet.CL2])+"</FONT></html>", GUI.WIDTH/3,40);
		c5=new TextPanel("<html><FONT COLOR=NAVY>Cluster #3:<br/>"+trim(d[DataSet.CL3])+"</FONT></html>", GUI.WIDTH/3,40);
		add(t);
		add(c1);
		add(c2);
		add(c3);
		add(c4);
		add(c5);
	}
	
	public void update(float[] data){
		d=data;
		c1.update("<html><FONT COLOR=NAVY>Average:<br/>"+trim(d[DataSet.AVG])+"</FONT></html>");
		c2.update("<html><FONT COLOR=NAVY>Median:<br/>"+trim(d[DataSet.MED])+"</FONT></html>");
		c3.update("<html><FONT COLOR=NAVY>Cluster #1:<br/>"+trim(d[DataSet.CL1])+"</FONT></html>");
		c4.update("<html><FONT COLOR=NAVY>Cluster #2:<br/>"+trim(d[DataSet.CL2])+"</FONT></html>");
		c5.update("<html><FONT COLOR=NAVY>Cluster #3:<br/>"+trim(d[DataSet.CL3])+"</FONT></html>");
	}
	
	private float trim(float x){
		int i=(int)(x*1000);
		int j=(int)(x);
		float k=(float) ((i%1000)/1000.0);
		float l=j+k;
		return l;
	}
	
	
	
	
	

}
