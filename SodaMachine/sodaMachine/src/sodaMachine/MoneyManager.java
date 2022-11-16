package sodaMachine;

import javax.swing.JOptionPane;

public class MoneyManager extends UserPanel{
	/*------------------------------soda값---------------------------*/
	public static int getCustomerReserveCash(int cash) {
		int total = DataManager.restoreReserveCash(cash);
		return total;
	}
	/*-----------------------------selection-------------------------*/
	public static int getCustomerReserveSelection(int sodaNum) {
		int selection = DataManager.restoreReserveSelection(sodaNum);
		return selection;
	}
	/*---------------------------selection의 가격-----------------------*/
	public static int getSelectionPrice(int sodaNum) {
		int selection = getCustomerReserveSelection(sodaNum);
		return price[selection];
	}
	/*----------------------------거스름돈 연산-------------------------*/
	public static void checkForChange(int cash, int change) {
		ch1000 = change / 1000;
        ch500 = change % 1000 / 500;
        ch100 = change % 1000 % 500 / 100;
        ch10 = change % 1000 % 500 % 100 / 10;
        
        changeNum[0] -= ch10;
        changeNum[1] -= ch100;
        changeNum[2] -= ch500;
        changeNum[3] -= ch1000;
        
        
        if(changeNum[0] < 0 || changeNum[1] < 0 || changeNum[2] < 0 || changeNum[3] < 0) {
        
           if(changeNum[3]<0) {
              
              //만약 500원의 갯수가 부족한 1000원짜리의 갯수를 500원으로 바꿔줄 수  갯수보다 많을때
              if(changeNum[2] > 0) 
                 changeNum[2] = (changeNum[2]*500 + changeNum[3]*1000)/500;
              else if(changeNum[2]<0)
                 changeNum[1] = (changeNum[1]*100 + changeNum[2]*500)/100;
         
           changeNum[3]=0;
           }
              
           if(changeNum[2] <0) {
              if(changeNum[1]>0)
                 changeNum[1] = (changeNum[1]*100 + changeNum[2]*500)/100;
              else if (changeNum[1] <0)
                 changeNum[0] = (changeNum[0]*10 + changeNum[0]*100)/10;
              changeNum[2] = 0;
              
           }
           
        }
        else if (changeNum[0] <= 0 && changeNum[1] <= 0 && changeNum[2] <= 0 && changeNum[3] <= 0)
           JOptionPane.showMessageDialog(null, "자판기 내의 거스름돈이 부족합니다. \n 관리자에게 문의하세요. \n 000-0000-0000");
	}
}
