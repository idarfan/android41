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

package andbas.Ch15DwShape;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.View;

public class Ch15DwShape extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));
    }

    private static class ghView extends View {
        private ShapeDrawable[] dwShape;
        private Shader SdmakeTiling,SdmakeLinear,SdmakeSweep;

        public ghView(Context context) {
            super(context);

            setFocusable(true);

            float[] outerR = new float[] { 12, 12, 12, 12, 0, 0, 0, 0 };
            RectF   inset = new RectF(6, 6, 6, 6);
            float[] innerR = new float[] { 12, 12, 0, 0, 12, 12, 0, 0 };

            Path path = new Path();
            path.moveTo(50, 0);
            path.lineTo(0, 50);
            path.lineTo(50, 100);
            path.lineTo(100, 50);
            path.close();
            
            dwShape = new ShapeDrawable[7];
            dwShape[0] = new ShapeDrawable(new RectShape());
            dwShape[1] = new ShapeDrawable(new OvalShape());
            dwShape[2] = new ShapeDrawable(
            		         new RoundRectShape(outerR, null,null));
            dwShape[3] = new ShapeDrawable(
            				 new RoundRectShape(outerR, inset,null));
            dwShape[4] = new ShapeDrawable(
            				 new RoundRectShape(outerR, inset,innerR));
            dwShape[5]= new ShapeDrawable(new PathShape(path, 100, 100));
            dwShape[6] = new ShapeDrawable(new ArcShape(45, -270));
           
            SdmakeSweep=new SweepGradient(150, 25,
                new int[] { 0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0xFFFF0000 },
                null);
            SdmakeLinear=new LinearGradient(0, 0, 50, 50,
                new int[] { 0xFFFF0000, 0xFF00FF00, 0xFF0000FF },
                null, Shader.TileMode.MIRROR);

            int[] pixels = new int[]{0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0};
            Bitmap bm = Bitmap.createBitmap(pixels, 2, 2,
                                            Bitmap.Config.ARGB_8888);
            SdmakeTiling=new BitmapShader(bm, Shader.TileMode.REPEAT,
                                        Shader.TileMode.REPEAT);
            
            dwShape[0].getPaint().setColor(0xFFFF0000);
            dwShape[1].getPaint().setColor(0xFF00FF00);
            dwShape[2].getPaint().setColor(0xFF0000FF);
            dwShape[3].getPaint().setShader(SdmakeSweep);
            dwShape[4].getPaint().setShader(SdmakeLinear);
            dwShape[5].getPaint().setShader(SdmakeTiling);
            dwShape[6].getPaint().setColor(0x88FF8844);
            
            PathEffect pe = new DiscretePathEffect(10, 4);
            PathEffect pe2 = new CornerPathEffect(4);
            dwShape[3].getPaint().setPathEffect(
                        new ComposePathEffect(pe2, pe));
        }

        protected void onDraw(Canvas canvas) {

            int x = 10;
            int y = 10;
            int width = 300;
            int height = 50;

            for (Drawable dr : dwShape) {
            	dr.setBounds(x, y, x + width, y + height);
            	dr.draw(canvas);
                y += height + 5;
            }
        }
    }
}