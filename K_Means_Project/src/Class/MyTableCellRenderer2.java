package Class;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer2 extends DefaultTableCellRenderer{ 
	
	public ArrayList<Color> colors;
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
{ 
    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
    if (! table.isRowSelected(row))
	{
	    if(value=="1")
	        c.setBackground(Color.GREEN);
	    else
	        c.setBackground(table.getBackground());
	}
	return c;
} 
} 
