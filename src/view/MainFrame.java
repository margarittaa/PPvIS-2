package view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Data;
import model.Sportsman;

public class MainFrame {

	protected static JFrame frame;
	protected static JPanel panel;
	public static DefaultTableModel model;
		
	public static JButton addButton = new JButton("Добавить запись");
	public static JButton parseButton = new JButton("Загрузить из XML");  
	public static JButton searchButton = new JButton("Поиск");  
	public static JButton deleteButton = new JButton("Удалить запись"); 
	public static JButton writeFileButton = new JButton("Записать в файл");  
	
	public static JButton firstPageButton = new JButton("Первая");  
	public static JButton lastPageButton = new JButton("Последняя");  
	public static JButton nextButton = new JButton(">");  
	public static JButton previousButton = new JButton("<");  
	public static JButton okButton = new JButton("OK");
	
	private static JScrollPane pane = new JScrollPane();
	private static JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 2, 15, 1));
	private static JLabel pageLabel;
	
	protected static String[] sportTitles = {
			"",
		    "футбол",
		    "баскетбол",
		    "волейбол",
		    "хоккей",
		    "гандбол"
	};
	
	protected static String[] categoryTitles = {
			"",
		    "1-й юношеский",
		    "2-й разряд",
		    "3й-разряд",
		    "кмс",
		    "мастер спорта"
	};
	
	
	public static void CreateGUI() {
		frame = new JFrame("Спортсмены");		
    	panel = new JPanel();
	    panel.setLayout(null);
	    
	    Object[] columns = {"ФИО", "Состав", "Позиция", "Титулы", "Вид спорта", "Разряд"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);  
        JTable table = new JTable(model);

        table.setFont(new Font("", 7, 14));
        table.setRowHeight(30); 
                           
        JLabel label = new JLabel("Записей на странице: ");
        pageLabel = new JLabel("0 / 0");
        pageLabel.setFont(new Font("", 1, 16));
        
        pane = new JScrollPane(table);
        pane.setBounds(40, 100, 1000, 400);
        panel.add(pane);
          
        Component[] components = new Component[] {label, spinner, pageLabel, okButton, firstPageButton, lastPageButton, nextButton, previousButton, parseButton, addButton, searchButton, deleteButton, writeFileButton};
        for(Component component : components)
        	panel.add(component);
        
        firstPageButton.setBounds(50, 40, 80, 30);
        previousButton.setBounds(150, 40, 50, 30);
    	nextButton.setBounds(220, 40, 50, 30);
    	lastPageButton.setBounds(290, 40, 110, 30);
    	label.setBounds(500, 40, 140, 30);
    	spinner.setBounds(650, 40, 40, 30);
    	okButton.setBounds(700, 40, 55, 30);
    	pageLabel.setBounds(1000, 40, 50, 30);
        
        parseButton.setBounds(50, 700, 160, 30);
        addButton.setBounds(250, 700, 160, 30);
        deleteButton.setBounds(450, 700, 160, 30);
        searchButton.setBounds(650, 700, 160, 30);
        writeFileButton.setBounds(850, 700, 160, 30);
        
        frame.setFocusable(true);
		frame.setSize(1100,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(panel);	 
		frame.setVisible(true);
	    frame.setLocationRelativeTo(null); 
	}
	
		
	public static void setPageLabel(int currentPage, int pages) {
		pageLabel.setText(currentPage + " / " + pages);
	}
	
	public static int getRowsOnPage() {
		return (int) spinner.getValue();
	}
	
	public static void setScrollPaneHeight(int height) {
		pane.setBounds(40, 100, 1000, height);
	}
	 

}
