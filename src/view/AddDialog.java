package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddDialog extends MainFrame{

	public static JDialog addDialog;
	public static JButton addRowButton = new JButton("Добавить");
	
	static JLabel [] labels = {
			new JLabel("ФИО"),
			new JLabel("Состав"),
			new JLabel("Позиция"),
			new JLabel("Титулы"),
			new JLabel("Вид спорта"),
			new JLabel("Разряд")
	};
		
	public static JTextField [] fields = {
			new JTextField(),
			new JTextField(),
			new JTextField(),
			new JTextField(),
			new JTextField(),
			new JTextField()
	};
	
	public static void create() {
		addDialog = new JDialog(frame, "Добавление записи");
		addDialog.setSize(500, 430);
		addDialog.setVisible(true);
		addDialog.setLocationRelativeTo(null);			
				
		for(int i=0; i<labels.length; i++) {
			addDialog.add(labels[i]);
			labels[i].setBounds(30, 30 + i*50, 100, 30);
			
			addDialog.add(fields[i]);
			fields[i].setBounds(150, 30 + i*50, 250, 30);
		}
		
		addRowButton.setBounds(150, 330, 100, 30);
		addDialog.add(addRowButton);
		
	}
}
