package andbas.Ch11ListFragment;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        
        buildViews();  //user define
    }

    private void buildViews(){
		TextView text = (TextView)findViewById(R.id.tvIdNews);
    	Bundle bundle=this.getIntent().getExtras();
    	int index=bundle.getInt("index");
		String st=readFromAss(index+1);
		text.setTextColor(Color.GREEN);
		text.setTextSize(16);
		text.setText(st);
    }
    private  String readFromAss(int index) {
    	String text = null;
    	String fileNa="news"+index+".txt";
    	try {
            InputStream inpSt = getAssets().open(fileNa);
            int size = inpSt.available();
            byte[] buffer = new byte[size];
            inpSt.read(buffer);
            inpSt.close();
            text =new String(buffer);
        } catch (IOException e) {
            Log.e("DetailsActivity", e.toString());
        }
    	return text;
	}
}
