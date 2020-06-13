package com.pengembangsebelah.calculatorrab.myutils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.FloatMath;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PolyG extends Drawable {
    int numberOfside = 3;
    Path polygon = new Path();
    Path temporal = new Path();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public PolyG(int collor, int side) {
        paint.setColor(collor);
        polygon.setFillType(Path.FillType.EVEN_ODD);
        this.numberOfside=side;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawPath(polygon,paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return paint.getAlpha();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
    }
}
