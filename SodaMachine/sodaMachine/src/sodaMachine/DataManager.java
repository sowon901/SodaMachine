package sodaMachine;

public class DataManager {
	//sodaNum: 0~7
	/*------------------------------cash 저장---------------------------*/
	public static int restoreReserveCash(int cash) {
		cash = Controller.getCustomerCash();
		return cash;
	}
	/*-----------------------------selection 저장---------------------------*/
	public static int restoreReserveSelection(int sodaNum) {
		sodaNum = Controller.getCustomerSelection(sodaNum);
		return sodaNum;
	}
}
