package andbas.Ch13ContProvid.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class CompContentProvider extends ContentProvider {
	private static final String AUTHORITY =
			"andbas.Ch13ContProvid.providers.CompContentProvider";
	private static final String DBname = "先進公司.db",
								TBname = "客戶";
	private static final int URI_ROOT = 0,
							 DB_TABLE_客戶 = 1;
	public static final Uri CONTENT_URI = 
			Uri.parse("content://"+ AUTHORITY + "/" + TBname);
	private static final UriMatcher uriMatcher = 
			new UriMatcher(URI_ROOT);
	static {
		uriMatcher.addURI(AUTHORITY, TBname, DB_TABLE_客戶);
	}
	private SQLiteDatabase CompDB;
	
	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if (uriMatcher.match(uri) != DB_TABLE_客戶) {
			throw new IllegalArgumentException("未知的 URI !" + uri);
		}
        
		long rowId = CompDB.insert(TBname, null, values);
        Uri uriAfterIns = ContentUris.withAppendedId(CONTENT_URI, rowId);
        getContext().getContentResolver().notifyChange(uriAfterIns, null);
		return uriAfterIns;
	}

	@Override
	public boolean onCreate() {
		CompDBHper dbHper = new CompDBHper(
        		getContext(), DBname, null, 1);
        CompDB = dbHper.getWritableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		if (uriMatcher.match(uri) != DB_TABLE_客戶) {
			throw new IllegalArgumentException("未知的 URI !" + uri);
		}
		Cursor cur = CompDB.query(true, TBname, projection, selection,
					null, null, null, null, null);
		cur.setNotificationUri(getContext().getContentResolver(), uri);
		return cur;
	}

	@Override
	public int update(Uri uri, ContentValues values, String whereClause,
			String[] selectionArgs) {
		if (uriMatcher.match(uri) != DB_TABLE_客戶) {
			throw new IllegalArgumentException("未知的 URI !" + uri);
		}
		int rowsAffected = CompDB.update(TBname, values, 
				whereClause, null);
		return rowsAffected;
	}
	@Override
	public int delete(Uri  uri, String  whereClause, 
			String[]  selectionArgs) {
		if (uriMatcher.match(uri) != DB_TABLE_客戶) {
			throw new IllegalArgumentException("未知的 URI !" + uri);
		}
		int rowsAffected = CompDB.delete(TBname, 
				whereClause, null);
		return rowsAffected;
	}
}
