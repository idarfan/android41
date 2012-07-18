package andbas.Ch14RecordAudio;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ch14RecordAudio extends Activity {
	private final String tag = getClass().getName();
	private MediaRecorder medioRec; //錄音器
	private TextView tvInfo; 
	private Button btRec; //「錄音」按鈕
	private Button btStopRec; //「停止錄音」按鈕
	private Button btPlay; //「播放」按鈕
	private Button btStopPlay; //「停止播放」按鈕
	private String path; 
	private MediaPlayer mp; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		buildViews();  //user define	
	}
	private void buildViews() {
		path = "/mnt/sdcard/tmp/audio01.3gp"; //錄音檔存放路徑
		tvInfo = (TextView)findViewById(R.id.tvInfo);
		btRec = (Button) findViewById(R.id.btIdRec);
		btStopRec = (Button) findViewById(R.id.btIdStopRec);
		btPlay = (Button) findViewById(R.id.btIdPlay);
		btStopPlay = (Button) findViewById(R.id.btIdStopPlay);
		
		btRec.setOnClickListener(btRecListener);
		btStopRec.setOnClickListener(btStopRecListener);
		btPlay.setOnClickListener(btPlayListener);
		btStopPlay.setOnClickListener(btStopPlayListener);
	}	
	//按下「錄音」鈕，所要處理的事情
    private OnClickListener btRecListener =new OnClickListener() {    
		public void onClick(View view) {
			String state = Environment.getExternalStorageState();
			if (! state.equals(Environment.MEDIA_MOUNTED)){
				Toast.makeText(Ch14RecordAudio.this, 
						"SDCard不存在，無法儲存 !", 
						Toast.LENGTH_LONG).show();
				return;
			}
			btRec.setEnabled(false); 
			recAudio();
		}
	};
	//按下「停止錄音」鈕，所要處理的事情
    private OnClickListener btStopRecListener =new OnClickListener() {    
		public void onClick(View view) {
			if(medioRec != null){
				medioRec.stop();
				medioRec.release();
				medioRec = null;
				tvInfo.setText("錄音檔存放路徑：" + path);
				btRec.setEnabled(true); 
			}				
		}
	};		

	private OnClickListener btPlayListener =new OnClickListener() {    
		public void onClick(View v) {
			playAudio(path);
		}
	};
    private OnClickListener btStopPlayListener =new OnClickListener() {    
		public void onClick(View v) {
			if (mp != null) {
				mp.stop();
			}
		}
	};
	
	private void recAudio() {
		if(medioRec == null)
			medioRec = new MediaRecorder();
		else
			medioRec.reset();
		try {
			medioRec.setAudioSource(MediaRecorder.AudioSource.MIC);
			medioRec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			medioRec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			medioRec.setOutputFile(path);				
			medioRec.prepare();
			medioRec.start();
		} catch (Exception e) {
			Log.e(tag, e.toString());
		}				
		tvInfo.setText("錄音進行中...");		
	}
	private void playAudio(String path) {
		tvInfo.setText("錄音檔案來源：" + path);
		if(mp == null)
			mp = new MediaPlayer();
		try {
			mp.reset(); 
			mp.setDataSource(path);	
			mp.setAudioStreamType(AudioManager.STREAM_MUSIC); 
			mp.prepare(); 
			mp.start();
		} catch (Exception e) {
			Log.e(tag, e.toString());
		}		
	}
	@Override
	protected void onPause() {
		super.onPause();
		if (mp != null) {
			mp.release();
			mp = null;
		}
	}
}