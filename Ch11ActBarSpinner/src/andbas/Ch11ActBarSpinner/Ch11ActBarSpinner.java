package andbas.Ch11ActBarSpinner;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Ch11ActBarSpinner extends Activity {
	private FragmentManager FragManager;
	private FragmentTransaction FragTran;
	private String[] stEduAry;
    private String stEdu;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }
    private void buildViews(){
    	ArrayAdapter <CharSequence> adEduList = 
    			ArrayAdapter.createFromResource(
				this, R.array.spnEduList, 
				android.R.layout.simple_spinner_dropdown_item);
        
        stEduAry = getResources().getStringArray(R.array.spnEduList);
    	ActionBar actBar = getActionBar();
    	actBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
    	actBar.setListNavigationCallbacks(adEduList, ListNaviCallback);
    }
    private OnNavigationListener ListNaviCallback = 
    		new OnNavigationListener() {    
        public boolean onNavigationItemSelected(int position, 
        		long itemId) {
        	SpinnerFragment newFragment = new SpinnerFragment();
        	FragManager = getFragmentManager();
        	FragTran = FragManager.beginTransaction();
        	FragTran.replace(R.id.fragcontent, 
        			newFragment, stEduAry[position]);
        	FragTran.commit();
        	return true;
    	}
    };
    
	public class SpinnerFragment extends Fragment {
        @Override
        public void onAttach(Activity activity) { 
          super.onAttach(activity);
          stEdu = getTag();
        }
        @Override
        public View onCreateView(LayoutInflater inflater, 
        		ViewGroup container,Bundle savedInstanceState) { 
            TextView tvEdu = new TextView(getActivity());
            tvEdu.setTextColor(Color.GREEN);
            tvEdu.setTextSize(20);
            tvEdu.setText(stEdu);
            Toast.makeText(Ch11ActBarSpinner.this, "教育程度為 ： " + 
            		stEdu, Toast.LENGTH_SHORT).show();
            return tvEdu;
        }
    }
}