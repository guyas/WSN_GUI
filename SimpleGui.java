import javax.swing.*;

public class SimpleGui extends JFrame{
	private static final int MAX_NEIGHBORS_NUM = 21;
	private static final int PANEL_WIDTH = 120;
	private static final int PANEL_HEIGHT = 200;
	
	// GUI elements
	private JFrame _frame;
	private JPanel[] _motePanels;
	private JLabel[] _moteTitles, _tempTitles, _medTitles, _avgTitles;
	private JLabel[] _tempValues, _medValues, _avgValues;
	
	private int _numOfMotes;
	
	public SimpleGui()
	{
		init();
	}
	
	private void init()
	{
		_numOfMotes = 0;
		_frame = new JFrame("Sensor network data");
		_frame.setSize(800,600);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_motePanels = new JPanel[MAX_NEIGHBORS_NUM+1];
		_moteTitles = new JLabel[MAX_NEIGHBORS_NUM+1];
		_tempTitles = new JLabel[MAX_NEIGHBORS_NUM+1];
		_medTitles = new JLabel[MAX_NEIGHBORS_NUM+1];
		_avgTitles = new JLabel[MAX_NEIGHBORS_NUM+1];
		_tempValues = new JLabel[MAX_NEIGHBORS_NUM+1];
		_medValues = new JLabel[MAX_NEIGHBORS_NUM+1];
		_avgValues = new JLabel[MAX_NEIGHBORS_NUM+1];
		for(int i=1;i<=MAX_NEIGHBORS_NUM;i++)
		{
			_motePanels[i] = new JPanel();
			_motePanels[i].setLayout(new BoxLayout(_motePanels[i],BoxLayout.PAGE_AXIS));
			_motePanels[i].setSize(PANEL_WIDTH,PANEL_HEIGHT);
		//	_motePanels[i].setLocation((i <= 10) ? (i-1)*PANEL_WIDTH : (i-11)*PANEL_WIDTH,
		//			(i <= 10) ? 0 : PANEL_HEIGHT);
			
			_moteTitles[i] = new JLabel("Mote " + i);
			_tempTitles[i] = new JLabel("Temperature:");
			_tempValues[i] = new JLabel("0");
			_medTitles[i] = new JLabel("Median:");
			_medValues[i] = new JLabel("0");
			_avgTitles[i] = new JLabel("Average:");
			_avgValues[i] = new JLabel("0");
			
			_tempValues[i].setVisible(true);
			_medValues[i].setVisible(true);
			
			_motePanels[i].add(_moteTitles[i]);
			_motePanels[i].add(_tempTitles[i]);
			_motePanels[i].add(_tempValues[i]);
			_motePanels[i].add(_medTitles[i]);
			_motePanels[i].add(_medValues[i]);
			_motePanels[i].add(_avgTitles[i]);
			_motePanels[i].add(_avgValues[i]);
			_motePanels[i].setVisible(false);
			_frame.add(_motePanels[i]);
		}
		_frame.setVisible(true);
	}
	
	public void setMeasuredTemperature(int moteId, float temperature)
	{
		_tempValues[moteId].setText(String.valueOf(temperature));
		makeVisible(_tempValues[moteId], _motePanels[moteId]);
	}
	
	public void setMedian(int moteId, float median)
	{
		_medValues[moteId].setText(String.valueOf(median));
		makeVisible(_medValues[moteId], _motePanels[moteId]);
	}
	
	public void setSignAverage(int moteId, float signAvg)
	{
		
	}
	
	public void setAvgIterations(int moteId, float avgIters)
	{
		
	}
	
	public void setMedIterations(int moteId, float medIters)
	{
		
	}
	
	public void setEpsilon(int moteId, float epsilon)
	{
		
	}
	
	public void reportLost(int moteId)
	{
		_motePanels[moteId].setVisible(false);
		_numOfMotes--;
		align();
	}
	
	private void makeVisible(JLabel label, JPanel panel)
	{
		if(!label.isVisible() || !panel.isVisible())
		{
			_numOfMotes++;
			if(_numOfMotes <=10)
			{
				panel.setLocation(PANEL_WIDTH*(_numOfMotes-1),0);
			}
			else
			{
				panel.setLocation(PANEL_WIDTH*(_numOfMotes-11),PANEL_HEIGHT);
			}
			panel.setVisible(true);
			label.setVisible(true);
		}
	}
	
	private void align()
	{
		int moteCount = 0;
		for(int i=1;i<MAX_NEIGHBORS_NUM;i++)
		{
			if(_motePanels[i].isVisible())
			{
				moteCount++;
				if(moteCount <= 10)
				{
					_motePanels[i].setLocation(PANEL_WIDTH*(moteCount-1), 0);
				}
				else
				{
					_motePanels[i].setLocation(PANEL_WIDTH*(moteCount-1), 0);
				}
			}
		}
	}
	
	public static void main3(String[] args) throws InterruptedException
	{
		SimpleGui gui = new SimpleGui();
		gui.setMeasuredTemperature(3, 30);
		gui.setMedian(3, 40);
		gui.setMeasuredTemperature(6, 30);
		gui.setMedian(6, 40);
		Thread.sleep(2000);
//		gui.reportLost(3);
	}
}