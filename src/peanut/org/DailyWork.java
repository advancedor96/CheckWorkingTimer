package peanut.org;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;

public class DailyWork implements Serializable{
	private int year;
	private int month;
	private int day;
	private int hour;
	private int min;
	private int sec;
	private long period;
	
	public DailyWork(){
		this.loadFromText();
		period = 600;
		System.out.println("預設10分鐘詢問一次");
	}
	public void addTenMin(){
		sec +=period;
		while(sec >=60){
			min++;
			sec-=60;
		}
		while(min >=60) {
			hour++;
			min -= 60;
		}
    	System.out.println("加" + period +"秒");
	}
	public void setPeriod(long period) {
		this.period = period;
    	System.out.println("設定週期：" + this.period + "秒");
	}
	public long getPeriod() {
		return this.period;
	}
	


	public String getCurrentWorkedTime() {
		return ""+this.hour + "時" + this.min + "分" + this.sec + "秒" ;
	}

	
	public void saveToText() {
		/*把今天目前的記錄儲存到檔案*/
		FileOutputStream fs;
		//把年的數值轉成固定4個位數,月,日,時,分,秒的數值轉成固定2個位數
		NumberFormat formatter = new DecimalFormat("0000");
		String YearOfTwoDigits = formatter.format(this.year);
		
		formatter = new DecimalFormat("00");
		String MonthOfTwoDigits = formatter.format(this.month);
		String DayOfTwoDigits = formatter.format(this.day);
		String HourOfTwoDigits = formatter.format(this.hour);
		String MinOfTwoDigits = formatter.format(this.min);
		String SecOfTwoDigits = formatter.format(this.sec);
		
		try {
			String filename = "[Rec]" + YearOfTwoDigits + "-" + MonthOfTwoDigits + "-" + DayOfTwoDigits +".txt";
			fs = new FileOutputStream(filename);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs));
			bw.write("[Date]" + YearOfTwoDigits + "-" + MonthOfTwoDigits + "-" + DayOfTwoDigits +"\r\n");
			bw.write("[Worked]" + HourOfTwoDigits + ":" + MinOfTwoDigits + ":" + SecOfTwoDigits +"\r\n");
			bw.close();
			fs.close();
	    	System.out.println("Save Now.");
		} catch (FileNotFoundException e1) {
			System.out.println("FileNotFoundException");
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
	public void loadFromText() {
		//若已有今天的記錄，load
		//若無，create new
		boolean isFound = false;
		DateFormat  dateFormat = new SimpleDateFormat("yyyy MM dd");
		String FormatedDateString = dateFormat.format(new Date());
		String []tokens = FormatedDateString.split(" ");

		String searchFileName = "[Rec]" + tokens[0] + "-" + tokens[1] + "-" + tokens[2] + ".txt";
	
		File workingDir = new File(  System.getProperty("user.dir") );  
		File [] FilesInWorkingDir = workingDir.listFiles();
		for(int i=0;i<FilesInWorkingDir.length;i++){
			if(searchFileName.equalsIgnoreCase(FilesInWorkingDir[i].getName())){
				isFound = true;
				break;
			}
		}
		if(isFound){
			//若已有今天的記錄，load
			System.out.println("已有今天的記錄，load");
			FileInputStream fileStream;
			try {
				fileStream = new FileInputStream(searchFileName);
				BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
				
				String firstLine = br.readLine();
				String secondLine = br.readLine();
				
				
				String yearInText = firstLine.substring(6, 10);
				String monthInText = firstLine.substring(11, 13);
				String dayInText = firstLine.substring(14, 16);
				
				if( ! (yearInText.equals(tokens[0]) && monthInText.equals(tokens[1]) && dayInText.equals(tokens[2]))  ){
					String errorData = ""+yearInText + "\t" + tokens[0] + "\n" +
							monthInText + "\t" + tokens[1] + "\n" +
							dayInText + "\t" + tokens[2] ;
					System.out.println("檔名日期與檔案內容日期不符！疑似被修改過！暫時繼續..." +errorData);
					JOptionPane.showMessageDialog(null, "檔名日期與檔案內容日期不符！疑似被修改過！暫時繼續...\n" + errorData);
				}
				
				String hourInText = secondLine.substring(8, 10);
				String minInText = secondLine.substring(11, 13);
				String secInText = secondLine.substring(14, 16);
				
				this.year = Integer.parseInt(yearInText);
				this.month=Integer.parseInt(monthInText);
				this.day=Integer.parseInt(dayInText);
				this.hour = Integer.parseInt(hourInText);
				this.min = Integer.parseInt(minInText);
				this.sec = Integer.parseInt(secInText);
				br.close();
				fileStream.close();
				JOptionPane.showMessageDialog(null, "載入今天記錄：\n\n" + this.year + "-" + this.month + "-" + this.day + "\n工作了 " + this.hour + ":" + this.min + ":" + this.sec );
			} catch (FileNotFoundException e1) {
				System.out.println("FileNotFound!");
				JOptionPane.showMessageDialog(null, "FileNotFound!");
				e1.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException!");
				JOptionPane.showMessageDialog(null, "IOException!");
				e.printStackTrace();
			}
		 }else{
			//若無，create new 
			System.out.println("今天尚未有記錄，開啟新記錄");
			JOptionPane.showMessageDialog(null, "無今天的記錄，create new ");
			this.year = Integer.parseInt(tokens[0]);
			this.month = Integer.parseInt(tokens[1]);
			this.day = Integer.parseInt(tokens[2]);
			this.hour = 0;
			this.min = 0;
			this.sec = 0;
		 }
	}


}

