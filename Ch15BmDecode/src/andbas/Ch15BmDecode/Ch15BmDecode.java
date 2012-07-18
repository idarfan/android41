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

package andbas.Ch15BmDecode;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

public class Ch15BmDecode extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));
    }

    private static class ghView extends View {
        private Bitmap bm;
        private Bitmap bm2;
        private Bitmap bm21;
        private Bitmap bm22;
        private Drawable mDrawable;
        private Paint paint = new Paint();

        private Movie mMovie;
        private long mMovieStart;

        // 設為false以便使用decodeByteArray
        private static final boolean DECODE_STREAM = true;

        private static byte[] streamToBytes(InputStream is) {
            ByteArrayOutputStream os = 
            		new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int len;
            try {
                while ((len = is.read(buffer)) >= 0) {
                    os.write(buffer, 0, len);
                }
            } catch (java.io.IOException e) {
            }
            return os.toByteArray();
        }

        public ghView(Context context) {
            super(context);
            setFocusable(true);

            java.io.InputStream is;
            is = context.getResources().
            		openRawResource(R.drawable.photo1);

            BitmapFactory.Options opts = 
            		new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            bm = BitmapFactory.decodeStream(is, null, opts);

            // 把點陣圖大小縮小為1/9倍(長寬各縮為1/3)
            opts.inJustDecodeBounds = false;    
            opts.inSampleSize = 3;             
            bm = BitmapFactory.decodeStream(is, null, opts);

            // 解碼一影像並使背景變透明
            is = context.getResources().
            		openRawResource(R.drawable.icon1);
            bm2 = BitmapFactory.decodeStream(is);

            // 把上面影像複製2份並設不同的組態
            int w = bm2.getWidth();
            int h = bm2.getHeight();
            int[] pixels = new int[w*h];
            bm2.getPixels(pixels, 0, w, 0, 0, w, h);
            bm21 = Bitmap.createBitmap(
            		pixels, 0, w, w, h,Bitmap.Config.ARGB_8888);
            bm22 = Bitmap.createBitmap(
            		pixels, 0, w, w, h,Bitmap.Config.ARGB_4444);

            mDrawable = context.getResources().
            		getDrawable(R.drawable.forward);
            mDrawable.setBounds(250, 20, 330, 100);

            is = context.getResources().
            		openRawResource(R.drawable.chris);

            if (DECODE_STREAM) {
                mMovie = Movie.decodeStream(is);
            } else {
                byte[] array = streamToBytes(is);
                mMovie = Movie.decodeByteArray(
                		array, 0, array.length);
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(0xFFCCCCCC);

            paint.setAntiAlias(true);

            canvas.drawBitmap(bm, 10, 10, null);
            canvas.drawBitmap(bm2, 10, 200, null);
            canvas.drawBitmap(bm21, 110, 200, null);
            canvas.drawBitmap(bm22, 210, 200, null);

            mDrawable.draw(canvas);

            paint.setColor(Color.BLUE);
            paint.setTextScaleX(2);
            paint.setTextSize(20);
            canvas.drawText("下面播放Gif檔",0 , 8, 100, 300, paint);

            long now = android.os.SystemClock.uptimeMillis();
            if (mMovieStart == 0) {   // first time
                mMovieStart = now;
            }
            if (mMovie != null) {
                int dur = mMovie.duration();
                if (dur == 0) {
                    dur = 1000;
                }
                int relTime = (int)((now - mMovieStart) % dur);
                mMovie.setTime(relTime);
                mMovie.draw(canvas, 100,320);
                invalidate();
            }
        }
    }
}