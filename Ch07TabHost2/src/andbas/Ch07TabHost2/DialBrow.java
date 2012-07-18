package andbas.Ch07TabHost2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DialBrow extends Activity {
	private Button btDialer,btBrowser;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dial);

        buildViews();  //user define
    }

    private void buildViews(){
    	btDialer = (Button)findViewById(R.id.btIdDialer);    
    	btBrowser = (Button)findViewById(R.id.btIdBrowser);    

    	btDialer.setOnClickListener(btDialListener);         	
    	btBrowser.setOnClickListener(btBrowListener);         	
    }
    
    private OnClickListener btDialListener = new OnClickListener() {    
    	public void onClick(View v) {      
			Uri uri = Uri.parse("tel:0226532827");
			Intent intent = new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent);
    	}
    };

    private OnClickListener btBrowListener = new OnClickListener() {    
    	public void onClick(View v) {      
			Uri uri = Uri.parse("http://www.google.com/");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
    	}
    };

    public static class DialBrowFragment extends Fragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static DialBrowFragment newInstance(int num) {
        	DialBrowFragment f = new DialBrowFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.dial, container, false);
            View tv = v.findViewById(R.id.dialtext);
            ((TextView)tv).setText("Fragment #" + mNum);
            tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
            return v;
        }
    }
    
}
