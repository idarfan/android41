package andbas.Ch08GetShaPref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Ch08GetShaPref extends Activity {
	private Button btSvPref;
	private Button btChgPref;
	private Button btRestPref;
	private EditText etItName;
	private int defSize=20;
	private int defColor=Color.GREEN;
	private int defTf=Typeface.ITALIC;
	private String prefName = "prefSets";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

    private void buildViews(){
    	etItName = (EditText)findViewById(R.id.etIdItName);    
    	btSvPref = (Button)findViewById(R.id.btIdSvPref);    
    	btChgPref = (Button)findViewById(R.id.btIdChgPref);    
    	btRestPref = (Button)findViewById(R.id.btIdRestPref);       	
    	etItName.setTextSize(defSize);
    	etItName.setTextColor(defColor);
    	etItName.setTypeface(Typeface.MONOSPACE,defTf);
   	
    	btSvPref.setOnClickListener(btSvPrefListener);         	
        btChgPref.setOnClickListener(btChgPrefListener);         	
        btRestPref.setOnClickListener(btRestPrefListener);         	
    }
    private OnClickListener btSvPrefListener = new OnClickListener() {    
    	public void onClick(View v) {      
    		SharedPreferences prefSets = 
					getSharedPreferences(prefName, Context.MODE_PRIVATE);         	
    		prefSets.edit()
    		.putInt("defSize", defSize)
    		.putInt("defColor", defColor)
    		.putInt("defTf", defTf)
    		.commit();		
    	}
    };    
    private OnClickListener btChgPrefListener = new OnClickListener() {    
    	public void onClick(View v) {      
        	etItName.setTextSize(30);
        	etItName.setTextColor(Color.RED);
        	etItName.setTypeface(Typeface.MONOSPACE,Typeface.BOLD);			
    	}
    };    
    private OnClickListener btRestPrefListener = new OnClickListener() {    
    	public void onClick(View v) {      
    		SharedPreferences prefSets = 
    				getSharedPreferences(prefName, Context.MODE_PRIVATE); 		
    			
    			Integer defSize = prefSets.getInt("defSize", 20);
    			Integer defColor = prefSets.getInt("defColor", Color.GREEN);
    			Integer defTf = prefSets.getInt("defTf", Typeface.ITALIC);
    	    	etItName.setTextSize(defSize);
    	    	etItName.setTextColor(defColor);
    	    	etItName.setTypeface(Typeface.MONOSPACE,defTf);
			
    	}
    };    
}