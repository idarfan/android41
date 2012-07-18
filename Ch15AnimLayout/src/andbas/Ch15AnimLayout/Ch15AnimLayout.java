/*
 * Copyright (C) 2010 The Android Open Source Project
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

package andbas.Ch15AnimLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class Ch15AnimLayout extends Activity {
    private int numButtons = 1;
    private Button btAddNew;
    private GridLayout gridContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gridContainer = (GridLayout) findViewById(R.id.gridContainer);

        btAddNew = (Button) findViewById(R.id.btIdAddNew);
        btAddNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button newButton = new Button(Ch15AnimLayout.this);
                newButton.setText(String.valueOf(numButtons++));
                newButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        gridContainer.removeView(v);
                    }
                });
                gridContainer.addView(newButton,
                		Math.min(1, gridContainer.getChildCount()));
            }
        });
    }
}