package konyvtarkezelo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {

	public boolean filled(JTextField text, String textname) {
		String stext = ReadTextField(text);
		if(stext.length()>0) {
			return true;
		}else {
			SendMessage("Hiba: "+textname+" mezõ üres!",0);
			return false;
		}
	}
	
	public boolean testInt(JTextField text, String textname) {
		String stext=ReadTextField(text);
		boolean bool = filled(text,textname);
		if(bool) try {
			Integer.parseInt(stext);
		}catch (NumberFormatException e) {
			SendMessage("Az "+textname+" mezõben hibás a számadat!",0);
			bool = false;
		}
		return bool;
	}
	
	public String ReadTextField(JTextField jtext) {
		return jtext.getText();
	}
	
	public void SendMessage(String msg, int pmsg) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet: ", pmsg);
	}
	
	public boolean DateFormatChecker(String SDate) {
		SimpleDateFormat sdateformat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date testdate=sdateformat.parse(SDate);
			if(!sdateformat.format(testdate).equals(SDate)) {
				return false;
			}else {
				return true;
			}
		}catch (ParseException eformat) {
			return false;
		}
	}
	
	public boolean testingDate(JTextField text, String textname) {
		String stext=ReadTextField(text);
		boolean bool = filled(text,textname);
		if(bool && DateFormatChecker(stext)) {
			return true;
		}else {
			SendMessage("A "+textname+" mezõben hibás a formátum!",0);
			return false;
		}
	}
	
	public int stringToInt(String text) {
		int x=-1;
		try {
			x=Integer.valueOf(text);
		}catch (NumberFormatException e) {
			SendMessage("Számadat: "+e.getMessage(),0);
		}
		return x;
	}
}
