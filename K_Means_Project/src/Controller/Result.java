package Controller;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Result {
	public List<List<String>> listC(JTable table, String[][] keldata){
		List<List<String>> list2 = new ArrayList<List<String>>(keldata[0].length);
		for(int i=0;i<keldata[0].length;i++){
			List<String> list = new ArrayList<String>(keldata.length);
			for(int x=0; x<keldata.length;x++){
				if(keldata[x][i].equals("1")){
					list.add(table.getValueAt(x, 0).toString());
				}
			}
			list2.add(list);
		}
		return list2;
	}
	
	public DefaultTableModel setTable(List<List<String>> listC, String[][] keldata){
		List<String> listE;
		int maxC=0;
		
		for(int i=0; i<listC.size();i++){
			if(listC.get(i).size()>maxC){
				maxC = listC.get(i).size();
			}
		}
		
		String[][] data = new String[maxC][listC.size()];
		String[] header = new String [listC.size()];
		
		for(int i=0;i<data[0].length;i++){
			listE = listC.get(i);
			header[i] = "Cluster-" +Integer.toString(i+1); 
			for(int x=0;x<data.length;x++){
				try {
					data[x][i]=listE.get(x);
				} catch (Exception e) {
					data[x][i]="-";
				}
			}
		}
		DefaultTableModel dm = new DefaultTableModel(data, header);
		return dm;
	}
	
	public DefaultTableModel tableK(ArrayList<Color> colors){
		String[][] data = new String[colors.size()][2];
		String[] header = new String [2];
		
		for(int i=0;i<data[0].length;i++){
			header[i] = "";
			for(int x=0;x<data.length;x++){
				if(i==0){
					data[x][i]="";
				}else{
					data[x][1]= "Cluster-" + Integer.toString(x+1);
				}
				
				
			}
		}
		
		
		DefaultTableModel dm = new DefaultTableModel(data, header);
		return dm;
	}
	
	public DefaultTableCellRenderer tc(){
		DefaultTableCellRenderer re = new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (! table.isRowSelected(row))
				{
				    if(row == 2 && column == 2)
				        c.setBackground(new java.awt.Color(0, 0, 255));
				    else
				        c.setBackground(table.getBackground());
				}
				
				
				return c;
			}
		};
		
		return re;
	}
	 
}


