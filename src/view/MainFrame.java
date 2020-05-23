package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainFrame {

	protected static JFrame frame;
	private static JPanel panel;
	public static DefaultTableModel model;
		
	public static JButton addButton;
	public static JButton parseButton;
	public static JButton searchButton;
	public static JButton deleteButton;
	public static JButton writeFileButton;
	
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
        Font font = new Font("", 1, 14);
        table.setFont(font);
        table.setRowHeight(30); 
                        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(40, 30, 1000, 600);
        panel.add(pane);

        parseButton = new JButton("Загрузить из XML");  
        addButton = new JButton("Добавить запись");
        deleteButton = new JButton("Удалить запись"); 
        searchButton = new JButton("Поиск");  
        writeFileButton = new JButton("Записать в файл");            
        
        panel.add(parseButton);
        panel.add(addButton); 
        panel.add(searchButton);
        panel.add(deleteButton);
        panel.add(writeFileButton);
        
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

}
