package andbas.Ch10StartService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class PlayMusicService extends Service {
	public static final int START_STICKY=1; 
	private MediaPlayer mp;
	private String Tag="PlayMusicService";

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(Tag,"onCreate");
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
//	public void onStart(Intent intent, int startId) {
		// 在此方法中撰寫希望在服務中要執行的工作
		super.onStart(intent, startId);
		mp = MediaPlayer.create(this, R.raw.skycity);
		mp.start();
		Log.e(Tag,"onStartCommand");
		return START_STICKY;
	}
	@Override
	public void onDestroy() {
		// 當服務結束時，停止音樂播放
		super.onDestroy();
		mp.stop();
		Log.e(Tag,"onDestroy");
	}
	@Override
	public IBinder onBind(Intent intent) {
		// 表示此服只能以tartService()啟動一個服務
		Log.e(Tag,"onBind");
		return null;
	}	
}
