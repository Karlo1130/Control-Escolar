
import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Tabla extends JTable {

	private String[] columns;
	private Object[][] data;
	private DefaultTableModel table;
	
	public Tabla(Object[][] data, String[] columns) {

		this.columns = columns;
		this.data = data;
		
		//cambia el tamaño de las filas
		this.setRowHeight(40);
		this.setSelectionBackground(Color.green);
		
		//evita que puedas mover las columnas de posicion
		this.tableHeader.setReorderingAllowed(false);
		//evita que puedas cambiar el tamaño de las columnas
		this.tableHeader.setResizingAllowed(false);
		
		this.setFocusable(false);
		
		crearTabla();
		
	}
	
	//hace que las celdas no sean editables y las editables
	public boolean isCellEditable(int row, int column) {
		if(column == 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public void crearTabla(){
		table = new DefaultTableModel(data, columns);

		this.setModel(table);

		this.getColumnModel().getColumn(columns.length-1).setCellRenderer(new ImageRender());
		
	}
	
}	
