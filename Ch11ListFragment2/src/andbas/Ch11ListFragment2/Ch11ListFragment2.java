package andbas.Ch11ListFragment2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

public class Ch11ListFragment2 extends Activity {
	private static FragmentManager fragManager;
	private static FragmentTransaction fragTran;
    private static String[] PicsTitle = 
    {
        "照片1：台北河濱夜景",   
        "照片2：台北奧萬大",
        "照片3：三芝天元宮",       
        "照片4：陽明山花鐘",
        "照片5：金山燭台嶼",
    };
    public static final int[] PICTURE = 
    {
    	R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,   
        R.drawable.pic4,R.drawable.pic5,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    			android.R.layout.simple_list_item_activated_1, PicsTitle));
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
    				fragTran.setTransition
    					(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
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
    public static class DetailsFragment extends Fragment {
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
            ImageView image = new ImageView(getActivity());
            scroller.addView(image, 480, 640);
            image.setImageResource(PICTURE[getShownIndex()]);
            return scroller;
        }
    }
    // 當螢幕為直立式時，利用此一活動顯示細節內容
    public static class DetailsActivity extends Activity {
    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    		if (getResources().getConfiguration().orientation
    				== Configuration.ORIENTATION_LANDSCAPE) {
    			// 如果螢幕為横式，細節可顯示在同一螢幕，因此，不需要此活動，直接結束活動
    			finish();
    			return;
    		}
    		if (savedInstanceState == null) {
    			// 把細節顯示在另一活動
    			DetailsFragment details = new DetailsFragment();
    			details.setArguments(getIntent().getExtras());

    			fragManager = getFragmentManager();
                fragTran = fragManager.beginTransaction();
                fragTran.add(android.R.id.content, details);
                fragTran.commit();
    		}
    	}	
    }
}
