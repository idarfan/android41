package andbas.Ch13ReadSMS;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

public class Ch13ReadSMS extends Activity {
	private static ContentResolver ContRes;
	private ListView lvSMS;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		lvSMS = (ListView) findViewById(R.id.lvIdSMS);

		ContRes = getContentResolver();
		Uri uri=Uri.parse("content://sms/inbox");
		String[] projection=null;
		String selection="address like ?";
		String[] selectionArgs=new String[]{ "0%" };
		String sortOrder=null;
		Cursor cur = ContRes.query(uri,projection,selection,
				selectionArgs,sortOrder);
		
		SMSAdapter smsAdapter = new SMSAdapter(this, cur);
		lvSMS.setAdapter(smsAdapter);
	}
}