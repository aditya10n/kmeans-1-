package Controller;

import java.awt.Checkbox;
import java.lang.instrument.ClassDefinition;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Class.ExcelConn;

public class SetData {
	private String alamat;
	private Frame.SetData main;
	private ExcelConn excel;
	private String[][] data;
	private String[] sheet;
	private int maxrow,maxcol;
	
	
	public void setAlamat(String alamat){
		this.alamat=alamat;
	}
	
	public String openFile(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
		fileChooser.setAcceptAllFileFilterUsed(false);
		
	    int open = fileChooser.showOpenDialog(null);
	    if (open == JFileChooser.APPROVE_OPTION) {
	       alamat=fileChooser.getSelectedFile().toString();
	    }
	    return alamat;
	}
	
	public String[] getSheet(String alamat){
		excel = new ExcelConn(alamat);
		sheet = excel.sheet;
		return sheet;
	}
	
	public String[][] getData(String alamat, String sheet){
		data=null;
		
		excel = new ExcelConn(alamat,sheet);
		this.data = excel.data;
		
		this.maxcol=excel.maxcol;
		this.maxrow=excel.maxrow;
		
		return data;
	}
	
	
	public DefaultTableModel table(){
		
		DefaultTableModel dm = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		for(int i=0;i<maxrow+1;i++){
			for(int s=0;s<maxcol+1;s++){
				if(data[i][s]!=null)
					data[i][s]=data[i][s];
				else
					data[i][s]="";
			}
		}
		
		String[] head=new String[maxcol+1];
		for(int i=0;i<maxcol+1;i++){
			/*if(data[0][i]!=null)
				head[i]=data[0][i];
			else*/
				head[i]="";
		}
		
		
		dm.setDataVector(data, head);
		return dm;
	}
	
	public String[][] getSelectedData(JTable table) {
		int[] selectedRow = table.getSelectedRows();
	    int[] selectedCol = table.getSelectedColumns();
	    String[][] hasil=null;
	    hasil= new String[selectedRow.length][selectedCol.length];
	    
	    for(int i=0; i<selectedRow.length;i++){
	    	for(int y=0; y<selectedCol.length;y++){
	    		hasil[i][y]= table.getValueAt(selectedRow[i],selectedCol[y]).toString();
	    		
	    	}
	    	
	    }return hasil;
	}
	
	public String fromCell(JTable table){
		int[] selectedRow = table.getSelectedRows();
	    int[] selectedCol = table.getSelectedColumns();
		return selectedCol[0]+","+selectedRow[0];
	}
	
	public String toCell(JTable table){
		int[] selectedRow = table.getSelectedRows();
	    int[] selectedCol = table.getSelectedColumns();
		return selectedCol[selectedCol.length-1]+","+selectedRow[selectedRow.length-1];
	}
	
	public String[] getAtt(JCheckBox withoutAtt, String[][] data){
		String[] att=new String[data[0].length];
		for(int i=0; i<data[0].length;i++){
			if(withoutAtt.isSelected()){
				att[i]="X" + Integer.toString(i+1);
			}else{
				att[i]=data[0][i];
			}
		}
		return att;
	}
	
	public String[][] getData(JCheckBox withoutAtt,String[][] data, JTable table){
		int[] selectedRow = table.getSelectedRows();
	    int[] selectedCol = table.getSelectedColumns();
	    String[][] hasil;
		if(withoutAtt.isSelected()){
			hasil=new String[selectedCol.length+1][selectedRow.length];
			for(int i=0; i<hasil.length;i++){
				for(int x=0; x<hasil[0].length;x++){
					if(x==0){
						hasil[i][x] = "Y" + Integer.toString(x+1);
					}else{
						System.out.println(selectedCol[i]+","+selectedRow[x]);
						hasil[i][x]= data[selectedCol[i]][selectedRow[x]];
					}
				}
			}
		}else{
			hasil=new String[selectedRow.length-1][selectedCol.length];
			for(int i=1; i<selectedRow.length;i++){
				for(int x=0; x<selectedCol.length;x++){
					hasil[i-1][x]=data[i][x];
				}
			}
		}
		
		
		return hasil;
	}
	
}
