package com.zch.picsshow;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tencent.smtt.sdk.TbsReaderView;
import com.zch.picsshow.tools.CommonUtils;
import com.zch.picsshow.tools.FileUtils;

import java.io.File;

/**
 * Created by Zch on 2020/4/23 10:23.
 */

public class Activity_Pdfshow extends Activity implements TbsReaderView.ReaderCallback {
    private final static String TAG = "Activity_Pdfshow";
    private Context context;

    private RelativeLayout rl_content;
    private TbsReaderView mTbsReaderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfshow);
        if (Build.VERSION.SDK_INT >= 23){
            CommonUtils.requestPermissions(this);
        }
        context = this;
        initView();
        String filepath = Environment.getExternalStorageDirectory() + "/FireMonitor/";
        displayFile(filepath + "报告123.pdf");
    }

    private void initView(){
        mTbsReaderView = new TbsReaderView(this, this);

        rl_content = findViewById(R.id.rl_content);
        rl_content.addView(mTbsReaderView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
    }

    private void displayFile(String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
            }
            Bundle bundle = new Bundle();
            bundle.putString("filePath", filepath);
            bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
            String ext = FileUtils.parseFormat("报告123.pdf");
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
}
