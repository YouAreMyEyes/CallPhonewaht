package com.yconme.callphone.Activity;

import android.view.View;
import android.widget.ImageView;

import com.yconme.callphone.Beasic.MyBaseActivity;
import com.yconme.callphone.R;
import com.yconme.callphone.Utils.ToastUtils;

/**
 * Created by saksamaa on 2017/6/15.
 */

public class CompletedActivity extends MyBaseActivity {

    private ImageView imageView;

    @Override
    public int setview() {
        return R.layout.comleted_activity;
    }

    @Override
    public void init() {
        imageView = (ImageView) findViewById(R.id.comleted_up);

    }

    @Override
    public void setbase() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(CompletedActivity.this, "没有需要上传录音");
            }
        });

    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }
}
