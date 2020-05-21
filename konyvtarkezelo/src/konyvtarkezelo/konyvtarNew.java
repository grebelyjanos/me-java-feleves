package konyvtarkezelo;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class konyvtarNew extends JDialog {
	private JTextField isbn;
	private JTextField title;
	private JTextField author;
	private JTextField releasedate;
	private JTextField status;
	private DBMethods dbmethods=new DBMethods();
	private Checker checker=new Checker();

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					konyvtarNew dialog = new konyvtarNew();
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
	public konyvtarNew() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Hozz\u00E1ad\u00E1s");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checker.testInt(isbn, "ISBN"))
					if(checker.filled(title, "Cím"))
						if(checker.filled(author, "Szerzõ"))
							if(checker.testingDate(releasedate, "Megjelenés éve"))
								if(checker.filled(status, "Státusz")) {
									dbmethods.DBConnect();
									dbmethods.DBInstertData(ReadTextField(isbn), ReadTextField(title), ReadTextField(author), ReadTextField(releasedate), ReadTextField(status));
									dbmethods.DBDisconnect();
								}
				
			}
		});
		btnAdd.setBounds(181, 227, 89, 23);
		getContentPane().add(btnAdd);
		
		
		JLabel lblNewLabel = new JLabel("ISBN:");
		lblNewLabel.setBounds(10, 11, 89, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00EDm:");
		lblNewLabel_1.setBounds(10, 36, 89, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Szerz\u0151:");
		lblNewLabel_2.setBounds(10, 61, 89, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Megjelen\u00E9s \u00E9ve:");
		lblNewLabel_3.setBounds(10, 86, 89, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("St\u00E1tusz:");
		lblNewLabel_4.setBounds(10, 111, 89, 14);
		getContentPane().add(lblNewLabel_4);
		
		isbn = new JTextField();
		isbn.setBounds(109, 8, 86, 20);
		getContentPane().add(isbn);
		isbn.setColumns(10);
		
		title = new JTextField();
		title.setBounds(109, 33, 86, 20);
		getContentPane().add(title);
		title.setColumns(10);
		
		author = new JTextField();
		author.setBounds(109, 58, 86, 20);
		getContentPane().add(author);
		author.setColumns(10);
		
		releasedate = new JTextField();
		releasedate.setBounds(109, 83, 86, 20);
		getContentPane().add(releasedate);
		releasedate.setColumns(10);
		
		status = new JTextField();
		status.setBounds(109, 108, 86, 20);
		getContentPane().add(status);
		status.setColumns(10);

	}
	
	public String ReadTextField(JTextField gettext) {
		return gettext.getText();
	}
}
