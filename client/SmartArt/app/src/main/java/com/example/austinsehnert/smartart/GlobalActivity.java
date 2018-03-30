package com.example.austinsehnert.smartart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class GlobalActivity extends AppCompatActivity implements OnClickListener{

    private Draw canvas;

    private float smallBrush;
    private float mediumBrush;
    private float largeBrush;


    private ImageButton currentColor, drawBtn, eraseBtn, newBtn, saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        canvas = findViewById(R.id.drawing);
        LinearLayout paintLayout = findViewById(R.id.paint_colors);
        currentColor = (ImageButton)paintLayout.getChildAt(0);
        currentColor.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        drawBtn = findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);

        canvas.setBrushSize(mediumBrush);

        eraseBtn = findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);

        newBtn = findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);

        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("New drawing");
        newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
        newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                canvas.startNew();
                dialog.dismiss();
            }
        });
        newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        newDialog.show();

        saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);

    }

    public void paintClicked(View view){
        if(view != currentColor){
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();

            canvas.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currentColor.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currentColor=(ImageButton)view;

            canvas.setBrushSize(canvas.getLastBrushSize());
        }
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.draw_btn) {
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Brush size:");

            brushDialog.setContentView(R.layout.brush_chooser);

            ImageButton smallBtn = brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    canvas.setBrushSize(smallBrush);
                    canvas.setLastBrushSize(smallBrush);
                    canvas.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton mediumBtn = brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    canvas.setBrushSize(mediumBrush);
                    canvas.setLastBrushSize(mediumBrush);
                    canvas.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    canvas.setBrushSize(largeBrush);
                    canvas.setLastBrushSize(largeBrush);
                    canvas.setErase(false);
                    brushDialog.dismiss();
                }
            });


            brushDialog.show();
        }

        else if(view.getId()==R.id.erase_btn){
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Eraser size:");
            brushDialog.setContentView(R.layout.brush_chooser);

            ImageButton smallBtn = brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    canvas.setErase(true);
                    canvas.setBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    canvas.setErase(true);
                    canvas.setBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton largeBtn = brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    canvas.setErase(true);
                    canvas.setBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });

            brushDialog.show();
        }

        else if(view.getId()==R.id.new_btn){
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New drawing");
            newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    canvas.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            newDialog.show();
        }

        else if(view.getId()==R.id.save_btn) {
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Save drawing");
            saveDialog.setMessage("Save drawing to device Gallery?");
            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //save drawing
                }
            });
            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            saveDialog.show();
            canvas.setDrawingCacheEnabled(true);
            String imgSaved = MediaStore.Images.Media.insertImage(
                    getContentResolver(), canvas.getDrawingCache(),
                    UUID.randomUUID().toString()+".png", "drawing");
            if(imgSaved!=null){
                Toast savedToast = Toast.makeText(getApplicationContext(),
                        "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                savedToast.show();
            }
            else{
                Toast unsavedToast = Toast.makeText(getApplicationContext(),
                        "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                unsavedToast.show();
            }

            canvas.destroyDrawingCache();
        }
    }




}
