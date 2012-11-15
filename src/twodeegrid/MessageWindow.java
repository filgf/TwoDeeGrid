package twodeegrid;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class MessageWindow {
	private boolean isModal = false;
	
	private String message;
	
	private JDialog dialog = new JDialog();
	
	public void setModal(boolean isModal) {
		this.isModal = isModal;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessageFromResource (Class base, String path) {
		InputStream is = base.getResourceAsStream(path);
		if (is != null) {
			setMessage(is);
		} else {
			setMessage("DOH! Could not read message to display!");
		}
	}

	public void setMessage(InputStream is) {
		String text;
		
		try {
			text = readTextFromInputStream(is);
		} catch (IOException e) {
			text = "DOH! Could not read message to display!";
		}
		this.message = text;
	}
	

	public void show() {
		Container c = dialog.getContentPane();
		c.removeAll();
		c.add(new JLabel(message));
		
		dialog.setModal(isModal);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(true);
		dialog.setVisible(true);
	}
	
	public void hide() {
		dialog.setVisible(false);
	}
	
	private final static char cbuf[] = new char[1024];

	private String readTextFromInputStream(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String ret = "";
		
		String line; 
		while ((line = br.readLine()) != null) {
			ret += line;
		}
		return ret;
	}
	
}
