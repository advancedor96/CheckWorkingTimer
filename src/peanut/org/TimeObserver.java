package peanut.org;

import java.util.*;

public class TimeObserver {
	DailyWork dailyWork;
	Timer AskWorkWindowTimer;

	
	public TimeObserver(DailyWork dailyWork) {
		this.dailyWork = dailyWork; 
	}
	public void countDown(){
		/*�C�j�@�q�ɶ��A����Y�u�@�C(Timer�BTimerTask���ϥ�)*/
		System.out.println("�}�l�˼�...");
		AskWorkWindowTimer = new Timer();
		AskWorkWindowTimer.schedule(new AskWorkWindow(AskWorkWindowTimer, this.dailyWork, this)  , dailyWork.getPeriod()*1000);
		
	}
}
