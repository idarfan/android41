package andbas.Ch10CallMsgService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Ch10CallMsgService extends Activity{
	private String Tag = "Ch10CallMsgService1"; 
	private boolean isBound; 
    private TextView tvActRecMsg,tvServRecMsg;
    private Button btSendMsg,btEnd;
	private Messenger ActMsger; // 活動(客戶)端的信訊息傳遞者
	private Messenger ServMsger; // 服務端的訊息傳遞者
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
	    super.onCreate(savedInstanceState); 
	    setContentView(R.layout.main); 
        buildViews();   //user define
    }
	private void buildViews() {
		tvActRecMsg = (TextView)findViewById(R.id.tvIdActRecMsg);
		tvServRecMsg = (TextView)findViewById(R.id.tvIdServRecMsg);
		btSendMsg = (Button)findViewById(R.id.btIdSendMsg);
        btEnd = (Button)findViewById(R.id.btIdEnd);
        btSendMsg.setOnClickListener(btSendMsgListener);
        btEnd.setOnClickListener(btEndListener);
	}
	
    @Override
    protected void onStart() {
        super.onStart();
        // 活動一啟動時即綁定 MyMsgService 服務
        bindService(new Intent(this, MyMsgService.class), serviceConn,
           Context.BIND_AUTO_CREATE);
        Log.e(Tag, "onStart-->bindService"); 
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 活動結束時即取消綁定服務
        if (isBound) {
	        Log.e(Tag, "unbindService"); 
            unbindService(serviceConn);
            isBound = false;
        }
    }
    
	private ServiceConnection serviceConn = new ServiceConnection() { 
	    public void onServiceConnected(ComponentName name, 
	    		IBinder service) { 
	        Log.e(Tag, "onServiceConnected"); 
	        ServMsger = new Messenger(service); 
	        ActMsger = new Messenger(mHandler); 
	        isBound = true;
	    } 
	
	    public void onServiceDisconnected(ComponentName name) { 
            Log.e(Tag, "onServiceDisconnected"); 
	         ServMsger = null; 
	    } 
	}; 

	private OnClickListener btSendMsgListener = new OnClickListener() {
    	public void onClick(View v) { 
	        if (!isBound) return;
            Log.e(Tag, "btSendMsgListener：開始    活動<--->服務   互傳訊息 !"); 
    	    Message message = Message.obtain(null, 
    	    		MyMsgService.MSG_SET_VALUE); 

    	    // 把活動端的信訊息傳遞者(ActMsger)，隨著訊息傳送給服務端，
            // 以便服務端發送訊息給活動。 
    	    message.replyTo = ActMsger; 
    	    
    		Bundle ActBundle=new Bundle();
    		String stName1="11111";
    		ActBundle.putString("Name", stName1);
    		ActBundle.setClassLoader(getClassLoader());
    		message.setData(ActBundle);
    		message.arg1=1;
    		
	    	// 使用服務端的信訊息傳遞者(ServMsger)，並向服務端發送一個訊息。 
	    	try { 
    	    	ServMsger.send(message); //傳送訊息給服務
    	    } catch (RemoteException e) { 
    	        e.printStackTrace(); 
    	    }
    	}
    };
	  
	private Handler mHandler = new Handler() { 
	    @Override
	    public void handleMessage(Message msg) { 
	        switch (msg.what) { 
	        case MyMsgService.MSG_SET_VALUE:
	        	// 顯示 服務 所收到的訊息
	        	String st=MyMsgService.st2;
	        	tvServRecMsg.setTextColor(Color.RED);
	        	tvServRecMsg.setText(st);

		        Log.e(Tag, "活動的handleMessage-->" +
		        		   "活動 接收到 服務 傳過來的訊息 '22222' !"); 
	        	// 顯示 活動 所收到的訊息
	        	Bundle ServBundle=msg.getData();
	        	String stName2=ServBundle.getString("Name");
	        	tvActRecMsg.setTextColor(Color.GREEN);
	        	st="接著 活動 也收到從 服務  傳過來的 訊息 !\n" +"訊息內容為"+ 
		            	  stName2+"\n arg1="+msg.arg1;
	        	tvActRecMsg.setText(st);
	        	
	            break; 
	        default: 
	            super.handleMessage(msg); 
	        } 
	    } 
	}; 
    private OnClickListener btEndListener = new OnClickListener() {
    	public void onClick(View v) { 
            Log.e(Tag, "btEndListener-->finish"); 
    		finish();
    	}
    };
}
