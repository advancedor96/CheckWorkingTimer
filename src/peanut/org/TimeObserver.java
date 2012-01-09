package peanut.org;

import java.util.*;

public class TimeObserver {
	DailyWork dailyWork;
	Timer AskWorkWindowTimer;

	
	public TimeObserver(DailyWork dailyWork) {
		this.dailyWork = dailyWork; 
	}
	public void countDown(){
		/*每隔一段時間，執行某工作。(Timer、TimerTask的使用)*/
		System.out.println("開始倒數...");
		AskWorkWindowTimer = new Timer();
		AskWorkWindowTimer.schedule(new AskWorkWindow(AskWorkWindowTimer, this.dailyWork, this)  , dailyWork.getPeriod()*1000);
		
	}
}
