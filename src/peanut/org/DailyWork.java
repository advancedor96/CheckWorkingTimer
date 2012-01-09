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
		System.out.println("�w�]10�����߰ݤ@��");
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
    	System.out.println("�[" + period +"��");
	}
	public void setPeriod(long period) {
		this.period = period;
    	System.out.println("�]�w�g���G" + this.period + "��");
	}
	public long getPeriod() {
		return this.period;
	}
	


	public String getCurrentWorkedTime() {
		return ""+this.hour + "��" + this.min + "��" + this.sec + "��" ;
	}

	
	public void saveToText() {
		/*�⤵�ѥثe���O���x�s���ɮ�*/
		FileOutputStream fs;
		//��~���ƭ��ন�T�w4�Ӧ��,��,��,��,��,���ƭ��ন�T�w2�Ӧ��
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
		//�Y�w�����Ѫ��O���Aload
		//�Y�L�Acreate new
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
			//�Y�w�����Ѫ��O���Aload
			System.out.println("�w�����Ѫ��O���Aload");
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
					System.out.println("�ɦW����P�ɮפ��e������šI�æ��Q�ק�L�I�Ȯ��~��..." +errorData);
					JOptionPane.showMessageDialog(null, "�ɦW����P�ɮפ��e������šI�æ��Q�ק�L�I�Ȯ��~��...\n" + errorData);
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
				JOptionPane.showMessageDialog(null, "���J���ѰO���G\n\n" + this.year + "-" + this.month + "-" + this.day + "\n�u�@�F " + this.hour + ":" + this.min + ":" + this.sec );
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
			//�Y�L�Acreate new 
			System.out.println("���ѩ|�����O���A�}�ҷs�O��");
			JOptionPane.showMessageDialog(null, "�L���Ѫ��O���Acreate new ");
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
////�Y�w�����Ѫ��O���Aload
////�Y�L�Acreate new
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
//	//�Y�w�����Ѫ��O���Aload
//	System.out.println("�w�����Ѫ��O���Aload");
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
//			System.out.printf("�N%d %d %d %d %d %d�m���idailyWork����%n",old_year,old_month,old_day,old_hour,old_min,old_sec);
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
//	//�Y�L�Acreate new 
//	System.out.println("�L���Ѫ��O���Acreate new ");
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
//	System.out.println("Save��" + filename);
//} catch (FileNotFoundException e1) {
//	System.out.println("FileNotFoundException");
//	e1.printStackTrace();
//} catch (IOException e) {
//	System.out.println("IOException");
//	e.printStackTrace();
//}
//}