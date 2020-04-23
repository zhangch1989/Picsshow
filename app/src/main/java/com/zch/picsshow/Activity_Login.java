package com.zch.picsshow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zch.picsshow.tools.ToastUtils;

/**
 * Created by Zch on 2020/4/23 13:50.
 */

public class Activity_Login extends Activity {
    private final static String TAG = "Activity_Login";
    private Context context;

    private EditText et_username, et_pwd;
    private Button btn_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        initView();
        initEvent();
    }

    private void initView(){
        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    private void initEvent(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()){
                    startActivity(new Intent(context, MainActivity.class));
                }
            }
        });
    }

    /**
     * 验证用户输入是否有效
     * @return
     */
    private boolean isValidate(){
        if(null == et_username.getText() || TextUtils.isEmpty(et_username.getText().toString())){
            ToastUtils.showToast(context, "请输入用户名！");
            return  false;
        }else if(null == et_pwd.getText() || TextUtils.isEmpty(et_pwd.getText().toString())){
            ToastUtils.showToast(context, "请输入密码！");
            return  false;
        }

        if (!et_username.getText().toString().equals("admin") || !et_pwd.getText().toString().equals("88888888")){
            ToastUtils.showToast(context, "用户名或者密码错误！");
            return false;
        }

        return  true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            System.gc();
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

}
