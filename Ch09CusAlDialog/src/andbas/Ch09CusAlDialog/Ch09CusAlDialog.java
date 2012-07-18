package andbas.Ch09CusAlDialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ch09CusAlDialog extends Activity {
	private Button btLogin;
	private Dialog CustDialog;
	String st=new String();

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        buildViews();  //user define
    }    
    private void buildViews(){
    	btLogin = (Button)findViewById(R.id.btIdlogin);           
    	btLogin.setOnClickListener(btListener);         	
    }
   
    private OnClickListener btListener = new OnClickListener() {    
		public void onClick(View v) {
			CustDialog = new Dialog(Ch09CusAlDialog.this);
			CustDialog.setTitle("登入畫面");
			CustDialog.setCancelable(false);
			CustDialog.setContentView(R.layout.custdial);
			Button btSure = 
					(Button)CustDialog.findViewById(R.id.btIdSure);
			Button btCancel = 
					(Button)CustDialog.findViewById(R.id.btIdCancel);
			btSure.setOnClickListener(btSureListener);
			btCancel.setOnClickListener(btCancelListener);
			CustDialog.show();			
		}
	};

    private OnClickListener btSureListener = new OnClickListener() {    
		public void onClick(View v) {
			EditText etUserNa = 
					(EditText)CustDialog.findViewById(R.id.etIdUserNa);
			EditText etPass = 
					(EditText)CustDialog.findViewById(R.id.etIdPass);
			st="你輸入的使用者名稱：" + etUserNa.getText().toString() +
			   "\n　　　　使用者密碼：" + etPass.getText().toString();
    		Toast.makeText(Ch09CusAlDialog.this,st,Toast.LENGTH_SHORT)
    		     .show();	
    		CustDialog.cancel();
		}
	};
	
    private OnClickListener btCancelListener = new OnClickListener() {    	
		public void onClick(View v) {
			st="你按了<取消>鈕。";
    		Toast.makeText(Ch09CusAlDialog.this,st,Toast.LENGTH_SHORT)
    		     .show();	
 			CustDialog.cancel();
		}
	};
}