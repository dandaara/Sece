package View;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import Control.CTurma;
import Model.Horario;


/*
 * Classe extra�da do seguinte link:http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
 * Data: 03/01/2013
 * 
 * */
public class ButtonEditor extends DefaultCellEditor {

	 protected JButton button;

	  private String label;
	  private JTable table;
	  private CTurma turma;
	  private VHorario VHorario=null;
	  
	  private boolean isPushed;

	  public ButtonEditor(JCheckBox checkBox, JTable table, CTurma turma) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    this.table = table;
	    this.turma = turma;
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        fireEditingStopped();
	      }
	    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value == null) ? "" : value.toString();
	    button.setText(label);
	    isPushed = true;
	    return button;
}

	
	public Object getCellEditorValue() {
	    if (isPushed) {
	      // 
	      // 

			Horario horario = (Horario)this.turma.getHorario(table.getSelectedRow(), table.getSelectedColumn());
			VHorario = new VHorario(horario);					
		
				
				VHorario.setVisible(true);				
				
				VHorario.setAlwaysOnTop(true);  
				VHorario.toFront(); 
				
			//button.setEnabled(false);
			
	      // System.out.println(label + ": Ouch!");
	    }
	    isPushed = false;
	    return new String(label);
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }



	  
}
