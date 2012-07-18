package andbas.Ch11ListFragment;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class Ch11ListFragment extends Activity {
	private static FragmentManager fragManager;
	private static FragmentTransaction fragTran;
    private static String[] NewsTitle = 
    {
        "News1：俄羅斯莫斯科阿基米德國際發明展競賽",   
        "News2：創意點子大募集",
        "News3：高科技設備前瞻技術發展計畫",       
        "News4：產業創新與經營學術研討會",
        "News5：人體研究法之之衝擊與因應研討會",
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public static class TitlesFragment extends ListFragment {
    	boolean mDualPane;
    	int chkPosition = 0;
    	@Override
    	public void onActivityCreated(Bundle savedInstanceState) {
    		super.onActivityCreated(savedInstanceState);
    		setListAdapter(new ArrayAdapter<String>(getActivity(),
    			android.R.layout.simple_list_item_activated_1, NewsTitle));
    		// 取得details面板，以判斷是否存在雙面板
    		View detailsFrame = getActivity().findViewById(R.id.details);
    		mDualPane = (detailsFrame != null) && 
    				(detailsFrame.getVisibility() == View.VISIBLE);
    		
    		if (savedInstanceState != null) {
    			// 重置最近被選取項目的狀態
    			chkPosition = savedInstanceState.getInt("curChoice", 0);
    		}
    		
    		if (mDualPane) {
    			// 在雙面板模式，下面設定被選擇項以高亮度顯示
    			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    			showDetails(chkPosition);  //顯示細部內容
    		}
    	}
    	
    	@Override    
    	public void onSaveInstanceState(Bundle outState) {
    		super.onSaveInstanceState(outState);
    		outState.putInt("curChoice", chkPosition);
    	}
    	@Override
    	public void onListItemClick(ListView l, View v, 
    			int position, long id) {
    		showDetails(position);
    	}    
    	void showDetails(int index) {
    		chkPosition = index;
    		if (mDualPane) {
    			// 如果為雙面板則把 新的細節內容 顯示在細節視窗區塊，取代舊有的
    			getListView().setItemChecked(index, true);
    			// 檢查目前細節視窗區塊顯示的內容為何，將會被取代
    	        fragManager= getFragmentManager();
    			DetailsFragment details = (DetailsFragment)
    					fragManager.findFragmentById(R.id.details);
    			if (details == null || details.getShownIndex() != index) {
    				// 建立一個 新選擇項目的 細節視窗區塊
    				details = DetailsFragment.newInstance(index);
    				// 細節視窗區塊的內容以新內容取代舊的
    				fragTran =fragManager.beginTransaction();
    				fragTran.replace(R.id.details, details);
    				fragTran.setTransition(
    						FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    				fragTran.commit();
    			}
    		} else {
    			// 如果為直立式，則啟動另一個活動以顯示細節
    			Intent intent = new Intent();
    			intent.setClass(getActivity(), DetailsActivity.class);
    			intent.putExtra("index", index);
    			startActivity(intent);
    		}
    	}
    }

    // 顯示細節的視窗區段
    public static  class DetailsFragment extends Fragment {
    	public static DetailsFragment newInstance(int index) {
    		DetailsFragment f = new DetailsFragment();
    		// 記載被選取項目的位置(index)
    		Bundle args = new Bundle();
    		args.putInt("index", index);
    		f.setArguments(args);
    		return f;
    		}
    	public int getShownIndex() {
    		return getArguments().getInt("index", 0);
    		}
    	@Override
    	public View onCreateView(LayoutInflater inflater, 
    			ViewGroup container,Bundle savedInstanceState) {
    		if (container == null) {
    			// 如果container是null，則傳回一個null view
    			return null;
    			}
    		ScrollView scroller = new ScrollView(getActivity());
    		TextView text = new TextView(getActivity());
    		scroller.addView(text);
    		String st=((Ch11ListFragment) getActivity()).
    				readFromAss(getShownIndex()+1);
    		text.setTextColor(Color.GREEN);
    		text.setTextSize(20);
    		text.setText(st);
    		return scroller;
    	}    
    }
    private  String readFromAss(int index) {
    	String text = null;
    	String fileNa="news"+index+".txt";
    	try {
            InputStream inpSt = getAssets().open(fileNa);
            int size = inpSt.available();
            byte[] buffer = new byte[size];
            inpSt.read(buffer);
            inpSt.close();
            text =new String(buffer);
        } catch (IOException e) {
            Log.e("Ch11ListFragment", e.toString());
        }
    	return text;
	}
}