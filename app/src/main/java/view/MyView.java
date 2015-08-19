package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by likunlang on 2015/8/18.
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
//        paint.setColor(Color.BLUE);
        canvas.drawLine(0, 100, 100, 100, paint);
//        canvas.drawCircle(30,50,25,paint);
    }
}
