package andbas.Ch10BindService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Ch10BindService extends Activity {
	private Button btEnd, btPlay,btStop;
	private String Tag="Ch10BindService";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
	}
	private void buildViews() {
		btEnd = (Button)findViewById(R.id.btIdEnd);
		btPlay = (Button)findViewById(R.id.btIdPlay);
		btStop = (Button)findViewById(R.id.btIdStop);

		btEnd.setOnClickListener(btEndListener);
		btPlay.setOnClickListener(btPlayListener);	
		btStop.setOnClickListener(btStopListener);	
	}

	private OnClickListener btEndListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};
    private OnClickListener btPlayListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(Ch10BindService.this, 
					PlayMusicService.class);
	        bindService(intent, serviceConn, Context.BIND_AUTO_CREATE);
			Log.e(Tag,"bindService");
		}
	};	
    private OnClickListener btStopListener = new OnClickListener() {
		public void onClick(View v) {
            unbindService(serviceConn);
			Log.e(Tag,"unbindService");
		}
	};	
    private ServiceConnection serviceConn = 
    		new ServiceConnection() {
    	public void onServiceConnected(ComponentName className, 
    			IBinder service) {
			Log.e(Tag,"onServiceConnected");
            Toast.makeText(Ch10BindService.this, 
            		"播放音樂的服務(Service)已經 連結.",
                    Toast.LENGTH_SHORT).show();
        }
    	
    	public void onServiceDisconnected(ComponentName className) {
			Log.e(Tag,"onServiceDisconnected");
            Toast.makeText(Ch10BindService.this, 
            		"播放音樂的服務(Service)已經 取消連結.",
                    Toast.LENGTH_SHORT).show();
        }
    };
}