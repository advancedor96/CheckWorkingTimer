package peanut.org;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
public class AskWorkDialog extends Dialog{
	private Dialog parent;
	private String title,text;
	private boolean modal;
	private Button btnYes,btnNo,btnSave;
	
	Timer AskWorkWindowTimer;
	DailyWork dailyWork;
	TimeObserver timeObserver;

	
	
	public AskWorkDialog(Dialog owner, String title, String text, boolean modal, Timer askWorkWindowTimer, final DailyWork dailyWork, TimeObserver timeObserver) {
		super(owner, title, modal);

		this.parent = owner;
	    this.title = title;
	    this.text = text;
	    this.modal = modal;
		this.AskWorkWindowTimer = askWorkWindowTimer;
		this.dailyWork = dailyWork;
		this.timeObserver = timeObserver;
	    
	    btnYes = new Button("是");
	    // 設定按鈕的最佳尺寸
	    btnYes.setPreferredSize(new Dimension(60, 25));
	    btnYes.addActionListener(new java.awt.event.ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        // 自訂方法
	        button_actionPerformed(e);
	      }
	    });
	    
	    btnNo = new Button("否");
	    // 設定按鈕的最佳尺寸
	    btnNo.setPreferredSize(new Dimension(60, 25));
	    btnNo.addActionListener(new java.awt.event.ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        // 自訂方法
	        button_actionPerformed(e);
	      }
	    });	
	    
	    btnSave = new Button("儲存記錄");
	    // 設定按鈕的最佳尺寸
	    btnSave.setPreferredSize(new Dimension(60, 25));
	    btnSave.addActionListener(new java.awt.event.ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        // 自訂方法
	        button_actionPerformed(e);
	      }
	    });
	    

	    
	    Label showWorkedTime = new Label("今天已工作：" + dailyWork.getCurrentWorkedTime());
	    Panel panel1 = new Panel();
	    panel1.add(new Label(text));

	    Panel panel2 = new Panel();
	    
	    panel2.add(btnYes);
	    panel2.add(btnNo);
	    panel2.add(btnSave);
	    

	    Panel panel3 = new Panel();
	    panel3.add(showWorkedTime);
	    add(panel1, BorderLayout.NORTH);
	    add(panel2, BorderLayout.CENTER);
	    add(panel3, BorderLayout.SOUTH);
		
	    // 設定對話盒是否為無邊框及標題列樣式
	    this.setUndecorated(false);

	    // 設定對話盒的大小
	    this.setSize(300, 200);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension dialogSize = this.getSize();
	    
	    if(dialogSize.height > screenSize.height)
	    	dialogSize.height = screenSize.height;
	    if(dialogSize.width > screenSize.width)
	    	dialogSize.width = screenSize.width;
	    
	    this.setLocation( (screenSize.width - dialogSize.width)/2 ,(screenSize.height - dialogSize.height)/2 );

		this.addWindowListener(new WindowAdapter()  {
			public void windowClosing(WindowEvent e) {
				dailyWork.saveToText();
				System.exit(0);
			}
		});
	    this.setVisible(false);
	    

	}

  private void button_actionPerformed(ActionEvent e) {
	    if (e.getSource().equals(btnYes)) {
	    	dailyWork.addTenMin();
	    	dailyWork.saveToText();
		    setVisible(false);
	    }else if (e.getSource().equals(btnNo)) {
	    	System.out.println("沒工作。");
	    	dailyWork.saveToText();
		    setVisible(false);
	    }else if (e.getSource().equals(btnSave)) {
	    	dailyWork.saveToText();
	    	return;
	    }
		AskWorkWindowTimer.cancel();
		timeObserver.countDown();

	  }
}
