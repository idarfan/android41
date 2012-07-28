package kojen.Ch06IntentFilterMe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AnotherAct extends Activity {
	String msg, stAct, stScheme,stData;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anotheract);
        
        buildViews();  //user define
    }
    
    private void buildViews() {
        Intent intent = getIntent();
        String stAct = intent.getAction();
        String stScheme = intent.getScheme();
        String stData=intent.getType();
        if (stScheme.equals("tel")) {
        	msg = "您現在使用替代活動<撥打電話> " ;
        }
        else if (stScheme.equals("http")) {
        	msg = "您現在使用替代活動<開啟網頁>" ;
       }
        else if (stScheme.equals("file")) {
        	if (stData.equals("audio/*")) {
        		msg= "您現在使用替代活動<播放音樂>";
	        }
        	else if (stData.equals("image/*")) {
        		msg= "您現在使用替代活動<檢視照片>";
 	        }
		}
        msg = msg +
              "  \n Action =" + stAct +
              "  \n Scheme =" + stScheme +
		      "  \n Data Type="+stData;
	    Toast.makeText(this,
	    		msg,
				Toast.LENGTH_SHORT)
		      .show();
	    finish();
   }	
}
