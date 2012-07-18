package andbas.Ch10StartService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ch10StartService extends Activity {
	private Button btEnd, btPlay,btStop;
	private String Tag="Ch10StartService";

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
			// 由此活動以startService()啟動一個 PlayMusicService 的服務以播放音樂
			Intent intent = new Intent(Ch10StartService.this, 
					PlayMusicService.class);
			startService(intent);
			Log.e(Tag,"startService");
		}
	};	
    private OnClickListener btStopListener = new OnClickListener() {
		public void onClick(View v) {
			// 結束一個 播放音樂 的服務
			Intent intent = new Intent(Ch10StartService.this, 
					PlayMusicService.class);
			stopService(intent);
			Log.e(Tag,"stopService");
		}
	};	
}