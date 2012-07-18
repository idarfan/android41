package andbas.Ch12BrowseRec;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CompDBHper extends SQLiteOpenHelper {
//	private static final String DBname = "先進公司.db";
//	private static final int DBversion = 1;
	private static final String TBname = "客戶";
	private static final String crTBsql = 
					"CREATE TABLE " + TBname + " ( " +
					" cusNo VARCHAR(10) NOT NULL, " +
					" cusNa VARCHAR(20) NOT NULL, " +
					" cusPho VARCHAR(20), " +
					" cusAdd VARCHAR(50), PRIMARY KEY (cusNo)); ";

	public CompDBHper(Context context, String DBname, 
			CursorFactory factory, int DBversion) {
//		super(context, DBname, null, DBversion);
		super(context, "先進公司.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(crTBsql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, 
			int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TBname);
		onCreate(db);
	}
	public void createTB() {
		SQLiteDatabase db = getWritableDatabase();		
		ContentValues[] rec = new ContentValues[3];
		for(int i=0; i<rec.length; i++)
			rec[i] = new ContentValues();
		
		rec[0].put("cusNo", "A1001");
		rec[0].put("cusNa", "林怡靜");
		rec[0].put("cusPho", "(03) 873-1234");
		rec[0].put("cusAdd", "桃園縣平安村49號");
		
		rec[1].put("cusNo", "A1002");
		rec[1].put("cusNa", "吳美虹");
		rec[1].put("cusPho", "(02) 822-3129");
		rec[1].put("cusAdd", "北市延平南路20號");
		
		rec[2].put("cusNo", "A1003");
		rec[2].put("cusNa", "許仁均");
		rec[2].put("cusPho", "(02) 704-1134");
		rec[2].put("cusAdd", "北市健康路15號");	
		
		for(ContentValues row : rec){
			db.insert(TBname, null, row);
		}	
		db.close();
	}

	public ArrayList<String> getRecSet(){
		SQLiteDatabase db = getReadableDatabase();
		String sql = "SELECT * FROM " + TBname;
		Cursor recSet = db.rawQuery(sql, null);
		ArrayList<String> recAry = new ArrayList<String>();
		int columnCount = recSet.getColumnCount();
		while(recSet.moveToNext()){
			String fldSet = "";
			for(int i=0; i<columnCount; i++)
				fldSet += recSet.getString(i) + "#";
			recAry.add(fldSet);			
		}
		recSet.close();
		db.close();
		return recAry;		
	}
}
