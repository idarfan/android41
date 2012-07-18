package andbas.Ch10CallMsgService;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MyMsgService extends Service {
    private String Tag = "MyMsgService"; 
    static final int MSG_SET_VALUE = 1; 
    static String st2=null; 
    
    @Override
    public void onDestroy() { 
        Log.e(Tag, "onDestroy"); 
        ActMsger = null; 
        super.onDestroy(); 
    } 
  
    @Override
    public boolean onUnbind(Intent intent) { 
        Log.e(Tag, "onUnbind"); 
        return super.onUnbind(intent); 
    } 
  
    private Handler mHandler = new Handler() { 
        @Override
        public void handleMessage(Message msg) { 
            Log.e(Tag, "服務的handleMessage-->服務 接收到 活動 傳過來的訊息'11111'" +
            		   "，也傳送訊息'22222'給活動 !"); 
            switch (msg.what) { 
            case MSG_SET_VALUE: 
                try { 
    	        	Bundle ActBundle=msg.getData();
    	        	String stName1=ActBundle.getString("Name");
    	            st2="服務先  接收到從 活動  傳過來的 訊息 !\n" +"訊息內容為"+ 
			            	  stName1+"\n arg1="+msg.arg1;
                	Message message = Message.obtain(null, 
                    		MyMsgService.MSG_SET_VALUE);                      
                    //得到活動(客戶)端的訊息傳遞者，並利用它向活動(客戶)端傳送訊息 
                    ActMsger = msg.replyTo;
                    
            		Bundle ServBundle=new Bundle();
            		String stName2="22222";
            		ServBundle.putString("Name", stName2);
            		ServBundle.setClassLoader(getClassLoader());
            		message.setData(ServBundle);
                    message.arg1 = 2; 
                                		
                    ActMsger.send(message);//傳送訊息給活動 
                } catch (RemoteException e) { 
                    e.printStackTrace(); 
                } 
                break; 
            default: 
                super.handleMessage(msg); 
            } 
        } 
    }; 
  
    // 服務端的訊息傳遞者
    private Messenger ServMsger = new Messenger(mHandler); 
    private Messenger ActMsger; // 活動(客戶)端的信訊息傳遞者
  
    @Override
    public IBinder onBind(Intent intent) { 
        Log.e(Tag, "onBind"); 
        //返回自己信訊息傳遞者的bindler,以供客戶端通過這個bindler
    	//得到服務端的信訊息傳遞者（通過new Messenger(bindler)） 
        return ServMsger.getBinder(); 
    } 
  
    @Override
    public void onRebind(Intent intent) { 
        Log.e(Tag, "onRebind");   
    }   
}
