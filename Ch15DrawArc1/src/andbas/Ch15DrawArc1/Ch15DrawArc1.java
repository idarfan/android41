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

package andbas.Ch15DrawArc1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class Ch15DrawArc1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));
    }

    private static class ghView extends View {
        private Paint[] paints;
        private Paint framePaint;
        private boolean[] isUseCent;
        private RectF[] rectf;
        private float startAng,sweepAng;

        private static final float SWEEP_INC = 2;
        private static final float START_INC = 15;

        public ghView(Context context) {
            super(context);

            paints = new Paint[3];
            isUseCent = new boolean[3];
            rectf = new RectF[3];

            paints[0] = new Paint();
            paints[0].setAntiAlias(true);
            paints[0].setStyle(Paint.Style.FILL);
            paints[0].setColor(0x88FF0000);
            isUseCent[0] = false;

            paints[1] = new Paint(paints[0]);
            paints[1].setStyle(Paint.Style.STROKE);
            paints[1].setStrokeWidth(4);
            paints[1].setColor(0x880000FF);
            isUseCent[1] = true;

            paints[2] = new Paint(paints[1]);
            paints[2].setStrokeWidth(5);
            paints[2].setColor(0xff885588);
            isUseCent[2] = false;

            rectf[0] = new RectF( 10, 10, 110, 110);
            rectf[1] = new RectF( 130, 10, 230, 110);
            rectf[2] = new RectF(250, 10, 350, 110);

            framePaint = new Paint();
            framePaint.setAntiAlias(true);
            framePaint.setStyle(Paint.Style.STROKE);
            framePaint.setStrokeWidth(0);
        }

        @Override 
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            for (int i = 0; i < 3; i++) {
                canvas.drawRect(rectf[i], framePaint);
                canvas.drawArc(rectf[i], startAng, sweepAng, 
                		isUseCent[i], paints[i]);
            }

            sweepAng += SWEEP_INC;
            if (sweepAng > 360) {
                sweepAng -= 360;
                startAng += START_INC;
                if (startAng >= 360) {
                    startAng -= 360;
                }
            }
            invalidate();
        }
    }
}