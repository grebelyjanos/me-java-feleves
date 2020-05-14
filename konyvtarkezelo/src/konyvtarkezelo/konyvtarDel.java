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

public class konyvtarDel extends JDialog {
	private JTable table;
	private konyvtarTable konyvtartm;
	private Checker checker=new Checker();
	private DBMethods dbmethods=new DBMethods();
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
	public konyvtarDel(JFrame f, konyvtarTable bktm) {
		super(f, "Könyv törlése", true);
		konyvtartm=bktm;
		
		setBounds(100, 100, 450, 300);
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
		scrollPane.setBounds(10, 11, 414, 205);
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
		
		JButton btnDelete = new JButton("Adatsor t\u00F6rl\u00E9se");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count=0, chk=0, x=0;
				for(x=0; x<konyvtartm.getRowCount();x++)
					if((Boolean)konyvtartm.getValueAt(x, 0)) {
						count++;
						chk=x;
					}
				if(count==0) {
					checker.SendMessage("Nincs kijelölve törlendõ rekord",0);
				}
				if(count>1) {
					checker.SendMessage("Maximum egy rekord törölhetõ egyszerre!", 0);
				}
				if(count==1) {
					/*
					konyvtartm.removeRow(chk);
					checker.SendMessage("A rekord törölve", 1);
					*/
					String isbn=konyvtartm.getValueAt(chk, 1).toString();
					dbmethods.DBConnect();
					dbmethods.DeleteData(isbn);
					dbmethods.DBDisconnect();
					konyvtartm.removeRow(chk);
					checker.SendMessage("A rekord törölve", 1);
				}
						
			}
		});
		btnDelete.setBounds(20, 227, 89, 23);
		getContentPane().add(btnDelete);
		TableRowSorter<konyvtarTable> tablerowsorter=(TableRowSorter<konyvtarTable>)table.getRowSorter();
		tablerowsorter.setSortable(0, false);
		
	}
}
