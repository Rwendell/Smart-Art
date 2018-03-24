package com.example.austinsehnert.smartart;

/**
 * Created by austinsehnert on 3/23/18.
 */


import android.content.Context;
import android.util.AttributeSet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

import android.view.View;

public class Draw extends View{

    private Path path;
    private Paint pathPaint;
    private Paint canvasPaint;
    private int color = 0xFF660000;
    private Canvas canvas;
    private Bitmap canvasBitmap;


    public Draw{
        super(context, attrs);
        drawingInit();
    }

    private void drawingInit(){
        path = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);

        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }
}
