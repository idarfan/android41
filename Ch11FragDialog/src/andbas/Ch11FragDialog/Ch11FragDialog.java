package andbas.Ch11FragDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Ch11FragDialog extends Activity {
	private Button btExitEd;
	String st=new String();

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
            DialogFragment newFrag = MyDialFragment.newInstance(
                    R.string.alert_dialog);
            newFrag.show(getFragmentManager(), "dialog");
    	}
    };

    public static  class MyDialFragment extends DialogFragment {
        public static  MyDialFragment newInstance(int title) {
            MyDialFragment frag = new MyDialFragment();
            Bundle args = new Bundle();
            args.putInt("title", title);
            frag.setArguments(args);
            return frag;
        }
        
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int title = getArguments().getInt("title");
            
    		return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.save)
                .setTitle(title)
                .setPositiveButton(R.string.btPtPosit,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, 
                        		int whichButton) {
                            ((Ch11FragDialog)getActivity()).doPositiveClick();
                        }
                    }
                )
                .setNegativeButton(R.string.btPtNeg,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, 
                        		int whichButton) {
                            ((Ch11FragDialog)getActivity()).doNegativeClick();
                        }
                    }
                )
                .setNeutralButton(R.string.btPtNeut,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, 
                        		int whichButton) {
                            ((Ch11FragDialog)getActivity()).doNeutralClick();
                        }
                    }
                )
                .create();
        }
    }
    public void doPositiveClick() {
		st="您按了'是'鈕，將會儲存檔案並結束編輯 !";
		this.finish();
		Toast.makeText(this,st,Toast.LENGTH_SHORT).show();	
    }
    public void doNegativeClick() {
 		st="您按了'否'鈕，將不會儲存檔案並結束編輯 !";    			
		this.finish();
		Toast.makeText(this,st,Toast.LENGTH_SHORT).show();	
    }
    public void doNeutralClick() {
		st="您按了'取消'鈕，將取消結束編輯回到編輯模式 !";    			
		Toast.makeText(this,st,Toast.LENGTH_SHORT).show();	
    }
}