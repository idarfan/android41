/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package andbas.Ch15AnimView2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ViewFlipper;

public class Ch15AnimView2 extends Activity {
    private String[] mStrings = {"向上推", "向左推", "交叉淡出", "超空間"};
    private ViewFlipper vwFlipper;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        vwFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
        vwFlipper.startFlipping();

        Spinner animSpin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mStrings);
        adapter.setDropDownViewResource(
        		android.R.layout.simple_spinner_dropdown_item);
        animSpin.setAdapter(adapter);
        animSpin.setOnItemSelectedListener(spnEduListener);
    }

	private OnItemSelectedListener spnEduListener =
			new OnItemSelectedListener () {
	    public void onItemSelected(AdapterView<?> parent, 
	    		View v, int position, long id) {
	      switch (position) {
	        case 0:
              vwFlipper.setInAnimation(AnimationUtils.loadAnimation(
	            		Ch15AnimView2.this,R.anim.push_up_in));
	            vwFlipper.setOutAnimation(AnimationUtils.loadAnimation(
	            		Ch15AnimView2.this,R.anim.push_up_out));
	            break;
	        case 1:
	            vwFlipper.setInAnimation(AnimationUtils.loadAnimation(
	            		Ch15AnimView2.this,R.anim.push_left_in));
	            vwFlipper.setOutAnimation(AnimationUtils.loadAnimation(
	            		Ch15AnimView2.this,R.anim.push_left_out));
	            break;
	        case 2:
	            vwFlipper.setInAnimation(AnimationUtils.loadAnimation(
	            		Ch15AnimView2.this,android.R.anim.fade_in));
	            vwFlipper.setOutAnimation(AnimationUtils.loadAnimation(
	            		Ch15AnimView2.this,android.R.anim.fade_out));
	            break;
	        default:
	            vwFlipper.setInAnimation(AnimationUtils.loadAnimation(
	            		Ch15AnimView2.this,R.anim.hyperspace_in));
	            vwFlipper.setOutAnimation(AnimationUtils.loadAnimation(
	            		Ch15AnimView2.this,R.anim.hyperspace_out));
	            break;
	      }
	    }
	    public void onNothingSelected(AdapterView<?> parent) {
	    }
	};
}