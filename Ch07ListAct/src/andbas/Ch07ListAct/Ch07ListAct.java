package andbas.Ch07ListAct;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Ch07ListAct extends ListActivity {
    private ListView lvEdu;
    String msg;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        buildViews();  //user define
	}

	private void buildViews() {
    	lvEdu = getListView();
    	ArrayAdapter <CharSequence> adEduList = ArrayAdapter.createFromResource(
				this, R.array.lvEduList, android.R.layout.simple_list_item_1);

    	setListAdapter(adEduList);
    	lvEdu.setTextFilterEnabled(true);
    	lvEdu.setOnItemClickListener(lvListener);
	}

	private OnItemClickListener lvListener = new OnItemClickListener() {    
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			msg="您的教育程度為：\n";
			msg += ((TextView) view).getText().toString();
    		Toast.makeText(Ch07ListAct.this,
    				msg,
    				Toast.LENGTH_SHORT)
    		     .show();			
		}
	};
}