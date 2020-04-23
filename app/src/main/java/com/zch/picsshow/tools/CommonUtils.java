package com.zch.picsshow.tools;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;

import com.zch.picsshow.BuildConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Author：zch  
 * create on ：2015年10月9日
 */
public class CommonUtils {
	private static String[] PERMISSIONS_STORAGE = {
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE }; //Android6.0以后操作系统的动态权限申请


	/**
	 * 用于Android6.0以后的操作系统，动态申请存储的读写权限
	 * @param context
     */
	public static void requestPermissions(Activity context){
		//用于Android6.0以后的操作系统，动态申请存储的读写权限
		int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if (permission != PackageManager.PERMISSION_GRANTED){
			ActivityCompat.requestPermissions(context,PERMISSIONS_STORAGE, 1 );
		}
	}

	/**
	 * 动态申请定位权限
	 */
	public static void requestLocPermissions(Activity activity){
		if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
				!= PackageManager.PERMISSION_GRANTED
				|| ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
				!= PackageManager.PERMISSION_GRANTED
				|| ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED
				|| ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED
				|| ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
				!= PackageManager.PERMISSION_GRANTED) {
			ToastUtils.showToast(activity, "没有权限,请手动开启定位权限");
			// 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
			ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE ,Manifest.permission.ACCESS_COARSE_LOCATION,
					Manifest.permission.ACCESS_FINE_LOCATION}, 0);
		}
	}

	/**
	 * 获取版本号
	 * 
	 * @return
	 */
	public static String getVersion(Context context) {
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "1.0.0";
		}
	}

	/*
	* 检查手机上是否安装了指定的软件
	* @param context
	* @param packageName：应用包名
	* @return
			*/
	public static boolean isAvilible(Context context, String packageName){
		//获取packagemanager
		final PackageManager packageManager = context.getPackageManager();
		//获取所有已安装程序的包信息
		List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
		//用于存储所有已安装程序的包名
		List<String> packageNames = new ArrayList<String>();
		//从pinfo中将包名字逐一取出，压入pName list中
		if(packageInfos != null){
			for(int i = 0; i < packageInfos.size(); i++){
				String packName = packageInfos.get(i).packageName;
				packageNames.add(packName);
			}
		}
		//判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
		return packageNames.contains(packageName);
	}


