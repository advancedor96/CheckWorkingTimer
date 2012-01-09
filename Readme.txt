=======簡介=========
由於未來想往軟體業發展，每天工作時都是在電腦前，
但雖說在工作，很容易不小心打開facebook，ptt等等去放鬆心情

為了檢視自己『一天到底認真工作多久』
便想寫一程式，每隔10分鐘就跳出來問你：『剛剛10分鐘是不是在工作？』
是的話，就把今天的工作時間加10分鐘；不是的話就不增加今
天的工作時間。

這樣一來，即使自己離開電腦很久才回來也不會失去精確度~

=======程式介紹=======
1.一開始跳一個視窗，讓使用者決定要隔多久時間詢問一次？
2.把每天的記錄儲存成一般的純文字檔案，上面標有日期、工作時間

========截圖==========
http://imageshack.us/photo/my-images/268/cwtforjavaworld.png/


=========問題=========
這個程式小的改了很久，程式碼也盡量物件導向化
或是程式的功能還需要加強的？
不知道能不能請各位前輩幫我看看？
(我自己review好幾次了~~或許給別人看比較容易看出盲點)

=========程式碼=========

AskPeriod.java : main函數所在。詢問使用者每隔多久時間詢問一次的視窗。
https://gist.github.com/1518697#file_ask_period.java


TimeObserver.java :負責控制時間一到，跳出視窗
https://gist.github.com/1518702


AskWorkWindow.java :有點冗的物件，只是為了呼叫AskWorkDialog
                    (但也不知怎麼避免多此物件)
https://gist.github.com/1518700

AskWorkDialog.java : 詢問使用者是不是在工作的視窗
https://gist.github.com/1518699

DailyWork.java: 儲存每天工作記錄的物件
https://gist.github.com/1518701


如果有寫不好的地方 麻煩各位先進不吝指教 謝謝~