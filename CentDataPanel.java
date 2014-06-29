import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class CentDataPanel extends JPanel {
	TextPanel c1,c2,t;
	float avg,med;
	float[] d;
	public CentDataPanel(float[] data){
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		d=data;
		avg=average();
		med=median();
		t=new TextPanel("Centralized data:",GUI.WIDTH,30);
		c1=new TextPanel("<html><FONT COLOR=PURPLE>Average Temperature: "+trim(avg)+"</FONT></html>", GUI.WIDTH/3,40);
		c2=new TextPanel("<html><FONT COLOR=PURPLE>Median Temperature: "+trim(med)+"</FONT></html>", GUI.WIDTH/3,40);
		add(t);
		add(c1);
		add(c2);
	}

	
	public void update(float[] data){
		d=data;
		avg=average();
		med=median();
		c1.update("<html><FONT COLOR=PURPLE>Average Temperature: "+trim(avg)+"</FONT></html>");
		c2.update("<html><FONT COLOR=PURPLE>Median Temperature: "+trim(med)+"</FONT></html>");
	}

	private float average(){
		float x=(float) 0.0;
		for(int i=0;i<d.length;i++) x+=(float)d[i];
		return (x/(d.length));		
	}
	
	private float median(){
		float[] d2=d.clone();
		quickSort(d2);
		int l=d2.length;
		if((l)%2==0) return (((d2[l/2])+(d2[(l/2)-1]))/2);
		return (d2[l/2]);		
	}
	
	private float trim(float x){
		int i=(int)(x*1000);
		int j=(int)(x);
		float k=(float) ((i%1000)/1000.0);
		float l=j+k;
		return l;
	}
		
	public void quickSort(float array[]) 
	{
		quickSort(array, 0, array.length - 1);              
	}
	
	public void quickSort(float array[], int start, int end)
	{
	     int i = start;                          
	     int k = end;                            
	     if (end - start >= 1)                   
	     {
	             float pivot = array[start];       
	             while (k > i)                   
	             {
	                     while (array[i] <= pivot && i <= end && k > i)  
	                             i++;                                    
	                     while (array[k] > pivot && k >= start && k >= i) 
	                         k--;                                        
	                     if (k > i)                                       
	                             swap(array, i, k);                      
	             }
	             swap(array, start, k);          
	                                              
	             quickSort(array, start, k - 1); 
	             quickSort(array, k + 1, end);  
	     }
	     else    
	     {
	             return;                   
	     }
	}
	
	public void swap( float array3[], int first, int second )
	   {
	      float hold = array3[ first ];
	      array3[ first ] = array3[ second ];
	      array3[ second ] = hold;
	   }

}