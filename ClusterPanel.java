import java.awt.Color;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class ClusterPanel extends JPanel {
	
	private int K=3;
	private int size;
	private int stepsNum=15;
	
	private float[] clusters;
	private int[] cSize;
	private int[] sensors;
	private float[] temp;
		
	private TextPanel c1,c2,c3,t;
	
	
	public ClusterPanel(float[] data){
		temp=data;
		size=data.length;
		clusters=new float[K];
		float a=avg();
		Random rand = new Random();
		for(int i=0;i<K;i++){
			clusters[i]=(float) (a+(0.5*rand.nextGaussian()));
		}

		sensors=new int[size];
		cSize=new int[K];
		
		kMeans(stepsNum);
		
		
		t=new TextPanel("<html>Centralized K-Means<br/>Clustering Results:</html>",GUI.WIDTH,30);
		c1=new TextPanel("<html><font color=White>Cluster #1<br/>Mean Temperature: "+trim(clusters[0])+"<br/>Size: "+cSize[0]+"</font></html>",Color.BLUE, GUI.WIDTH/3,75);
		c1.setBorder(BorderFactory.createLineBorder(Color.black));
		c2=new TextPanel("<html>Cluster #2<br/>Mean Temperature: "+trim(clusters[1])+"<br/>Size: "+cSize[1]+"</html>",Color.GREEN, GUI.WIDTH/3,75);
		c2.setBorder(BorderFactory.createLineBorder(Color.black));
		c3=new TextPanel("<html><font color=White>Cluster #3<br/>Mean Temperature: "+trim(clusters[2])+"<br/>Size: "+cSize[2]+"</font></html>",Color.RED, GUI.WIDTH/3,75);
		c3.setBorder(BorderFactory.createLineBorder(Color.black));
		add(t);
		add(c1);
		add(c2);
		add(c3);
	}

	
	public void update(float[] data){
		temp=data;
		kMeans(stepsNum);
		c1.update("<html><font color=White>Cluster #1<br/>Mean Temperature: "+trim(clusters[0])+"<br/>Size: "+cSize[0]+"</font></html>");
		c2.update("<html>Cluster #2<br/>Mean Temperature: "+trim(clusters[1])+"<br/>Size: "+cSize[1]+"</html>");
		c3.update("<html><font color=White>Cluster #3<br/>Mean Temperature: "+trim(clusters[2])+"<br/>Size: "+cSize[2]+"</font></html>");
	}
	
	public void kMeans(int steps){
		for(int j=0;j<steps;j++){
			for(int i=0;i<size;i++){
				sensors[i]=getD(temp[i]);
			}
			updateCluster();
		}
	}
	
	public int getD(float x){
		float[] dist=new float[K];
		for(int i=0;i<K;i++) dist[i]=abs(x-clusters[i]);
		return getMin(dist);
	}
	
	private float abs(float x){
		if (x<0){
			return(((float)(-1.0))*x);
		}
		return x;
	}
	
	public int getMin(float[] dist){
		int x=0;
		float y=dist[0];
		for(int i=0;i<dist.length;i++) 
			if (dist[i]<y){
				x=i; 
				y=dist[i];
		}
		return x;
	}
	
	public void updateCluster(){
		float[] c=new float[K];
		for(int i=0;i<K;i++){
			c[i]=0;
			cSize[i]=0;
		}
		for(int i=0;i<size;i++){
			cSize[sensors[i]]++;
			c[sensors[i]]+=temp[i];
		}
		for(int i=0;i<K;i++){
			float x=c[i]/cSize[i];
			clusters[i]=x;
		}
		if(cSize[0]==0) clusters[0]=(float) (0.5*(clusters[1]+clusters[2]));
		if(cSize[1]==0) clusters[1]=(float) (0.5*(clusters[0]+clusters[2]));
		if(cSize[2]==0) clusters[2]=(float) (0.5*(clusters[1]+clusters[0]));
		sort();
	}
	
	private float avg(){
		float x=0;
		int n=temp.length;
		for(int i=0;i<n;i++) x+=temp[i];
		return (x/n);
	}
	
	private float trim(float x){
		int i=(int)(x*1000);
		int j=(int)(x);
		float k=(float) ((i%1000)/1000.0);
		float l=j+k;
		return l;
	}
	
	private void sort(){
		bubbleSort();
	}
	
	private void bubbleSort(){
		for(int i=0;i<K;i++){
			for(int j=0;j<i;j++){
				if (clusters[j]>clusters[j+1]){                                       
                    swap(clusters, j, (j+1));                                       
                    swap(cSize, j, (j+1));                                       
                    swap(sensors, j, (j+1));
				}
			}
		}
	}
	
		public void swap( float array3[], int first, int second )
	   {
	      float hold = array3[ first ];
	      array3[ first ] = array3[ second ];
	      array3[ second ] = hold;
	   }
	
	public void swap( int array3[], int first, int second )
	   {
	      int hold = array3[ first ];
	      array3[ first ] = array3[ second ];
	      array3[ second ] = hold;
	   }
}
