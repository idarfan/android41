package andbas.Ch12InsertRec;

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
	public long insertRec(String CusNo,String CusNa,
			String CusPho,String CusAdd) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues rec = new ContentValues();
		rec.put("cusNo", CusNo);
		rec.put("cusNa", CusNa);
		rec.put("cusPho", CusPho);
		rec.put("cusAdd", CusAdd);
		long rowID  = db.insert(TBname, null, rec);
		db.close();
		return rowID ;
	}
	public int RecCount(){
		SQLiteDatabase db = getWritableDatabase();
		String sql = "SELECT * FROM " + TBname;
		Cursor recSet = db.rawQuery(sql, null);
		return recSet.getCount();
	}
}