//private void setDateToMember(Date date) {
//	DateFormat  dateFormat = new SimpleDateFormat("yyyy MM dd");
//	String FormatedDateString = dateFormat.format(date);
//	String []tokens = FormatedDateString.split(" ");
//	this.year = Integer.parseInt(tokens[0]);
//	this.month = Integer.parseInt(tokens[1]);
//	this.day = Integer.parseInt(tokens[2]);
//}

//public void load() {
////若已有今天的記錄，load
////若無，create new
//boolean isFound = false;
//DateFormat  dateFormat = new SimpleDateFormat("yyyy MM dd");
//String FormatedDateString = dateFormat.format(new Date());
//String []tokens = FormatedDateString.split(" ");
//
//String searchFileName = "[Record]" + tokens[0] + "-" + tokens[1] + "-" + tokens[2] + ".ser";
//
//File workingDir = new File(  System.getProperty("user.dir") );  
//File [] FilesInWorkingDir = workingDir.listFiles();
//for(int i=0;i<FilesInWorkingDir.length;i++){
//	if(searchFileName.equalsIgnoreCase(FilesInWorkingDir[i].getName())){
//		isFound = true;
//		break;
//	}
//}
//if(isFound){
//	//若已有今天的記錄，load
//	System.out.println("已有今天的記錄，load");
//	FileInputStream fileStream;
//	try {
//		fileStream = new FileInputStream(searchFileName);
//		ObjectInputStream ois = new ObjectInputStream(fileStream);
//		Object record = ois.readObject();
//		if(record instanceof DailyWork){
//			int old_year = ((DailyWork) record).year;
//			int old_month = ((DailyWork) record).month;
//			int old_day = ((DailyWork) record).day;
//			int old_hour = ((DailyWork) record).hour;
//			int old_min = ((DailyWork) record).min;
//			int old_sec = ((DailyWork) record).sec;
//			
//			System.out.printf("將%d %d %d %d %d %d置換進dailyWork物件中%n",old_year,old_month,old_day,old_hour,old_min,old_sec);
//			this.year = old_year;
//			this.month=old_month;
//			this.day=old_day;
//			this.hour = old_hour;
//			this.min = old_min;
//			this.sec = old_sec;
//		}
//				
//		ois.close();
//		fileStream.close();
//	} catch (ClassNotFoundException e) {
//		System.out.println("ClassNotFound!");
//		e.printStackTrace();
//	} catch (FileNotFoundException e1) {
//		System.out.println("FileNotFound!");
//		e1.printStackTrace();
//	} catch (IOException e) {
//		System.out.println("IOException!");
//		e.printStackTrace();
//	}
// }else{
//	//若無，create new 
//	System.out.println("無今天的記錄，create new ");
//	this.year = Integer.parseInt(tokens[0]);
//	this.month = Integer.parseInt(tokens[1]);
//	this.day = Integer.parseInt(tokens[2]);
//	this.hour = 0;
//	this.min = 0;
//	this.sec = 0;
// }
//
//
//}

//public void save() {
//FileOutputStream fs;
//try {
//	String filename = "[Record]" + this.year + "-" + this.month + "-" + this.day +".ser";
//	fs = new FileOutputStream(filename);
//	ObjectOutputStream oos = new ObjectOutputStream(fs);
//	oos.writeObject(this);
//	oos.close();
//	fs.close();
//	System.out.println("Save到" + filename);
//} catch (FileNotFoundException e1) {
//	System.out.println("FileNotFoundException");
//	e1.printStackTrace();
//} catch (IOException e) {
//	System.out.println("IOException");
//	e.printStackTrace();
//}
//}