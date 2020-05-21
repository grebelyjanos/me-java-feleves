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

public class konyvtarList extends JDialog {
	private JTable table;
	private konyvtarTable konyvtartm;
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
	public konyvtarList(JFrame varframe, konyvtarTable vartable) {
		super(varframe, "Könyvtár lista", true);
		konyvtartm=vartable;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnClose = new JButton("Bez\u00E1r");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(180, 227, 89, 23);
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
				tablecolumn.setPreferredWidth(180);
			}else {
				tablecolumn.setPreferredWidth(300);
			}
		}
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter<konyvtarTable> tablerowsorter=(TableRowSorter<konyvtarTable>)table.getRowSorter();
		tablerowsorter.setSortable(0, false);
		
	}
}
