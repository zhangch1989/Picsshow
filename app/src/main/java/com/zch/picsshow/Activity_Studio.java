package com.zch.picsshow;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.zch.picsshow.base.BaseActivity;
import com.zch.picsshow.tools.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zch on 2020/4/24 15:30.
 * 工作室主页
 */

public class Activity_Studio extends BaseActivity {
    private Context context;

    private VideoView videoview;
    private Button btn_first, btn_organization, btn_goods;
    private TextView tv_des;
    private ImageButton ibtn_back;

    private String url;

    private List<String> data;
    private List<View> views1;
    private static int Strint_size = 1;

    private long firstTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23){
            CommonUtils.requestPermissions(this);
        }
        setContentView(R.layout.activity_studio);
        context = this;
        initView();
        initEvent();
    }

    private void initView(){
        ibtn_back = (ImageButton) findViewById(R.id.btn_back);
        videoview = (VideoView) findViewById(R.id.videoview);
        btn_first = (Button) findViewById(R.id.btn_first);
        btn_organization = (Button) findViewById(R.id.btn_organization);
        btn_goods = (Button) findViewById(R.id.btn_goods);
        tv_des = (TextView) findViewById(R.id.tv_des);

        url = Environment.getExternalStorageDirectory() + "/Sanitation/环卫集团-黑石子劳模工作室.mp4";
        videoview.setVideoURI(Uri.parse(url));
        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });

        tv_des.setText("\t\t\t\t工作室于2012年4月由重庆市劳动模范文武牵头创立。2015年起，由“全国劳动模范”“全国五一巾帼标兵”获得者杨菊平牵头负责。工作室汇集环境工程、机械、化工、自动控制、安全管理等众多领域的24名本科及以上技术骨干，主攻餐厨垃圾无害化处理及资源化利用新工艺新装备研发。工作室坚持用党的最新理论指导实践，大力弘扬新时代工匠精神，发挥劳模示范、引领和辐射作用，在传承与创新上狠下功夫，把工作室打造成为企业创新工作“助推器”，技术人才培养“孵化器”，企业形象展示“新窗口”，为环卫事业汇聚力量。");
        tv_des.setMovementMethod(ScrollingMovementMethod.getInstance());
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


        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.instance == null){
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                finish();
            }
        });

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        btn_organization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Activity_Organization.class));
            }
        });

        btn_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Activity_Goods.class));
            }
        });
    }



    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        Strint_size = size;
        return list;
    }

    public static String substring(String str, int f, int t) {
        if (f > str.length()) {
            return null;
        }
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
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
        if (videoview.isPlaying()){
            videoview.pause();
        }
    }
}
