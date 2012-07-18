package andbas.Ch09AldBuilder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Ch09AldBuilder extends Activity {
	protected static final int BUTTON_POSITIVE = -1;
	protected static final int BUTTON_NEGATIVE = -2;
	protected static final int BUTTON_NEUTRAL = -3;
	private Button btExitEd;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }
    private void buildViews(){
    	btExitEd = (Button)findViewById(R.id.btIdExitEd);           
        btExitEd.setOnClickListener(btListener);         	
    }
    
    private OnClickListener btListener = new OnClickListener() {    
    	public void onClick(View v) {      
			AlertDialog.Builder adBuild=
					new AlertDialog.Builder(Ch09AldBuilder.this){};
			adBuild.setTitle(R.string.aldTitle);
			adBuild.setMessage(R.string.aldPrompt);
			adBuild.setIcon(R.drawable.save);
			adBuild.setCancelable(false);
			adBuild.setPositiveButton(R.string.btPtPosit, aldBtListener);
			adBuild.setNegativeButton(R.string.btPtNeg, aldBtListener);
			adBuild.setNeutralButton(R.string.btPtNeut, aldBtListener);
			adBuild.show();
    	}
    };

    private DialogInterface.OnClickListener aldBtListener = 
    		new DialogInterface.OnClickListener() {    
    	public void onClick(DialogInterface dialog, int id) {      
        	String st=new String();
    		switch (id){
	    		case BUTTON_POSITIVE :
	    			st="您按了'是'鈕，將會儲存檔案並結束編輯 !";
	    			Ch09AldBuilder.this.finish();
	    			break;
	    		case BUTTON_NEGATIVE :
	    			st="您按了'否'鈕，將不會儲存檔案並結束編輯 !";    			
	    			Ch09AldBuilder.this.finish();
	    			break;
	    		case BUTTON_NEUTRAL :
	    			st="您按了'取消'鈕，將取消結束編輯回到編輯模式 !";    			
	    			break;
    		}
    		
    		Toast.makeText(Ch09AldBuilder.this,
    				st,
    				Toast.LENGTH_SHORT)
    		     .show();	
    	}    
    };    
}