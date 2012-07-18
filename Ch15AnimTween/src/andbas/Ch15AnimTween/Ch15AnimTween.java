package andbas.Ch15AnimTween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Ch15AnimTween extends Activity {
	private ImageView ivPhoto1;
	private Animation anim;
	private Button btSure;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

    private void buildViews(){
        btSure = (Button)findViewById(R.id.btIdSure);    
        btSure.setOnClickListener(btListener);         	
    }
    private OnClickListener btListener = 
    		new OnClickListener() {    
    	public void onClick(View v) {      
        ivPhoto1 = (ImageView)findViewById(R.id.ivIdPhoto1);
        //定義Tween Animation動畫的xml檔案
        anim=AnimationUtils.loadAnimation(
        		Ch15AnimTween.this, R.anim.anim_acts);
        ivPhoto1.setAnimation(anim);
        ivPhoto1.startAnimation(anim);
    	}
    };
}