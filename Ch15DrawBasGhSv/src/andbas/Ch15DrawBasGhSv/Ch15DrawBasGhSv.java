package andbas.Ch15DrawBasGhSv;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Ch15DrawBasGhSv extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));
    }
    private static class ghView extends SurfaceView {
        private Paint mPaint = new Paint();
        private int[] col=
        	{Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,
        	 Color.CYAN};
    	private SurfaceHolder sfHolder;

        public ghView(Context context) {
            super(context);
    		sfHolder = this.getHolder();
    		sfHolder.addCallback(sfHolderListener);		
        }
        
    	private SurfaceHolder.Callback sfHolderListener = 
    			new SurfaceHolder.Callback() {
    		public void surfaceCreated(SurfaceHolder holder) {
    			Canvas canvas = holder.lockCanvas();
    			
                Paint paint = mPaint;
               canvas.drawColor(Color.WHITE);
                          
                paint.setStrokeWidth(0);
                paint.setAntiAlias(true);
                for (int i = 0; i < 5; i++) {
                    paint.setColor(col[i]);
                	canvas.drawCircle(250, 120, 20*(5-i), paint);
                }

    			holder.unlockCanvasAndPost(canvas);
    		}		
    		public void surfaceChanged(SurfaceHolder holder, 
    				int format, int width, int height) {
    			
    		}
    		public void surfaceDestroyed(SurfaceHolder holder) {
    			
    		}		
    	};
    }
}