package andbas.Ch15DrawArc2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class Ch15DrawArc2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));
    }

    private static class ghView extends View 
             implements Runnable {
        private Paint paint;
        private boolean isUseCent;
        private RectF rect1;
        private float startAng,sweepAng;
        private Handler handler = new Handler();
        private Thread thread=new Thread(this);

        private static final float SWEEP_INC = 2;
        private static final float START_INC = 15;

        public ghView(Context context) {
            super(context);

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);
            paint.setColor(Color.RED);
            isUseCent = true;
            rect1 = new RectF( 10, 10, 110, 110);
            
    		thread.start();
        }

        @Override 
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            canvas.drawRect(rect1, paint);
            canvas.drawArc(
            		rect1, startAng, sweepAng, isUseCent, paint);

            sweepAng += SWEEP_INC;
            if (sweepAng > 360) {
                sweepAng -= 360;
                startAng += START_INC;
                if (startAng >= 360) {
                    startAng -= 360;
                }
            }
        }
		public void run() {
			while (true) {
				handler.post(new Runnable() {
					public void run() {
						invalidate();
					}
				});
			}
		}
    }
}