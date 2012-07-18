package andbas.Ch10BindService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class PlayMusicService extends Service {
	private MediaPlayer mp;
	private String Tag="PlayMusicService";

	@Override
	public void onCreate() {
		Log.e(Tag,"onCreate");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// 當服務結束時，停止音樂播放
		Log.e(Tag,"onDestroy");
		super.onDestroy();
		mp.stop();
	}

	public class PlayBinder extends Binder {
		PlayMusicService getService() {
			Log.e(Tag,"getService");
			return PlayMusicService.this;
		}
	}
	
	private final IBinder playBinder = new PlayBinder();

	@Override
	public IBinder onBind(Intent intent) {
		Log.e(Tag,"onBind");
		// 在此方法中撰寫希望在服務中要執行的工作
		mp = MediaPlayer.create(this, R.raw.skycity);
		mp.start();		
		return playBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.e(Tag,"onUnbind");
		// 在此方法中撰寫當取消綁定服務時，要執行的工作
		return super.onUnbind(intent);
	}	
}
