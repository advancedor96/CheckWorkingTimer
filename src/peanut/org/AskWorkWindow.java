package peanut.org;

import java.awt.*;
import java.util.*;

public class AskWorkWindow extends TimerTask{
	Timer AskWorkWindowTimer;
	DailyWork dailyWork;
	TimeObserver timeObserver;
	AskWorkDialog askDialog ;
	
	public AskWorkWindow(Timer askWorkWindowTimer, DailyWork dailyWork, TimeObserver timeObserver) {
		this.AskWorkWindowTimer = askWorkWindowTimer;
		this.dailyWork = dailyWork;
		this.timeObserver = timeObserver;
		askDialog = new AskWorkDialog(null,"�߰�","�z���O�b�u�@�ܡH",true, AskWorkWindowTimer, dailyWork, timeObserver);
	}
	

	@Override
	public void run() {
		/*�C�j�@�q�ɶ��N�|����o��run()���
		 * �߰ݨϥΪ̭��O���O���b�u�@�C
		 * �ѩ�߰ݪ������|�`�`���X�ӡA�K�ϥ�setVisible�Ӱ�����or��ܪ��}���C
		 * (�C����ܳ��nnew���ܡA�į���G���Ӧn)
		 */
		askDialog.setVisible(true);
	}

}
