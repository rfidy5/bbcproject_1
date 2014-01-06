package chrono_views;

import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {
	
	Object [] [] donnees = {};
	String [] entetes;
	public ModelTable(Object [][] _donnees, String [] _entetes){
		this.donnees = _donnees;
		this.entetes= _entetes;
	}

	@Override
	public int getColumnCount() {
		 return donnees[0].length;
	}

	@Override
	public int getRowCount() {
	return donnees.length;
	}

	@Override
	public Object getValueAt(int param1, int param2) {
		return donnees[param1][param2];
	}

	
	  public String getColumnName(int col){ 
	      return entetes[col]; 
	   } 
}
