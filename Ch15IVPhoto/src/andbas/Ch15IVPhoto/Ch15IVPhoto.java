package andbas.Ch15IVPhoto;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Ch15IVPhoto extends Activity {
	private ImageView ivPhoto1,ivPhoto2,ivPhoto3;
	private Drawable dwBackImg,dwImg;
	private Bitmap bitmap;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

    private void buildViews(){
    	ivPhoto1 = (ImageView)findViewById(R.id.ivIdPhoto1); 
    	ivPhoto2 = (ImageView)findViewById(R.id.ivIdPhoto2); 
    	ivPhoto3 = (ImageView)findViewById(R.id.ivIdPhoto3);
    	
    	Resources res=getResources();
    	dwBackImg=res.getDrawable(R.drawable.photo1);
    	ivPhoto1.setBackgroundDrawable(dwBackImg);
    	
    	ivPhoto1.setImageResource(R.drawable.girl);
    	
    	dwImg=res.getDrawable(R.drawable.photo2);
    	ivPhoto2.setImageDrawable(dwImg);
    	
    	bitmap=BitmapFactory.decodeResource(res, R.drawable.photo3);
    	ivPhoto3.setImageBitmap(bitmap);
    }
}