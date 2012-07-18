package andbas.Ch09TimePicker;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class Ch09TimePicker extends Activity {
	private TimePicker tpTime;
	private Button btSure;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        buildViews();  //user define
    }
    
    private void buildViews() {
    	tpTime = (TimePicker)findViewById(R.id.tpIdTime);

    	final Calendar cal=Calendar.getInstance();
    	
    	int hour=cal.get(Calendar.HOUR);
    	int minute=cal.get(Calendar.MINUTE);
/*
    	Time t=new Time();
    	t.setToNow();
    	int year=t.year;
    	int month=t.month;
    	int day=t.monthDay;
    	int hour=t.hour;
    	int minute=t.minute;
    	int second=t.second;
    	t.set(second, minute, hour, day, month, year);
*/
    	tpTime.setCurrentHour(hour+8);
    	tpTime.setCurrentMinute(minute);
    	tpTime.setIs24HourView(true);
    	
    	btSure = (Button)findViewById(R.id.btIdSure);
    	btSure.setOnClickListener(btListener);
    }
    
    private OnClickListener btListener = new OnClickListener() {
		public void onClick(View v) {
			String st =new String();
	   		st="您輸入的時間為 : " + tpTime.getCurrentHour() + "點" +  
	   				tpTime.getCurrentMinute() + "分";
			Toast.makeText(Ch09TimePicker.this,
					st,Toast.LENGTH_SHORT)
			     .show();	
		}
	};
}