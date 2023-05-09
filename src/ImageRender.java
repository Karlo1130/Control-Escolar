
import java.awt.Color;
import java.awt.Component;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRender extends DefaultTableCellRenderer{

	public Component getTableCellRendererComponent(JTable table, Object value, 
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		if(value instanceof JLabel) {
			JLabel image = (JLabel)value;
			return image;
		}
		
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
	}
	
}
