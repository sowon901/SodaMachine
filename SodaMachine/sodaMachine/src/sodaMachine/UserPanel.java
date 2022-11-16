package sodaMachine;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class UserPanel extends JFrame {

   private String [] sodas = {"soda1", "soda2", "soda3", "soda4", "soda5", "soda6", "soda7", "soda8"};
   private static JTextField [] tn = new JTextField[8];	//수량표시
   int count1, count2, count3, count4 = 0;
   static JTextField [] tf = new JTextField[4];
   static JTextField [] tf2 = new JTextField[4];
   String at, bt, ct, dt;
   int ai;
   int bi;
   int ci;   
   int di;
   static int total;              //total = 투입한 총 값
   int count = 10;
   String cs;
   int r=0;
   static int change;
   static int ch10;		//반환해야하는 동전 개수
   static int ch100;
   static int ch500;
   static int ch1000;
   
   private ImageIcon [] images = {
          new ImageIcon("images/soda1.jpg"),
          new ImageIcon("images/soda2.jpg"),
          new ImageIcon("images/soda3.jpg"),
          new ImageIcon("images/soda4.jpg"),
          new ImageIcon("images/soda5.png"),
          new ImageIcon("images/soda6.jpg"),
          new ImageIcon("images/soda7.png"),
          new ImageIcon("images/soda8.jpg")};

   private String [] sodaNames = {"Soda1", "Soda2", "Soda3", "Soda4", "Soda5",
         "Soda7" , "Soda8"};
   static int price[] = { 900, 1000, 900, 1500, 1500, 1800, 1500, 500}; //가격
   static int changeNum[] = {10, 10, 10, 0}; 							//거스름돈 초기개수
   static int num[] = {10, 9, 15, 8, 5, 5, 0, 7}; 						//수량
   
   static JButton but[] = new JButton[8];	// 음료 뽑기 버튼 생성
   static JTextArea changetf = new JTextArea(1, 5); //거스름돈 표시
   static JButton confirm = new JButton("확인"); //확인버튼
   static JTextArea ta = new JTextArea(1,5);		//투입한 돈 총액
   static JButton changeBut =  new JButton("반환"); //반환버튼
   
   //-----------------------User Panel-------------------------------//
   public UserPanel() {
	  setTitle("Soda Machine");
   	  setSize(900, 1000);
   	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Container c = getContentPane();
  
      //수량 표시
      UserPanel.turnOnCountLED();
      
      //패널 49개 생성
      JPanel [] panel = new JPanel[49];
      
      //panel 3 + 8 (soda개수)
      for(int i=0; i<11; i++) 
    	  panel[i] = new JPanel();
      
      for(int i=3; i<11; i++)
    	  panel[i].setLayout(new GridLayout(2,1));
   
      panel[1].setLayout(new BorderLayout());
      panel[2].setLayout(new GridLayout(2,3));
      panel[0].setLayout(new BorderLayout());
      
      for(int i=0; i<=10; i++)
    	  panel[i].setBackground(new Color(0, 130, 0));

      c.setLayout(new BorderLayout());
   
      
      JLabel [] label = new JLabel[9];	//soda이름표 + 가격 생성
      for(int i=0; i<8; i++) {
         label[i] = new JLabel("<html> soda" + (i+1) + "<br>" + price[i] + "원");
         label[i].setHorizontalAlignment(JLabel.CENTER);
         but[i] = new JButton("◦");
         //but[i].setBackground(new Color(100, 100, 100));
         but[i].setEnabled(false);
         //버튼의 초기 상태: 눌리지 않음
      }
      
   //음료 label각각 생성   
      for(int i=15; i<=22; i++) 
    	  panel[i] = new JPanel();
    	  
      JPanel moneyPanel = new JPanel();
      moneyPanel.setLayout(new BorderLayout());
      
      for(int i=15; i<=22; i++) {
    	  panel[i].setLayout(new BorderLayout());
    	  panel[i].add(label[i-15], BorderLayout.NORTH);
    	  panel[i].add(but[i-15], BorderLayout.CENTER);
    	  panel[i].setBackground(new Color(0, 130, 0));
      }
      
   //수량확인을 위한 panel 따로 생성   
      JPanel ss = new JPanel();
  
      for(int i=41; i<=48; i++) {
    	  panel[i] = new JPanel();
    	  panel[i].setBackground(new Color(0, 130, 0));
    	  panel[i].setLayout(new FlowLayout());
    	  panel[i].add(new JLabel("수량"));
    	  panel[i].add(tn[i-41]);
      } 
      
   //Soda
      for(int i=3; i<=10; i++) {
    	  panel[i].add(new JLabel(images[i-3]));
    	  panel[i+12].add(panel[i+38], BorderLayout.SOUTH);
    	  panel[i].add(panel[i+12]);
    	  panel[2].add(panel[i]);
      }

      //내가 넣은 돈
      for(int i=0; i<4; i++) {
         tf[i] = new JTextField("0");
      }   
      // money를 panel 위에 붙임
      JPanel flow = new JPanel(new BorderLayout());
      JPanel money = new JPanel();
      
      
      money.setLayout(new GridLayout(5,2));
      // 동전 삽입   
      money.add(new JLabel("10원 :"));
      money.add(tf[0]);
      money.add(new JLabel("100원 :"));
      money.add(tf[1]);
      money.add(new JLabel("500원 :"));
      money.add(tf[2]);
      money.add(new JLabel("1000원 :"));
      money.add(tf[3]);
      money.add(new JLabel("총금액"));
      
      
      
      moneyPanel.add(money, BorderLayout.CENTER);
      moneyPanel.add(confirm, BorderLayout.EAST);
      
      //초기 거스름돈 개수 -- 천원짜리는 0으로 한다.
      for(int i=0; i<4; i++) {
    	  tf2[i] = new JTextField(2);
    	  tf2[i].setText(changeNum[i] + "");
    	  tf2[i].setEditable(false);
      }
      
    //거스름돈 Panel
      JPanel MachinePanel = new JPanel();
      MachinePanel.setLayout(new BorderLayout());
      JPanel money2 = new JPanel();
      money2.setLayout(new GridLayout(5,2));  
         money2.add(new JLabel("10원 :"));
         money2.add(tf2[0]);
         money2.add(new JLabel("100원 :"));
         money2.add(tf2[1]);
         money2.add(new JLabel("500원 :"));
         money2.add(tf2[2]);
         money2.add(new JLabel("1000원 :"));
         money2.add(tf2[3]);
      MachinePanel.add(new JLabel("자판기 내 금액 현황"), BorderLayout.NORTH);
      MachinePanel.add(money2, BorderLayout.CENTER);
      
     
      flow.add(moneyPanel, BorderLayout.NORTH);
      flow.add(MachinePanel, BorderLayout.SOUTH);
 
      JPanel won = new JPanel();
      
      
      JPanel changePan = new JPanel();   
      
      
      changetf.setEditable(false);
      changePan.add(new JLabel("거스름돈:"));
      changePan.add(changetf);
      changePan.add(new JLabel("원"));
      changePan.add(changeBut);
      
      won.add(ta);
      won.add(new JLabel("원"));
      money.add(won);
      
      moneyPanel.add(changePan, BorderLayout.SOUTH);
      
      panel[0].add(panel[1], BorderLayout.NORTH);
      panel[1].add(panel[2]);
      c.add(panel[0], BorderLayout.CENTER);
      c.add(flow, BorderLayout.EAST);
      setSize(800, 500);
      setVisible(true);
      
      /*----------------------------Event-----------------------*/
      UserPanel.acceptCash();

      UserPanel.ButtonClicked(0);
      UserPanel.ButtonClicked(1);
      UserPanel.ButtonClicked(2);
      UserPanel.ButtonClicked(3);
      UserPanel.ButtonClicked(4);
      UserPanel.ButtonClicked(5);
      UserPanel.ButtonClicked(6);
      UserPanel.ButtonClicked(7);

      UserPanel.changeExit();
      
      
   }
 //-----------------------TurnOnButtonLight-------------------------------//
   public static void turnOnButtonLight(int i) {
	   but[i].setEnabled(true);
   }
 //-----------------------ButtonClicked-------------------------------//
   public static void ButtonClicked(int selection) {
	   but[selection].addActionListener(new ActionListener() {         
	         public void actionPerformed(ActionEvent e) {
	        	Dispenser.releaseProduct(selection);
	            Controller.getCustomerSelection(selection);
	            
	        	change -= price[selection];
	            changetf.setText(change + "");
	            
	            //수량 -1
	            ProductManager.countLeftSoda(selection);
	            UserPanel.changeCountLED(selection);
	            
	            if(num[selection] <= 0)
	            	but[selection].setEnabled(false);
	            for(int i=0; i<8; i++)
	            	if(change < price[i])
	            		but[i].setEnabled(false);
	         }
	  });
   }
 //-----------------------AcceptCash-------------------------------//
   public static void acceptCash() {
	// 확인 버튼 클릭
       confirm.addActionListener(new ActionListener() {
          
          public void actionPerformed(ActionEvent e) {

              //거스름돈 현황
              changeNum[0] += Controller.getMoneyCount(10);
              changeNum[1] += Controller.getMoneyCount(100);
              changeNum[2] += Controller.getMoneyCount(500);
              changeNum[3] += Controller.getMoneyCount(1000);
              for(int i=0; i<4; i++)
              	tf2[i].setText(changeNum[i] + ""); 		
              
              Controller.getCustomerCash();
              change = total; 	//거스름돈의 초기값은 total과 같다

              ta.setText(total + "");
              changetf.setText(change + "");
             
              for(int i=0; i<8; i++) {
              	if(ProductManager.checkAvailability(total, i) && (ProductManager.countSoda(i) > 0)) {
              		UserPanel.turnOnButtonLight(i);
              	}
              }
          }
    
       });
   }
 //-----------------------changeExit-------------------------------//
   public static void changeExit() {
	 //반환버튼 클릭
	      changeBut.addActionListener(new ActionListener() {         
	          public void actionPerformed(ActionEvent e) {
	            MoneyManager.checkForChange(total, change);
	            UserPanel.recieveChange();
	          }
	          
	   });
   }
 //-----------------------turnOnCountLED-------------------------------//
   public static void turnOnCountLED() {
	   for(int i=0; i<8; i++) {
	         tn[i] = new JTextField(2);
	         tn[i].setText(ProductManager.countSoda(i) + "");
	         tn[i].setEditable(false);
	      }
   }
 //-----------------------changeCountLED-------------------------------//
   public static void changeCountLED(int sodaNum) {
	   int selection = ProductManager.takeProduct(sodaNum);
	   int count = ProductManager.countSoda(selection);
	   tn[selection].setText(count + "");
   }
 //-----------------------recieveChange-------------------------------//
   public static void recieveChange() {
	   JOptionPane.showMessageDialog(null, changetf.getText() + "원이 반환되었습니다.");
       total = 0;
       change = 0;
       ta.setText(total + "");
       changetf.setText(change + "");
       
       for(int i=0; i<4; i++) {
          tf[i].setText("0");
          tf2[i].setText(changeNum[i] + "");
       }
       for(int i=0; i<8; i++)
          but[i].setEnabled(false);
   }
   public static void main(String[] args) {
      new UserPanel();
   }
}
