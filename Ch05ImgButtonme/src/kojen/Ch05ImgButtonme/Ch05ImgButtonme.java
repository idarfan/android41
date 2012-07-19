package kojen.Ch05ImgButtonme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Ch05ImgButtonme extends Activity {
	private ImageButton ibSure;
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
    	ibSure = (ImageButton)findViewById(R.id.ibIdSure);    

    	ibSure.setOnClickListener(btListener);         	
    }
    
    private OnClickListener btListener = new OnClickListener() {    
    	public void onClick(View v) {      
    		CharSequence csName=etName.getText();
    		CharSequence csSex=etSex.getText();
        	String st=new String();
    		if (csSex.toString().equals("male"))
    			{st=csName.toString()+"先生, 你好!";}
    		else
    			{st=csName.toString()+"小姐, 妳好!";}
    		Toast.makeText(Ch05ImgButtonme.this,
    				st,
    				Toast.LENGTH_SHORT)
    		     .show();			
			
    	}
    };
}