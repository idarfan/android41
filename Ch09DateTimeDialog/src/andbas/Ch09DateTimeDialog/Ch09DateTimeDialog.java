package andbas.Ch09DateTimeDialog;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class Ch09DateTimeDialog extends Activity {
    private Button btInpDate, btInpTime;

    private TextView tvResult;

		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
        buildViews();  //user define
	}
	
	private void buildViews() {
		btInpTime = (Button)findViewById(R.id.btIdInpTime);
		btInpDate = (Button)findViewById(R.id.btIdInpDate);
		tvResult = (TextView)findViewById(R.id.tvIdResult);
		
		btInpDate.setOnClickListener(btInpDateListener);
		btInpTime.setOnClickListener(btInpTimeListener);
	}

    private OnClickListener btInpDateListener = 
    		new OnClickListener() {    
		public void onClick(View v) {
			tvResult.setText("");

			Calendar now = Calendar.getInstance();

			DatePickerDialog datePicDlg = 
					new DatePickerDialog(Ch09DateTimeDialog.this,
					dsListener,	now.get(Calendar.YEAR),
					now.get(Calendar.MONTH), 
					now.get(Calendar.DAY_OF_MONTH));
			datePicDlg.setTitle("輸入日期");
			datePicDlg.setMessage("請輸入日期");
			datePicDlg.setIcon(android.R.drawable.ic_dialog_info);
			datePicDlg.setCancelable(false);
			datePicDlg.show();
		}
	};
	
    private OnDateSetListener dsListener = new OnDateSetListener() {    
		public void onDateSet (DatePicker view, int year, 
				               int monthOfYear, int dayOfMonth) {
			tvResult.setText("您輸入的日期是 ：" +
							Integer.toString(year) + "年" +
							Integer.toString(monthOfYear + 1) + "月" +
							Integer.toString(dayOfMonth) + "日");
		}
	};

    private OnClickListener btInpTimeListener = 
    		new OnClickListener() {    
		public void onClick(View v) {
			tvResult.setText("");

			Calendar now = Calendar.getInstance();

			TimePickerDialog timePicDlg = 
					new TimePickerDialog(Ch09DateTimeDialog.this,
					tsListener,	now.get(Calendar.HOUR_OF_DAY),
					now.get(Calendar.MINUTE), true);
			timePicDlg.setTitle("輸入時間");
			timePicDlg.setMessage("請輸入時間");
			timePicDlg.setIcon(android.R.drawable.ic_dialog_info);
			timePicDlg.setCancelable(false);
			timePicDlg.show();
		}
	};
	
    private OnTimeSetListener tsListener = 
    		new OnTimeSetListener() {    
		public void onTimeSet (TimePicker  view, 
				int hourOfDay, int minute) {
			tvResult.setText("您輸入的時間是 ：" +
							Integer.toString(hourOfDay) + "點" +
							Integer.toString(minute) + "分");
		}
	};
}