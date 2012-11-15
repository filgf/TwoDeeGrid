package twodeegrid.test;

import twodeegrid.MessageWindow;
import twodeegrid.TwoDeeGrid;

public class TestMessages {
	
	public static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(10,10, "<{Griddie Example}>");

	/*	MessageWindow mw = new MessageWindow();
		mw.setMessage("HALLO!");
		mw.setModal(true);
		mw.show();
*/
		MessageWindow mw2 = new MessageWindow();
		mw2.setMessageFromResource(TestMessages.class, "test.html");
		mw2.setModal(true);
		mw2.show();

	}

}
