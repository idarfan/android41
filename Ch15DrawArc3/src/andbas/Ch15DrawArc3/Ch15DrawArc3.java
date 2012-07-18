package andbas.Ch15DrawArc3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Ch15DrawArc3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));       
    }

    private static class ghView extends SurfaceView  
       			implements Runnable {
        private Paint paint;
        private boolean isUseCent;
        private RectF rect1;
        private float startAng,sweepAng;
    	private SurfaceHolder sfHolder;
        private Thread thread=new Thread(this);
        private Canvas canvas;
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
            
    		sfHolder = this.getHolder();
    		sfHolder.addCallback(sfHolderListener);		
        }
    	private SurfaceHolder.Callback sfHolderListener = 
    			new SurfaceHolder.Callback() {
    		public void surfaceCreated(SurfaceHolder holder) {
        		thread.start();
    		}		
    		public void surfaceChanged(SurfaceHolder holder, 
    				int format, int width, int height) {		
    		}
    		public void surfaceDestroyed(SurfaceHolder holder) {
    		}		
    	};
		public void run() {
			while (true) {
				beginDraw(canvas);  // user defined
			}
		}
       protected void beginDraw(Canvas canvas) {
			canvas = sfHolder.lockCanvas();

			if (canvas != null) {
				canvas.drawColor(Color.WHITE);
	            canvas.drawRect(rect1, paint);
	            canvas.drawArc(rect1, startAng, sweepAng, 
	            		       isUseCent, paint);	
	            sweepAng += SWEEP_INC;
	            if (sweepAng > 360) {
	                sweepAng -= 360;
	                startAng += START_INC;
	                if (startAng >= 360) {
	                    startAng -= 360;
	                }
	            }
	            sfHolder.unlockCanvasAndPost(canvas);
			}
        }
    }
}