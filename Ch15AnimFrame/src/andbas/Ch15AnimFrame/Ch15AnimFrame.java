package andbas.Ch15AnimFrame;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Ch15AnimFrame extends Activity {
	private AnimationDrawable anim;
	private Button btBeg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

    private void buildViews(){
        ImageView ivFlow = 
        		(ImageView) findViewById(R.id.ivIdFlow);  
//      ivFlow.setBackgroundResource(R.anim.animact);  
//      anim = (AnimationDrawable) ivFlow.getBackground();
        ivFlow.setImageResource(R.anim.animact);  
        anim = (AnimationDrawable) ivFlow.getDrawable();
		anim.start();
		
        btBeg = (Button)findViewById(R.id.btIdBeg);
        btBeg.setOnClickListener(btListener);         	
    }
    
    private OnClickListener btListener = 
    		new OnClickListener() {    
		public void onClick(View v) {
			if ( ! anim.isRunning() ) {
				anim.start();
				btBeg.setText("按此鈕結束");
			}
			else {
				anim.stop();
				btBeg.setText("按此鈕開始");
			}
		}	
    };
}