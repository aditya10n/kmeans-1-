package Controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Iterasi {
	
	public DefaultTableModel clusterT(String[] att, String[][] dc){
		String[] att1 = new String[att.length];
		att1[0]="";
		for(int i=1; i<att.length;i++){
			att1[i]=att[i];
		}
		
		for(int i=0;i<dc.length;i++){
			for(int y=1;y<dc[0].length;y++){
				dc[i][y]=String.format("%.3f",Double.parseDouble(dc[i][y]));
			}
		}
		DefaultTableModel dm = new DefaultTableModel(dc,att1);
		
		return dm;
	}
	
	public double[][] hitungJarakP(String[][] data, String[][] dc){
		double[][] hasil= new double[data.length][dc.length];
		
		for(int i=0;i<data.length;i++){
			for(int x=1;x<data[0].length;x++){
				
				hasil[i][x-1] =0.0;
				for(int y=1;y<dc.length+1;y++){
					hasil[i][x-1] += (Math.pow((Double.parseDouble(data[i][y]) - Double.parseDouble(dc[(x-1)%dc.length][y])),2));
					//System.out.println("(("+data[i][y]+"-"+dc[(x-1)%dc.length][y]+")^2) +");
				}
				hasil[i][x-1] = Math.sqrt(hasil[i][x-1]);
				//System.out.println("hasil ke"+i+","+Integer.toString(x-1)+":" + hasil[i][x-1]);
			}
		}
		
		return hasil;
	}
	
	public String[][] kelompokData(double[][] hasil){
		String[][] hasilK=new String[hasil.length][hasil[0].length];
		for(int i=0; i<hasilK.length;i++){
			for(int x=0;x<hasilK[0].length;x++){
				if(hasil[i][x]==terkecil(hasil[i].clone())){
					hasilK[i][x]= "1";
				}else{
					hasilK[i][x]= "0";
				}
				//System.out.println(i+","+x+":"+hasilK[i][x]);
			}
		}
		
		return hasilK;
	}
	
	
	
	public double terkecil(double[] arr){
		double kecil=arr[0];
		
		for(int i=1;i<arr.length;i++){
			if(arr[i]<kecil){
				kecil = arr[i];
			}
		}
		
		return kecil;
	}
	
	public DefaultTableModel dataIt(String[] att, String[][] data, String[][] dc, double[][] hasil, String[][] keldata){
		String[] att1 = new String[att.length+dc.length+dc.length];
		att1[0]="";
		for(int i=1; i<att1.length;i++){
			if(i<att.length){
				att1[i]=att[i];
			}else if((i>=att.length)&&(i<att.length+dc.length)){
				
				att1[i]="Centroid" + Integer.toString((i-att.length)%dc.length+1);
				
			}else{
				att1[i]=dc[(i-att.length)%dc.length][0];
			}
			
		}
		
		String[][] fulldata= new String[data.length][data[0].length+hasil[0].length+keldata[0].length];
		for(int i=0; i<fulldata.length;i++){
			for(int x=0; x<fulldata[0].length;x++){
				
				if(x<att.length){
					fulldata[i][x] = data[i][x];
				}else if((x>=att.length)&&(x<att.length+dc.length)){
					fulldata[i][x] = String.format("%.3f",(hasil[i][(x-att.length)%dc.length]));
				}else{
					fulldata[i][x] = keldata[i][(x-att.length)%dc.length];
				}
				
			}
		}
		
		DefaultTableModel dm = new DefaultTableModel(fulldata,att1);
		
		return dm;
	}
	public String[][] pusatBaru(String[][] keldata, String[][] data){
		String[][] hasilP = new String[keldata[0].length][data[0].length];
		
		for(int i=0;i<hasilP.length;i++){
			hasilP[i][0]= "C"+Integer.toString(i+1);
		}
		
		
		for(int i=0;i<hasilP.length;i++){
			for(int x=1; x<hasilP[0].length;x++){
				double hasilSum=0;
				int jumlah =0;
				for(int y=0; y<keldata.length;y++){
					if(keldata[y][i].equals("1")){
						hasilSum += Double.parseDouble(data[y][x]);
						//System.out.println(data[y][x]+"jumlah"+jumlah);
						jumlah++;
					}
				}
				hasilP[i][x] = Double.toString(hasilSum / jumlah);
				//System.out.println(hasilP[i][x]+"("+i+","+x+")");
			}
		}
		return hasilP;
	}
	
	/*public String[][] getDataTable(JTable table){
		String[][] data = new String[table.getRowCount()][table.getColumnCount()];
		for(int i=0; i<data.length;i++){
			for(int x=0; x<data[0].length;x++){
				data[i][x]= table.getValueAt(i, x).toString();
			}
			
		}
		return data;
	}*/
	
	public boolean checkStop(String[][] a, String[][] b){
		int hasil=0;
		for(int i=0;i<a.length;i++){
			for(int y=1;y<a[0].length;y++){
				if(a[i][y].equals(b[i][y])){
					hasil++;
					System.out.println(a[i][y]+" dgn "+b[i][y]);
				}
			}
		}
		if(hasil==0){
			return true;
		}else{
			return false;
		}
	}
	
	

}
