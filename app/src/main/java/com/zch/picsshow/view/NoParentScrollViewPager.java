package com.zch.picsshow.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Zch on 2019/5/16 21:31.
 * 作为嵌套子控件时，子控件的左右滑动禁止父级的左右滑动
 */

public class NoParentScrollViewPager extends ViewPager {
    /** 触摸时按下的点 **/
    PointF downP = new PointF();
    /** 触摸时当前的点 **/
    PointF curP = new PointF();

    OnSingleTouchListener onSingleTouchListener;


    public NoParentScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public NoParentScrollViewPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        //每次进行onTouch事件都记录当前的按下的坐标
        if(getChildCount()<=1)
        {
            return super.onTouchEvent(arg0);
        }
        curP.x = arg0.getX();
        curP.y = arg0.getY();
        if(arg0.getAction() == MotionEvent.ACTION_DOWN)
        {
            //记录按下时候的坐标
            //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
            downP.x = arg0.getX();
            downP.y = arg0.getY();
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if(arg0.getAction() == MotionEvent.ACTION_MOVE){
//            MLog.e("NoParentScrollViewPager", " On Touch move" );
            if(Math.abs(arg0.getX() - downP.x) >= Math.abs(arg0.getY() - downP.y)) {
                //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
                getParent().requestDisallowInterceptTouchEvent(true);
            }else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        if(arg0.getAction() == MotionEvent.ACTION_UP || arg0.getAction() == MotionEvent.ACTION_CANCEL){
            //在up时判断是否按下和松手的坐标为一个点
            //如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick
            getParent().requestDisallowInterceptTouchEvent(false);
            if(downP.x==curP.x && downP.y==curP.y){
                onSingleTouch();
                return true;
            }
        }
        super.onTouchEvent(arg0); //注意这句不能 return super.onTouchEvent(arg0); 否则触发parent滑动
        return true;
    }

    /**
     * 单击
     */
    public void onSingleTouch() {
        if (onSingleTouchListener!= null) {

            onSingleTouchListener.onSingleTouch();
        }
    }

    /**
     * 创建点击事件接口
     * @author wanpg
     *
     */
    public interface OnSingleTouchListener {
        public void onSingleTouch();
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }
}
