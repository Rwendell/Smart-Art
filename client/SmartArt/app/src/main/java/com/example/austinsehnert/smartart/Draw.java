package com.example.austinsehnert.smartart;

/**
 * Created by austinsehnert on 3/23/18.
 */


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

import android.view.View;
import android.graphics.Color;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.TypedValue;

import com.example.austinsehnert.smartart.utils.ArrayCopy;
import com.example.austinsehnert.smartart.utils.ImgUtils;

import java.io.File;
import java.io.IOException;

public class Draw extends View{

    private Path path;
    private Paint pathPaint;
    private Paint canvasPaint;
    private int color = 0xFF660000;
    private Canvas canvas;
    private Bitmap canvasBitmap;

    private float brushSize;
    private float lastBrushSize;

    private boolean erase = false;



    public Draw(Context context, AttributeSet attrs){
        super(context, attrs);
        drawingInit();
    }

    private void drawingInit(){
        path = new Path();
        pathPaint = new Paint();
        pathPaint.setColor(color);

        pathPaint.setAntiAlias(true);
        pathPaint.setStrokeWidth(brushSize);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeJoin(Paint.Join.ROUND);
        pathPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);




        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(path, pathPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float xCord = event.getX();
        float yCord = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xCord, yCord);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xCord, yCord);
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(path, pathPaint);
                path.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void setColor(String colorSet){
        invalidate();
        color = Color.parseColor(colorSet);
        pathPaint.setColor(color);
    }

    public void setBrushSize(float newSize){
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize=pixelAmount;
        pathPaint.setStrokeWidth(brushSize);
    }

    public void setLastBrushSize(float lastSize){
        lastBrushSize=lastSize;
    }
    public float getLastBrushSize(){
        return lastBrushSize;
    }

    public void setErase(boolean isErase){
        erase = isErase;
        if(erase)
            pathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else
            pathPaint.setXfermode(null);
    }

    public void startNew(){
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }



    public String loadArtboard() {


        String fc = "";


        String[] fcArr = fc.split("\\s+");

        int[] fiBytesAsInt = new int[fc.length()];
        for (int i = 0; i < fc.length(); i++) {
            fiBytesAsInt[i] = Integer.parseInt(fcArr[i]);
        }
        byte[] fiBytes = ArrayCopy.int2byte(fiBytesAsInt);
        ;

        fiBytes = ArrayCopy.int2byte(fiBytesAsInt);

        //Context context = getApplicationContext();


        File outputDir = .getCacheDir();
        File outputFile = null;
        try {
            outputFile = File.createTempFile("global", ".png", outputDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(outputFile.getAbsolutePath());


        ImgUtils.byteArrtoFile(fiBytes, outputFile.getAbsolutePath() + ".png");

        return outputFile.getAbsolutePath();
    }
}
