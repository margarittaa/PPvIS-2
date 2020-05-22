package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DeleteDialog extends MainFrame{

	public static JDialog deleteDialog = new JDialog(frame, "Удаление записей");
	public static JButton deleteButton = new JButton("Удалить");
			
	public static JTextField FIO	= new JTextField();
	public static JTextField titlesFrom	= new JTextField();
	public static JTextField titlesTo	= new JTextField();
	public static JComboBox sports = new JComboBox(sportTitles);
	public static JComboBox categories = new JComboBox(categoryTitles);
	
	public static void create() {
		deleteDialog.setSize(500, 430);
		deleteDialog.setVisible(true);
		deleteDialog.setLocationRelativeTo(null);		
		
		JLabel [] labels = {
				new JLabel("ФИО"),
				new JLabel("Титулы"),
				new JLabel("Вид спорта"),
				new JLabel("Разряд")
		};		
		
		JLabel fromLabel = new JLabel("от");
		JLabel toLabel = new JLabel("до");
		
		
		for(int i=0; i<labels.length; i++) {
			deleteDialog.add(labels[i]);
			labels[i].setBounds(30, 30 + i*50, 100, 30);
		}
		
		FIO.setBounds(150, 30, 250, 30);
		fromLabel.setBounds(150, 80, 30, 30);
		titlesFrom.setBounds(180, 80, 80, 30);
		toLabel.setBounds(290, 80, 30, 30);
		titlesTo.setBounds(320, 80, 80, 30);
		sports.setBounds(150, 130, 250, 30);
		categories.setBounds(150, 180, 250, 30);
		
		deleteButton.setBounds(150, 330, 100, 30);
		deleteDialog.add(deleteButton);
		deleteDialog.add(FIO);
		deleteDialog.add(fromLabel);
		deleteDialog.add(toLabel);
		deleteDialog.add(titlesFrom);
		deleteDialog.add(titlesTo);
		deleteDialog.add(sports);
		deleteDialog.add(categories);
		
	}
}
