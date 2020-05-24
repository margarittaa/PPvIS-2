package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import model.*;
import view.*;

public class Listeners {
	
	static File currentDirectory = new File(System.getProperty("user.dir"));
	static JFileChooser fileChooser = new JFileChooser(currentDirectory); 
	
	static ActionListener parseListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {  
        	File file = null;
        	int dialog = fileChooser.showOpenDialog(null);
        	if(dialog == JFileChooser.APPROVE_OPTION) 
        		file = fileChooser.getSelectedFile();
        	try {
				SAXparser.mainSAXParser(file);
			} catch (ParserConfigurationException | SAXException | IOException e1) {
				e1.printStackTrace();
			}
        	PageController.refreshTableModel();
        }
    };
	
	static ActionListener writeFileListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {     
        	File file = null;
        	int dialog = fileChooser.showSaveDialog(null);
        	if(dialog == JFileChooser.APPROVE_OPTION) 
        		file = fileChooser.getSelectedFile();
        	try {
				DOMwriter.mainDOMwriter(file);
			} catch (ParserConfigurationException | TransformerFactoryConfigurationError
					| TransformerException e1) {
				e1.printStackTrace();
			}
        }
    };
    
    static ActionListener addListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {             
        	AddDialog.create();
        }
        
    };
    
    static ActionListener deleteListener  = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {             
        	DeleteDialog.create();        	
        }
    };
    
    static ActionListener searchListener  = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {             
        	SearchDialog.create();        	
        }
    };
    
    static ActionListener addRowListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {             
        	        	
        	for(int i = 0; i < AddDialog.fields.length; i++) {
        		if(AddDialog.fields[i].getText().equals("")){
        			JOptionPane.showMessageDialog(AddDialog.addDialog, "Заполните все поля!");
        			return;
        		}
        	}
        	
        	Sportsman man = new Sportsman();
        	man.setFIO(AddDialog.fields[0].getText());
        	man.setComposition(AddDialog.fields[1].getText());
        	man.setPosition(AddDialog.fields[2].getText());
        	man.setTitles(Integer.parseInt(AddDialog.fields[3].getText()));
        	man.setSport(AddDialog.fields[4].getText());
        	man.setCategory(AddDialog.fields[5].getText());
        	
        	Data.sportsmenList.add(man);
        	PageController.refreshTableModel();
        	
        }
    };
    
    static ActionListener searchRowsListener = new ActionListener(){
    	boolean wrongInput;
    	
        @Override
        public void actionPerformed(ActionEvent e) {             
        	   
        	ArrayList<Sportsman> searchResult = new ArrayList<>();
        	
        	String FIO = SearchDialog.FIO.getText();
        	String titlesFrom = SearchDialog.titlesFrom.getText();
        	String titlesTo = SearchDialog.titlesTo.getText();
        	String sport = (String)SearchDialog.sports.getSelectedItem();
        	String category = (String)SearchDialog.categories.getSelectedItem();
        	
        	wrongInput = false;
                	
        	Data.sportsmenList.forEach(s -> {
        		if((!FIO.isEmpty() || !sport.isEmpty())
        			&& titlesFrom.isEmpty() && titlesTo.isEmpty() && category.isEmpty()) {
        			if(FIO.equals(s.getFIO()) || sport.equals(s.getSport()))
        				searchResult.add(s);
        		}
        		else if(!titlesFrom.isEmpty() && !titlesTo.isEmpty() && category.isEmpty() && FIO.isEmpty() && sport.isEmpty()) {
        			if(s.getTitles() > Integer.parseInt(titlesFrom) && s.getTitles() < Integer.parseInt(titlesTo))
        				searchResult.add(s);
        		}
        		else if((!FIO.isEmpty() || !category.isEmpty())
            			&& titlesFrom.isEmpty() && titlesTo.isEmpty() && sport.isEmpty()) {
        				if(FIO.equals(s.getFIO()) || category.equals(s.getCategory()))
        				searchResult.add(s);
        		}
        		else wrongInput  = true;
        		
        	});
        	
        	if(!wrongInput) {
        		SearchDialog.showSearchResult(searchResult);
        	}
        	else {
        		JOptionPane.showMessageDialog(SearchDialog.searchDialog, "Условия поиска:\r\n" + 
    					"-	по фио или виду спорта;\r\n" + 
    					"-	по количеству завоеваний титула;\r\n" + 
    					"-	по фио или разряду.");
        	}
        }
    };
    
    static ActionListener deleteRowsListener = new ActionListener(){
    	boolean wrongInput;
    	int counter;
    	
        @Override
        public void actionPerformed(ActionEvent e) {   
        	String FIO = DeleteDialog.FIO.getText();
        	String titlesFrom = DeleteDialog.titlesFrom.getText();
        	String titlesTo = DeleteDialog.titlesTo.getText();
        	String sport = (String)DeleteDialog.sports.getSelectedItem();
        	String category = (String)DeleteDialog.categories.getSelectedItem();
        	
        	wrongInput = false;
        	counter = 0;
        	
        	for(int i = 0; i < Data.sportsmenList.size(); i++){
        		Sportsman s = Data.sportsmenList.get(i);
        		if((!FIO.isEmpty() || !sport.isEmpty())
        			&& titlesFrom.isEmpty() && titlesTo.isEmpty() && category.isEmpty()) {
        			if(FIO.equals(s.getFIO()) || sport.equals(s.getSport())) {
        				Data.sportsmenList.remove(i);
        				counter++;
        			}
        		}
        		else if(!titlesFrom.isEmpty() && !titlesTo.isEmpty() && category.isEmpty() && FIO.isEmpty() && sport.isEmpty()) {
        			if(s.getTitles() > Integer.parseInt(titlesFrom) && s.getTitles() < Integer.parseInt(titlesTo)) {
        				Data.sportsmenList.remove(i);
        				counter++;
        			}
        		}
        		else if((!FIO.isEmpty() || !category.isEmpty())
            			&& titlesFrom.isEmpty() && titlesTo.isEmpty() && sport.isEmpty()) {
        				if(FIO.equals(s.getFIO()) || category.equals(s.getCategory())) {
        					Data.sportsmenList.remove(i);
            				counter++;
        				}
        		}
        		else wrongInput  = true;
        		
        	};
        	        
        	if(!wrongInput) {
            	PageController.refreshTableModel();
        		if(counter == 0)
        			JOptionPane.showMessageDialog(DeleteDialog.deleteDialog, "Записи с указанными фильтрами не найдены");
        		else 
        			JOptionPane.showMessageDialog(DeleteDialog.deleteDialog, "Удалено " + counter + " записей");
        	}
        	else {
        		JOptionPane.showMessageDialog(DeleteDialog.deleteDialog, "Условия поиска и удаления:\r\n" + 
    					"-	по фио или виду спорта;\r\n" + 
    					"-	по количеству завоеваний титула;\r\n" + 
    					"-	по фио или разряду.");
        	}
        	
        }
    };
    
			

   
    
}
