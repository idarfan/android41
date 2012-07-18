package andbas.Ch10CallIntentService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ch10CallIntentService extends Activity {
	private Button btEnd, btPlay;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
	}
	private void buildViews() {
		btEnd = (Button)findViewById(R.id.btIdEnd);
		btPlay = (Button)findViewById(R.id.btIdPlay);

		btEnd.setOnClickListener(btEndListener);
		btPlay.setOnClickListener(btPlayListener);	
	}

	private OnClickListener btEndListener = new OnClickListener() {
		public void onClick(View v) {
			// 結束活動，但被此活動的服務並未跟著結束，該活動繼續執行，直到結束為止。
			finish();
		}
	};
    private OnClickListener btPlayListener = new OnClickListener() {
		public void onClick(View v) {
			// 以 startService()啟動一個 IntentService，
			// 在IntentService自己的工作執行緒(worker thread)中執行播放音樂
			Intent intent = new Intent(Ch10CallIntentService.this, 
					MyIntentService.class);
			startService(intent);
		}
	};	
}