package com.zch.picsshow;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.zch.picsshow.base.BaseActivity;
import com.zch.picsshow.tools.CommonUtils;
import com.zch.picsshow.tools.ToastUtils;

public class MainActivity extends BaseActivity {
    private final static String TAG = "MainActivity";
    private long firstTime = 0; //记录两次返回按钮的时间
    private Context context;
    public static MainActivity instance;

    private VideoView videoview;
    private Button btn_project, btn_studio, btn_introduce;

    private String url;
    private MyHndler hndler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        instance = this;
        hndler = new MyHndler();
        initView();
        initEvent();
    }

    private void initMediaPlayer(){

        url = Environment.getExternalStorageDirectory() + "/Sanitation/环卫集团宣传片.mp4";
        videoview.setVideoURI(Uri.parse(url));
        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
    }

    private void initView(){
        videoview = (VideoView) findViewById(R.id.videoview);
        btn_project = (Button) findViewById(R.id.btn_project);
        btn_studio = (Button) findViewById(R.id.btn_studio);
        btn_introduce = (Button) findViewById(R.id.btn_introduce);

        //权限判断，如果没有权限就请求权限
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, CommonUtils.PERMISSIONS_STORAGE, 1);
            }else{
                initMediaPlayer();
            }
        }else {
            initMediaPlayer();
        }
    }

    private void initEvent(){
        //点击控制播放和暂停
        videoview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    if (System.currentTimeMillis() - firstTime < 1000){//双击全屏
                        Intent intent = new Intent(context, Activity_Videoshow.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("filepath", url);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }else {
                        firstTime = System.currentTimeMillis();
                        if (videoview.isPlaying()){
                            videoview.pause();
                        }else {
                            videoview.start();
                        }
                    }
                }
                return false;
            }
        });

        btn_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Activity_Project.class));
            }
        });

        btn_studio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Activity_Studio.class));
            }
        });

        btn_introduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Activity_Introduce.class));
            }
        });
    }

    public class MyHndler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            initMediaPlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!videoview.isPlaying()){
            videoview.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
        if (videoview.isPlaying()){
            videoview.pause();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    hndler.sendEmptyMessage(0);
                }else{
                    Toast.makeText(this, "拒绝权限，将无法使用程序。", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis() - firstTime > 2000){
                ToastUtils.showToast(this, "再按一次退出程序");
                firstTime = System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }else{
            return super.onKeyDown(keyCode,event);
        }
    }
}
