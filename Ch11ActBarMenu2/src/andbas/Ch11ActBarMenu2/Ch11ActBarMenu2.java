package andbas.Ch11ActBarMenu2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class Ch11ActBarMenu2 extends Activity {
	private static final int VISIBLE = 0;
	private static final int INVISIBLE = 4;
	private MediaPlayer mp1=null,mp2=null;
	private ImageView ivPhoto;
	private SubMenu menu1,menu2;
	private boolean isPlaying1=false,isPlaying2=false;
	private static final int itPhoto = Menu.FIRST,
							 itMusic = Menu.FIRST + 1,
						 	 itInfo = Menu.FIRST + 2,
							 itStop = Menu.FIRST + 3,
							 subitPhoto1 = Menu.FIRST + 4,
							 subitPhoto2 = Menu.FIRST + 5,
							 subitMusic1 = Menu.FIRST + 6,
							 subitMusic2 = Menu.FIRST + 7;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.main);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu1 = menu.addSubMenu(0, itPhoto, 0, "瀏覽照片")
					 .setIcon(R.drawable.frame);
		MenuItem MenuItem1=menu1.getItem();
		MenuItem1.setShowAsAction(
				MenuItem.SHOW_AS_ACTION_IF_ROOM | 
        		MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		menu1.add(0, subitPhoto1, 0, "照片flow1.png");
		menu1.add(0, subitPhoto2, 1, "照片flow2.png");
		
		menu2 = menu.addSubMenu(0, itMusic, 0, "播放音樂")
					    .setIcon(R.drawable.music);	
		MenuItem MenuItem2=menu2.getItem();
		MenuItem2.setShowAsAction(
				MenuItem.SHOW_AS_ACTION_IF_ROOM | 
        		MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		menu2.add(0, subitMusic1, 0, "天空之城.midi");
		menu2.add(0, subitMusic2, 1, "灌藍高手.midi");

		MenuItem MenuItem3=menu.add(0, itInfo, Menu.NONE, "關於")
				 .setIcon(R.drawable.info);	
		MenuItem3.setShowAsAction(
				MenuItem.SHOW_AS_ACTION_IF_ROOM | 
        		MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        MenuItem MenuItem4=menu.add(0, itStop, Menu.NONE, "結束")
				 .setIcon(R.drawable.stop);	
        MenuItem4.setShowAsAction(
				MenuItem.SHOW_AS_ACTION_IF_ROOM | 
        		MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		ivPhoto = (ImageView)findViewById(R.id.ivIdPhoto);			
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String msg = "";
		ivPhoto.setVisibility(INVISIBLE);
		if (isPlaying1) {mp1.stop();isPlaying1=false;}
		if (isPlaying2) {mp2.stop();isPlaying2=false;}

		switch (item.getItemId()) {
		case itPhoto:
			msg = "您選擇 瀏覽照片 功能";
			break;
		case subitPhoto1:
			msg = "您選擇 瀏覽照片 功能-->瀏覽照片   flow1.png";
			ivPhoto.setVisibility(VISIBLE);
			ivPhoto.setImageResource(R.drawable.flow1);
			break;
		case subitPhoto2:
			msg = "您選擇 瀏覽照片 功能-->瀏覽照片   flow2.png";
			ivPhoto.setVisibility(VISIBLE);
			ivPhoto.setImageResource(R.drawable.flow2);
			break;
			
		case itMusic:
			msg = "您選擇 播放音樂 功能";
			if (isPlaying1) {mp1.stop();isPlaying1=false;}
			if (isPlaying2) {mp2.stop();isPlaying2=false;}
			break;
		case subitMusic1:
			msg = "您選擇 播放音樂 功能-->播放   天空之城.midi";
			mp1 = MediaPlayer.create(this, R.raw.skycity);
			mp1.start();
			isPlaying1=true;
			break;
		case subitMusic2:
			msg = "您選擇 播放音樂 功能-->播放   灌藍高手.midi";
			mp2 = MediaPlayer.create(this, R.raw.basket);
			mp2.start();		
			isPlaying2=true;
			break;

		case itInfo:
			msg = "您選擇 關於 功能";
			ShowDial();
			break;
		case itStop:
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
				new AlertDialog.Builder(Ch11ActBarMenu2.this){};
		adBuild.setTitle("關於本程式");
		adBuild.setMessage("本程式示範  自訂動作列功能表  之設計 !");
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