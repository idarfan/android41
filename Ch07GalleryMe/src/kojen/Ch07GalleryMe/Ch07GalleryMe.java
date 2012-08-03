package kojen.Ch07GalleryMe;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class Ch07GalleryMe extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }
	private void buildViews() {
		Gallery gal = (Gallery) findViewById(R.id.glIdPhoto);    
		gal.setAdapter(new ImageAdapter(this));
		gal.setOnItemClickListener(galListener);
	}

	private OnItemClickListener galListener = new OnItemClickListener() {    
		public void onItemClick(AdapterView<?> parent, View v, int position, long id ) {
    		Toast.makeText(Ch07GalleryMe.this,
    				"您點選的照片為編號 ：" + (position+1),
    				Toast.LENGTH_SHORT)
    		     .show();			
		}
	};

	public class ImageAdapter extends BaseAdapter {
		int galItemBackground;
		private Context cont;

		private Integer[] AryThumPhoto = {
	            R.drawable.flow01, R.drawable.flow02, R.drawable.flow03,
	            R.drawable.flow04, R.drawable.flow05, R.drawable.flow06 };

		public ImageAdapter(Context c) {
			cont = c;
			TypedArray attr = cont.obtainStyledAttributes(R.styleable.galStyle);
			galItemBackground = attr.getResourceId(
			R.styleable.galStyle_android_galleryItemBackground, 0);
			attr.recycle();
		}

		public int getCount() {
			return AryThumPhoto.length;
		}

		public Object getItem(int position) {
			return position;
		}
		
		public long getItemId(int position) {
			return position;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(cont);
			
			imageView.setImageResource(AryThumPhoto[position]);
			imageView.setLayoutParams(new Gallery.LayoutParams(150, 100));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setBackgroundResource(galItemBackground);
			
			return imageView;
		}
	}
}