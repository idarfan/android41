package andbas.Ch05MultiLang;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ToggleButton;

public class Ch05MultiLang extends Activity {
	private ToggleButton tbSet;
	private EditText etName;
	private EditText etSex;
	private int defBgColr=Color.YELLOW;
	private int defTxtColr=Color.RED;
	private int newBgColr=Color.GREEN;
	private int newTxtColr=Color.BLUE;

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

    	etName.setBackgroundColor(defBgColr);
    	etName.setTextColor(defTxtColr);
    	etSex.setBackgroundColor(defBgColr);
    	etSex.setTextColor(defTxtColr);
    	
    	tbSet = (ToggleButton)findViewById(R.id.tbIdSet);
    	tbSet.setChecked(true);
    	tbSet.setText(R.string.tbPtSet);
        tbSet.setOnClickListener(tbListener);         	
    }
    private OnClickListener tbListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {
    		ToggleButton tb=(ToggleButton) v;
    		if (! tb.isChecked()){
    			tb.setChecked(false);
    			tb.setText(R.string.tbPtRest);
    	    	etName.setBackgroundColor(newBgColr);
    	    	etName.setTextColor(newTxtColr);
    	    	etSex.setBackgroundColor(newBgColr);
    	    	etSex.setTextColor(newTxtColr);  			
    		} else {
    			tb.setChecked(true);
    			tb.setText(R.string.tbPtSet);
    			etName.setBackgroundColor(defBgColr);
    	    	etName.setTextColor(defTxtColr);
    	    	etSex.setBackgroundColor(defBgColr);
    	    	etSex.setTextColor(defTxtColr);
    		}			
    	}
    };
}