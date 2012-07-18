package andbas.Ch05App1;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ch05App1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LinearLayout.LayoutParams lp;
		lp=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				                         LayoutParams.FILL_PARENT);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout.LayoutParams tvLP;
        tvLP = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 
                                             LayoutParams.WRAP_CONTENT);
        TextView tvName = new TextView(this);
        tvName.setText("姓名：");
		ll.addView(tvName, tvLP);
		
		EditText etName = new EditText(this);
	    ll.addView(etName, tvLP);

	    TextView tvSex = new TextView(this);
	    tvSex.setText("性別：");
	    ll.addView(tvSex, tvLP);

	    EditText etSex = new EditText(this);
	    ll.addView(etSex, tvLP);       

	    Button btSure = new Button(this);
	    btSure.setText("確定");
	    ll.addView(btSure, tvLP);
		
	    this.addContentView(ll, lp);
	}
}
