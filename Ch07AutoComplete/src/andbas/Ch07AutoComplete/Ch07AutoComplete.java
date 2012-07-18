package andbas.Ch07AutoComplete;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Ch07AutoComplete extends Activity {
    private AutoCompleteTextView acPlace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
    }

	private void buildViews() {
		acPlace = (AutoCompleteTextView)findViewById(R.id.acIdPlace);
		String[] PlaceAry = 
			getResources().getStringArray(R.array.PlaceArray);
		ArrayAdapter<String> adapterPlace = 
			new ArrayAdapter<String>(this, R.layout.listitem, PlaceAry);
		acPlace.setAdapter(adapterPlace);
	}	
}