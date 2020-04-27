package com.zch.picsshow;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.tencent.smtt.sdk.TbsReaderView;
import com.zch.picsshow.base.BaseActivity;
import com.zch.picsshow.tools.CommonUtils;
import com.zch.picsshow.tools.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zch on 2020/4/24 10:29.
 */

public class Activity_Detail extends BaseActivity implements TbsReaderView.ReaderCallback, OnPageChangeListener, OnLoadCompleteListener {
    private Context context;

    private TbsReaderView mTbsReaderView;
//    private RelativeLayout rl_content;
    private ImageButton ibtn_back;
    private LinearLayout ll_btns;
    private PDFView pdfView;

    private String fileName;
    private String filepath = Environment.getExternalStorageDirectory() + "/Sanitation/";
    private List<String> names = new ArrayList<>();
    private int currentIndex = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23){
            CommonUtils.requestPermissions(this);
        }
        context = this;
        setContentView(R.layout.activity_detail);
        initData();
        initView();
        initEvent();
    }

    private void initData(){
        Bundle bundle =getIntent().getExtras();
        if (null != bundle) {
            currentIndex = bundle.getInt("index");
        }
        names.clear();
        names.add("sss");
        names.add("创新条件好.pdf");
        names.add("工作机制好.pdf");
        names.add("创新业绩好.pdf");
        names.add("创新队伍好.pdf");
        names.add("示范效益好.pdf");
    }

    private void initView(){
        ibtn_back = (ImageButton) findViewById(R.id.btn_back);
        ll_btns = (LinearLayout) findViewById(R.id.ll_btns);
        pdfView = (PDFView) findViewById(R.id.pdfview);
//        rl_content = findViewById(R.id.rl_content);

        mTbsReaderView = new TbsReaderView(this, this);
//        rl_content.addView(mTbsReaderView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        updataBtnBg(currentIndex);
        showPDF();
    }

    private void initEvent(){
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for (int i = 0; i < ll_btns.getChildCount(); i++){
            Button btn = (Button) ll_btns.getChildAt(i);
            final int index = i;
            if (i > 0) {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currentIndex = index;
                        updataBtnBg(index);
                        showPDF();
                    }
                });
            }else {//首页按钮的事件
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, Activity_Studio.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }
    }

    private void showPDF(){
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdir();
        }
        fileName = names.get(currentIndex);
//        displayFile(filepath + names.get(currentIndex));
//        pdfView.fromFile(new File(filepath + names.get(currentIndex)))
        pdfView.fromAsset(names.get(currentIndex))
                .defaultPage(0)
                .onPageChange(this)
                .swipeHorizontal(false)
                //.swipeVertical(false)
                //.showMinimap(false)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .load();
    }

    /**
     * 更新按钮点击效果
     * @param index
     */
    private void updataBtnBg(int index){
        for(int i = 1; i < ll_btns.getChildCount(); i++){
            Button btn = (Button) ll_btns.getChildAt(i);
            if (i == index){
                btn.setBackgroundResource(R.mipmap.good_btn_selected);
                btn.setTextColor(getResources().getColor(R.color.white));
            }else {
                btn.setBackgroundResource(R.mipmap.good_btn_nl);
                btn.setTextColor(getResources().getColor(R.color.bottom_btn));
            }
        }
    }

    private void displayFile(String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("filePath", filepath);
            bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
            String ext = FileUtils.parseFormat(fileName);
            boolean result = mTbsReaderView.preOpen(ext, false);
            if (result) {
                mTbsReaderView.openFile(bundle);
            } else {
                Toast.makeText(this, "暂不支持该类型的文档", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }
}
