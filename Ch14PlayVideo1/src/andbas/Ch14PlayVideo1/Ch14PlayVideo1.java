package andbas.Ch14PlayVideo1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Ch14PlayVideo1 extends Activity {
	private String vedioPath; 
	private VideoView vvPlayVid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        buildViews();  //user define
	}

	private void buildViews() {
		vvPlayVid = (VideoView) findViewById(R.id.vvIdPlayVid);
		
		vedioPath = "/mnt/sdcard/tmp/bamboolake.3gp";
		vvPlayVid.setVideoPath(vedioPath);
		MediaController medioCont = new MediaController(this);
		vvPlayVid.setMediaController(medioCont);
		vvPlayVid.requestFocus();
		vvPlayVid.start();
	}
}