package andbas.Ch05EdText;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ch05EdText extends Activity {
	private Button btSure;
	private EditText etName;
	private EditText etSex;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        buildViews();  //user define
    }

    private void buildViews(){
    	etName = (EditText)findViewById(R.id.etIdName);    
    	etSex = (EditText)findViewById(R.id.etIdSex);    
        btSure = (Button)findViewById(R.id.btIdSure);    
        
        etSex.setOnKeyListener(kyListener);         	
        btSure.setOnClickListener(btListener);         	
    }
 
    private OnKeyListener kyListener = new OnKeyListener() {    
		public boolean onKey(View v, int keyCode, KeyEvent event) {
    		String stSex=etSex.getText().toString();
 			if(event.getAction() == KeyEvent.ACTION_DOWN &&
					keyCode == KeyEvent.KEYCODE_ENTER){
        		if ( ! (stSex.equals("male") || stSex.equals("female") ))
    			   {
        		    Toast.makeText(Ch05EdText.this,
            				"輸入錯誤，只能輸入'male'或 'female' !",
            				Toast.LENGTH_SHORT)
            		      .show();			
    			   }
			    return true;
			}
    		else
    		    return false;
    	}
    };
    
    private OnClickListener btListener = new OnClickListener() {    
    	public void onClick(View v) {      
    		CharSequence csName=etName.getText();
    		CharSequence csSex=etSex.getText();
        	String st=new String();
    		if (csSex.toString().equals("male"))
    			{st=csName.toString()+"先生, 你好!";}
    		else
    			{st=csName.toString()+"小姐, 妳好!";}
    		Toast.makeText(Ch05EdText.this,
    				st,
    				Toast.LENGTH_SHORT)
    		     .show();			
			
    	}
    };
}