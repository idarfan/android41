package andbas.Ch11ActBarTab2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Image1 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

    public static class Image1Fragment extends Fragment {
      int mNum;
      static Image1Fragment newInstance(int num) {
	    	Image1Fragment frag = new Image1Fragment();
	        Bundle args = new Bundle();
	        args.putInt("num", num);
	        frag.setArguments(args);
	        return frag;
      }
      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? 
            		getArguments().getInt("num") : 1;
      }
      @Override
      public View onCreateView(LayoutInflater inflater, 
        		ViewGroup container,Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.image1, 
            		container, false);
            return v;
      }
    }
}