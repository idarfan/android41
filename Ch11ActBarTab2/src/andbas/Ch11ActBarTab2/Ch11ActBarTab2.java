package andbas.Ch11ActBarTab2;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

public class Ch11ActBarTab2 extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actBar = getActionBar();
        actBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, 0);

        actBar.addTab(actBar.newTab()
   	         .setText("檢視照片1")
   	         .setTabListener(new TabListener<Image1.Image1Fragment>(
       			this, "檢視照片1", Image1.Image1Fragment.class)));
        actBar.addTab(actBar.newTab()
                .setText("檢視照片2")
                .setTabListener(new TabListener<Image2.Image2Fragment>(
                   this, "檢視照片2", Image2.Image2Fragment.class)));
        
        if (savedInstanceState != null) {
        	actBar.setSelectedNavigationItem(savedInstanceState.
            		getInt("tab", 0));
        }
     }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().
        		getSelectedNavigationIndex());
    }

    public static class TabListener<T extends Fragment> 
	    implements ActionBar.TabListener {
	        private final Activity mActivity;
	        private final String mTag;
	        private final Class<T> mClass;
	        private final Bundle mArgs;
	        private FragmentManager FragManager;
	    	private FragmentTransaction FragTran;
	        private Fragment frag;
	
	        public TabListener(Activity activity, 
	        		String tag, Class<T> clz) {
	            this(activity, tag, clz, null);
	        }

        public TabListener(Activity activity, String tag, 
        		Class<T> clz, Bundle args) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
            mArgs = args;

            //檢查這個Tab是否已經存在一個fragment，如果存在，則取消其作用
            FragManager=mActivity.getFragmentManager();
            frag = FragManager.findFragmentByTag(mTag);
            if (frag != null && !frag.isDetached()) {
            	FragTran = FragManager.beginTransaction();
            	FragTran.detach(frag);
            	FragTran.commit();
            }
        }

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            if (frag == null) {
                frag = Fragment.instantiate(mActivity, 
                		mClass.getName(), mArgs);
                ft.add(android.R.id.content, frag, mTag);
            } else {
                ft.attach(frag);
            }
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (frag != null) {
                ft.detach(frag);
            }
        }

        public void onTabReselected(Tab tab,FragmentTransaction ft) {
            Toast.makeText(mActivity, "Reselected!", 
            		Toast.LENGTH_SHORT).show();
        }
    }
}