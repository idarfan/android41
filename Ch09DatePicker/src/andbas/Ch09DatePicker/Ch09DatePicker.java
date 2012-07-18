package andbas.Ch09DatePicker;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class Ch09DatePicker extends Activity {
	private DatePicker dpDate;
	private Button btSure;
    private int mYear;
    private int mMonth;
    private int mDay;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        buildViews();  //user define
    }
    
    private void buildViews() {
    	dpDate = (DatePicker)findViewById(R.id.dpIdDate);

    	final Calendar c = Calendar.getInstance(); 
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    	dpDate.updateDate(mYear, mMonth, mDay);
    	
    	btSure = (Button)findViewById(R.id.btIdSure);
    	btSure.setOnClickListener(btListener);
    }
    
    private OnClickListener btListener = new OnClickListener() {
		public void onClick(View v) {
		String st =new String();
   		st="您輸入的日期為 : " + dpDate.getYear() + "年" +
		                    (dpDate.getMonth()+1) +"月 " + 
   				            dpDate.getDayOfMonth() + "日";
		Toast.makeText(Ch09DatePicker.this,st,Toast.LENGTH_SHORT)
		     .show();	
		}
	};
}