package andbas.Ch12QueryRec;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ch12QueryRec extends Activity {
	private static final String DBname = "先進公司.db";
	private static final int DBversion = 1;
	private EditText etCusNo;
	private Button btSure; 
	private CompDBHper dbHper; 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
	}

	private void buildViews() {
		etCusNo = (EditText)findViewById(R.id.etIdCusNo);
		btSure = (Button)findViewById(R.id.btIdSure);
		btSure.setOnClickListener(btSureListener);         	

		initDB(); 
	}

	private void initDB() {
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname, 
					null, DBversion);
		dbHper.createTB();	
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
	
	private OnClickListener btSureListener = 
			new OnClickListener() {    
		public void onClick(View v) {
			String result=null;
			String CusNo = etCusNo.getText().toString().trim();
			if(CusNo.length()!=0){
				String rec = dbHper.FindRec(CusNo);
				if(rec!=null){
					result="找到的客戶資料為 ：\n"+rec;						
				}else{
					result="找不到指定的 客戶編號："+CusNo;						
				}
				Toast.makeText(Ch12QueryRec.this, 
						result, Toast.LENGTH_LONG).show();
			} 
		}
	};
}