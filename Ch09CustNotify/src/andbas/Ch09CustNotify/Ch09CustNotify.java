package andbas.Ch09CustNotify;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class Ch09CustNotify extends Activity {
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
	        ShowCustNotify(); //user define
		}
	};	
	private void ShowCustNotify() {
		int icon = R.drawable.icon1;        
		CharSequence tickerText = "軟體更新通知";              
		long when = System.currentTimeMillis();         
		Notification ntf = new Notification(icon,tickerText, when); 

		RemoteViews contentView = new RemoteViews(getPackageName(), 
				                      R.layout.custnotify);
		contentView.setImageViewResource(R.id.image, icon);
		contentView.setTextViewText(R.id.title, "軟體更新通知");
		contentView.setTextViewText(R.id.text, "已有新版軟體可下載");
		ntf.contentView = contentView;

		Intent intent = new Intent(this, Ch09CustNotify.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		ntf.contentIntent = pi;
		
		ntfMgr.notify(Ntf_ID, ntf);
	}
	
    private OnClickListener btCanNtfListener = new OnClickListener() {
		public void onClick(View v) {
			ntfMgr.cancel(Ntf_ID); 
		}
	};	
}