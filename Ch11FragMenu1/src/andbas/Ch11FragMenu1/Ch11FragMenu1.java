package andbas.Ch11FragMenu1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;

public class Ch11FragMenu1 extends Activity {
	private FragmentManager fragManager;
	private FragmentTransaction fragTran;
	private Fragment frag1,frag2;
	private SubMenu menu1,menu2;
	private CheckBox chBox1,chBox2;
	private static final int itPhoto = Menu.FIRST,
							 itMusic = Menu.FIRST + 1,
							 subitPhoto1 = Menu.FIRST + 4,
							 subitPhoto2 = Menu.FIRST + 5,
							 subitMusic1 = Menu.FIRST + 6,
							 subitMusic2 = Menu.FIRST + 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmenu);
        buildViews();  //user define
    }
    
    // 下面建立兩個由功能表組成視窗區塊
    private void buildViews(){
        fragManager = getFragmentManager();
        fragTran = fragManager.beginTransaction();
        if (frag1 == null) {
            frag1 = new MenuFragment1();
            fragTran.add(frag1, "f1");
        }
        if (frag2 == null) {
            frag2 = new MenuFragment2();
            fragTran.add(frag2, "f2");
        }
        fragTran.commit();
        
        chBox1 = (CheckBox)findViewById(R.id.menu1);
        chBox2 = (CheckBox)findViewById(R.id.menu2);
        chBox1.setOnClickListener(chbListener);
        chBox2.setOnClickListener(chbListener);
        
        // 根據 核取方塊的狀態 更新 功能表的顯示或隱藏
        UpdFagVisibility();
    }

    @Override
    protected void onRestoreInstanceState
    		(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        	UpdFagVisibility();
    }

    private final OnClickListener chbListener = 
    		new OnClickListener() {
        public void onClick(View v) {
            UpdFagVisibility();
        }
    };

    // 根據 核取方塊的狀態 更新 功能表的顯示或隱藏
    void UpdFagVisibility() {
        fragTran = fragManager.beginTransaction();
        if (chBox1.isChecked()) fragTran.show(frag1);
        else fragTran.hide(frag1);
        
        if (chBox2.isChecked()) fragTran.show(frag2);
        else fragTran.hide(frag2);
        fragTran.commit();
    }
    //建立第1個功能表的視窗區塊
    public class MenuFragment1 extends Fragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, 
        		MenuInflater inflater) {
    		menu1 = menu.addSubMenu(0, itPhoto, 0, "瀏覽照片")
					 .setIcon(R.drawable.frame);
			MenuItem MenuItem1=menu1.getItem();
			MenuItem1.setShowAsAction(
				MenuItem.SHOW_AS_ACTION_IF_ROOM | 
       		MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
			menu1.add(0, subitPhoto1, 0, "照片flow1.png");
			menu1.add(0, subitPhoto2, 1, "照片flow2.png");
        }
    }
    //建立第2個功能表的視窗區塊
    public class MenuFragment2 extends Fragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, 
        		MenuInflater inflater) {
    		menu2 = menu.addSubMenu(0, itMusic, 0, "播放音樂")
				    .setIcon(R.drawable.music);	
			MenuItem MenuItem2=menu2.getItem();
			MenuItem2.setShowAsAction(
			MenuItem.SHOW_AS_ACTION_IF_ROOM | 
    		MenuItem.SHOW_AS_ACTION_WITH_TEXT);

			menu2.add(0, subitMusic1, 0, "天空之城.midi");
			menu2.add(0, subitMusic2, 1, "灌藍高手.midi");
        }
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String msg = "";
		switch (item.getItemId()) {
		case itPhoto:
			msg = "您選擇 瀏覽照片 功能";
			break;
		case subitPhoto1:
			msg = "您選擇 瀏覽照片 功能-->瀏覽照片   flow1.png";
			break;
		case subitPhoto2:
			msg = "您選擇 瀏覽照片 功能-->瀏覽照片   flow2.png";
			break;
			
		case itMusic:
			msg = "您選擇 播放音樂 功能";
			break;
		case subitMusic1:
			msg = "您選擇 播放音樂 功能-->播放   天空之城.midi";
			break;
		case subitMusic2:
			msg = "您選擇 播放音樂 功能-->播放   灌藍高手.midi";
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		return true;
	}
}