package andbas.Ch10userBroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BCReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		String msg = intent.getStringExtra("msg");
		Toast.makeText(context, "已收到廣播通知：" + msg + "!", 
			           Toast.LENGTH_LONG).show();
	}
}
