package andbas.Ch10userBroadCast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ch10userBroadCast extends Activity {
	private Button btSendBD;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }
    private void buildViews() {
    	btSendBD = (Button) this.findViewById(R.id.btIdSendBD);
    	btSendBD.setOnClickListener(btSendBDListener);
    }
        
    private OnClickListener btSendBDListener = 
    		new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setAction("andbas.broadcast.send.UPDATED");
			intent.putExtra("msg", "已完成更新作業 !");
			sendBroadcast(intent);
		}   	
    };
}