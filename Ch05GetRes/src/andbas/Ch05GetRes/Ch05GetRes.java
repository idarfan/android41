package andbas.Ch05GetRes;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Ch05GetRes extends Activity {
	private TextView tvText;
	private TextView tvString;
	private TextView tvRes;
	private ImageView ivFlow;
	private MediaPlayer mp;
	private Button btStop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		buildViews();  //user define
	}

	private void buildViews() {
		tvText = (TextView) findViewById(R.id.tvIdText);
		tvString = (TextView) findViewById(R.id.tvIdString);
		tvRes = (TextView) findViewById(R.id.tvIdRes);
		ivFlow = (ImageView)findViewById(R.id.ivIdFlow);		
		CharSequence cs;
		String str;		
		
		cs = getText(R.string.stytext);
		tvText.setText(cs);
		
		str = getString(R.string.stytext);
		tvString.setText(str);
		
		Resources res = getResources();		
		cs = res.getText(R.string.stytext);
		tvRes.setText(cs);		
		ivFlow.setImageDrawable(res.getDrawable(R.drawable.flow03b));		
		mp = MediaPlayer.create(Ch05GetRes.this, R.raw.skycity);
		mp.start();
		
		btStop = (Button)findViewById(R.id.btIdStop);		
		btStop.setOnClickListener(btStopListener);		
	}
    private OnClickListener btStopListener = new OnClickListener() {
		public void onClick(View v) {
			mp.stop();
			finish();
		}
	};	
}