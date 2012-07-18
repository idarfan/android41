package andbas.Ch05Button3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ch05Button3 extends Activity {
	private Button btSure;
	private EditText etName;
	private EditText etSex;

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

        btSure.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
	    		CharSequence csName=etName.getText();
	    		CharSequence csSex=etSex.getText();
	        	String st=new String();
	    		if (csSex.toString().equals("male"))
	    			{st=csName.toString()+"先生, 你好!";}
	    		else
	    			{st=csName.toString()+"小姐, 妳好!";}
	    		Toast.makeText(Ch05Button3.this,
	    				st,
	    				Toast.LENGTH_SHORT)
	    		     .show();
			}
		});
    }
}