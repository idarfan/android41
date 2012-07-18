package andbas.Ch10CallIntentService;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

public class MyIntentService extends IntentService {
	private MediaPlayer mp;
	
	public MyIntentService() {
	    super("MyIntentService");
	}
	@Override
	protected void onHandleIntent(Intent intent) {
		// 在此工作執行緒中執行播放音樂的功能
		mp = MediaPlayer.create(this, R.raw.skycity);
		mp.start();
	}
}
