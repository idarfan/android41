package kojen.Ch06StartActForResultMe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LogSuccAct extends Activity {
	private String sayHello=new String();

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logsucc);
        buildViews();  //user define
    }

    private void buildViews(){
    	final TextView tvShName;
        final Button btSure;
        btSure = (Button)findViewById(R.id.btIdSure);    
    	tvShName = (TextView)findViewById(R.id.tvIdShName);    
    	
    	Bundle bundle=this.getIntent().getExtras();
    	String stName=bundle.getString("Name");
    	String stSex=bundle.getString("Sex");
    	
		if (stSex.equals("male"))
		   {sayHello=stName+"先生, 你好!";}
	    else
		   {sayHello=stName+"小姐, 妳好!";}
    	
		tvShName.setText(sayHello);	
        btSure.setOnClickListener(btListener);         	
    }
    
    private OnClickListener btListener = new OnClickListener() {    
    	public void onClick(View v) {      
            Intent intent=new Intent();
    		Bundle backbundle=new Bundle();
    		backbundle.putString("sayHello", sayHello);
    		intent.putExtras(backbundle);

    		setResult(RESULT_OK,intent);
    		finish();
    	}
    };
}