package andbas.Ch13ReadSMS;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class SMSAdapter extends CursorAdapter {
	private LayoutInflater layoutInflater;
	private TextView tvPhoneNo,tvCont;

	public SMSAdapter(Context context, Cursor cursor)
	{
		super(context, cursor);
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		tvPhoneNo = (TextView) view.findViewById(R.id.tvIdPhoneNo);
		tvCont = (TextView) view.findViewById(R.id.tvIdCont);

		tvPhoneNo.setText(cursor.getString(cursor.getColumnIndex("address")));
		tvCont.setText(cursor.getString(cursor.getColumnIndex("body")));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = layoutInflater.inflate(R.layout.item, null);
		return view;
	}
}
