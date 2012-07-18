package andbas.Ch09Handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class Ch09Handler extends Activity {
	ImageView ivFlow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

    private void buildViews(){
        ivFlow = (ImageView) findViewById(R.id.ivIdFlow);
        userThread myThread = new userThread(this);
        myThread.start();
    }    

    Handler myHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 0:
				ivFlow.setImageResource(R.drawable.flow01b);
				break;
			case 1:
				ivFlow.setImageResource(R.drawable.flow02b);
				break;
			case 2:
				ivFlow.setImageResource(R.drawable.flow03b);
				break;
			case 3:
				ivFlow.setImageResource(R.drawable.flow04b);
				break;	
			}
			super.handleMessage(msg);
		}
    };
}