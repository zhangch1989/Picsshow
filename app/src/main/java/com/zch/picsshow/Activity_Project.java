package com.zch.picsshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.zch.picsshow.adapter.Image_PagerAdapter;
import com.zch.picsshow.base.BaseActivity;
import com.zch.picsshow.view.DepthPageTransformer;
import com.zch.picsshow.view.NoParentScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zch on 2020/4/24 15:34.
 * 餐厨垃圾处置项目
 */

public class Activity_Project extends BaseActivity {
    private Context context;

    private ImageButton ibtn_back;
    private Button btn_first;
    private NoParentScrollViewPager viewPager;
    private LinearLayout ll_dots;

    private Image_PagerAdapter adapter;
    private List<ImageInfo> img_data = new ArrayList<>();
    private List<View> dots = new ArrayList<>();
    private int currentIndex = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        context = this;
        initData();
        initView();
        initEvent();
    }

    private void initData(){
        img_data.clear();
        img_data.add(new ImageInfo("project/1.png"));
        img_data.add(new ImageInfo("project/2.png"));
        img_data.add(new ImageInfo("project/3.png"));
        img_data.add(new ImageInfo("project/4.png"));
        img_data.add(new ImageInfo("project/5.png"));
        img_data.add(new ImageInfo("project/6.png"));
    }

    private void initView(){
        ibtn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_first = (Button) findViewById(R.id.btn_first);
        viewPager = (NoParentScrollViewPager) findViewById(R.id.id_viewpager);
        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);

        adapter = new Image_PagerAdapter(context, img_data);
        viewPager.setAdapter(adapter);
        viewPager.setPageMargin(20);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        for (int i = 0; i < img_data.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_dot, null);//加载布局
            View dot = view.findViewById(R.id.dot);//得到布局中的dot点组件
            //收集dot
            dots.add(dot);
            //把布局添加到线性布局
            ll_dots.addView(view);
        }
        viewPager.setCurrentItem(currentIndex);
        updateDots(0, ll_dots);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateDots(position, ll_dots);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEvent(){
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }

    /**
     * 更新小圆点
     * @param index
     */
    private void updateDots(int index, ViewGroup group){
        for(int i = 0; i< group.getChildCount(); i++){
            View view = ((LinearLayout) group.getChildAt(i)).getChildAt(0);
            if(index == i){
                view.setBackgroundResource(R.mipmap.dot_select);
            }else{
                view.setBackgroundResource(R.mipmap.dot_normol);
            }
        }
    }
}
