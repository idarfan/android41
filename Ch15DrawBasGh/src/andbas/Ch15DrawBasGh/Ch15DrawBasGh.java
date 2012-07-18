package andbas.Ch15DrawBasGh;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class Ch15DrawBasGh extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ghView(this));
    }
    private static class ghView extends View {
        private Paint mPaint = new Paint();
        private int[] col=
        	{Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,
        	 Color.CYAN};
        private float[] pts={10,310,20,300,20,300,50,330,50,330,
        		60,330,60,330,70,310,70,310,90,320};
        private RectF rect1,rect2;

        public ghView(Context context) {
            super(context);
        }

        @Override 
        protected void onDraw(Canvas canvas) {
            Paint paint = mPaint;
            canvas.translate(10, 10);
            canvas.drawColor(Color.WHITE);
            
            paint.setColor(Color.BLUE);
            paint.setTextScaleX(2);
            paint.setTextSize(20);
            canvas.drawText("繪製靜態圖案",0 , 6, 100, 20, paint);
            
            for (int i = 0; i < 5; i++) {
                paint.setColor(col[i]);
                paint.setStrokeWidth(10+10*(i+1));
                canvas.drawPoint(100+(i+1)*50,50, paint);
            }
            
            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(0);
            canvas.drawLine(200,200,200,100, paint);
            canvas.drawLine(200,200,300,200, paint);
            canvas.drawLine(200,200,200,300, paint);
            canvas.drawLine(200,200,100,200, paint);
           
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(5);
            canvas.drawPoint(200,100, paint);
            canvas.drawPoint(300,200, paint);
            canvas.drawPoint(200,300, paint);
            canvas.drawPoint(100,200, paint);
            
            paint.setStrokeWidth(0);
            paint.setAntiAlias(true);
            for (int i = 0; i < 5; i++) {
                paint.setColor(
                		Color.argb(120+i*20, 120+i*30, 90+i*10, 140+i*20));
            	canvas.drawCircle(200, 200, 20*(5-i), paint);
            }
            
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(4);
            canvas.drawLines(pts, paint);

            for (int i = 0; i < 5; i++) {
                paint.setColor(col[i]);
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawRect(50+i*10, 350+i*10,250-i*10, 450-i*10, paint);
            }

            paint.setStyle(Paint.Style.FILL);
            rect1=new RectF(300, 350, 350, 400);
            canvas.drawRect(rect1,paint);
            
            rect2=new RectF(360, 350, 460, 400);
            canvas.drawOval(rect2, paint);
            
            paint.setStrokeWidth(4);
            for (int i = 0; i < 5; i++) {
                paint.setColor(col[i]);
                rect2=new RectF(10+(90*i), 500, 110+(90*i), 600);
                canvas.drawArc(rect2, 30, 260, true, paint);
            }
        }
    }
}