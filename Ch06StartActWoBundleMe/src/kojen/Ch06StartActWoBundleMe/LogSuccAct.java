package kojen.Ch06StartActWoBundleMe;

import android.app.Activity;
import android.os.Bundle;

public class LogSuccAct extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logsucc);
    }
}