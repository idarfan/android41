package kojen.Ch05TgButtonMe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ToggleButton;

public class Ch05TgButtonMe extends Activity {
	private ToggleButton tbSet;
	private EditText etName;
	private EditText etSex;
	private int defBgColor=Color.YELLOW;
	private int defTextColor=Color.RED;
	private int newBgColor=Color.GREEN;
	private int newTextColor=Color.BLUE;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        buildViews(); //user define
    }
    
    private void buildViews() {
    	etName = (EditText)findViewById(R.id.etIdName);    
    	etSex = (EditText)findViewById(R.id.etIdSex);
    	
    	etName.setBackgroundColor(defBgColor);
    	etName.setTextColor(defTextColor);    	
    	etSex.setBackgroundColor(defBgColor);
    	etSex.setTextColor(defTextColor);
    	
    	tbSet = (ToggleButton)findViewById(R.id.tbIdset);
    	tbSet.setChecked(false);
    	tbSet.setText(R.string.tbPtSet);
    	tbSet.setOnClickListener(tblistener);    	
    }
    private OnClickListener tblistener = 
    		new OnClickListener() {    
    	public void onClick(View v) {
    		ToggleButton tb=(ToggleButton) v;
    		if (tb.isChecked()){
    			tb.setText(R.string.tbPtRest);
    	    	etName.setBackgroundColor(newBgColor);
    	    	etName.setTextColor(newTextColor);
    	    	etSex.setBackgroundColor(newBgColor);
    	    	etSex.setTextColor(newTextColor);  			
    		} else {
    			tb.setText(R.string.tbPtSet);
    			etName.setBackgroundColor(defBgColor);
    	    	etName.setTextColor(defTextColor);
    	    	etSex.setBackgroundColor(defBgColor);
    	    	etSex.setTextColor(defTextColor);
    		}			
    	}
    };
}