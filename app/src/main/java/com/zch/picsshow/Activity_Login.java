package com.zch.picsshow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.zch.picsshow.tools.PerfHelper;
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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  //该参数指布局能延伸到navigationbar，我们场景中不应加这个参数
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT); //设置navigationbar颜色为透明
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_login);
        context = this;

        initView();
        initEvent();
        test();
    }

    private void initView(){
        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);


        et_username.setText(PerfHelper.getStringData("loginuser"));
        et_pwd.setText(PerfHelper.getStringData("loginpwd"));
    }

    private void initEvent(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()){
                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                }
            }
        });
    }

    private void test(){
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        float scaldensity = metric.scaledDensity;
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        double diagonalPixels = Math.sqrt(Math.pow(width, 2)+Math.pow(height, 2)) ;
        double screenSize = diagonalPixels/(160*density) ;
        Log.e(TAG, "density="+density + ", densityDpi=" + densityDpi + ", scaldensity="+scaldensity + ", screenSize="+screenSize);
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

        //记录登录用户信息
        PerfHelper.setInfo("loginuser", et_username.getText().toString().trim());
        PerfHelper.setInfo("loginpwd", et_pwd.getText().toString().trim());

        return  true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (Activity_Login.this.getCurrentFocus() != null) {
                if (Activity_Login.this.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(Activity_Login.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
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
