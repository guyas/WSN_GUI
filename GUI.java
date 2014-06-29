import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI implements Runnable {

	private JFrame frame;
	private JPanel[] p;
	private JLabel title, bottom;
	protected final static int WIDTH=750;
	protected int pnum=9, height=750, n=0;
	private int[] ph={30,320,40,50,40,25,65,130,20};
	
	private JComboBox box;
	private int[] idList;
	private String[] list;
	private JLabel t;
	private String selector="Select a sensor to preview:";
	private String titleString="<html><font color=Green size=+2><b><u>Mote #"+n+" viewpoint:</u></b></font></html>";
	private String bottomString="<html><font size=2>© Yinon Douchan, Guy Ashiri, HUJI 2013</font></html>";
	
	private Graph g;
	private JPanel moteView;
	private SensorPanel sp;
	private AlertPanel ap;
	private CentDataPanel cdp;
	private ClusterPanel cp;
	private TablePanel tp;
	

	private DataSet ds;
	private float[][] d;
	
	public GUI(){
		init();
		frame.setSize(WIDTH+20, height);
	}
	
	private void init(){
		int k=0;
		p=new JPanel[pnum];
		for(int i=0;i<pnum;i++){
			p[i]=new JPanel();
			p[i].setBounds(0, k, WIDTH, ph[i]);
			k+=ph[i];
			p[i].setLayout(new BoxLayout(p[i],BoxLayout.PAGE_AXIS));
			p[i].setVisible(true);
		}
		ds=new DataSet();
		d=ds.getData();
		
		///////////////////////////////////////////
		//PANEL 0: TITLE
		title=new JLabel(titleString,JLabel.CENTER);
		p[0].add(title);
		p[0].setBorder(BorderFactory.createMatteBorder(1,1,0,1, Color.red));
		///////////////////////////////////////////
		
		
		
		///////////////////////////////////////////
		//PANEL 1: GRAPH
		int[] ngb=ds.getNGB(n);
		g=new Graph(ngb,ds.getTemp(ngb),ds.getClusteringList(ngb));
		p[1].add(g);
		p[1].setBorder(BorderFactory.createMatteBorder(0,1,0,1, Color.red));
		///////////////////////////////////////////
		
		
		
		///////////////////////////////////////////
		//PANEL 2: SENSOR ADDITIONAL DATA
		sp=new SensorPanel(ds.getSensorData(n));
		p[2].add(sp);
		p[2].setBorder(BorderFactory.createMatteBorder(0,1,0,1, Color.red));
		///////////////////////////////////////////
		
		
		
		///////////////////////////////////////////
		//PANEL 3: ALERTED SENSORS
		int[] alert=ds.getAlert();
		Random rand=new Random();
		int[] alert2={rand.nextInt(9)+1};
		ap=new AlertPanel(alert2,ds.getTemp(alert2));
		p[3].add(ap);
		p[3].setBorder(BorderFactory.createMatteBorder(0,1,1,1, Color.red));
		///////////////////////////////////////////
		
		
		
		///////////////////////////////////////////
		//PANEL 4: SENSOR SELECTION
		
		idList=ds.getIDList();
		list=new String[idList.length];
		for(int i=0;i<list.length;i++) list[i]=String.valueOf(idList[i]);
		box=new JComboBox(list);
		box.setSize(WIDTH/3, ph[4]);
		box.setMaximumRowCount(4);
		box.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent event)
						{
							if (event.getStateChange()== ItemEvent.SELECTED){
								changeSensor(idList[box.getSelectedIndex()]);
							}
						}
					}
				);
		p[4].setLayout(new BoxLayout(p[4],BoxLayout.X_AXIS));
		t=new JLabel(selector);
		t.setBounds(0, 0, WIDTH/4,ph[4]);
		box.setBounds(0,0,(int) (WIDTH*0.5),ph[4]);
		JPanel b=new JPanel();
		b.add(t);
		b.add(box);
		b.setBounds(0,0,WIDTH,ph[4]);
		p[4].add(b);
		p[4].setSize(WIDTH, ph[4]);
		///////////////////////////////////////////
		
		
		
		///////////////////////////////////////////
		//PANEL 5: CENTRALIZED DATA
		cdp=new CentDataPanel(d[DataSet.TEMP]);
		p[5].add(cdp);
		p[5].setBounds(0, p[5].getY(), 500, ph[5]);
		///////////////////////////////////////////

		
		
		///////////////////////////////////////////
		//PANEL 6: CLUSTER DATA 
		cp=new ClusterPanel(d[DataSet.TEMP]);
		p[6].add(cp);
		p[6].setBounds(0, p[6].getY(), 700, ph[6]+5);
		///////////////////////////////////////////
		
		
		
		///////////////////////////////////////////
		//PANEL 7: TABLE
		tp=new TablePanel(ds.toObj());
		p[7].add(tp);
		p[7].setBounds(0, p[7].getY(), WIDTH, ph[7]);
		///////////////////////////////////////////
		
				

		///////////////////////////////////////////
		//PANEL 8: BOTTOM 
		bottom=new JLabel(bottomString,JLabel.CENTER);
		p[8].add(bottom);
		///////////////////////////////////////////
		
		moteView=new JPanel();
		int h=ph[0]+ph[1]+ph[2]+ph[3];
		moteView.setSize(WIDTH-20, h);
		moteView.setLayout(null);
		frame=new JFrame();
		frame.setLayout(null);
		

		for(int i=0;i<3;i++){
			p[i].setVisible(true);
			moteView.add(p[i]);
		}
		
		moteView.setBorder(BorderFactory.createRaisedBevelBorder());
//		frame.add(moteView);
		
		
		for(int i=0;i<pnum;i++){
			p[i].setVisible(true);
			frame.add(p[i]);
			p[i].setVisible(true);
		}
		
		
		frame.setTitle("WSN for Dist. Temp. Monitoring (2013 Project) GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String args[]){
		GUI gui=new GUI();
		gui.run();
//		int[] x=gui.ds.getNGB(1);
//		float[] f=gui.ds.getTemp(x);
//		for(int i=0;i<f.length;i++) System.out.println(f[i]);
		
	}
	
	public void run(){
		while(true){
			update();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	private void update(){
		ds.dataUpdate();
		d=ds.getData();
		titleString="<html><font color=Green size=+2><b><u>Mote #"+n+" viewpoint:</u></b></font></html>";
		title.setText(titleString);

		int[] ngb=ds.getNGB(n);
		g.update(ngb,ds.getTemp(ngb),ds.getClusteringList(ngb));
		sp.update(ds.getSensorData(n));
		

		idList=ds.getIDList();
		list=new String[idList.length];
		for(int i=0;i<list.length;i++) list[i]=String.valueOf(idList[i]);
		
		
		
		cdp.update(d[DataSet.TEMP]);
		cp.update(d[DataSet.TEMP]);
		tp.update(ds.toObj());
	}
	
	public void changeSensor(int sensor){
		if (sensor!=n){
			n=sensor;
			update();
		}
	}
}
