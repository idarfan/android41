package andbas.Ch06startActWtBundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Ch06startActWtBundle extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        buildViews();  //user define
    }

    private void buildViews(){
        final Button btSure;
        btSure = (Button)findViewById(R.id.btIdSure);    
        btSure.setOnClickListener(btListener);         	
    }
    
    private OnClickListener btListener = new OnClickListener() {    
    	public void onClick(View v) {      
    		final EditText etName;
    		final EditText etSex;

    		etName = (EditText)findViewById(R.id.etIdName);    
        	etSex = (EditText)findViewById(R.id.etIdSex);    
    		String stName=etName.getText().toString();
    		String stSex=etSex.getText().toString();

        	Intent intent=new Intent();
    		intent.setClass(Ch06startActWtBundle.this, LogSuccAct.class);
    		
    		Bundle bundle=new Bundle();
    		bundle.putString("Name", stName);
    		bundle.putString("Sex", stSex);
    		intent.putExtras(bundle);

    		startActivity(intent);
    	}
    };
}