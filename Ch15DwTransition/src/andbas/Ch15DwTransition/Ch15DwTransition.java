package andbas.Ch15DwTransition;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Ch15DwTransition extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		Resources res = getResources();
        TransitionDrawable dwTran = 
        		(TransitionDrawable) res.getDrawable(R.drawable.photo_tran);
        ImageView ivPhoto1 = (ImageView) findViewById(R.id.ivIdPhoto1);
        ivPhoto1.setImageDrawable(dwTran);
        dwTran.startTransition(5000);
    }
}