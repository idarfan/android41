package andbas.Ch14PlayVideo2;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Ch14PlayVideo2 extends Activity {
	private final String tag = getClass().getName(); 
	private int videoWidth; //video寬度
	private int videoHeight; //video高度
	private MediaPlayer mp; 
	private SurfaceView sfView; //SurfaceView元件，用來呈現video畫面
	private SurfaceHolder sfHolder; //控制surface的SurfaceHolder元件
	private String vedioPath; //video路徑
	private boolean isVideoSizeKnown = false; //是否取得video尺寸
	private boolean isVideoReady = false; //video是否準備妥當

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		buildViews();  //user define
	}
	private void buildViews() { 		
		vedioPath = "/mnt/sdcard/tmp/bamboolake.3gp";
		sfView = (SurfaceView) findViewById(R.id.svIdScreen);
		sfHolder = sfView.getHolder();
		sfHolder.addCallback(sfHolderListener);
	}	
	private SurfaceHolder.Callback sfHolderListener = 
			new SurfaceHolder.Callback() {
		public void surfaceCreated(SurfaceHolder holder) {
			startMedPlay();
		}
		public void surfaceChanged(SurfaceHolder holder, 
				int format, int width, int height) {
			Log.d(tag, "surfaceChanged called");
		}
		public void surfaceDestroyed(SurfaceHolder surfaceholder) {
			releaseMedPlayer();
			doReset();
		}
	};
	
	private void startMedPlay() {
		doReset();
		if(mp == null)
			mp = new MediaPlayer();
		try {
			mp.setDataSource(vedioPath);
//			mp=MediaPlayer.create(this, R.raw.bamboolake);
			mp.setDisplay(sfHolder);
			mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
			
			// 如果以59行的create()方法讀取資源，會自動執行prepare()方法，
			// 因此不需執行下一行
			mp.prepare();  
			mp.setOnBufferingUpdateListener(mpBufUpdListener);
			mp.setOnCompletionListener(mpCompletListener);
			mp.setOnPreparedListener(mpPrepListener);
			mp.setOnVideoSizeChangedListener(mpVidSizeChgListener);
			mp.start();
		} catch (Exception e) {
			Log.e(tag, e.toString());
		}
	}

	private void playVideo() {
		sfHolder.setFixedSize(videoWidth, videoHeight);
		mp.start();
	}

    private OnBufferingUpdateListener mpBufUpdListener = 
    		new OnBufferingUpdateListener() {    
		public void onBufferingUpdate(MediaPlayer mp, int percent) {
			Log.d(tag, "onBufferingUpdate percent:" + percent);
		}
    };
    private OnCompletionListener mpCompletListener = 
    		new OnCompletionListener() {    
		public void onCompletion(MediaPlayer mp) {
			Log.d(tag, "onCompletion called");
		}
    };
    private OnPreparedListener mpPrepListener = 
    		new OnPreparedListener() {    
		public void onPrepared(MediaPlayer mp) {
			isVideoReady = true;
			if (isVideoSizeKnown) {
				playVideo();
			}
		}
    };
    private OnVideoSizeChangedListener mpVidSizeChgListener = 
    		new OnVideoSizeChangedListener() {    
		public void onVideoSizeChanged(
				MediaPlayer mp, int width, int height) {
			if (width == 0 || height == 0) {
				Log.e(tag, 
					"invalid video width(" + width + 
					") or height(" + height	+ ")");
				return;
			}
			isVideoSizeKnown = true;
			videoWidth = width;
			videoHeight = height;
			if (isVideoReady) {
				playVideo();
			}
		}
    };
	private void releaseMedPlayer() {
		if (mp != null) {
			mp.release();
			mp = null;
		}
	}
	private void doReset() {
		videoWidth = 0;
		videoHeight = 0;
		isVideoReady = false;
		isVideoSizeKnown = false;
	}
}