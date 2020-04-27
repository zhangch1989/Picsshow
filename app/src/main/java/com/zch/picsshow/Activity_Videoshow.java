package com.zch.picsshow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zch.picsshow.R;
import com.zch.picsshow.base.BaseActivity;
import com.zch.picsshow.tools.CommonUtils;
import com.zch.picsshow.tools.FileUtils;

/**
 * Created by Zch on 2020/4/23 14:05.
 */

public class Activity_Videoshow extends BaseActivity {
    private Context context;

    private VideoView videoview;
    private String filepath = "环卫集团-黑石子劳模工作室.mp4";
    private long firstTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if (Build.VERSION.SDK_INT >= 23){
            CommonUtils.requestPermissions(this);
        }
        setContentView(R.layout.activity_videoshow);
        initData();
        initView();
    }

    private void initData(){
        Bundle bundle = getIntent().getExtras();
        if (null != bundle){
            filepath = bundle.getString("filepath");
        }
    }

    private void initView(){
        videoview = (VideoView) findViewById(R.id.videoview);
        videoview.setMediaController(new MediaController(context));
        videoview.setVideoURI(Uri.parse(filepath));
        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
        //点击控制播放和暂停
        videoview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    if (System.currentTimeMillis() - firstTime < 1000){//双击全屏
                        finish();
                    }else {
                        firstTime = System.currentTimeMillis();
//                        if (videoview.isPlaying()){
//                            videoview.pause();
//                        }else {
//                            videoview.start();
//                        }
                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoview.isPlaying()){
            videoview.pause();
        }
    }
}
