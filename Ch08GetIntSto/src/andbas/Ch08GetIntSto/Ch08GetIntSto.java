package andbas.Ch08GetIntSto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ch08GetIntSto extends Activity {
    private TextView tvFilePath;
    private TextView tvIntro;
    private Button btSave;
    private Button btRead;
    String textCont = new String();
    byte[] buffer;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();   //user define
    }

	private void buildViews() {
		tvFilePath = (TextView)findViewById(R.id.tvIdFilePath);
		tvIntro = (TextView)findViewById(R.id.tvIdIntro);
		btSave = (Button)findViewById(R.id.btIdSave);
		btRead = (Button)findViewById(R.id.btIdRead);
		readFromAss();
		
		btSave.setOnClickListener(btSvIntListener);         	
		btRead.setOnClickListener(btRdIntListener);         	
	}

 	protected void readFromAss() {
        try {
            InputStream inpSt = getAssets().open("intro.txt");
            int size = inpSt.available();
            buffer = new byte[size];
            inpSt.read(buffer);
            inpSt.close();
            textCont = new String(buffer);
            tvIntro.setText(textCont);
        } catch (IOException e) {
            Log.e("Ch08GetIntSto", e.toString());
        }		
	}

     private OnClickListener btSvIntListener = new OnClickListener() {    
      	public void onClick(View v) {      
			FileOutputStream flOutSt = null;
			try {
				flOutSt = openFileOutput("intro.txt", 
						Context.MODE_PRIVATE);
				flOutSt.write(buffer);
				flOutSt.close();
			} catch (IOException e) {
				Log.e("Ch08GetIntSto", e.toString());
			}	
			File path=getFileStreamPath("intro.txt");
			Toast.makeText(Ch08GetIntSto.this,
					"檔案已存到內部記憶體 ! 路徑為 : \n"+ 
					path.toString(), Toast.LENGTH_LONG).show();
      	}
     };    

     private OnClickListener btRdIntListener = new OnClickListener() {    
       	public void onClick(View v) {      
       		FileInputStream flInpSt = null;
			StringBuilder stBd = new StringBuilder();
			try {
				flInpSt = openFileInput("intro.txt");
				File path=getFileStreamPath("intro.txt");
				InputStreamReader inpStRd = 
						new InputStreamReader(flInpSt);
				BufferedReader buffRd = new BufferedReader(inpStRd);
				String str = "";
				while((str = buffRd.readLine())!=null){
					stBd.append(str+"\n");
				}
				buffRd.close();
				inpStRd.close();
				flInpSt.close();
				tvFilePath.setTextColor(Color.RED);
				tvFilePath.setText("讀取檔案路徑："+path.toString());
				tvIntro.setTextColor(Color.GREEN);
	            tvIntro.setText(stBd);
			} catch (IOException e) {
				Log.e("Ch08GetIntSto", e.toString());
			}
       	}
     };    
}