package andbas.Ch11TabHost1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class Ch11TabHost1 extends Activity {
    private Spinner spnEdu;
    static String stEdu,st;
	private Button btDialer,btBrowser;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        buildViews();  //user define
	}

	private void buildViews() {
        buildTabPages();  //user define
        buildEduViews();  //user define
        buildDailBrowViews();  //user define
	}

	private void buildTabPages() {
		TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
		tabHost.setup();

		Resources res = getResources(); 
		TabSpec tabspec=tabHost.newTabSpec("tab1");
		tabspec.setContent(R.id.tab1);
		tabspec.setIndicator("教育程度",
				res.getDrawable(R.drawable.icon8));
		tabHost.addTab(tabspec);
		
		tabspec=tabHost.newTabSpec("tab2");
		tabspec.setContent(R.id.tab2);
		tabspec.setIndicator("打電話及上網",
				res.getDrawable(R.drawable.ic_tab_artists));
		tabHost.addTab(tabspec);

		tabHost.setCurrentTab(0);
	}
	
	private void buildEduViews() {
    	spnEdu = (Spinner)findViewById(R.id.spnIdEdu);
    	ArrayAdapter <CharSequence> adEduList = 
    			ArrayAdapter.createFromResource(
				this, R.array.spnEduList, 
				android.R.layout.simple_spinner_item);
    	adEduList.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
    	spnEdu.setAdapter(adEduList);
    	spnEdu.setOnItemSelectedListener(spnEduListener);
	}
	
	private void buildDailBrowViews() {
    	btDialer = (Button)findViewById(R.id.btIdDialer);    
    	btBrowser = (Button)findViewById(R.id.btIdBrowser);    
    	btDialer.setOnClickListener(btDialListener);         	
    	btBrowser.setOnClickListener(btBrowListener);         	
	}
	
	private OnItemSelectedListener spnEduListener =
			new OnItemSelectedListener () {
		public void onItemSelected(AdapterView <?> parent,
										View v,
										int position,
										long id) {
			stEdu=parent.getSelectedItem().toString();
			st="教育程度為 " + stEdu;
			Toast.makeText(Ch11TabHost1.this,
					st,
					Toast.LENGTH_SHORT)
			     .show();			
		}
		
		public void onNothingSelected(AdapterView <?> parent) {
		}
	};

    private OnClickListener btDialListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {      
			Uri uri = Uri.parse("tel:0226532827");
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent);
    	}
    };

    private OnClickListener btBrowListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {      
			Uri uri = Uri.parse("http://www.google.com/");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
    	}
    };
}