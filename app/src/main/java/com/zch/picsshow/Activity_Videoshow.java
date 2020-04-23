package com.zch.picsshow;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zch.picsshow.R;
import com.zch.picsshow.tools.CommonUtils;
import com.zch.picsshow.tools.FileUtils;

/**
 * Created by Zch on 2020/4/23 14:05.
 */

public class Activity_Videoshow extends Activity {
    private Context context;

    private VideoView videoview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if (Build.VERSION.SDK_INT >= 23){
            CommonUtils.requestPermissions(this);
        }
        setContentView(R.layout.activity_videoshow);
        initView();

        FileUtils.copyFilesFassets(context, "", FileUtils.filepath);
    }

    private void initView(){
        videoview = (VideoView) findViewById(R.id.videoview);
        videoview.setMediaController(new MediaController(context));
        String url = Environment.getExternalStorageDirectory() + "/ZCHTEST/环卫集团-黑石子劳模工作室.mp4";
        videoview.setVideoURI(Uri.parse(url));
        videoview.start();
    }
}
