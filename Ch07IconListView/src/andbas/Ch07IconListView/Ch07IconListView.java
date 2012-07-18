package andbas.Ch07IconListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Ch07IconListView extends ListActivity {
    private ListView lvEdu;
    private List<Map<String, Object>> mapAry;
    String msg;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        buildViews();  //user define
	}

	private void buildViews() {
    	int[] icImg={R.drawable.edu1,R.drawable.edu2,R.drawable.edu3,
    				  R.drawable.edu4,R.drawable.edu5,R.drawable.edu6};
		String[] stEduList = getResources().getStringArray(R.array.lvEduList);
		mapAry = new ArrayList<Map<String,Object>>();

		for (int i = 0; i < stEduList.length; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("imgView", icImg[i]);
			item.put("txtView", stEduList[i]);
			mapAry.add(item);
		}

		SimpleAdapter adEduList = new SimpleAdapter(this, mapAry,
			R.layout.listitem,
			new String[] { "imgView", "txtView" },
			new int[] { R.id.imgIdEdu ,R.id.tvIdEdu });

		setListAdapter(adEduList);
		lvEdu = getListView();
		lvEdu.setTextFilterEnabled(true);
		lvEdu.setOnItemClickListener(lvListener);
	}

	private OnItemClickListener lvListener = new OnItemClickListener() {    
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			msg="您的教育程度為：\n";
			msg += ((TextView)view.findViewById(R.id.tvIdEdu)).getText().toString();
    		Toast.makeText(Ch07IconListView.this,
    				msg,
    				Toast.LENGTH_SHORT)
    		     .show();			
		}
	};
}