package andbas.Ch11TabHost2;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Ch11TabHost2 extends TabActivity {
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        buildViews();  //user define
     }
	private void buildViews() {
		TabHost tabHost = getTabHost();

		Resources res = getResources(); 
		Intent intent = new Intent();
		intent.setClass(Ch11TabHost2.this, EduSel.class);
		TabSpec tabspec=tabHost.newTabSpec("tab1");
		tabspec.setContent(intent);
		tabspec.setIndicator("教育程度",
				res.getDrawable(R.drawable.icon8));
		tabHost.addTab(tabspec);

		intent = new Intent();
		intent.setClass(Ch11TabHost2.this, DialBrow.class);
		tabspec=tabHost.newTabSpec("tab2");
		tabspec.setContent(intent);
		tabspec.setIndicator("打電話及上網",
				res.getDrawable(R.drawable.ic_tab_artists));
		tabspec.setContent(intent);
		tabHost.addTab(tabspec);

		tabHost.setCurrentTab(0);
	}
}