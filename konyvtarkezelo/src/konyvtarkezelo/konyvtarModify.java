package konyvtarkezelo;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class konyvtarModify extends JDialog {
	private JTable table;
	private konyvtarTable konyvtartm;
	private Checker checker=new Checker();
	private DBMethods dbmethods=new DBMethods();
	private JTextField textField_title;
	private JTextField textField_author;
	private JTextField textField_releasedate;
	private JTextField textField_status;
	private JTextField textField_isbn;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					konyvtarList dialog = new konyvtarList();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the dialog.
	 */
	public konyvtarModify(JFrame f, konyvtarTable bktm) {
		super(f, "Könyv módosítása", true);
		konyvtartm=bktm;
		
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(null);
		
		JButton btnClose = new JButton("Bez\u00E1r");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(335, 227, 89, 23);
		getContentPane().add(btnClose);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 165);
		getContentPane().add(scrollPane);
		
		table = new JTable(konyvtartm);
		scrollPane.setViewportView(table);
		
		TableColumn tablecolumn=null;
		for(int i=0;i<6;i++){
			tablecolumn=table.getColumnModel().getColumn(i);
			if(i==0) {
				tablecolumn.setPreferredWidth(30);
			}else if(i==1) {
				tablecolumn.setPreferredWidth(250);
			}else {
				tablecolumn.setPreferredWidth(400);
			}
		}
		
		table.setAutoCreateRowSorter(true);
		
		JButton btnModify = new JButton("Adatsor m\u00F3dos\u00EDt\u00E1s");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count=0, chacked=0, x=0;
				for(x=0; x<konyvtartm.getRowCount();x++)
					if((Boolean)konyvtartm.getValueAt(x, 0)) {
						count++;
						chacked=x;
					}
				if(count==0) {
					checker.SendMessage("Nincs kijelölve módosítandó rekord",0);
				}
				if(count>1) {
					checker.SendMessage("Maximum egy rekord módosítható egyszerre!", 0);
				}
				if(count==1) {
					if(testingModifyData()>0) {
						boolean okay=true;
						if(checker.filled(textField_isbn, "ISBN")) okay=checker.testInt(textField_isbn, "ISBN");
						if(okay) {
							String isbn=konyvtartm.getValueAt(chacked, 1).toString();
							dbmethods.DBConnect();
							if(checker.filled(textField_isbn, "ISBN")) dbmethods.UpdateData(isbn, "isbn", checker.ReadTextField(textField_isbn));
							if(checker.filled(textField_title, "Title")) dbmethods.UpdateData(isbn, "title", checker.ReadTextField(textField_title));
							if(checker.filled(textField_author, "Author")) dbmethods.UpdateData(isbn, "author", checker.ReadTextField(textField_author));
							if(checker.filled(textField_releasedate, "Release date")) dbmethods.UpdateData(isbn, "releasedate", checker.ReadTextField(textField_releasedate));
							if(checker.filled(textField_status, "Status")) dbmethods.UpdateData(isbn, "status", checker.ReadTextField(textField_status));
							dbmethods.DBDisconnect();
							
							
							if(checker.filled(textField_isbn, "ISBN")) konyvtartm.setValueAt(checker.stringToInt(checker.ReadTextField(textField_isbn)), chacked, 1);
							if(checker.filled(textField_title, "Title")) konyvtartm.setValueAt(checker.ReadTextField(textField_title), chacked, 2);
							if(checker.filled(textField_author, "Author")) konyvtartm.setValueAt(checker.ReadTextField(textField_author), chacked, 3);
							if(checker.filled(textField_releasedate, "Release date")) konyvtartm.setValueAt(checker.ReadTextField(textField_releasedate), chacked, 4);
							if(checker.filled(textField_status, "Status")) konyvtartm.setValueAt(checker.ReadTextField(textField_status), chacked, 5);
							checker.SendMessage("A rekord módosítva", 1);
							resetModifyData(chacked);
						}
					}else {
						checker.SendMessage("Nincs kitöltve egyetlen módosítandó mezõ sem!", 0);
					}
				}
						
			}
		});
		btnModify.setBounds(20, 227, 147, 23);
		getContentPane().add(btnModify);
		
		textField_title = new JTextField();
		textField_title.setBounds(107, 187, 112, 20);
		getContentPane().add(textField_title);
		textField_title.setColumns(10);
		
		textField_author = new JTextField();
		textField_author.setBounds(221, 187, 110, 20);
		getContentPane().add(textField_author);
		textField_author.setColumns(10);
		
		textField_releasedate = new JTextField();
		textField_releasedate.setBounds(333, 187, 110, 20);
		getContentPane().add(textField_releasedate);
		textField_releasedate.setColumns(10);
		
		textField_status = new JTextField();
		textField_status.setBounds(445, 187, 110, 20);
		getContentPane().add(textField_status);
		textField_status.setColumns(10);
		
		textField_isbn = new JTextField();
		textField_isbn.setBounds(32, 187, 73, 20);
		getContentPane().add(textField_isbn);
		textField_isbn.setColumns(10);
		TableRowSorter<konyvtarTable> tablerowsorter=(TableRowSorter<konyvtarTable>)table.getRowSorter();
		tablerowsorter.setSortable(0, false);
		
	}
	
	public int testingModifyData() {
		int flag=0;
		if(checker.filled(textField_isbn, "ISBN")) flag++;
		if(checker.filled(textField_title, "Cím")) flag++;
		if(checker.filled(textField_author, "Szerzõ")) flag++;
		if(checker.filled(textField_releasedate, "Dátum")) flag++;
		if(checker.filled(textField_status, "Státusz")) flag++;
		return flag;
	}
	
	public void resetModifyData(int i) {
		textField_isbn.setText("");
		textField_title.setText("");
		textField_author.setText("");
		textField_releasedate.setText("");
		textField_status.setText("");
		konyvtartm.setValueAt(false, i, 0);
	}
}
