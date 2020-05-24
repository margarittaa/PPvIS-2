package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Data;
import view.MainFrame;

public class PageController {

	static int currentPage = 1;	
	static int rowsOnPage;
	static int pages;
	
	private static void calculations() {
		rowsOnPage = MainFrame.getRowsOnPage();
		pages =  Data.sportsmenList.size() / rowsOnPage;
		if(Data.sportsmenList.size() % rowsOnPage != 0) pages++;
	}
	
	public static void refreshTableModel() {
		calculations();
		MainFrame.model.setRowCount(0);
		MainFrame.setPageLabel(currentPage, pages);
		
		for(int i = (currentPage - 1)*rowsOnPage; i < (currentPage - 1)*rowsOnPage + rowsOnPage && i < Data.sportsmenList.size(); i++) {
			Controller.pushDataToModel(Data.sportsmenList.get(i), MainFrame.model);
		}
		
		MainFrame.setScrollPaneHeight(23 + rowsOnPage*30 );				
	}
	
	
	static void startPageControl() {
		
		MainFrame.firstPageButton.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {   
	        	currentPage = 1;
	        	refreshTableModel();
	        }
	    });
		
		MainFrame.lastPageButton.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {   
	        	currentPage = pages;
	        	refreshTableModel();
	        }
	    });
		
		MainFrame.nextButton.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {   
	        	if(currentPage < pages) {
	        		currentPage++;
	        		refreshTableModel();
	        	}	        	
	        }
	    });
		
		MainFrame.previousButton.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {   
	        	if(currentPage > 1) {
	        		currentPage--;
	        		refreshTableModel();
	        	}	        	
	        }
	    });
		
		MainFrame.okButton.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {    
	        	//calculations();
	        	refreshTableModel();
	        }
	    });
	}
	
	
}
