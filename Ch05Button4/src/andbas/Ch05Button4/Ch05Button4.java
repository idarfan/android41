package andbas.Ch05Button4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ch05Button4 extends Activity implements OnClickListener {
	private Button btSure;
	private EditText etName;
	private EditText etSex;

	public void onClick(View v) {      
		CharSequence csName=etName.getText();
		CharSequence csSex=etSex.getText();
    	String st=new String();
		if (csSex.toString().equals("male"))
			{st=csName.toString()+"先生, 你好!";}
		else
			{st=csName.toString()+"小姐, 妳好!";}
		Toast.makeText(this,
				st,
				Toast.LENGTH_SHORT)
		     .show();			
	}
	
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

        btSure.setOnClickListener(this);         	
    }
    
}