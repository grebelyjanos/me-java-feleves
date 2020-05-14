package konyvtarkezelo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import konyvtarkezelo.DBMethods;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Program extends JFrame {

	private JPanel contentPane;
	private DBMethods dbmethods = new DBMethods();
	private konyvtarTable konyvtartm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Program() {
		dbmethods.DBRegistration();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnList = new JButton("Lista");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				dbmethods.DBConnect();
				dbmethods.DBReadData();
				dbmethods.DBDisconnect();
				*/
				dbmethods.DBConnect();
				konyvtartm=dbmethods.DBReadData();
				dbmethods.DBDisconnect();
				konyvtarList konyvtarlist= new konyvtarList(Program.this, konyvtartm);
				konyvtarlist.setVisible(true);
			}
		});
		btnList.setBounds(10, 11, 89, 23);
		contentPane.add(btnList);
		
		JButton btnNew = new JButton("\u00DAj adatsor");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				konyvtarNew konyvtarnew= new konyvtarNew();
				konyvtarnew.setVisible(true);
			}
		});
		btnNew.setBounds(109, 11, 130, 23);
		contentPane.add(btnNew);
		
		JButton btnDelete = new JButton("Adatsor t\u00F6rl\u00E9se");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbmethods.DBConnect();
				konyvtartm=dbmethods.DBReadData();
				dbmethods.DBDisconnect();
				konyvtarDel konyvtardel=new konyvtarDel(Program.this, konyvtartm);
				konyvtardel.setVisible(true);
			}
		});
		btnDelete.setBounds(249, 11, 160, 23);
		contentPane.add(btnDelete);
		
		JButton btnModify = new JButton("Adatsor m\u00F3dos\u00EDt\u00E1s");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbmethods.DBConnect();
				konyvtartm=dbmethods.DBReadData();
				dbmethods.DBDisconnect();
				konyvtarModify konyvtarmodify=new konyvtarModify(Program.this, konyvtartm);
				konyvtarmodify.setVisible(true);
			}
		});
		btnModify.setBounds(426, 11, 160, 23);
		contentPane.add(btnModify);
		
		Object konyvtartmn[]= {"Jel","ISBN","Cím","Szerzõ","Megjelenés dátum","Állapot"};
		konyvtartm=new konyvtarTable(konyvtartmn,0);
	}
}
