package com.zch.picsshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zch.picsshow.base.BaseActivity;

/**
 * Created by Zch on 2020/4/25 15:09.
 */

public class Activity_Goods extends BaseActivity {
    private Context context;

    private ImageButton ibtn_back;
    private LinearLayout ll_btns;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        context = this;
        initView();
        initEvent();
    }

    private void initView(){
        ibtn_back = (ImageButton) findViewById(R.id.btn_back);
        ll_btns = (LinearLayout) findViewById(R.id.ll_btns);
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
                        pageTo(index);
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

    private void pageTo(int index){
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        Intent intent = new Intent(context, Activity_Detail.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
