package kojen.Ch06StartActWoBundleMe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ch06StartActWoBundleMe extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		buildViews(); // user define
	}

	private void buildViews() {
		final Button btSure;
		btSure = (Button) findViewById(R.id.btIdSure);
		btSure.setOnClickListener(btListener);
	}

	private OnClickListener btListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Ch06StartActWoBundleMe.this, LogSuccAct.class);
			startActivity(intent);
		}
	};
}