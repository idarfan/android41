package andbas.Ch12BrowseRec;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ch12BrowseRec extends Activity {
	private static final String DBname = "先進公司.db";
	private static final int DBversion = 1;
	private EditText etCusNo,etCusNa,etCusPho,etCusAdd;
	private TextView tvTitle;
	private Button btNext,btPrev; 
	private CompDBHper dbHper; 
	private ArrayList<String> recSet; 
	private int index=0; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
	}

	private void buildViews() {
		tvTitle = (TextView)findViewById(R.id.tvIdTitle);
		etCusNo = (EditText)findViewById(R.id.etIdCusNo);
		etCusNa = (EditText)findViewById(R.id.etIdCusNa);
		etCusPho = (EditText)findViewById(R.id.etIdCusPho);
		etCusAdd = (EditText)findViewById(R.id.etIdCusAdd);   
		btNext = (Button)findViewById(R.id.btIdNext);
		btPrev = (Button)findViewById(R.id.btIdPrev);

		btNext.setOnClickListener(btNextListener);         	
		btPrev.setOnClickListener(btPrevListener);         	

		initDB(); 
        showRec(index);
	}

	private void initDB() {
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname, null, DBversion);
		dbHper.createTB();	
		recSet = dbHper.getRecSet();
	}
	@Override
	public void onResume() {
		super.onResume();
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname, null, DBversion); 
		recSet = dbHper.getRecSet();
		showRec(index);		
	}
	@Override
	public void onPause() {
		super.onPause();
		if(dbHper != null){
			dbHper.close(); 
			dbHper = null;
		}
		recSet.clear(); 
	}
	
	private OnClickListener btNextListener = 
			new OnClickListener() {    
		public void onClick(View v) {
			index++;
			if(index >= recSet.size()) index = 0;
			showRec(index);
		}
	};
        
	private OnClickListener btPrevListener = 
			new OnClickListener() {    
		public void onClick(View v) {
			index--;
			if(index < 0) index = recSet.size()-1;
			showRec(index);
		}
	};
	
	private void showRec(int index) {	
		if(recSet.size()!=0){
			String stHead="顯示客戶資料：第 "+ (index +1) + " 筆 / 共 " + 
					recSet.size()+" 筆";
			tvTitle.setTextColor(Color.YELLOW);
			tvTitle.setText(stHead);
			Toast.makeText(this, recSet.get(index).toString(), 
					Toast.LENGTH_SHORT).show();
			String[] fld=recSet.get(index).split("#");
			etCusNo.setText(fld[0]);
			etCusNa.setText(fld[1]);
			etCusPho.setText(fld[2]);
			etCusAdd.setText(fld[3]);	
		}else{
			etCusNo.setText("");
			etCusNa.setText("");
			etCusPho.setText("");
			etCusAdd.setText("");				
		}
	}
}