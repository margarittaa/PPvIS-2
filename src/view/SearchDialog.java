package view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Sportsman;

public class SearchDialog extends MainFrame{

	public static JDialog searchDialog = new JDialog(frame, "Поиск записей");
	public static JButton searchButton = new JButton("Найти");
		
	public static JTextField FIO	= new JTextField();
	public static JTextField titlesFrom	= new JTextField();
	public static JTextField titlesTo	= new JTextField();
	public static JComboBox sports = new JComboBox(sportTitles);
	public static JComboBox categories = new JComboBox(categoryTitles);
	
	public static void create() {
		searchDialog.setSize(500, 430);
		searchDialog.setVisible(true);
		searchDialog.setLocationRelativeTo(null);		
		
		JLabel [] labels = {
				new JLabel("ФИО"),
				new JLabel("Титулы"),
				new JLabel("Вид спорта"),
				new JLabel("Разряд")
		};		
		
		JLabel fromLabel = new JLabel("от");
		JLabel toLabel = new JLabel("до");
		
		
		for(int i=0; i<labels.length; i++) {
			searchDialog.add(labels[i]);
			labels[i].setBounds(30, 30 + i*50, 100, 30);
		}
		
		FIO.setBounds(150, 30, 250, 30);
		fromLabel.setBounds(150, 80, 30, 30);
		titlesFrom.setBounds(180, 80, 80, 30);
		toLabel.setBounds(290, 80, 30, 30);
		titlesTo.setBounds(320, 80, 80, 30);
		sports.setBounds(150, 130, 250, 30);
		categories.setBounds(150, 180, 250, 30);
		
		searchButton.setBounds(150, 330, 100, 30);
		searchDialog.add(searchButton);
		searchDialog.add(FIO);
		searchDialog.add(fromLabel);
		searchDialog.add(toLabel);
		searchDialog.add(titlesFrom);
		searchDialog.add(titlesTo);
		searchDialog.add(sports);
		searchDialog.add(categories);
		
	}
	
	public static void showSearchResult(ArrayList<Sportsman> arr) {
		JDialog resultDialog = new JDialog(frame, "Поиск записей");
		resultDialog.setSize(900, 430);
		resultDialog.setVisible(true);
		resultDialog.setLocationRelativeTo(null);	
		
		Object[] columns = {"ФИО", "Состав", "Позиция", "Титулы", "Вид спорта", "Разряд"};
		DefaultTableModel searchModel = new DefaultTableModel();
		searchModel.setColumnIdentifiers(columns);  
        JTable table = new JTable(searchModel);
        Font font = new Font("", 1, 14);
        table.setFont(font);
        table.setRowHeight(30); 
                        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 0, 800, 400);
        resultDialog.add(pane);
        
        arr.forEach((item) -> {
        	Controller.pushDataToModel(item, searchModel);
        });
	}
}
