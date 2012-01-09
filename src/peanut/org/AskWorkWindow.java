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
		askDialog = new AskWorkDialog(null,"詢問","您剛剛是在工作嗎？",true, AskWorkWindowTimer, dailyWork, timeObserver);
	}
	

	@Override
	public void run() {
		/*每隔一段時間就會執行這個run()函數
		 * 詢問使用者剛剛是不是正在工作。
		 * 由於詢問的視窗會常常跳出來，便使用setVisible來做隱藏or顯示的開關。
		 * (每次顯示都要new的話，效能似乎不太好)
		 */
		askDialog.setVisible(true);
	}

}
