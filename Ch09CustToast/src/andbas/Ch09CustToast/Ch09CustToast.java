package andbas.Ch09CustToast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Ch09CustToast extends Activity {
	private Button btShow;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }    
	private void buildViews() {
		btShow = (Button)findViewById(R.id.btIdShow);
		btShow.setOnClickListener(btShowfListener);	
	}
    private OnClickListener btShowfListener = new OnClickListener() {
		public void onClick(View v) {
	        ShowCustToast(); //user define
		}
	};	
    
	private void ShowCustToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toastlayout,
               (ViewGroup) findViewById(R.id.toastlayout));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.notes);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("這是使用者自訂的  Toast 訊息!");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}