//	public static void startBaiduAPP(final Context context, final StationInfo info){
//		if(isAvilible(context,"com.baidu.BaiduMap")){//传入指定应用包名
//			AlertDialog.Builder builder = new AlertDialog.Builder(context);
//			builder.setMessage("打开百度地图导航？");
//			builder.setTitle("提示");
//			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//				 @Override
//				public void onClick(DialogInterface dialog, int which) {
//					 dialog.dismiss();
//					 try {
//						 Intent intent = Intent.getIntent("intent://map/direction?" +
//								 //"origin=latlng:"+"34.264642646862,108.95108518068&" +   //起点  此处不传值默认选择当前位置
//								 "destination=latlng:"+info.getLat()+","+info.getLon()+"|name:" + info.getName() +        //终点
//								 "&mode=driving&" +          //导航路线方式
//								 "region=" +           //
//								 "&src=#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//						 context.startActivity(intent); //启动调用
//					 } catch (URISyntaxException e) {
//						 Log.e("intent", e.getMessage());
//					 }
//					}
//				});
//			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//				@Override
//				 public void onClick(DialogInterface dialog, int which) {
//					   dialog.dismiss();
//					  }
//				  });
//			 builder.create().show();
//
//		}else if (isAvilible(context, "com.autonavi.minimap")) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(context);
//			builder.setMessage("打开高德地图导航？");
//			builder.setTitle("提示");
//			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//
//					try {
//						double[] lonlat = MapUtils.bd_decrypt(Double.parseDouble(info.getLat()),Double.parseDouble(info.getLon()));
//						Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=amap"
//								+"&lat="+lonlat[1]+"&lon="+lonlat[0]+"&dev=0&stype=0");
//						context.startActivity(intent); //启动调用
//					} catch (URISyntaxException e) {
//						Log.e("intent", e.getMessage());
//					}
//				}
//			});
//			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			});
//			builder.create().show();
//		}
//		else{//未安装
//			//market为路径，id为包名
//			//显示手机上所有的market商店
//			AlertDialog.Builder builder = new AlertDialog.Builder(context);
//			builder.setMessage("您尚未安装地图软件，是否下载？");
//			builder.setTitle("提示");
//			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//					Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
//					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//					context.startActivity(intent);
//				}
//			});
//			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			});
//			builder.create().show();
//
//		}
//	}

	/**
	 * 坐标转换，百度地图坐标转换成腾讯地图坐标
	 * @param lat  百度坐标纬度
	 * @param lon  百度坐标经度
	 * @return 返回结果：纬度,经度
	 */
	public static  double[] map_bd2hx(double lat, double lon){
		double tx_lat;
		double tx_lon;
		double x_pi=3.14159265358979324;
		double x = lon - 0.0065, y = lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		tx_lon = z * Math.cos(theta);
		tx_lat = z * Math.sin(theta);

		double[] doubles = new double[]{tx_lat,tx_lon};
		return doubles;
	}

	/**
	 * 打开文件
	 * @param file
	 */
	public static void openPDFFile(final Context context, File file) throws Exception{
//        pdfView.fromFile(file).defaultPage(pageNumber).load();
		Intent intent = getPdfFileIntent(context, file);
		if(null != intent && intent.resolveActivity(context.getPackageManager()) != null) {
			context.startActivity(intent);
		}else{
//			ToastUtils.showToast(context, "未安装PDF阅读器");
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("您尚未安装PDF阅读器，是否下载？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					Uri uri = Uri.parse("market://details?id=cn.wps.moffice_eng");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					context.startActivity(intent);
				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
		}
	}

	/**
	 * android获取一个用于打开PDF文件的intent
	 * @param file 要打开的文件的绝对路径
	 * @return
	 */
	public static Intent getPdfFileIntent(Context context, File file ){
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//判断是否是AndroidN以及更高的版本 7.0h后的
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
			intent.setDataAndType(contentUri, "application/pdf");
		} else {
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "application/pdf");
		}
		return intent;

	}

	/**
	 * 安装apk
	 */
	public static void installApk(Context mContext, File file) throws Exception{
		Intent it = new Intent();
		it.setAction(Intent.ACTION_VIEW);
		//判断是否是AndroidN以及更高的版本 7.0h后的
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			it.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			Uri contentUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileProvider", file);
			it.setDataAndType(contentUri, "application/vnd.android.package-archive");
		} else {
			Uri fileUri = Uri.fromFile(file);
			it.setDataAndType(fileUri, "application/vnd.android.package-archive");
			it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 防止打不开应用
		}
		mContext.startActivity(it);
	}

	/**
	 * 获取登录设备mac地址
	 *
	 * @return Mac地址
	 */
	public static String getMacAddress(Context context) {
		WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo connectionInfo = wm.getConnectionInfo();
		String mac = connectionInfo.getMacAddress();
		return mac == null ? "" : mac;
	}


	/**
	 * 判断当前网络状态是否为移动网络
	 */
	public static boolean checkSIMCard(Context context){
		boolean flag = false;
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				if(mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI){
					//当前为WIFI连接
					flag = true;
				}else if(mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
					//当前为移动网络连接
					if( getMobileType(context) == 1 ){ //移动运营商
						flag = true;
					}

				}

			}
		}
		return flag;
	}

	/**
	 * 获取手机卡类型，移动1、联通2、电信3
	 *
	 */
	private static int getMobileType(Context context) {
		int type = 0;
		TelephonyManager iPhoneManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String iNumeric = iPhoneManager.getSimOperator();
		MLog.e("网络状态码", iNumeric);
		if (iNumeric.length() > 0) {
			if (iNumeric.equals("46000") || iNumeric.equals("46002") || iNumeric.equals("46007")) {
				// 中国移动
				type = 1;
			} else if (iNumeric.equals("46001") || iNumeric.equals("46006")) {
				// 中国联通
				type = 2;
			} else if (iNumeric.equals("46003") || iNumeric.equals("46005")) {
				// 中国电信
				type = 3;
			}
		}
		return type;
	}

	/**
	 * 获取网络连接类型
	 * @param context
	 * @return
     */
	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

	/**
	 * 将半角字符转换为全角字符
	 * 解决textview排版错乱
	 * @param input
	 * @return
     */
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}
}
