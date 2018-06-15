package com.vrajaninfotech.vcare.gesture_demo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnTouchListener {


    private ViewGroup mainLayout;
    private ImageView image;

    private int Position_X;
    private int Position_Y;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (RelativeLayout) findViewById(R.id.main);
        image = (ImageView) findViewById(R.id.img1);

        image.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                Log.e("Action Down: ","lay_left :"+layoutParams.leftMargin+" lay_top:"+layoutParams.topMargin);
                Position_X = X - layoutParams.leftMargin;
                Position_Y = Y - layoutParams.topMargin;

                Log.e("Action Down: ","X :"+Position_X+" Y:"+Position_Y);
                break;

            case MotionEvent.ACTION_UP:
                Toast.makeText(getApplicationContext(),"Button Click",Toast.LENGTH_LONG).show();
                break;

            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                int screen_height=mainLayout.getHeight();
                int screen_width=mainLayout.getWidth();
                int image_width=image.getWidth();
                int image_height=image.getHeight();
                int fitwsize=screen_width-image_width;
                int fithsize=screen_height-image_height;

                int x1=X - Position_X;
                int y1=Y - Position_Y;
                int x2=-500;
                int y2=-500;
                if(x1<0)
                {
                    x1=0;
                }
                if(y1<0)
                {
                    y1=0;
                }
                if(x1>fitwsize)
                {
                    x1=fitwsize;
                }
                if(y1>fithsize)
                {
                    y1=fithsize;
                }

                Params.leftMargin = x1;
                Params.topMargin = y1;
                Params.rightMargin=x2;
                Params.bottomMargin=y2;
                view.setLayoutParams(Params);
                break;

        }
        mainLayout.invalidate();
        return true;
    }
}
