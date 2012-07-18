package andbas.Ch08GetExtSto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Ch08GetExtSto extends Activity {
	private ImageView ivFlow;
	private Button btSvPub;
	private Button btRdPub;
	private TextView tvFlowPath;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
     }

     private void buildViews(){
    	 ivFlow = (ImageView)findViewById(R.id.ivIdFlow);
    	 tvFlowPath = (TextView)findViewById(R.id.tvIdFlowPath);
       	 ivFlow.setImageDrawable(
 				getResources().getDrawable(R.drawable.flow01));
    	 btSvPub = (Button)findViewById(R.id.btIdSvPub);      	
    	 btRdPub = (Button)findViewById(R.id.btIdRdPub);      	
    	 btSvPub.setOnClickListener(btSvPubListener);         	
    	 btRdPub.setOnClickListener(btRdPubListener);         	
     }
     private OnClickListener btSvPubListener = new OnClickListener() {    
     	public void onClick(View v) {      
//			File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
	        File path = Environment.getExternalStoragePublicDirectory(
	                Environment.DIRECTORY_PICTURES);
	        File file = new File(path, "flow01.jpg");
	        writeToExt(file);
     	}
     };    
     private OnClickListener btRdPubListener = new OnClickListener() {    
      	public void onClick(View v) {      
// 			File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
 	        File path = Environment.getExternalStoragePublicDirectory(
 	                Environment.DIRECTORY_PICTURES);
 	        File file = new File(path, "flow02.jpg");
 	        readFromExt(file);
      	}
      };    
 	protected void writeToExt(File file) {
		File parentPath = file.getParentFile();
		if (! isSdcardWrable()){
			Toast.makeText(this, 
					"外部記憶卡不存在 !", Toast.LENGTH_LONG).show();
			return;
		}
        try {
            if(!parentPath.exists()) parentPath.mkdirs();
            if(file.exists()) file.delete();
            InputStream inpSt = getResources().
            		openRawResource(R.drawable.flow01);
            OutputStream outSt = new FileOutputStream(file);
            byte[] data = new byte[inpSt.available()];
            inpSt.read(data);
            outSt.write(data);
			Toast.makeText(this,"照片已存到外部記憶卡 ! 路徑為 : \n"+ 
					file.toString(), Toast.LENGTH_LONG).show();
			inpSt.close();
			outSt.close();  
        } catch (IOException e) {
            Log.e("Ch08GetExtSto", e.toString());
        }	
        
        String[] paths = {file.toString()};
        MediaScannerConnection.scanFile(this, paths, 
        		null,scanCompListener);
	}

 	protected void readFromExt(File file) {
 		Bitmap pic = null;
		if (! isSdcardRdable()){
			Toast.makeText(this, 
					"外部記憶卡不存在 !", Toast.LENGTH_LONG).show();
			return;
		}
        try {
            InputStream inpSt =new FileInputStream(file); 
            pic = BitmapFactory.decodeStream(inpSt);
            tvFlowPath.setText("讀取照片路徑："+file.toString());
            ivFlow.setImageBitmap(pic);
            Toast.makeText(this, "已自外部記憶卡讀取照片flow02.jpg ! 路徑為 : \n"+ 
            				file.toString(), Toast.LENGTH_LONG).show();
            inpSt.close();
        } catch (IOException e) {
            Log.e("Ch08GetExtSto", e.toString());
        }	
	}

 	private boolean isSdcardWrable() { 
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED))
			return true;
		else			
			return false;		
	}

	private boolean isSdcardRdable() { 
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED) || 
			state.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
			return true;
		else			
			return false;		
	}

	private OnScanCompletedListener scanCompListener = 
    		new OnScanCompletedListener() {    
        public void onScanCompleted(String path, Uri uri) {
            Log.i("Ch08GetExtSto", "Scanned " + path + ":");
            Log.i("Ch08GetExtSto", "-> uri=" + uri);
     	}
     };    
}