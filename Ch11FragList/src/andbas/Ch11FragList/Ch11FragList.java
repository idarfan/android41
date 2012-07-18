package andbas.Ch11FragList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Ch11FragList extends Activity {
	private FragmentManager fragManager;
	private FragmentTransaction fragTran;
    private String msg;
    private String[] NewsTitle = 
    {
            "News1：俄羅斯莫斯科阿基米德國際發明展競賽",   
            "News2：創意點子大募集",
            "News3：高科技設備前瞻技術發展計畫",       
            "News4：產業創新與經營學術研討會",
            "News5：人體研究法之之衝擊與因應研討會",
    };
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragManager = getFragmentManager();
        if (fragManager.findFragmentById(android.R.id.content) 
        		== null) {
            MyListFragment list = new MyListFragment();
            fragTran =fragManager.beginTransaction();
            fragTran.add(android.R.id.content, list);
            fragTran.commit();
        }
    }

    public  class MyListFragment extends ListFragment {
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, NewsTitle));
        }

        @Override
        public void onListItemClick(ListView l, View v, 
        		int position, long id) {
			msg="您的選擇的新聞標題：\n";
			msg += ((TextView) v).getText().toString();
    		Toast.makeText(Ch11FragList.this, msg,
    				Toast.LENGTH_SHORT).show();			
       }
    }
}
