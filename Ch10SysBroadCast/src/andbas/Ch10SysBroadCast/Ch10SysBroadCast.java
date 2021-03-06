package andbas.Ch10SysBroadCast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ch10SysBroadCast extends Activity {
	private Button btEnd;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }
    private void buildViews() {
    	btEnd = (Button) this.findViewById(R.id.btIdEnd);
    	btEnd.setOnClickListener(btEndListener);
    }
        
    private OnClickListener btEndListener = 
    		new OnClickListener() {
		public void onClick(View v) {
			System.exit(0);
		}   	
    };
}