package com.zch.picsshow.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zch.picsshow.ImageInfo;
import com.zch.picsshow.R;
import com.zch.picsshow.tools.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zch on 2019/4/25 14:06.
 */

public class Image_PagerAdapter extends PagerAdapter {
    private Context context;
    private List<ImageInfo> datalist = new ArrayList<>();
    private LayoutInflater inflater;

    public Image_PagerAdapter(Context context, List<ImageInfo> data){
        this.context = context;
        this.datalist = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.item_imageinfo, null);
        ImageView img = (ImageView) view.findViewById(R.id.img);

        Bitmap bitmap = FileUtils.getImageFromAssetsFile(context, datalist.get(position).getName());
        img.setImageBitmap(bitmap);
        //点击事件
        container.addView(view);
        return view;
    }

    public void Refresh(List<ImageInfo> data){
        this.datalist = data;
        notifyDataSetChanged();
    }

}
