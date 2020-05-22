package controller;

import javax.swing.table.DefaultTableModel;

import model.Data;
import model.Sportsman;
import view.*;

public class Controller {

	public static void main(String[] args) { 		
		MainFrame.CreateGUI();		
		MainFrame.parseButton.addActionListener(Listeners.parseListener);		
		MainFrame.writeFileButton.addActionListener(Listeners.writeFileListener);	
		MainFrame.addButton.addActionListener(Listeners.addListener);	
		MainFrame.searchButton.addActionListener(Listeners.searchListener);
		MainFrame.deleteButton.addActionListener(Listeners.deleteListener);
		
		AddDialog.addRowButton.addActionListener(Listeners.addRowListener);
		SearchDialog.searchButton.addActionListener(Listeners.searchRowsListener);
		DeleteDialog.deleteButton.addActionListener(Listeners.deleteRowsListener);
	}
	
	 public static void pushDataToModel(Sportsman s, DefaultTableModel model) {	
			Object[] row = new Object[6];
	        row[0] = s.getFIO();
	        row[1] = s.getComposition();
	        row[2] = s.getPosition();
	        row[3] = s.getTitles();
	        row[4] = s.getSport();
	        row[5] = s.getCategory();
	        model.addRow(row);			
	}

	 static void refreshTableModel(DefaultTableModel model) {
		model.setRowCount(0);
     	Data.sportsmenList.forEach((s) -> {Controller.pushDataToModel(s, model);});
	 }
	
	
}
