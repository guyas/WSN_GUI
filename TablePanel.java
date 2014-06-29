import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;


public class TablePanel extends JPanel {
	Object[][] o;
	JTable jt;
	JPanel p;
	String[] columnNames={"ID","Temperature","Average","Median", "Neighbors Number", "Cluster","Cluster 1","Cluster 2","Cluster 3"};
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	JScrollPane ssp;
	
	public TablePanel(Object[][] t){
		o=t;
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		jt=new JTable(o,columnNames);
		adjust();
		ssp=new JScrollPane(jt);
		ssp.setSize(GUI.WIDTH,120);
		p=new JPanel();
		p.add(ssp);
		add(p);
		setVisible(true);
	}
	
	private void adjust(){
		jt.setPreferredScrollableViewportSize(new Dimension(660,100));
//		jt.setFillsViewportHeight(false);
		jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		jt.getColumnModel().getColumn(0).setPreferredWidth(40);
		jt.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		jt.getColumnModel().getColumn(1).setPreferredWidth(80);
		jt.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		jt.getColumnModel().getColumn(2).setPreferredWidth(75);
		jt.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		jt.getColumnModel().getColumn(3).setPreferredWidth(75);
		jt.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		jt.getColumnModel().getColumn(4).setPreferredWidth(105);
		jt.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
		jt.getColumnModel().getColumn(5).setPreferredWidth(50);
		jt.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
		jt.getColumnModel().getColumn(6).setPreferredWidth(70);
		jt.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
		jt.getColumnModel().getColumn(7).setPreferredWidth(70);
		jt.getColumnModel().getColumn(8).setCellRenderer( centerRenderer );
		jt.getColumnModel().getColumn(8).setPreferredWidth(70);
		jt.setSize(GUI.WIDTH,100);
		
	}
	
	public void update(Object[][] t){
		for(int i=0;i<t.length;i++)
			for(int j=0;j<t[0].length;j++)
				o[i][j]=t[i][j];
		adjust();
		setVisible(true);
	}

}
