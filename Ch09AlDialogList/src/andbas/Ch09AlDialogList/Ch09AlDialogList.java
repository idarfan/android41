package andbas.Ch09AlDialogList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class Ch09AlDialogList extends Activity {
	private EditText etColor1;
	private EditText etColor2;
	private EditText etHobby;
	private	AlertDialog.Builder adBuild;
	private CharSequence[] ClItems = {"紅色", "綠色", "藍色"};
	private CharSequence[] HbItems = {"打球", "健行", "爬山"};
	private String st = "";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

    private void buildViews(){
    	etColor1 = (EditText)findViewById(R.id.etIdColor1);    
    	etColor2 = (EditText)findViewById(R.id.etIdColor2);    
    	etHobby = (EditText)findViewById(R.id.etIdHobby);    
        
    	etColor1.setOnKeyListener(kyListener);         	
    	etColor2.setOnKeyListener(kyListener);         	
    	etHobby.setOnKeyListener(kyListener);         	
    }
 
    private OnKeyListener kyListener = new OnKeyListener() {    
		public boolean onKey(View v, int keyCode, KeyEvent event) {
 			if(event.getAction() == KeyEvent.ACTION_DOWN &&
					keyCode == KeyEvent.KEYCODE_ENTER){
 				EditText et=(EditText) v;
 				Integer id=et.getId();
 				adBuild=new AlertDialog.Builder(Ch09AlDialogList.this){};
 	 			if (id==R.id.etIdColor1) {
	 					adBuild.setTitle("請選擇一個顏色");
		 				adBuild.setItems(ClItems, aldLtListener);}
 	 			else if (id==R.id.etIdColor2){
		 				adBuild.setTitle("請選擇一個顏色");
		 				adBuild.setSingleChoiceItems(ClItems,-1, 
		 						aldRgListener);
		 				adBuild.setPositiveButton("確定", aldBtListener1);
		 			 }
 				else {
		 				adBuild.setTitle("請選擇興趣(可複選)");
		 				adBuild.setMultiChoiceItems(HbItems,
		 						new boolean[]{false, false, false}, 
		 						aldCgListener);
		 				adBuild.setPositiveButton("確定", aldBtListener2);
		 			 }				
 	 			AlertDialog alert = adBuild.create();
 	 			alert.show();
			    return true;
			}
    		else
    		    return false;
    	}
    };
    private DialogInterface.OnClickListener aldLtListener = 
    		new DialogInterface.OnClickListener() {    
    	public void onClick(DialogInterface dialog, int id) {      

    		etColor1.setText(ClItems[id]);
    	}    
    };    
    private DialogInterface.OnClickListener aldRgListener = 
    		new DialogInterface.OnClickListener() {    
    	public void onClick(DialogInterface dialog, int id) {      
    		etColor2.setText(ClItems[id]);
    	}    
    };    

    private DialogInterface.OnMultiChoiceClickListener aldCgListener = 
    		new DialogInterface.OnMultiChoiceClickListener() {    
		public void onClick(DialogInterface dialog, 
				int which, boolean isChecked) {

			if (isChecked)
				st += HbItems[which]+"/";
		}    
    };
    
    private DialogInterface.OnClickListener aldBtListener1 = 
    		new DialogInterface.OnClickListener() {    
    	public void onClick(DialogInterface dialog, int id) {      
        	dialog.cancel();
    	}
    };    
    private DialogInterface.OnClickListener aldBtListener2 = 
    		new DialogInterface.OnClickListener() {    
    	public void onClick(DialogInterface dialog, int id) {      
    		etHobby.setText(st);
			
    	}
    };    
}