package Class;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer{ 
	
	public ArrayList<Color> colors;
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
{ 
    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
    if (! table.isRowSelected(row))
	{
	    if(column == 0)
	        c.setBackground(colors.get(row));
	    else
	        c.setBackground(table.getBackground());
	}
	return c;
} 

} 
