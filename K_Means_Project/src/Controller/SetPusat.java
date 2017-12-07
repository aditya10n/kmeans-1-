package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JTable;
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
	
	public int[] getIdRand(String data[][], JTable tableInitial){
		Random r = new Random();
		int[] id = new int[tableInitial.getRowCount()];
		
		 List<Integer> list = new ArrayList<Integer>(data.length);
		    for (int i=0;i<data.length;i++)
		        list.add(i);
		    Collections.shuffle(list);

		    for (int i = 0; i < id.length; i++)
		        id[i] = list.get(i);
		    Arrays.sort(id);
		return id;
	}
	
	public boolean checkSameD(int id, int[] arr){
		
		for(int i=0;i<arr.length;i++){
			if(id==arr[i]){
				return true;
			}
		}
		return false;
	}
	
	
	
}
