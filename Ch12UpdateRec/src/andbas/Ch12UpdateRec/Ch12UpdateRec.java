package andbas.Ch12UpdateRec;

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

public class Ch12UpdateRec extends Activity {
	private static final String DBname = "先進公司.db";
	private static final int DBversion = 1;
	private EditText etCusNa,etCusPho,etCusAdd;
	private TextView tvTitle,tvCusNo;
	private Button btNext,btPrev; 
	private Button btEdit,btDel; 
	private CompDBHper dbHper; 
	private ArrayList<String> recSet; 
	private int index=0; 
	String msg=null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
	}

	private void buildViews() {
		tvTitle = (TextView)findViewById(R.id.tvIdTitle);
		tvCusNo = (TextView)findViewById(R.id.tvIdCusNo);
		etCusNa = (EditText)findViewById(R.id.etIdCusNa);
		etCusPho = (EditText)findViewById(R.id.etIdCusPho);
		etCusAdd = (EditText)findViewById(R.id.etIdCusAdd);   
		btNext = (Button)findViewById(R.id.btIdNext);
		btPrev = (Button)findViewById(R.id.btIdPrev);
		btEdit = (Button)findViewById(R.id.btIdEdit);
		btDel = (Button)findViewById(R.id.btIdDel);

		btNext.setOnClickListener(btNextListener);         	
		btPrev.setOnClickListener(btPrevListener);         	
		btEdit.setOnClickListener(btEditListener);         	
		btDel.setOnClickListener(btDelListener);         	

		initDB(); 
        showRec(index);
	}

	private void initDB() {
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname,null, DBversion);
		dbHper.createTB();	
		recSet = dbHper.getRecSet();
	}
	@Override
	public void onResume() {
		super.onResume();
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname,null, DBversion); 
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
	private OnClickListener btEditListener = 
			new OnClickListener() {    
		public void onClick(View v) {
			String CusNo = tvCusNo.getText().toString().trim();
			String CusNa = etCusNa.getText().toString().trim();
			String CusPho = etCusPho.getText().toString().trim();
			String CusAdd = etCusAdd.getText().toString().trim();
			int rowsAffected  = 
					dbHper.updateRec(CusNo,CusNa,CusPho,CusAdd);
			if (rowsAffected==-1) {
				msg="資料表已空, 無法修改 !";
			} else if (rowsAffected==0) {
				msg="找不到欲修改的記錄, 無法修改 !";
			} else {
				msg="第 "+ (index+1) + " 筆記錄  已修改 ! \n" + 
						"共 "+rowsAffected + " 筆記錄   被修改 !";
				recSet = dbHper.getRecSet();
				showRec(index);				
			}
			Toast.makeText(Ch12UpdateRec.this, msg,Toast.LENGTH_SHORT)
		         .show();
		}
	};
	private OnClickListener btDelListener = 
			new OnClickListener() {    
		public void onClick(View v) {
			String CusNo = tvCusNo.getText().toString().trim();
			int rowsAffected = dbHper.deleteRec(CusNo);
			if (rowsAffected==-1) {
				msg="資料表已空, 無法刪除 !";
			} else if (rowsAffected==0) {
				msg="找不到欲刪除的記錄, 無法刪除 !";
			} else {
				msg="第 "+ (index+1) + " 筆記錄  已刪除 ! \n" + 
						"共 "+rowsAffected + " 筆記錄   被刪除 !";
				recSet = dbHper.getRecSet();
				showRec(0);				
			}
			Toast.makeText(Ch12UpdateRec.this, msg,Toast.LENGTH_SHORT)
			     .show();
		}
	};
	
	private void showRec(int index) {	
		if(recSet.size()!=0){
			String stHead="顯示客戶資料：第 "+ (index +1) + " 筆 / 共 " + 
					recSet.size()+" 筆";
			tvTitle.setTextColor(Color.YELLOW);
			tvTitle.setText(stHead);
			String[] fld=recSet.get(index).split("#");
			tvCusNo.setTextColor(Color.RED);
			tvCusNo.setText(fld[0]);
			etCusNa.setText(fld[1]);
			etCusPho.setText(fld[2]);
			etCusAdd.setText(fld[3]);	
		}else{
			tvCusNo.setText("");
			etCusNa.setText("");
			etCusPho.setText("");
			etCusAdd.setText("");				
		}
	}
}