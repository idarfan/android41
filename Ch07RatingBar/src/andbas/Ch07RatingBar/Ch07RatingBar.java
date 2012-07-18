package andbas.Ch07RatingBar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

public class Ch07RatingBar extends Activity {
    private RatingBar rbPhoto;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        buildViews();  //user define
    }

    private void buildViews(){
    	rbPhoto = (RatingBar)findViewById(R.id.rbIdPhoto);
    	rbPhoto.setOnRatingBarChangeListener(rbListener);
    }
	private OnRatingBarChangeListener rbListener = 
			new OnRatingBarChangeListener() {
		public void onRatingChanged(RatingBar ratingBar, 
				float rating, boolean fromUser) {
		    String msg=null;
			msg="您給的評分為： "+ rating +" 分";
			Toast.makeText(Ch07RatingBar.this, 
					msg, Toast.LENGTH_SHORT).show();			
		}		
	};	
}