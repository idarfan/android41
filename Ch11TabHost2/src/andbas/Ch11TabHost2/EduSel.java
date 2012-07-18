package andbas.Ch11TabHost2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class EduSel extends Activity {   
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edu);
        
        buildViews();  //user define
    }

    public void buildViews(){
    	Spinner spnEdu;
    	spnEdu = (Spinner)findViewById(R.id.spnIdEdu);
    	ArrayAdapter <CharSequence> adEduList = 
    			ArrayAdapter.createFromResource(
    			EduSel.this, R.array.spnEduList, 
    			android.R.layout.simple_spinner_item);
    	adEduList.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
    	spnEdu.setAdapter(adEduList);
    	spnEdu.setOnItemSelectedListener(spnEduListener);
    }
	
	private  OnItemSelectedListener spnEduListener =
			new OnItemSelectedListener () {
		public void onItemSelected(AdapterView <?> parent,
										View v,
										int position,
										long id) {
			String stEdu,st;
			stEdu=parent.getSelectedItem().toString();
			st="教育程度為 " + stEdu;
			Toast.makeText(EduSel.this,
					st,
					Toast.LENGTH_SHORT)
			     .show();			
		}
		
		public void onNothingSelected(AdapterView <?> parent) {
		}
	};

}
