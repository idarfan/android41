package andbas.Ch08GetAsset;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Ch08GetAsset extends Activity {
    private TextView tvIntro;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

	private void buildViews() {
		tvIntro = (TextView)findViewById(R.id.tvIdIntro);
		readFromAss();
	}
	
 	protected void readFromAss() {
        try {
            InputStream inpSt = getAssets().open("intro.txt");
            int size = inpSt.available();
            byte[] buffer = new byte[size];
            inpSt.read(buffer);
            inpSt.close();
            String text = new String(buffer);
            tvIntro.setText(text);
        } catch (IOException e) {
            Log.e("Ch08GetAsset", e.toString());
        }		
	}
}