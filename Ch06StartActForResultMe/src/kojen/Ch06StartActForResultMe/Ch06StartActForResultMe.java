package kojen.Ch06StartActForResultMe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ch06StartActForResultMe extends Activity {
	final private int LAUNCH_LogSuccAct = 0;
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
    		intent.setClass(Ch06StartActForResultMe.this, LogSuccAct.class);
    		
    		Bundle bundle=new Bundle();
    		bundle.putString("Name", stName);
    		bundle.putString("Sex", stSex);
    		intent.putExtras(bundle);

    		startActivityForResult(intent,LAUNCH_LogSuccAct);

    	}
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent  data) {
    	if (requestCode != LAUNCH_LogSuccAct)
    		return;
    	
    	switch (resultCode) {
    	case RESULT_OK:
    		 Bundle bundle = data.getExtras();  		
    		 String sayHello = bundle.getString("sayHello");
    		
    		 Toast.makeText(Ch06StartActForResultMe.this,
    				sayHello,
    				Toast.LENGTH_SHORT)
    		      .show();			
    		
    		break;
    	case RESULT_CANCELED:
    		 Toast.makeText(Ch06StartActForResultMe.this,
    				"發生錯誤 !",
    				Toast.LENGTH_SHORT)
    		      .show();			
    	}    	
    }       
}