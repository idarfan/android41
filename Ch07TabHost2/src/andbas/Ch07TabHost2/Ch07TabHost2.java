package andbas.Ch07TabHost2;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

public class Ch07TabHost2 extends Activity {
/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//使ActionBar變得透明
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.main);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// remove the activity title to make space for tabs
		actionBar.setDisplayShowTitleEnabled(false);
		AFragment aFragment = new AFragment(); 
		actionBar.addTab(actionBar.newTab().setText("Tab-A") 
		.setTabListener(new ListenerA(aFragment))); 
		
		BFragment bFragment = new BFragment(); 
		actionBar.addTab(actionBar.newTab().setText("Tab-B") 
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

	private class ListenerA implements ActionBar.TabListener {
		private AFragment mFragment;
	// Called to create an instance of the listener when adding a new tab
		public ListenerA(AFragment fragment) {
			mFragment = fragment;
		}
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.add(android.R.id.content, mFragment, null);
		}
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(mFragment);
		}
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// do nothing }
		}
	}
	
	private class ListenerB implements ActionBar.TabListener {
		private BFragment mFragment;
	// Called to create an instance of the listener when adding a new tab
		public ListenerB(BFragment fragment) {
		mFragment = fragment;
		}
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				ft.add(android.R.id.content, mFragment, null);
		}
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				ft.remove(mFragment);
		}
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// do nothing }
		}
	}
}
