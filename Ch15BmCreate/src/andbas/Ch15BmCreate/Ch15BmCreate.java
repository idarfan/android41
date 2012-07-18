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

package andbas.Ch15BmCreate;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class Ch15BmCreate extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));
    }

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int STRIDE = 110;   // must be >= WIDTH

    private static int[] createColors() {
        int[] cols = new int[STRIDE * HEIGHT];
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int r = x * 255 / (WIDTH - 1);
                int g = y * 255 / (HEIGHT - 1);
                int b = 255 - Math.min(r, g);
                int a = Math.max(r, g);
                cols[y * STRIDE + x] = (a << 24) | (r << 16) | (g << 8) | b;
            }
        }
        return cols;
    }
    
    private static class ghView extends View {
        private Bitmap[] bm;
        private Bitmap[] bmJPEG;
        private Bitmap[] bmPNG;
        private int[]    colors;
        private Paint    paint;

        public ghView(Context context) {
            super(context);

            setFocusable(true);
            colors =createColors();    		
            bm = new Bitmap[3];
            bm[0] = Bitmap.createBitmap
            		(colors, 0, STRIDE, WIDTH, HEIGHT,Bitmap.Config.ARGB_8888);
            bm[1] = Bitmap.createBitmap
            		(colors, 0, STRIDE, WIDTH, HEIGHT,Bitmap.Config.RGB_565);
            bm[2] = Bitmap.createBitmap
            		(colors, 0, STRIDE, WIDTH, HEIGHT,Bitmap.Config.ARGB_4444);

            // 分別以JPEG及PNG格式對點陣圖進行壓縮
            bmJPEG = new Bitmap[bm.length];
            bmPNG = new Bitmap[bm.length];
            for (int i = 0; i < bm.length; i++) {
                bmJPEG[i] = codec(bm[i], Bitmap.CompressFormat.JPEG, 80);
                bmPNG[i] = codec(bm[i], Bitmap.CompressFormat.PNG, 0);
            }
       	}

        private static Bitmap codec(Bitmap src, Bitmap.CompressFormat format,
                int quality) {
        	ByteArrayOutputStream os = new ByteArrayOutputStream();
        	src.compress(format, quality, os);

        	byte[] array = os.toByteArray();
        	return BitmapFactory.decodeByteArray(array, 0, array.length);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            for (int i = 0; i < bm.length; i++) {
                canvas.drawBitmap(bm[i], 0, 0, null);
                canvas.drawBitmap(bmJPEG[i], 120, 0, null);
                canvas.drawBitmap(bmPNG[i], 240, 0, null);
                canvas.translate(0, bm[i].getHeight());
            }

            // 直接繪製顏色陣列，而不事先建bitmap物件
            canvas.translate(0, HEIGHT);
            canvas.drawBitmap(colors, 0, STRIDE, 0, 0, WIDTH, HEIGHT,
                              true, null);
            
            canvas.translate(120, 0);
            paint = new Paint();
            paint.setDither(true);
            canvas.drawBitmap(colors, 0, STRIDE, 0, 0, WIDTH, HEIGHT,
                              false, paint);
        }
    }
}