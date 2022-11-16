package sodaMachine;

public class Controller extends UserPanel{
	static int index;
	//sodaNum: 0~7 
	/*---------------------------단위별 돈 개수--------------------------*/
	public static int getMoneyCount(int moneyType) {
		if (moneyType == 10)	index = 0;
		else if(moneyType == 100)	index = 1;
		else if(moneyType == 500) 	index = 2;
		else if(moneyType == 1000) 	index = 3;
		String moneyTypeStr = tf[index].getText();
		int count = Integer.parseInt(moneyTypeStr);
		return count;
	}
	/*---------------------------투입한 돈 총 값--------------------------*/
	public static int getCustomerCash() {
        total = change + 10*getMoneyCount(10) + 100*getMoneyCount(100) 
        			+ 500*getMoneyCount(500) + 1000*getMoneyCount(1000);
		return total;
	}
	/*------------------------------selection 받아오기---------------------------*/
	public static int getCustomerSelection(int sodaNum) {
		return sodaNum;
	}
	/*-------------------------구매가능한 음료 받아오기----------------------*/
	public static boolean getAvailableProduct(int cash, int sodaNum) {
		if(ProductManager.checkAvailability(cash, sodaNum))
			return true;
		return false;
	}
}
