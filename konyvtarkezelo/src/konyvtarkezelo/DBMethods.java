package konyvtarkezelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBMethods {
	private Statement dbstatement=null;	//s
	private Connection dbconnection=null;	//conn
	private ResultSet dbresultset=null;	//rs
	
	public void DBRegistration() {
		try {
			Class.forName("org.sqlite.JDBC");
			SendMessage("Sikeres driver regisztráció",1);
		}	catch (ClassNotFoundException e) {
			SendMessage("Hibás driver regisztráció"+e.getMessage(),0);
		}
	}
	
	public void SendMessage(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}

	public void DBConnect() {
		try {
			String url ="jdbc:sqlite:C:/Users/grebe/eclipse-workspace/konyvtarkezelo/konyvtarLite.db";
			dbconnection=DriverManager.getConnection(url);
			SendMessage("Csatlakozás rendben!",1);
		} catch (SQLException e){
			SendMessage("JDBC Connect: "+e.getMessage(), 0);
		}
	}
	
	public void DBDisconnect() {
		try {
			dbconnection.close();
			SendMessage("Kapcsolat bontva!", 1);
		} catch (SQLException e){
			SendMessage(e.getMessage(), 0);
		}
	}
	
	public konyvtarTable DBReadData() {
		
		Object konyvtartmn[]= {"Jel","ISBN","Cím","Szerzõ","Megjelenés dátum","Állapot"};
		konyvtarTable konyvtartm=new konyvtarTable(konyvtartmn,0);
		
		String author="", title="", status="", releasedate="", tab="\t";
		int isbn=0;
		String sqlcommand="SELECT isbn,title,author,releasedate,status FROM konyvtar";
		try {
			dbstatement=dbconnection.createStatement();
			dbresultset=dbstatement.executeQuery(sqlcommand);
			while(dbresultset.next()) {
				isbn=dbresultset.getInt("isbn");
				title=dbresultset.getString("title");
				author=dbresultset.getString("author");
				releasedate=dbresultset.getString("releasedate");
				status=dbresultset.getString("status");
				konyvtartm.addRow(new Object[]{false,isbn,title,author,releasedate,status});
				//System.out.println(isbn+tab+title+tab+author+tab+releasedate+tab+status);
			}
			dbresultset.close();
		} catch (SQLException e) {
			SendMessage(e.getMessage(),0);
		}
		return konyvtartm;
	}
	
	public void DBInstertData(String isbn, String title, String author, String releasedate, String status) {
		String sqlcommand="INSERT INTO konyvtar VALUES("+isbn+",'"+title+"','"+author+"','"+releasedate+"','"+status+"')";
		try {
			dbstatement=dbconnection.createStatement();
			dbstatement.execute(sqlcommand);
			SendMessage("Beszúrás rendben!", 1);
		}catch (SQLException e){
			SendMessage("JDBC beszúrás: "+e.getMessage(), 0);
		}
	}
	
	public void DeleteData(String isbn) {
		String sqlcommand="DELETE FROM konyvtar WHERE isbn="+isbn;
		try {
			dbstatement=dbconnection.createStatement();
			dbstatement.execute(sqlcommand);
		}catch (SQLException e) {
			SendMessage("JDBC törlés: "+e.getMessage(),0);
		}
	}
	
	public void UpdateData(String isbn, String modname, String moddata) {
		String sqlcommand="UPDATE konyvtar SET "+modname+"='"+moddata+"' WHERE isbn="+isbn;
		try {
			dbstatement=dbconnection.createStatement();
			dbstatement.execute(sqlcommand);
		}catch(SQLException e) {
			SendMessage("JDBC Update:"+e.getMessage(),0);
		}
	}
}
