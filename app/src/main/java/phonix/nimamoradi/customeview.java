package phonix.nimamoradi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class customeview extends View {
    public customeview(Context context) {
        super(context);
    }

    public customeview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public customeview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public customeview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.parseColor("#a16868"));
        Path path=new Path();
        path.moveTo(0,0);
        path.lineTo(getWidth(),0);
        path.lineTo(getWidth()*9/10,getHeight());
        path.lineTo(0,getHeight());
        path.lineTo(0,0);
        canvas.drawPath(path,paint);
    }
}
