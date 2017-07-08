package com.yconme.callphone.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yconme.callphone.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by samksaa on 2017/7/8.
 */

public class CallPhoneActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callphone_activity);
        callphonee("10086");
        TextView textView = (TextView) findViewById(R.id.callphone_activity_text);
        Button button = (Button) findViewById(R.id.callphone_acivty_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callphonee("10086");
            }
        });


    }


    public void callphonee(final String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        startActivity(intent);
    }
}
