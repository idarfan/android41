package andbas.Ch11TabHost2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialBrow extends Activity {
	private Button btDialer,btBrowser;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dial);

        buildViews();  //user define
    }

    private void buildViews(){
    	btDialer = (Button)findViewById(R.id.btIdDialer);    
    	btBrowser = (Button)findViewById(R.id.btIdBrowser);    

    	btDialer.setOnClickListener(btDialListener);         	
    	btBrowser.setOnClickListener(btBrowListener);         	
    }
    
    private OnClickListener btDialListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {      
			Uri uri = Uri.parse("tel:0226532827");
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent);
    	}
    };

    private OnClickListener btBrowListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {      
			Uri uri = Uri.parse("http://www.google.com/");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
    	}
    };
   
}
