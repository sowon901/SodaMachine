package sodaMachine;

import javax.swing.JOptionPane;

public class Dispenser {
	/*---------------------------soda dispense------------------------*/
	public static void releaseProduct(int sodaNum) {
		int selection = ProductManager.takeProduct(sodaNum);
		JOptionPane.showMessageDialog(null, "Soda" + (selection+1) + "이(가) 나왔습니다. "
				+ "\n 맛있게 드세요!");
	}
}
