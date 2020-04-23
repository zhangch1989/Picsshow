package com.zch.picsshow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;

import com.zch.picsshow.view.PageWidget;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int width, height;
    private PageWidget pageWidget;

    private List<Integer> imglist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        pageWidget = new PageWidget(this);

        Display display = getWindowManager().getDefaultDisplay();
        width  = display.getWidth();
        height = display.getHeight();

        pageWidget.SetScreen(width, height);

//        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
//        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
//
//        Bitmap foreImage = Bitmap.createScaledBitmap(bm1, width, height,false);
//        Bitmap bgImage = Bitmap.createScaledBitmap(bm2, width, height,false);
//
//        pageWidget.setBgImage(bgImage);
//        pageWidget.setForeImage(foreImage);
        initData();
        show(0);

        setContentView(pageWidget);
        startActivity(new Intent(this, com.zch.picsshow.mypage.MainActivity.class));
    }

    private void initData(){
        imglist.clear();
        imglist.add(R.drawable.img1);
        imglist.add(R.drawable.img2);
        imglist.add(R.drawable.img3);
        imglist.add(R.drawable.img4);

    }

    private void show(int index){
        int last = -1;
        if (index == imglist.size()){
            last = 0;
        }else {
            last = index + 1;
        }
        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), imglist.get(index));
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), imglist.get(last));
        Bitmap foreImage = Bitmap.createScaledBitmap(bm1, width, height,false);
        Bitmap bgImage = Bitmap.createScaledBitmap(bm2, width, height,false);

        pageWidget.setBgImage(bgImage);
        pageWidget.setForeImage(foreImage);
    }
}
