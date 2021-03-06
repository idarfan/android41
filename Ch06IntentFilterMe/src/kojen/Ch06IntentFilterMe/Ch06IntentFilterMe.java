package kojen.Ch06IntentFilterMe;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ch06IntentFilterMe extends Activity {
	private Button btDialer;
	private Button btBrowser;
	private Button btPlayer;
	private Button btPhoto;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		buildViews(); // user define
	}

	private void buildViews() {
		btDialer = (Button) findViewById(R.id.btIdDialer);
		btBrowser = (Button) findViewById(R.id.btIdBrowser);
		btPlayer = (Button) findViewById(R.id.btIdPlayer);
		btPhoto = (Button) findViewById(R.id.btIdPhoto);

		btDialer.setOnClickListener(btDialListener);
		btBrowser.setOnClickListener(btBrowListener);
		btPlayer.setOnClickListener(btPlayListener);
		btPhoto.setOnClickListener(btPhotListener);
	}

	private OnClickListener btDialListener = new OnClickListener() {
		public void onClick(View v) {
			Uri uri = Uri.parse("tel:0226532827");
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent);
		}
	};

	private OnClickListener btBrowListener = new OnClickListener() {
		public void onClick(View v) {
			Uri uri = Uri.parse("http://www.google.com/");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
	};
	/* 底下的寫法不能用File file = new File("/sdcard/tmp/rose.mp3"); 直指路徑的方式 */
	private OnClickListener btPlayListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()
					+ "/" + "tmp/rose.mp3");
			intent.setDataAndType(Uri.fromFile(file), "audio/*");
			startActivity(intent);
		}
	};
	private OnClickListener btPhotListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()
					+ "/" + "tmp/Photo1.jpg");
			intent.setDataAndType(Uri.fromFile(file), "image/*");
			startActivity(intent);
		}
	};
}