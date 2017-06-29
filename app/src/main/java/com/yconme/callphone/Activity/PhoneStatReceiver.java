package com.yconme.callphone.Activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by saksamaa on 2017/6/13.
 */

public class PhoneStatReceiver extends BroadcastReceiver {
    private static final String TAG = "TAG";
    private static boolean incomingFlag = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        //拨打电话
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            incomingFlag = false;
            final String phoneNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.e("PhoneReceiver", "phoneNum: " + phoneNum);
            PhoneListActivity phoneListActivity = new PhoneListActivity();
            phoneListActivity.handler.sendEmptyMessage(1);


        } else {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    final PhoneStateListener listener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                //电话等待接听
                case TelephonyManager.CALL_STATE_RINGING:
                    incomingFlag = true;
                    Log.e("PhoneReceiver", "CALL IN RINGING :" + incomingNumber);
                    break;
                //电话接听
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if (incomingFlag) {
                        Log.e("PhoneReceiver", "CALL IN ACCEPT :" + incomingNumber);
                    }
                    break;
                //电话挂机
                case TelephonyManager.CALL_STATE_IDLE:
                    if (incomingFlag) {
                        Log.e("PhoneReceiver", "CALL IDLE");
                    }
                    break;
            }
        }
    };

}



