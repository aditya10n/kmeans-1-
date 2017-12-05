package Class;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelConn {
	
	public String[] sheet;
	public String[][] data;
	public int maxrow, maxcol;
	
	
	public ExcelConn(String alamat){
		FileInputStream inputStream;
		FileInputStream excelFile;
		Workbook workbook = null;
		try {
			excelFile = new FileInputStream(new File(alamat));
            workbook = new XSSFWorkbook(excelFile);
            
            sheet = new String[workbook.getNumberOfSheets()];
            for(int i=0; i<workbook.getNumberOfSheets(); i++){
            	sheet[i]=workbook.getSheetName(i);
            }
            
            JOptionPane.showMessageDialog(null, "File ditemukan");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Tidak dapat membuka file ERR0R(01)");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Tidak dapat membuka file ERR0R(02)");
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Tidak dapat membuka file");
			e.printStackTrace();
		}
         
	}
	
	public void maxR(int row){
		if(row>this.maxrow){
			this.maxrow=row;
		}
	}
	public void maxC(int col){
		if(col>this.maxcol){
			this.maxcol=col;
		}
	}
	
	public void setRC(String alamat, String sheet){
		int row,col;
		try {
			
	        FileInputStream excelFile = new FileInputStream(new File(alamat));
	        Workbook workbook = new XSSFWorkbook(excelFile);
	        Sheet datatypeSheet = workbook.getSheet(sheet);
	        Iterator<Row> iterator = datatypeSheet.iterator();
	        
	        row = 0;
	        while (iterator.hasNext()) {
	        	
	        	col = 0;
	            Row currentRow = iterator.next();  //System.out.println(row +","+ col);
	            currentRow.getLastCellNum();
	            Iterator<Cell> cellIterator = currentRow.iterator();
	            
	            while (cellIterator.hasNext()) {
	                Cell currentCell = cellIterator.next(); 
	               
	                if (currentCell.getCellTypeEnum() == CellType.STRING) {
	                	//System.out.println(row +","+ col);
	                	maxC(col++);
	                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
	                	//System.out.println(row +","+ col);
	                	maxC(col++);
	                }
	                
	            }maxR(++row);
	            
	
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public ExcelConn(String alamat, String sheet){
		setRC(alamat,sheet); System.out.println(maxrow +","+ maxcol);
		int row, col;
		try {
	
	        FileInputStream excelFile = new FileInputStream(new File(alamat));
	        Workbook workbook = new XSSFWorkbook(excelFile);
	        Sheet datatypeSheet = workbook.getSheet(sheet);
	        Iterator<Row> iterator = datatypeSheet.iterator();
	        
	        data = new String[maxrow+1][maxcol+1];
	        
	        row = 0;
	        while (iterator.hasNext()) {
	        	
	            Row currentRow = iterator.next();
	            currentRow.getLastCellNum();
	            Iterator<Cell> cellIterator = currentRow.iterator();
	            col = 0;
	            while (cellIterator.hasNext()) {
	            	
	                Cell currentCell = cellIterator.next(); 
	                
	                System.out.println(row+","+col); 
	                if (currentCell.getCellTypeEnum() == CellType.STRING) {
	                	data[row][col++] = currentCell.getStringCellValue();
	                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
	                	data[row][col++] = Double.toString(currentCell.getNumericCellValue());
	                }
	
	            }row++;
	            //System.out.println();
	
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
