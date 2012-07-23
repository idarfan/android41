package kojen.Ch06StartActWTBundleMe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LogSuccAct extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logsucc);

        buildViews();  //user define
    }

    private void buildViews(){
    	final TextView tvShName;
    	tvShName = (TextView)findViewById(R.id.tvIdShName);    
    	
    	Bundle bundle=this.getIntent().getExtras();
    	String stName=bundle.getString("Name");
    	String stSex=bundle.getString("Sex");
    	
    	String st=new String();
		if (stSex.equals("male"))
		   {st=stName+"先生, 你好!";}
	    else
		   {st=stName+"小姐, 妳好!";}
    	
		tvShName.setText(st);	
    }
}