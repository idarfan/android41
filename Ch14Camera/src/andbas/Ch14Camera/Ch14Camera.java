package andbas.Ch14Camera;

import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Ch14Camera extends Activity {
    private final String tag = getClass().getName();
	private Camera UserCamera; //相機
	private SurfaceView sfView; //相機預覽
	private SurfaceHolder sfHolder;
	private boolean isPreview = false; //是否在預覽狀態
	private String path = "/sdcard/photo01.jpg";//照片存放路徑
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
		    try {  
		    	UserCamera.setPreviewDisplay(holder);  
		    }  
		    catch (Exception e) {  
		        e.printStackTrace();  
		    } 
		}		
		public void surfaceChanged(SurfaceHolder holder, 
				int format, int width, int height) {		
			Parameters params = UserCamera.getParameters();	
			params.setPreviewSize(width, height);
			params.setFlashMode("auto"); 
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
			if(UserCamera != null){ 
				UserCamera.release();
				UserCamera = null;
			}
		}		
	};

    private Camera.AutoFocusCallback AutoFocListener =  
			    new Camera.AutoFocusCallback() {    
		public void onAutoFocus(boolean success,final Camera camera) {
			
			if (success) {
		        camera.takePicture(ShutterListener,null, PictureListener);  

		        new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						BeginPreview();
					}
				}.start(); 
			}
		}		
	};
	private Camera.ShutterCallback ShutterListener =   
			    new Camera.ShutterCallback() {  
	    public void onShutter() { 
	        Log.i(tag, "onShutter");  
	    }  
	};  

	private Camera.PictureCallback PictureListener =   
			new Camera.PictureCallback() {     
	    public void onPictureTaken(byte[] data, Camera camera) {  
	        FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(path);
				fos.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} 
	    }  
	};  
	
	private OnClickListener btnListener=new OnClickListener() {     
	    public void onClick(View v) {  
	       if(UserCamera != null){
	    	  UserCamera.autoFocus(AutoFocListener);  
	       }  
	    }  
	};    
	private void BeginPreview() {
		if(UserCamera != null && !isPreview){
			UserCamera.startPreview();
			isPreview = true;
			System.gc(); 
		}
	}
	private void StopPreview(){
		if(UserCamera != null && isPreview){
			UserCamera.stopPreview();
			isPreview = false;
		}
	}
}