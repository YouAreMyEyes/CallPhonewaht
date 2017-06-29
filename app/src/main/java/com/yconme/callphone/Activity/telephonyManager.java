package com.yconme.callphone.Activity;

import android.telephony.PhoneStateListener;

/**
 * Created by saksamaa on 2017/6/13.
 */

public class telephonyManager  extends PhoneStateListener {
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);



    }
}
