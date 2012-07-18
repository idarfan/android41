package andbas.Ch13ReadMedia;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Ch13ReadMedia extends Activity {
	private static ContentResolver ContRes;
	private ListView lvMedia;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		lvMedia = (ListView) findViewById(R.id.lvIdMedia);		

		ContRes = getContentResolver();
		Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String[] projection=null;
		String selection=null;
		String[] selectionArgs=null;
		String sortOrder=null;
		Cursor cur = ContRes.query(uri,projection,selection,
				selectionArgs,sortOrder);
		
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cur, new String[]
				{ MediaStore.Audio.Media.ALBUM }, new int[]
				{ android.R.id.text1 });	
		lvMedia.setAdapter(simpleCursorAdapter);
		
		startManagingCursor(cur);
		int albumIdx = cur.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM); 
		int titleIdx = cur.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);

		String[] result = new String[cur.getCount()];
		if (cur.moveToFirst())
		  do { 
		    String title = cur.getString(titleIdx);//歌名
		    String album = cur.getString(albumIdx);//專輯名
		    result[cur.getPosition()] = title + " (" + album + ")";
			Toast.makeText(this, result[cur.getPosition()], Toast.LENGTH_SHORT)
		         .show();		    
		  } while(cur.moveToNext());
		stopManagingCursor(cur);
	}
}