package andbas.Ch07TabHost2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
//        setContentView(R.layout.edu);
        
//        buildViews();  //user define
    }

    public void buildViews(){
    	Spinner spnEdu;
    	spnEdu = (Spinner)findViewById(R.id.spnIdEdu);
    	ArrayAdapter <CharSequence> adEduList = ArrayAdapter.createFromResource(
    			EduSel.this, R.array.spnEduList, android.R.layout.simple_spinner_item);
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
    
    public static class EduFragment extends Fragment {
        int mNum;

        /**
         * Create a new instance of EduFragment, providing "num"
         * as an argument.
         */
      static EduFragment newInstance(int num) {
        	EduFragment f = new EduFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.edu, container, false);
//            View tv = v.findViewById(R.id.text);
//            ((TextView)tv).setText("Fragment #" + mNum);
//            tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
            return v;
        }
    }
}
