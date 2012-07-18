package andbas.Ch09Notify;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ch09Notify extends Activity {
	private final static int Ntf_ID = 0;
	private Button btNtf, btCanNtf;
	private NotificationManager ntfMgr;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
	}

	private void buildViews() {
		ntfMgr = (NotificationManager) 
				getSystemService(NOTIFICATION_SERVICE);		
		
		btNtf = (Button)findViewById(R.id.btIdNtf);
		btCanNtf = (Button)findViewById(R.id.btIdCanNtf);

		btNtf.setOnClickListener(btNtfListener);
		btCanNtf.setOnClickListener(btCanNtfListener);	
	}

	private OnClickListener btNtfListener = new OnClickListener() {
		public void onClick(View v) {
			int icon = R.drawable.icon1;        
			CharSequence tickerText = "軟體更新通知";              
			long when = System.currentTimeMillis();         
			Notification ntf = new Notification(icon,tickerText, when); 

			Context context = getApplicationContext();      
			CharSequence contentTitle = "軟體更新通知";  
			CharSequence contentText = "已有新版軟體";      
			Intent intent=new Intent(context, Ch09Notify.class);
			PendingIntent pi = PendingIntent.getActivity(context,0, intent, 0); 
			ntf.setLatestEventInfo(context,contentTitle,contentText,pi); 

			ntfMgr.notify(Ntf_ID, ntf);
		}
	};
    private OnClickListener btCanNtfListener = new OnClickListener() {
		public void onClick(View v) {
			ntfMgr.cancel(Ntf_ID); 
		}
	};	
}