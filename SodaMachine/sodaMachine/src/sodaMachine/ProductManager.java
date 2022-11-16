package sodaMachine;

public class ProductManager extends UserPanel{
	//sodaNum: 0~7 
	   /*------------------------------soda 수---------------------------*/
	   public static int countSoda(int sodaNum) {
		   return num[sodaNum];
	   }
	   /*---------------------------구매 가능한 soda-----------------------*/
	   public static boolean checkAvailability(int cash, int sodaNum) {
		   if(cash >= price[sodaNum])
			   return true;
		   return false;
	   }
	   /*----------------------------soda 가져오기-------------------------*/
	   public static int takeProduct(int sodaNum) {
		   int selection = MoneyManager.getCustomerReserveSelection(sodaNum);
		   return selection;
	   }
	   /*--------------------------남은 soda 수량 파악-----------------------*/
	   public static void countLeftSoda(int sodaNum) {
		   int selection = takeProduct(sodaNum);
		   num[selection]--;
	   }

}
