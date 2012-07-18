package andbas.Ch14PlayAudio2;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Ch14PlayAudio2 extends Activity {
    private ListView lvMus;
	private ImageButton ibPlay,ibPause,ibStop;
    private String msg,title;
    private String musicPath=null;
	private MediaPlayer mp; 
	private boolean isStoped = true; 
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        buildViews();  //user define
    }

    private void buildViews(){
    	lvMus = (ListView)findViewById(R.id.lvIdMus);
    	ArrayAdapter <CharSequence> adMsuList = 
    			ArrayAdapter.createFromResource(
				this, R.array.lvMusRes, 
				android.R.layout.simple_list_item_1);
    	lvMus.setAdapter(adMsuList);
    	lvMus.setOnItemClickListener(lvListener);

    	ibPlay = (ImageButton)findViewById(R.id.ibIdPlay);    
    	ibPause = (ImageButton)findViewById(R.id.ibIdPause);    
    	ibStop = (ImageButton)findViewById(R.id.ibIdStop);    

    	ibPlay.setOnClickListener(ibPlayListener);         	
    	ibPause.setOnClickListener(ibPauseListener);         	
    	ibStop.setOnClickListener(ibStopListener);         	
    }
	
	private OnItemClickListener lvListener = 
			new OnItemClickListener() {    
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			msg="您選擇欲播放的音樂來源：\n";
			title = ((TextView) view).getText().toString();
			if (title.equals("來自SDCard")){
				musicPath="/mnt/sdcard/tmp/cherry.mp3";
			} else if  (title.equals("來自網站資源")){
				musicPath="http://203.64.253.13/cherry.mp3";				
			} 
			msg=msg+title+"\n"+musicPath;
			Toast.makeText(Ch14PlayAudio2.this,
					msg,Toast.LENGTH_SHORT).show();			
		}
	};
    private OnClickListener ibPlayListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {  
			if(mp == null || isStoped){ 
				mp = new MediaPlayer();
				isStoped = false;
			}
			try {
				mp.reset(); 
				mp.setAudioStreamType(AudioManager.STREAM_MUSIC); 
				mp.setDataSource(musicPath);	
				mp.prepare(); 
				mp.start();
			} catch (Exception e) {
				Log.e("播放音樂錯誤 !", e.toString());
			}		
		}
	};
    private OnClickListener ibPauseListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {  
			if(mp == null || isStoped)	return;
			mp.pause();
		}
	};
    private OnClickListener ibStopListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {  
			if(mp == null || isStoped) 	return;
			mp.stop();
			isStoped = true;
		}
	};
	@Override
	protected void onPause() {
		super.onPause();
		if (mp != null) {
			mp.release();
			mp = null;
		}
	}
}