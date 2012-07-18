package andbas.Ch12InsertRec;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ch12InsertRec extends Activity {
	private static final String DBname = "先進公司.db";
	private static final int DBversion = 1;
//	private static final String TBname = "客戶";
	private EditText etCusNo,etCusNa,etCusPho,etCusAdd; 
	private Button btIns,btCancel; 
	private CompDBHper dbHper; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

	@Override
	public void onResume() {
		super.onResume();
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname, 
					null, DBversion); 
	}

	@Override
	public void onPause() {
		super.onPause();
		if(dbHper != null){
			dbHper.close(); 
			dbHper = null;
		}
	}
	
	private void buildViews() {
		etCusNo = (EditText)findViewById(R.id.etIdCusNo);
		etCusNa = (EditText)findViewById(R.id.etIdCusNa);
		etCusPho = (EditText)findViewById(R.id.etIdCusPho);
		etCusAdd = (EditText)findViewById(R.id.etIdCusAdd);   
		btIns = (Button)findViewById(R.id.btIdIns);
		btCancel = (Button)findViewById(R.id.btIdCancel);

		btIns.setOnClickListener(btInsListener);         	
		btCancel.setOnClickListener(btCancelListener);         	
	}
	
	private OnClickListener btInsListener = 
			new OnClickListener() {    
		public void onClick(View v) {
			String CusNo = etCusNo.getText().toString().trim();
			String CusNa = etCusNa.getText().toString().trim();
			String CusPho = etCusPho.getText().toString().trim();
			String CusAdd = etCusAdd.getText().toString().trim();
			if(CusNo.equals("") || CusNa.equals("")){
				Toast.makeText(Ch12InsertRec.this, 
						"請輸入欲新增的客戶資料 !", Toast.LENGTH_SHORT)
					 .show();
				return;
			}
			
			String msg = null;
			long rowID  = dbHper.insertRec(CusNo,CusNa,
					CusPho,CusAdd);
			if(rowID != -1){
				msg ="新增記錄  成功 ! \n" + 
			         "目前客戶資料表共有 "+dbHper.RecCount()+" 筆記錄 !";
			}else{
				msg ="新增記錄  失敗 !";
			}
			Toast.makeText(Ch12InsertRec.this, msg,
					Toast.LENGTH_SHORT).show();
		}
	};
        
    private OnClickListener btCancelListener = 
    		new OnClickListener() {    
		public void onClick(View v) {
			etCusNo.setText("");
			etCusNa.setText("");
			etCusPho.setText("");
			etCusAdd.setText("");	
		}
	};
}