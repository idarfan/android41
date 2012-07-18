package andbas.Ch15IVDrawGh;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Ch15IVDrawGh extends Activity {
	private ImageView iv1,iv2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

    private void buildViews(){
    	iv1 = (ImageView)findViewById(R.id.ivId1); 
    	iv2 = (ImageView)findViewById(R.id.ivId2); 
    	
        GradientDrawable gradShape1 = new GradientDrawable();
        gradShape1.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gradShape1.setShape(GradientDrawable.OVAL);
		gradShape1.setColor(0x88ff00ff);
		iv1.setImageDrawable(gradShape1);
		
        GradientDrawable gradShape2 = new GradientDrawable();
        gradShape2.setShape(GradientDrawable.RECTANGLE);
		gradShape2.setColor(0xaa00ffff);
		iv2.setImageDrawable(gradShape2);
    }
}