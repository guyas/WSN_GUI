import java.util.Random;


public class DataSet {
	private float[][] d;
	private int size=9;
	private int[] alerted={1,2};
	private int[][] neighborhood={{1,2,4},{2,1,3,5},{3,2,6},{4,1,5,7},{5,2,4,6,8},{6,3,5,9},{7,4,8},{8,5,7,9},{9,6,8}};

	public static final int ID=0;
	public static final int TEMP=1;
	public static final int AVG=2;
	public static final int MED=3;
	public static final int NGB=4;
	public static final int CLS=5;
	public static final int CL1=6;
	public static final int CL2=7;
	public static final int CL3=8;
	public static final int DATA_SIZE=9;
	
	public float[][] getData(){
		return d;
	}
	
	public int[] getNGB(int n){
		for(int i=0;i<size;i++)
			if(neighborhood[i][0]==n)
				return neighborhood[i];
		int[] x={n};
		return x;
	}
	
	
	public int[] getAlert(){
		return alerted;
	}
	
	public void dataUpdate(){
		Random rand=new Random();
		for (int i=1;i<d.length;i++)
			for(int j=0;j<d[0].length;j++)
			d[i][j]+=(float) (0.1*(rand.nextGaussian()));
	}
	
	public DataSet(){
		Random rand=new Random();
//		size=rand.nextInt(9)+4;
		d=new float[DATA_SIZE][size];
		for(int j=0;j<d[ID].length;j++) d[ID][j]=(j+1);
		for (int i=1;i<d.length;i++){
			for(int j=0;j<d[ID].length;j++){
			d[i][j]=(float) (30+(rand.nextGaussian()));
			}
		}
		for(int i=0;i<d[ID].length;i++) d[CLS][i]=(float)(rand.nextInt(2)+1);
	}

	public float[] getSensorData(int n) {
		float[] f=new float[DATA_SIZE];
		int s=findSensor(n);
		if(s>-1) 
			for(int i=0;i<DATA_SIZE;i++)
				f[i]=d[i][s];
		return f;
	}
	
	private int findSensor(int n){
		for(int i=0;i<d[ID].length;i++)
			if(d[ID][i]==n)
				return i;
		return -1;
	}

	public int[] getIDList(){
		int[] list=new int[size];
		for(int i=0;i<size;i++) list[i]=(int) d[ID][i];
		return list;
	}

	public int[] getIDList(int[] n){
		int[] list=new int[n.length];
		int j=0;
		for(int i=0;i<n.length;i++){
			list[j]=(int) d[ID][i];
			j++;
		}
		return list;
	}
	
	public int[] getClusteringList(){
		int[] list=new int[size];
		for(int i=0;i<size;i++) list[i]=(int) d[CLS][i];
		return list;
	}
	
	public int[] getClusteringList(int[] n){
		int[] list=new int[n.length];
		int j=0;
		for(int i=0;i<n.length;i++){
			list[j]=(int) d[CLS][i];
			j++;
		}
		return list;
	}
	
	public Object[][] toObj(){
	int r=d.length;
	int c=d[0].length;
	Object[][] o=new Object[c][r];
	for(int i=0;i<r;i++)
		for(int j=0;j<c;j++)
			o[j][i]=d[i][j];
	for(int i=0;i<c;i++){
		o[i][ID]=(int)d[ID][i];
		o[i][NGB]=(int)d[NGB][i];
		o[i][CLS]=(int)d[CLS][i];
	}
	return o;
	}
	
	public float[] getTemp(){
		float[] list=new float[size];
		for(int i=0;i<size;i++){
		list[i]=d[TEMP][i];
		}
		return list;
	}
	
	public float[] getTemp(int[] n){
		float[] list=new float[n.length];
		int j=0;
		for(int i=0;i<n.length;i++){
			list[j]=d[TEMP][i];
			j++;
		}
		return list;
	}
}
