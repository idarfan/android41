package andbas.Ch10SysBroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BCReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("BCReceiver", "SUCCESS!!!");
		Toast.makeText(context,"已收到廣播，通知已更改系統日期 !", 
					   Toast.LENGTH_LONG).show();
	}
}
