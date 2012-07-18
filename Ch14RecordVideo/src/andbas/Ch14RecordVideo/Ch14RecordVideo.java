package andbas.Ch14RecordVideo;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class Ch14RecordVideo extends Activity {
	private final String tag = getClass().getName();
	private Camera UserCamera;
	private SurfaceView sfView;
	private SurfaceHolder sfHolder;
	private boolean isPreview = false;
	private boolean isRecord = false; //是否錄影中
	private MediaRecorder mediaRec;
	private CamcorderProfile profile; //錄影設定檔
	int videoWidth; //video寬度
	int videoHeight; //video高度
	private int maxDurationInMs = 10 * 60 * 1000; //錄影時間限制
	private long maxFileSizeInBytes = 10 * 1024 * 1024; //錄影檔案大小限制
	private String path = "/mnt/sdcard/recvideo.3gp";
	private ImageButton ibCamera;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		setContentView(R.layout.main); 
		buildViews();  //user define
	}
	private void buildViews() {		
		sfView = (SurfaceView)findViewById(R.id.svIdScreen);
		sfHolder = sfView.getHolder();
		sfHolder.addCallback(sfHolderListener);
		sfHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		ibCamera = (ImageButton)findViewById(R.id.ibIdCamera);  
		ibCamera.setOnClickListener(btnListener);  
	}
	private SurfaceHolder.Callback sfHolderListener = 
			new SurfaceHolder.Callback() {
		public void surfaceCreated(SurfaceHolder holder) {
			if(UserCamera == null) UserCamera=Camera.open(); 
		    try {UserCamera.setPreviewDisplay(holder); }  
		    catch (Exception e) {e.printStackTrace(); } 
		}
		public void surfaceChanged(SurfaceHolder holder, 
				int format, int width, int height) {	
			Parameters params = UserCamera.getParameters();
			params.setPreviewSize(width, height);
			videoWidth = width;
			videoHeight = height;
			UserCamera.setParameters(params);			
			try {
				UserCamera.setPreviewDisplay(holder); 				 
				BeginPreview();
			} catch (IOException e) {
				Log.e(tag, e.toString());
			}
		}
		public void surfaceDestroyed(SurfaceHolder holder) {
			StopPreview();
			if(mediaRec != null){
				mediaRec.release();
				mediaRec = null;
			}
			if(UserCamera != null){ 
				UserCamera.release();
				UserCamera = null;
			}
		}		
	};
	private OnClickListener btnListener=new OnClickListener() {     
	    public void onClick(View v) {  
			String state = Environment.getExternalStorageState();
			if (! state.equals(Environment.MEDIA_MOUNTED)){
				Toast.makeText(Ch14RecordVideo.this, 
						"SDCard不存在，無法儲存 !", Toast.LENGTH_LONG).show();
			}
			
			if(isRecord)
				stopRecord();
			else
				startRecord();
	    }  
	};    
	private void startRecord() {
		BeginPreview();
		if(mediaRec == null)
			mediaRec = new MediaRecorder();
		else
			mediaRec.reset();		
		UserCamera.unlock();
		mediaRec.setCamera(UserCamera);
		profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
		profile.videoFrameWidth = videoWidth; 
		profile.videoFrameHeight = videoHeight;
		try {
			mediaRec.setAudioSource(MediaRecorder.AudioSource.MIC);
			mediaRec.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			mediaRec.setProfile(profile);
			mediaRec.setOutputFile(path);
			mediaRec.setMaxDuration(maxDurationInMs); 
			mediaRec.setPreviewDisplay(sfHolder.getSurface());
			mediaRec.setMaxFileSize(maxFileSizeInBytes); 
			mediaRec.prepare();
		} catch (Exception e) {
			Log.e(tag, e.toString());			
		}
		mediaRec.start();
		isRecord = true;
		Toast.makeText(this, "錄影進行中...",Toast.LENGTH_SHORT).show();		
	}
		
	private void stopRecord() {
		if(mediaRec != null){
			Toast.makeText(this, "停止錄影",Toast.LENGTH_SHORT).show();
			Toast.makeText(this,"錄影檔存放路徑：" 
			            + path.toString(),Toast.LENGTH_LONG).show();
			mediaRec.stop();
			isRecord = false;
			mediaRec.release();
			mediaRec = null;
			try {
				UserCamera.reconnect();
			} catch (IOException e) {
				Log.e(tag, e.toString());
			}
			StopPreview();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					BeginPreview();
				}
			};
			Timer timer = new Timer();
			timer.schedule(task, 5 * 1000);
		}		
	}

	private void BeginPreview(){
		if(UserCamera != null && !isPreview){
			UserCamera.startPreview();
			isPreview = true;
		}
	}
	private void StopPreview(){
		if(UserCamera != null && isPreview){
			UserCamera.stopPreview();
			isPreview = false;
		}
	}
}