package com.yconme.callphone.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.yconme.callphone.Beasic.InterfaceManagement;
import com.yconme.callphone.Beasic.MyBaseActivity;
import com.yconme.callphone.R;
import com.yconme.callphone.Utils.SharedPreferencesUtils;
import com.yconme.callphone.Utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by saksamaa on 2017/6/19.
 */

public class PerfectActivity extends MyBaseActivity {

    private static final String TAG = "TAG";
    private Handler handler = new Handler();
    private ImageView iv_perfect_iv_back;
    private EditText text_perfect_et_user;
    private EditText text_perfect_et_phone;
    private EditText text_perfect_et_pass;
    private Button button_perfect_btn_go;
    private OkHttpClient okHttpClient;
    private String taoken;
    private ProgressDialog progressDialog;

    @Override
    public int setview() {
        return R.layout.perfect_activity;
    }

    @Override
    public void init() {
        iv_perfect_iv_back = (ImageView) findViewById(R.id.perfect_iv_back);
        text_perfect_et_user = (EditText) findViewById(R.id.perfect_et_user);
        text_perfect_et_phone = (EditText) findViewById(R.id.perfect_et_phone);
        text_perfect_et_pass = (EditText) findViewById(R.id.perfect_et_pass);
        button_perfect_btn_go = (Button) findViewById(R.id.perfect_btn_go);
    }

    @Override
    public void setbase() {
        okHttpClient = new OkHttpClient();
        taoken = SharedPreferencesUtils.getstring("taoken", "");
        progressDialog = new ProgressDialog(PerfectActivity.this);
        iv_perfect_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Log.e(TAG, "taoken: " + taoken);
        button_perfect_btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                //完善信息提交
                String srt_user = text_perfect_et_user.getText().toString().trim();
                String srt_phone = text_perfect_et_phone.getText().toString().trim();
                String srt_pass = text_perfect_et_pass.getText().toString().trim();
                if (!srt_user.equals("") & !srt_phone.equals("") & !srt_pass.equals("")) {
                    Log.e("TAG", taoken + "+++++" + srt_user + "+++" + srt_phone + "+++" + srt_pass);
                    FormBody build = new FormBody.Builder().add("taoken", taoken).add("realname", srt_user).add("tel", srt_phone).add("password", srt_pass).build();
                    final Request request = new Request.Builder().url(InterfaceManagement.PathUrl.Perfectinformation).post(build).build();
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "Per_onFailure" + e.toString());
                            progressDialog.dismiss();

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            progressDialog.dismiss();
                            String string = response.body().string();
                            Log.e(TAG, "perfect_string: " + string);
                            try {
                                JSONObject jsonObject = new JSONObject(string);
                                String status = jsonObject.getString("status");
                                final String message = jsonObject.getString("message");
                                if (status.equals("1")) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ToastUtils.showToast(PerfectActivity.this, message);
                                            startActivity(new Intent(PerfectActivity.this, StratActivity.class));
                                            finish();
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                } else {
                    progressDialog.dismiss();
                    ToastUtils.showToast(PerfectActivity.this, "不允许为空，请重新输入");
                }

            }
        });


    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }
}
