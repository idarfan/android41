package com.ch05iimageviewandbuttonsound;

import android.os.Bundle;
import android.view.MotionEvent;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.app.Activity;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Ch05ImageViewAndButtonSound extends Activity {
	private EditText etName;
	private ImageView ivSure;
	private RadioGroup rgSex;
	private String stSex, st = new String();
	private MediaPlayer buttonSound;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		buildViews();
	}

	private void buildViews() {
		etName = (EditText) findViewById(R.id.etIdName);		
		rgSex = (RadioGroup) findViewById(R.id.rgIdSex);
		ivSure = (ImageView) findViewById(R.id.ivIdSure);
		buttonSound = MediaPlayer.create(Ch05ImageViewAndButtonSound.this,
				R.raw.button2);
		ivSure.setOnTouchListener(ivListener);
		rgSex.setOnCheckedChangeListener(rgdListener);
	}

	private OnCheckedChangeListener rgdListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(RadioGroup group, int checkId) {
			// int intChkrb=rgSex.getCheckedRadioButtonId();
			switch (checkId) {
			case R.id.rbIdMale:
				stSex = "男性";
				break;
			case R.id.rbIdFemale:
				stSex = "女性";
				break;
			case R.id.rbIdNoneSex:
				stSex = "人妖";
				break;
			}
		}
	};
	private OnTouchListener ivListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			buttonSound.start();
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (etName.getText().length() == 0)
					Toast.makeText(Ch05ImageViewAndButtonSound.this, "請輸入姓名",
							Toast.LENGTH_LONG).show();
				else {
					if (stSex.equals("男性")) {
						st = etName.getText().toString() + "先生，你好";
					} else if (stSex.equals("女性")) {
						st = etName.getText().toString() + "小姐，你好";
					} else if (stSex.equals("人妖")) {
						st = etName.getText().toString() + "人妖，你好";
					}
					Toast.makeText(Ch05ImageViewAndButtonSound.this, st,
							Toast.LENGTH_LONG).show();
				}
			}
			return true;

		}
	};

}
