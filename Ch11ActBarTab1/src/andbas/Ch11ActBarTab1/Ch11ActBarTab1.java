package andbas.Ch11ActBarTab1;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

public class Ch11ActBarTab1 extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 使ActionBar變得透明
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		final ActionBar actBar = getActionBar();
		actBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// 移除活動的標題列，以便顯示ActionBar
		actBar.setDisplayShowTitleEnabled(false);
		AFragment aFragment = new AFragment(); 
		actBar.addTab(actBar.newTab().setText("檢視照片1") 
		         .setTabListener(new ListenerA(aFragment))); 
		
		BFragment bFragment = new BFragment(); 
		actBar.addTab(actBar.newTab().setText("檢視照片2") 
		         .setTabListener(new ListenerB(bFragment)));
    }
	
    //點擊顯示or隱藏ActionBar
	public boolean onTouchEvent(MotionEvent event){
		ActionBar bar = getActionBar();
		switch(event.getAction()){
		case MotionEvent.ACTION_UP:
			if(bar.isShowing()) bar.hide();
			else bar.show();
		break;
			default:
		break;
		}
		return true;
	}

	// 當新增一個頁籤時，呼叫此監聽器
	private class ListenerA implements ActionBar.TabListener {
		private AFragment frag;
	    
		public ListenerA(AFragment fragment) {
			frag = fragment;
		}
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.add(android.R.id.content, frag, null);
		}
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(frag);
		}
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// do nothing }
		}
	}
	// 當新增一個頁籤時，呼叫此監聽器
	private class ListenerB implements ActionBar.TabListener {
		private BFragment frag;
		public ListenerB(BFragment fragment) {
			frag = fragment;
		}
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.add(android.R.id.content, frag, null);
		}
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(frag);
		}
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// do nothing }
		}
	}
}