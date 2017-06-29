package com.yconme.callphone.Activity;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by saksamaa on 2017/6/13.
 */

public class ReadLog extends Thread{
    private Context ctx;
    private int logCount;

    private static final String TAG = "LogInfo OutGoing Call";

    /**
     *  前后台电话
     * @author sdvdxl
     *
     */
    private static class CallViewState {
        public static final String FORE_GROUND_CALL_STATE = "mForeground";
    }

    /**
     * 呼叫状态
     * @author sdvdxl
     *
     */
    private static class CallState {
        public static final String DIALING = "DIALING";
        public static final String ALERTING = "ALERTING";
        public static final String ACTIVE = "ACTIVE";
        public static final String IDLE = "IDLE";
        public static final String DISCONNECTED = "DISCONNECTED";
    }

    public ReadLog(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * 读取Log流
     * 取得呼出状态的log
     * 从而得到转换状态
     */
    @Override
    public void run() {
        Log.d(TAG, "开始读取日志记录");

        String[] catchParams = {"logcat", "InCallScreen *:s"};
        String[] clearParams = {"logcat", "-c"};

        try {
            Process process=Runtime.getRuntime().exec(catchParams);
            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line=reader.readLine())!=null) {
                logCount++;
                //输出所有
                Log.v(TAG, line);

                //日志超过512条就清理
                if (logCount>512) {
                    //清理日志
                    Runtime.getRuntime().exec(clearParams)
                            .destroy();//销毁进程，释放资源
                    logCount = 0;
                    Log.v(TAG, "-----------清理日志---------------");
                }

                /*---------------------------------前台呼叫-----------------------*/
                //空闲
                if (line.contains(ReadLog.CallViewState.FORE_GROUND_CALL_STATE)
                        && line.contains(ReadLog.CallState.IDLE)) {
                    Log.d(TAG, ReadLog.CallState.IDLE);
                }

                //正在拨号，等待建立连接，即已拨号，但对方还没有响铃，
                if (line.contains(ReadLog.CallViewState.FORE_GROUND_CALL_STATE)
                        && line.contains(ReadLog.CallState.DIALING)) {
                    Log.d(TAG, ReadLog.CallState.DIALING);
                }

                //呼叫对方 正在响铃
                if (line.contains(ReadLog.CallViewState.FORE_GROUND_CALL_STATE)
                        && line.contains(ReadLog.CallState.ALERTING)) {
                    Log.d(TAG, ReadLog.CallState.ALERTING);
                }

                //已接通，通话建立
                if (line.contains(ReadLog.CallViewState.FORE_GROUND_CALL_STATE)
                        && line.contains(ReadLog.CallState.ACTIVE)) {
                    Log.d(TAG, ReadLog.CallState.ACTIVE);
                }

                //断开连接，即挂机
                if (line.contains(ReadLog.CallViewState.FORE_GROUND_CALL_STATE)
                        && line.contains(ReadLog.CallState.DISCONNECTED)) {
                    Log.d(TAG, ReadLog.CallState.DISCONNECTED);
                }

            } //END while

        } catch (IOException e) {
            e.printStackTrace();
        } //END try-catch
    } //END run
} //END class ReadLog
