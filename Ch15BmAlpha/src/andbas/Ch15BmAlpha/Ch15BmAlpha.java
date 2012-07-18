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

package andbas.Ch15BmAlpha;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

public class Ch15BmAlpha extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));
    }

    private static class ghView extends View {
        private Bitmap bm,bm2,bm3;
        private Shader SdmakeLinear;

        private static void drawIntoBitmap(Bitmap bm) {
            float x = bm.getWidth();
            float y = bm.getHeight();
            Canvas c = new Canvas(bm);
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            paint.setAlpha(0x80);
            c.drawCircle(x/2, y/2, x/2, paint);

            paint.setAlpha(0x30);
            paint.setXfermode(
           		new PorterDuffXfermode(PorterDuff.Mode.SRC));
            paint.setTextSize(60);
            paint.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetrics fm = paint.getFontMetrics();
            c.drawText("程式設計", x/2, (y-fm.ascent)/2, paint);
        }

        public ghView(Context context) {
            super(context);
            setFocusable(true);

            InputStream is = context.getResources().
            		openRawResource(R.drawable.forward);
            bm = BitmapFactory.decodeStream(is);
            bm2 = bm.extractAlpha();
            bm3 = Bitmap.createBitmap(
            		200, 200, Bitmap.Config.ALPHA_8);
            drawIntoBitmap(bm3);

            SdmakeLinear = new LinearGradient(
            	0, 0, 100, 70, 
            	new int[] {Color.RED, Color.GREEN, Color.BLUE },
                null, Shader.TileMode.MIRROR);
        }

        @Override protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            Paint paint = new Paint();
            float y = 10;

            paint.setColor(Color.RED);
            canvas.drawBitmap(bm, 10, y, paint);
            y += bm.getHeight() + 10;
            canvas.drawBitmap(bm2, 10, y, paint);
            y += bm2.getHeight() + 10;
            paint.setShader(SdmakeLinear);
            canvas.drawBitmap(bm3, 10, y, paint);
        }
    }
}