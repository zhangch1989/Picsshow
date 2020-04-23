package com.zch.picsshow.mypage;

/**
 * Created by Administrator on 2017/10/12.
 */

import android.net.Uri;

/**
 * Created by zhaoyong on 16/4/18.
 *
 */
public interface FrescoBitmapCallback<T> {

    void onSuccess(Uri uri, T result);

    void onFailure(Uri uri, Throwable throwable);

    void onCancel(Uri uri);
}