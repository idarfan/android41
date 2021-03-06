package kojen.Ch07ListViewMe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Ch07ListViewMe extends Activity {   
    private ListView lvEdu;
    static String msg;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        buildViews();  //user define
    }

    private void buildViews(){
    	lvEdu = (ListView)findViewById(R.id.lvIdEdu);
    	ArrayAdapter <CharSequence> adEduList = ArrayAdapter.createFromResource(
				this, R.array.lvEduList, android.R.layout.simple_list_item_1);

    	lvEdu.setAdapter(adEduList);
    	lvEdu.setOnItemClickListener(lvListener);
    }	
	private OnItemClickListener lvListener = new OnItemClickListener() {    
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			msg="您的教育程度為：\n";
			msg += ((TextView) view).getText().toString();
    		Toast.makeText(Ch07ListViewMe.this,
    				msg,
    				Toast.LENGTH_SHORT)
    		     .show();			
		}
	};
}