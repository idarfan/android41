package kojen.Ch07GridViewMe;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Ch07GridViewMe extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
	}

	private void buildViews() {
	    GridView gridview = (GridView) findViewById(R.id.gridview);    
	    gridview.setAdapter(new ImageAdapter(this));
	    gridview.setOnItemClickListener(gridviewListener);
	}

	private OnItemClickListener gridviewListener = new OnItemClickListener() {    
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    		Toast.makeText(Ch07GridViewMe.this,
    				"您點選的照片為編號 ：" + (position+1),
    				Toast.LENGTH_SHORT)
    		     .show();			
		}
	};

	
	public class ImageAdapter extends BaseAdapter {    
		private Integer[] mThumbIds = {            
			       R.drawable.flow01, R.drawable.flow02,            
			       R.drawable.flow03, R.drawable.flow04,            
			       R.drawable.flow05, R.drawable.flow06,            
			       R.drawable.flow07, R.drawable.flow08,            
			       R.drawable.flow09, R.drawable.flow10,            
			       R.drawable.flow11, R.drawable.flow12,            
			       R.drawable.flow01, R.drawable.flow02,            
			       R.drawable.flow03, R.drawable.flow04,            
			       R.drawable.flow05, R.drawable.flow06,            
			       R.drawable.flow07, R.drawable.flow08,            
			       R.drawable.flow09, R.drawable.flow10    };			
		private Context mContext;    

		public ImageAdapter(Context c) {        
			mContext = c;    
			}    
		public int getCount() {        
			return mThumbIds.length;    
			}    
		public Object getItem(int position) {        
			return null;    
			}    
		public long getItemId(int position) {        
			return 0;    
			}
		
		// 對Adapter參考到每一項目建立一個新的ImageView    
		public View getView(int position, View convertView, ViewGroup parent) {        
			ImageView imageView;        
			if (convertView == null) {  
				imageView = new ImageView(mContext);            
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));            
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);            
				imageView.setPadding(8, 8, 8, 8);        
				} 
			else {        
				imageView = (ImageView) convertView;        
				}        
			imageView.setImageResource(mThumbIds[position]);        
			return imageView;    
			}    
	}
}