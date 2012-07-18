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

package andbas.Ch15PathEffect;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class Ch15PathEffect extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));
    }

    private static class ghView extends View {
        private Paint paint;
        private Path path;
        private PathEffect[] pathEff;
        private int[] colors;
        private float phase;

        public ghView(Context context) {
            super(context);

            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(6);

            path = makeFollowPath();
            pathEff = new PathEffect[6];
            colors = new int[] { Color.BLACK, Color.RED, Color.BLUE,
                                  Color.GREEN, Color.MAGENTA, Color.BLACK
                                };
        }

        private static Path makeFollowPath() {
        	Path path= new Path();
        	path.moveTo(0, 0);
            for (int i = 1; i <= 15; i++) {
            	path.lineTo(i*20, (float)Math.random() * 35);
            }
            return path;
        }

        private static Path makePathDash() {
            Path path = new Path();
            path.moveTo(4, 0);
            path.lineTo(0, -4);
            path.lineTo(8, -4);
            path.lineTo(12, 0);
            path.lineTo(8, 4);
            path.lineTo(0, 4);
            return path;
        }
        private static void makeEffects(PathEffect[] e, float phase) {
            e[0] = null;     // 沒有效果
            e[1] = new CornerPathEffect(10);
            e[2] = new DashPathEffect(new float[] {10, 5, 5, 5}, phase);
            e[3] = new PathDashPathEffect(makePathDash(), 12, phase,
                                          PathDashPathEffect.Style.ROTATE);
            e[4] = new ComposePathEffect(e[2], e[1]);
            e[5] = new ComposePathEffect(e[3], e[1]);
        }
        
        @Override 
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            RectF bounds = new RectF();
            path.computeBounds(bounds, false);
            canvas.translate(10 - bounds.left, 10 - bounds.top);

            makeEffects(pathEff, phase);
            phase += 1;
            invalidate();

            for (int i = 0; i < pathEff.length; i++) {
                paint.setPathEffect(pathEff[i]);
                paint.setColor(colors[i]);
                canvas.drawPath(path, paint);
                canvas.translate(0, 28);
            }
        }
    }
}