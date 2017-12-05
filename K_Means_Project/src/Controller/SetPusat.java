package Controller;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class SetPusat {
	String[] cluster, atribut;
	String[][] data;
		
	public DefaultTableModel tableData(String[] atribut,String[][] data){
		DefaultTableModel dm = new DefaultTableModel(data, atribut){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return dm;
	}
	
	public DefaultTableModel tableInit(int row, String[] atribut){
		DefaultTableModel dm = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
            }
		};
		String[] atribut1 = new String[atribut.length];
		for(int i=0;i<atribut.length;i++){
			if(i==0){
				atribut1[i]="Cluster";
			}else{
				atribut1[i]=atribut[i];
			}
			
		}
		
		String[][] data0 = new String[row][atribut.length];
		for(int i=0;i<row;i++){
			for(int x=0;x<atribut.length;x++){
				data0[i][0] = "C"+Integer.toString(i+1);
			}
		}
		
		dm.setDataVector(data0, atribut1);
		return dm;
	}
	
	
	
}
