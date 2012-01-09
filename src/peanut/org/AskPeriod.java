package peanut.org;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class AskPeriod extends java.awt.Frame{
	private TimeObserver timeObserver;
	private DailyWork dailyWork;
	Label prompt,unit,warning;
	Panel panel1,panel2;
	Button btnEnter;
	TextField in;
	public AskPeriod(TimeObserver timeObserver, DailyWork dailyWork) { 
		//畫出一視窗。
		this.setTitle("輸入詢問週期");
		
		in = new TextField(6);
		in.setText(""+dailyWork.getPeriod());
		prompt = new Label("您希望每隔多久的時間，詢問您是否正在工作？");
		unit = new Label("秒");
		warning = new Label("請輸入0~43200(12小時)之間的數值");
		warning.setForeground(Color.RED);
		warning.setVisible(false);
		panel1 = new Panel();
		panel1.add(prompt);
		panel1.add(in);
		panel1.add(unit);
		panel1.add(warning);
		
		btnEnter = new Button("確定");
		btnEnter.setPreferredSize(new Dimension(60, 25));
		btnEnter.addActionListener(new java.awt.event.ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        // 自訂方法
		        button_actionPerformed(e);
		      }
		    });
		panel2 = new Panel();
		panel2.add(btnEnter);
		
		add(panel1, BorderLayout.CENTER);
	    add(panel2, BorderLayout.SOUTH);
		
		
		
		
		this.setSize(500,200);
		this.setBackground(new Color(245,185,20));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension dialogSize = this.getSize();
	    if(dialogSize.height > screenSize.height)
	    	dialogSize.height = screenSize.height;
	    if(dialogSize.width > screenSize.width)
	    	dialogSize.width = screenSize.width;
	    this.setLocation( (screenSize.width - dialogSize.width)/2 ,(screenSize.height - dialogSize.height)/2 );
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter()  {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//按下確定後btnActionPerform 將控制權傳回timeObserver{
		this.dailyWork = dailyWork;
		this.timeObserver = timeObserver;
	}
	public static void main(String[] args) {
		DailyWork dailyWork = new DailyWork();
		TimeObserver timeObserver = new TimeObserver(dailyWork);
		AskPeriod askPeriod = new AskPeriod(timeObserver, dailyWork);
	}
	private void button_actionPerformed(ActionEvent e) {
	    if (e.getSource().equals(btnEnter)) {
	    	/*輸入週期後，確認週期在0秒~43200秒(12小時)之間
	    	 * 然後開始倒數
	    	 */
	    	try{
	    		long Input_Sec = Long.parseLong(in.getText()) ;
	    		if(   !(Input_Sec >0  && Input_Sec <43200)  ){
	    			throw new NumberFormatException();
	    		}
		    	dailyWork.setPeriod(Input_Sec); //週期變數存放在dailyWork物件的period成員變數。
	    		JOptionPane.showMessageDialog(null, "程式已啟動！將於" + dailyWork.getPeriod() + "秒後詢問您是否在工作。" );
		    	timeObserver.countDown();//時間管理者開始倒數啦~~
		    	this.dispose();//由於此視窗只會出現一次，故使用dispose 釋放資源。
	    	}catch(NumberFormatException e1) {
    			this.warning.setVisible(true); //在顯窗上顯示『請輸入0~43200的數值』
	    		System.out.println("請輸入0~43200的數值");
    			this.in.setText("");
	    		this.validate(); //更新這個視窗
	    		return;
	    	}
	  }
}
}
