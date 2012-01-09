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
		//�e�X�@�����C
		this.setTitle("��J�߰ݶg��");
		
		in = new TextField(6);
		in.setText(""+dailyWork.getPeriod());
		prompt = new Label("�z�Ʊ�C�j�h�[���ɶ��A�߰ݱz�O�_���b�u�@�H");
		unit = new Label("��");
		warning = new Label("�п�J0~43200(12�p��)�������ƭ�");
		warning.setForeground(Color.RED);
		warning.setVisible(false);
		panel1 = new Panel();
		panel1.add(prompt);
		panel1.add(in);
		panel1.add(unit);
		panel1.add(warning);
		
		btnEnter = new Button("�T�w");
		btnEnter.setPreferredSize(new Dimension(60, 25));
		btnEnter.addActionListener(new java.awt.event.ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        // �ۭq��k
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
		//���U�T�w��btnActionPerform �N�����v�Ǧ^timeObserver{
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
	    	/*��J�g����A�T�{�g���b0��~43200��(12�p��)����
	    	 * �M��}�l�˼�
	    	 */
	    	try{
	    		long Input_Sec = Long.parseLong(in.getText()) ;
	    		if(   !(Input_Sec >0  && Input_Sec <43200)  ){
	    			throw new NumberFormatException();
	    		}
		    	dailyWork.setPeriod(Input_Sec); //�g���ܼƦs��bdailyWork����period�����ܼơC
	    		JOptionPane.showMessageDialog(null, "�{���w�ҰʡI�N��" + dailyWork.getPeriod() + "���߰ݱz�O�_�b�u�@�C" );
		    	timeObserver.countDown();//�ɶ��޲z�̶}�l�˼ư�~~
		    	this.dispose();//�ѩ󦹵����u�|�X�{�@���A�G�ϥ�dispose ����귽�C
	    	}catch(NumberFormatException e1) {
    			this.warning.setVisible(true); //�b�㵡�W��ܡy�п�J0~43200���ƭȡz
	    		System.out.println("�п�J0~43200���ƭ�");
    			this.in.setText("");
	    		this.validate(); //��s�o�ӵ���
	    		return;
	    	}
	  }
}
}
