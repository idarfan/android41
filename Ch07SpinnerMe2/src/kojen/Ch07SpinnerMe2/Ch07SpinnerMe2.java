package kojen.Ch07SpinnerMe2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Ch07SpinnerMe2 extends Activity {
	private Button btSure;
    private Spinner spnEdu;
    String stEdu,st;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        buildViews();  //user define
    }

    private void buildViews(){
    	spnEdu = (Spinner)findViewById(R.id.spnIdEdu);
    	
    	String [] stAryEdu={"國小","國中","高中","大學","碩士","博士"};
    	ArrayAdapter <String> adEduList =new ArrayAdapter <String>(
    			this, android.R.layout.simple_spinner_item,stAryEdu); 

    	adEduList.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
    	spnEdu.setAdapter(adEduList);
    	spnEdu.setOnItemSelectedListener(spnEduListener);

    	btSure = (Button)findViewById(R.id.btIdSure);    
        btSure.setOnClickListener(btListener);         	
    }
        
    private OnItemSelectedListener spnEduListener =
    	new OnItemSelectedListener () {
    		public void onItemSelected(AdapterView <?> parent,
    									View v,
    									int position,
    									long id) {
    			stEdu=parent.getSelectedItem().toString();
    			st="教育程度為 " + stEdu;
    		    }
    		public void onNothingSelected(AdapterView <?> parent) {
    		}
    };
    private OnClickListener btListener = new OnClickListener() {    
    	public void onClick(View v) {      
    		Toast.makeText(Ch07SpinnerMe2.this,
    				st,
    				Toast.LENGTH_SHORT)
    		     .show();			
    	}
    };
}