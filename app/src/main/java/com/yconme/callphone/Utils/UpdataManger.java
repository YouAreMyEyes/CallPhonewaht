package com.yconme.callphone.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yconme.callphone.Bean.Updata;
import com.yconme.callphone.Beasic.InterfaceManagement;
import com.yconme.callphone.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class UpdataManger {
    private static String packNa;

    private Handler handler = new Handler();
    //下载
    private static final int DOWNLOAD = 1;
    //打开
    private static final int OPENAPP = 2;
    //删除
    private static final int DELETE = 3;
    //下载
    private static final int DOWNLOADINT = 1;
    //下载完成
    private static final int DOWNLOAD_FINISH = 2;
    private boolean mIsCancel = false;
    private int mProgerss;
    private String mSavePath;
    private ProgressBar mProgressBar;
    private Dialog mDownloadDialog;
    private static final String TAG = "TAG";
    //版本
    private String mVersion_code;
    //名字
    private String mVersion_name;
    //内容
    private String mVersion_desc;
    //更新的网址
    private String mVersion_path;
    private static Context mContext;
    private static final String bingo_packname = "com.yconme.callphone";
    private Handler mGetversionHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String jsonObject = (String) msg.obj;
//            Log.e(TAG, "jsonObject: " + jsonObject);
            try {

                Gson gson = new Gson();
                Updata updata = gson.fromJson(jsonObject, Updata.class);

                mVersion_code = updata.getVersion();
                mVersion_name = "更新提示";
                List<String> note = updata.getNote();
                for (int i = 0; i < note.size(); i++) {
                    String s = note.get(i);
                    Log.e(TAG, "s: " + s);
                    mVersion_desc = s + s;
                }
                mVersion_path = updata.getUrl();


                if (isUpdate()) {
                    Toast.makeText(mContext, "需要更新", Toast.LENGTH_SHORT).show();
                    //需要弹出对话框提示用户
                    showNoticeDialog();
                } else {
                    //不需要更新
//                    Toast.makeText(mContext, "已经是最新版本", Toast.LENGTH_SHORT).show();
                    Log.e("UpdataManger", "updata_app: " + "已经是最新版本");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private Handler mUpdateProgressHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADINT:
                    textview.setText(mProgerss + "%");
                    mProgressBar.setProgress(mProgerss);
                    break;
                case DOWNLOAD_FINISH:
                    mDownloadDialog.dismiss();
                    installApk();

                    break;
            }

        }
    };
    private TextView textview;
    private static File dir;

    public UpdataManger(Context context) {
        this.mContext = context;

    }


    /**
     * 检测软件是否更新
     */
    public void checkUpdate() {
        //请求队列
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url(InterfaceManagement.PathUrl.upUpdateUri).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();
                Log.e("TAG", "升级数据: " + string);
                Message msg = Message.obtain();
                //请求成功的值，赋值给，msg
                msg.obj = string;
//                Log.e(TAG, "onResponse: " + string);
                //把值发送给mGetversionHanlder
                mGetversionHanlder.sendMessage(msg);
            }
        });

    }

    /**
     * 与本地比较判断是否需要更新
     */
    protected boolean isUpdate() {
        int serverVersion = Integer.parseInt(mVersion_code);
        int localVersion = 1;
        try {
            localVersion = mContext.getPackageManager().getPackageInfo("com.yconme.callphone", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (serverVersion > localVersion) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 有更新时显示提示对话框
     */
    protected void showNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mVersion_name);
        String message = mVersion_desc;
        builder.setMessage(message);
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //隐藏当前对话框
                dialog.dismiss();
                //显示下载对话框
                showDownloadDialog();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mIsCancel = true;
            }
        });
        builder.create().show();
    }

    /**
     * 显示正在下载对话框
     */
    protected void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("下载中");
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_pro, null);
        mProgressBar = (ProgressBar) view.findViewById(R.id.id_progress);
        textview = (TextView) view.findViewById(R.id.dial_text);
        builder.setView(view);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                Log.e(TAG, "isFile: " + dir);
                deleteFile(dir);
                // 设置下载状态为取消
                Toast.makeText(mContext, "已取消更新", Toast.LENGTH_SHORT).show();
                mIsCancel = true;

            }
        });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();

        downLoadApk();
    }

    /**
     * 开启线程下载apk
     */
    private void downLoadApk() {
        new Thread() {
            @Override
            public void run() {
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        String sdPath = Environment.getExternalStorageDirectory() + "/";
                        mSavePath = sdPath + "callphone_download";
                        dir = new File(mSavePath);
                        if (!dir.exists()) {
                            dir.mkdir();
                            // 下载文件
                            HttpURLConnection conn = (HttpURLConnection) new URL(mVersion_path).openConnection();
                            conn.connect();
                            InputStream is = conn.getInputStream();
                            //获取文件长度
                            int length = conn.getContentLength();
                            //保存路径和文件名字
                            File apkFile = new File(mSavePath, mVersion_name + ".apk");
                            FileOutputStream fos = new FileOutputStream(apkFile);
                            int count = 0;
                            byte[] buffer = new byte[1024];
//                            while ((count=is.read(buffer))!=-1){
                            while (!mIsCancel) {
                                //本次读取的数
                                int numread = is.read(buffer);
                                //下载的总量
                                count += numread;
                                //计算进度条当前的位置
                                mProgerss = (int) (((float) count / length) * 100);
                                //更新进度条
                                mUpdateProgressHanlder.sendEmptyMessage(DOWNLOADINT);
                                //下载完成
                                if (numread < 0) {
                                    mUpdateProgressHanlder.sendEmptyMessage(DOWNLOAD_FINISH);
                                    break;
                                }
                                fos.write(buffer, 0, numread);

                            }
//                            }

                            fos.close();
                            is.close();
                        } else {

                            // 下载文件
                            HttpURLConnection conn = (HttpURLConnection) new URL(mVersion_path).openConnection();
                            conn.connect();
                            InputStream is = conn.getInputStream();
                            //获取文件长度
                            int length = conn.getContentLength();
                            //保存路径和文件名字
                            File apkFile = new File(mSavePath, mVersion_name + ".apk");
                            FileOutputStream fos = new FileOutputStream(apkFile);
                            int count = 0;
                            byte[] buffer = new byte[1024];
                            while (!mIsCancel) {
                                //本次读取的数
                                int numread = is.read(buffer);
                                //下载的总量
                                count += numread;
                                //计算进度条当前的位置
                                mProgerss = (int) (((float) count / length) * 100);
                                //更新进度条
                                mUpdateProgressHanlder.sendEmptyMessage(DOWNLOADINT);
                                //下载完成
                                if (numread < 0) {
                                    mUpdateProgressHanlder.sendEmptyMessage(DOWNLOAD_FINISH);
                                    break;
                                }
                                fos.write(buffer, 0, numread);

                            }
                            fos.close();
                            is.close();

                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /***
     * 下载本地进行安装apk
     */
    protected void installApk() {
        File apkFile = new File(mSavePath, mVersion_name + ".apk");
        if (!apkFile.exists())
            return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + apkFile.toString());
        ////////////////////////////////////////////////
        /*
         就是一个标记， 如果有相同的任务栈，刚直接把这个栈整体移动到前台并保持栈中的状态不变 。如果没有，新建一个栈来存放act
         */
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ///////////////////////////////////////////////
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        mContext.startActivity(intent);

    }


//    public static class UpdataBroac extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
//                String packageName = intent.getDataString();
//                String substring = packageName.substring(packageName.indexOf(":") + 1, packageName.length());
//                Log.e(TAG, "onReceive: " + bingo_packname + "----------" + substring);
//                if (bingo_packname.equals(substring)) {
//                    deleteFile(dir);
//                    Log.e(TAG, "onReceive: " + bingo_packname + "----------" + substring);
//                }
//            }
//
//            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
//                String packageName = intent.getDataString();
//                String substring = packageName.substring(packageName.indexOf(":") + 1, packageName.length());
//
//            }
//        }
//    }

    public static void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();

        } else {
//            Toast.makeText(mContext, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

//    /**
//     * 打开apk
//     */
//    public static void oPenapp(String packname) {
//        PackageManager packageManager = mContext.getPackageManager();
//        Intent intent = new Intent();
//        intent = packageManager.getLaunchIntentForPackage(packname);
//        mContext.startActivity(intent);
//    }


}
