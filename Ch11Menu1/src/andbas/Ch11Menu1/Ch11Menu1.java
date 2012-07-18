package andbas.Ch11Menu1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class Ch11Menu1 extends Activity {
	private static final int VISIBLE = 0;
	private static final int INVISIBLE = 4;
	private MediaPlayer mp1=null,mp2=null;
	private ImageView ivPhoto;
	private boolean isPlaying1=false,isPlaying2=false;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		ivPhoto = (ImageView)findViewById(R.id.ivIdPhoto);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String msg = "";
		ivPhoto.setVisibility(INVISIBLE);
		if (isPlaying1) {mp1.stop();isPlaying1=false;}
		if (isPlaying2) {mp2.stop();isPlaying2=false;}

		switch (item.getItemId()) {
		case R.id.itPhoto:
			msg = "您選擇 瀏覽照片 功能";
			break;
		case R.id.subitPhoto1:
			msg = "您選擇 瀏覽照片 功能-->瀏覽照片   flow1.png";
			ivPhoto.setVisibility(VISIBLE);
			ivPhoto.setImageResource(R.drawable.flow1);
			break;
		case R.id.subitPhoto2:
			msg = "您選擇 瀏覽照片 功能-->瀏覽照片   flow2.png";
			ivPhoto.setVisibility(VISIBLE);
			ivPhoto.setImageResource(R.drawable.flow2);
			break;
			
		case R.id.itMusic:
			msg = "您選擇 播放音樂 功能";
			if (isPlaying1) {mp1.stop();isPlaying1=false;}
			if (isPlaying2) {mp2.stop();isPlaying2=false;}
			break;
		case R.id.subitMusic1:
			msg = "您選擇 播放音樂 功能-->播放   天空之城.midi";
			mp1 = MediaPlayer.create(this, R.raw.skycity);
			mp1.start();
			isPlaying1=true;
			break;
		case R.id.subitMusic2:
			msg = "您選擇 播放音樂 功能-->播放   灌藍高手.midi";
			mp2 = MediaPlayer.create(this, R.raw.basket);
			mp2.start();		
			isPlaying2=true;
			break;

		case R.id.itInfo:
			msg = "您選擇 關於 功能";
			ShowDial();
			break;
		case R.id.itStop:
			msg = "您選擇 結束 功能--->將結束所有作業";
			if (isPlaying1) {mp1.stop();isPlaying1=false;}
			if (isPlaying2) {mp2.stop();isPlaying2=false;}
			finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		return true;
	}
	private void ShowDial() {      
		AlertDialog.Builder adBuild=
				new AlertDialog.Builder(Ch11Menu1.this){};
		adBuild.setTitle("關於本程式");
		adBuild.setMessage("本程式示範  功能表  之設計 !");
		adBuild.setCancelable(false);
		adBuild.setPositiveButton("確定", aldBtListener);
		adBuild.show();
	}
    private DialogInterface.OnClickListener aldBtListener = 
    		new DialogInterface.OnClickListener() {    
    	public void onClick(DialogInterface dialog, int id) {      
    	}
    };
}