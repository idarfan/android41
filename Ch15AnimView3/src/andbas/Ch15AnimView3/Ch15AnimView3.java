/*
 * Copyright (C) 2009 The Android Open Source Project
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

package andbas.Ch15AnimView3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Ch15AnimView3 extends Activity {
	private static final String[] INTERPOLATORS = {
	   "加速", "減速", "加速/減速","搶先", "過衝", "搶先/過衝","彈跳"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	
	    Spinner animSpin = (Spinner) findViewById(R.id.spinner);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_spinner_item, INTERPOLATORS);
	    adapter.setDropDownViewResource(
	    		android.R.layout.simple_spinner_dropdown_item);
	    animSpin.setAdapter(adapter);
	    animSpin.setOnItemSelectedListener(spnListener);
	}
	
	private OnItemSelectedListener spnListener =
			new OnItemSelectedListener () {
		public void onItemSelected(AdapterView<?> parent, 
				View v, int position, long id) {
		  final View target = findViewById(R.id.target);
		  final View targetParent = (View) target.getParent();
		
		  Animation anim = new TranslateAnimation(0.0f,
		      targetParent.getWidth() - target.getWidth() - 
		      targetParent.getPaddingLeft() -
		      targetParent.getPaddingRight(), 0.0f, 0.0f);
		  anim.setDuration(1000);
		  anim.setStartOffset(300);
		  anim.setRepeatMode(Animation.RESTART);
		  anim.setRepeatCount(Animation.INFINITE);
		
		  switch (position) {
		    case 0:
		   	  anim.setInterpolator(AnimationUtils.loadInterpolator(
		         Ch15AnimView3.this,android.R.anim.accelerate_interpolator));
		      break;
		    case 1:
		      anim.setInterpolator(AnimationUtils.loadInterpolator(
		         Ch15AnimView3.this,android.R.anim.decelerate_interpolator));
		      break;
		    case 2:
		      anim.setInterpolator(AnimationUtils.loadInterpolator(
		    	 Ch15AnimView3.this,
		    	 android.R.anim.accelerate_decelerate_interpolator));
		      break;
		    case 3:
		      anim.setInterpolator(AnimationUtils.loadInterpolator(
		    	 Ch15AnimView3.this,android.R.anim.anticipate_interpolator));
		      break;
		    case 4:
		      anim.setInterpolator(AnimationUtils.loadInterpolator(
		    	 Ch15AnimView3.this,android.R.anim.overshoot_interpolator));
		      break;
		    case 5:
		      anim.setInterpolator(AnimationUtils.loadInterpolator(
		    	 Ch15AnimView3.this,
		    	 android.R.anim.anticipate_overshoot_interpolator));
		      break;
		    case 6:
		      anim.setInterpolator(AnimationUtils.loadInterpolator(
		    	 Ch15AnimView3.this,android.R.anim.bounce_interpolator));
		      break;
		    }		
		    target.startAnimation(anim);
		}		
		public void onNothingSelected(AdapterView<?> parent) {
		}
	};
}