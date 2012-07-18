package andbas.Ch13ContProvid;

import andbas.Ch13ContProvid.providers.CompContentProvider;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ch13ContProvid extends Activity {
	private static ContentResolver ContRes;
	private EditText etCusNo,etCusNa,etCusPho,etCusAdd;
	private EditText etResult;
	private Button btIns,btCls,btList; 
	private Button btQry,btEdit,btDel; 
	String msg=null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
		ContRes = getContentResolver();
	}

	private void buildViews() {
		etCusNo = (EditText)findViewById(R.id.etIdCusNo);
		etCusNa = (EditText)findViewById(R.id.etIdCusNa);
		etCusPho = (EditText)findViewById(R.id.etIdCusPho);
		etCusAdd = (EditText)findViewById(R.id.etIdCusAdd); 
		
		etResult = (EditText)findViewById(R.id.etIdResult); 
		
		btIns = (Button)findViewById(R.id.btIdIns);
		btCls = (Button)findViewById(R.id.btIdCls);
		btList = (Button)findViewById(R.id.btIdList);
		btQry = (Button)findViewById(R.id.btIdQry);
		btEdit = (Button)findViewById(R.id.btIdEdit);
		btDel = (Button)findViewById(R.id.btIdDel);

		btIns.setOnClickListener(btInsListener);         	
		btCls.setOnClickListener(btClsListener);         	
		btList.setOnClickListener(btListListener);         	
		btQry.setOnClickListener(btQryListener);         	
		btEdit.setOnClickListener(btEditListener);         	
		btDel.setOnClickListener(btDelListener);         	
	}

	private OnClickListener btInsListener = new OnClickListener() {    
		public void onClick(View v) {
	        ContentValues contVal = new ContentValues();
			Uri uri=CompContentProvider.CONTENT_URI;
	        contVal=FillRec();
	        ContRes.insert(uri, contVal);
		}
	};
	private ContentValues FillRec(){  //user define method
        ContentValues contVal = new ContentValues();
        contVal.put("cusNo", etCusNo.getText().toString());
        contVal.put("cusNa", etCusNa.getText().toString());
        contVal.put("cusPho", etCusPho.getText().toString());
        contVal.put("cusAdd", etCusAdd.getText().toString());
        return contVal;
	}
	private OnClickListener btClsListener = new OnClickListener() {    
		public void onClick(View v) {
			etCusNo.setText("");
			etCusNa.setText("");
			etCusPho.setText("");
			etCusAdd.setText("");
		}
	};
	private OnClickListener btListListener = new OnClickListener() {    
		public void onClick(View v) {
			Uri uri=CompContentProvider.CONTENT_URI;
			String[] projection=new String[]{"cusNo", "cusNa", 
					"cusPho", "cusAdd"};
			String selection=null;
			String[] selectionArgs=null;
			String sortOrder=null;
			Cursor cur = ContRes.query(uri,projection,selection,
					selectionArgs,sortOrder);

			if (cur == null) return;
			if (cur.getCount() == 0) {
				etResult.setText("");
				Toast.makeText(Ch13ContProvid.this, "沒有資料", 
						Toast.LENGTH_LONG).show();
			}
			else {
				cur.moveToFirst();
				etResult.setText(cur.getString(0)+" " + 
					cur.getString(1)+" " +cur.getString(2)+
					" " + cur.getString(3));
				
				while (cur.moveToNext())
					etResult.append("\n" + cur.getString(0)+" " + 
						cur.getString(1)+" " + cur.getString(2)+" "
						+ cur.getString(3));
				Toast.makeText(Ch13ContProvid.this, "共 "+
						cur.getCount()+" 筆記錄", Toast.LENGTH_LONG)
				.show();
			}
		}
	};
	private OnClickListener btQryListener = new OnClickListener() {    
		public void onClick(View v) {

			Cursor cur = null;
			if (etCusNo.getText().toString().isEmpty() == false) {
				Uri uri=CompContentProvider.CONTENT_URI;
				String[] projection=new String[]{"cusNo", "cusNa", 
						"cusPho", "cusAdd"};
				String selection="cusNo=" + "\'" + 
						etCusNo.getText().toString()+ "\'";
				String[] selectionArgs=null;
				String sortOrder=null;
				cur = ContRes.query(uri,projection,selection,
						selectionArgs,sortOrder);
			} else {
				Toast.makeText(Ch13ContProvid.this, 
						"客戶編號為空值，只能以客戶編號查詢 !",Toast.LENGTH_LONG)
					 .show();
			}

			if (cur == null) return;
			if (cur.getCount() == 0) {
				etResult.setText("");
				Toast.makeText(Ch13ContProvid.this, "沒有資料", 
						Toast.LENGTH_LONG).show();
			}
			else {
				cur.moveToFirst();
				etCusNo.setText(cur.getString(0));
				etCusNa.setText(cur.getString(1));
				etCusPho.setText(cur.getString(2));
				etCusAdd.setText(cur.getString(3));
			}
		}
	};        
	
	private OnClickListener btEditListener = new OnClickListener() {    
		public void onClick(View v) {

			Uri uri=CompContentProvider.CONTENT_URI;
	        ContentValues contVal = new ContentValues();
	        contVal=FillRec();
	        String CusNo=etCusNo.getText().toString().trim();
			String whereClause = "cusNo='" + CusNo + "'";		
			String[] selectionArgs=null;
			int rowsAffected  = 
				ContRes.update(uri,contVal,whereClause,selectionArgs);
			if (rowsAffected==-1) {
				msg="資料表已空, 無法修改 !";
			} else if (rowsAffected==0) {
				msg="找不到欲修改的記錄, 無法修改 !";
			} else {
				msg="共 "+rowsAffected + " 筆記錄   被修改 !";
			}
			Toast.makeText(Ch13ContProvid.this, msg, 
					Toast.LENGTH_SHORT).show();

		}
	};
	private OnClickListener btDelListener = 
			new OnClickListener() {    
		public void onClick(View v) {

			Uri uri=CompContentProvider.CONTENT_URI;
			String CusNo = etCusNo.getText().toString().trim();
			String whereClause = "cusNo='" + CusNo + "'";		
			String[] selectionArgs=null;		
			int rowsAffected = 
					ContRes.delete(uri,whereClause,selectionArgs);

			if (rowsAffected==-1) {
				msg="資料表已空, 無法刪除 !";
			} else if (rowsAffected==0) {
				msg="找不到欲刪除的記錄, 無法刪除 !";
			} else {
				msg="目前的 "+rowsAffected + " 筆記錄   已被刪除 !";
			}
			Toast.makeText(Ch13ContProvid.this, msg, 
					Toast.LENGTH_SHORT).show();

		}
	};
}