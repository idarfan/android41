package andbas.Ch07ImgViewSkBar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Ch07ImgViewSkBar extends Activity {
	private Button btNext;
	private Button btPrev;
	private ImageView ivFlow;
	private TextView tvCurrPhoNo;
	private SeekBar sbAlp;
	int currPhotoId = 0;
    private Integer[] AryPhoto = {
    		R.drawable.flow01b, R.drawable.flow02b, R.drawable.flow03b,
            R.drawable.flow04b, R.drawable.flow05b, R.drawable.flow06b };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }
    private void buildViews() {
    	btNext = (Button)findViewById(R.id.btIdNext);    
    	btPrev = (Button)findViewById(R.id.btIdPrev); 
    	ivFlow = (ImageView)findViewById(R.id.ivIdFlow); 
    	tvCurrPhoNo = (TextView)findViewById(R.id.tvIdCurrPhoNo); 
    	sbAlp = (SeekBar)findViewById(R.id.sbIdAlp); 
		tvCurrPhoNo.setText("目前檢視的照片編號：1"+ "/  共 " + (AryPhoto.length) + "張");
    	
		btNext.setOnClickListener(btListener);         	
    	btPrev.setOnClickListener(btListener);         	
    	sbAlp.setOnSeekBarChangeListener(sbListener);         	
    }
    
    private OnClickListener btListener = new OnClickListener() {    
    	public void onClick(View v) {  
    		Button v1=(Button) v;
			if (v1.getText().equals("下一張")) {				
				currPhotoId = (currPhotoId+1)%AryPhoto.length;
				ivFlow.setImageResource(AryPhoto[currPhotoId]);	
			}
			else {		
				currPhotoId = (currPhotoId-1+AryPhoto.length)%AryPhoto.length;
				ivFlow.setImageResource(AryPhoto[currPhotoId]);	
			}
			tvCurrPhoNo.setText("目前檢視的照片編號：" + (currPhotoId+1) 
					         + "/  共 " + (AryPhoto.length) + "張");
    	}
    };

    private OnSeekBarChangeListener sbListener = new OnSeekBarChangeListener() {    
    	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  
			ivFlow.setAlpha(255*progress/100);			
    	}

		public void onStartTrackingTouch(SeekBar arg0) {
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
		}
    };
}