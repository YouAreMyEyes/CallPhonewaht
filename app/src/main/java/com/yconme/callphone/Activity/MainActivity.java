package com.yconme.callphone.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.yconme.callphone.Bean.LoginBean;
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
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * For the brave souls who get this far: You are the chosen ones,
 * the valiant knights of programming who toil away, without rest,
 * fixing our most awful code. To you, true saviors, kings of men,
 * I say this: never gonna give you up, never gonna let you down,
 * never gonna run around and desert you. Never gonna make you cry,
 * never gonna say goodbye. Never gonna tell a lie and hurt you.
 */
public class MainActivity extends MyBaseActivity {
    private Handler handler = new Handler();

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String TAG = "TAG";
    private EditText text_user;
    private EditText text_pass;
    private CheckBox ck_ivv;
    private Button but_login;
    private OkHttpClient okHttpClient;
    private Gson gson;
    private boolean isboolean = false;
    //登录回调
    private Handler lgonHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    progressDialog.dismiss();
//                    ToastUtils.showToast(MainActivity.this, "账号密码错误");
                    break;
                case 2:
                    progressDialog.dismiss();
                    startActivity(new Intent(MainActivity.this, StratActivity.class));
                    finish();
                    break;
                case 3:
                    progressDialog.dismiss();
                    startActivity(new Intent(MainActivity.this, PerfectActivity.class));
                    break;
            }

        }
    };
    private ProgressDialog progressDialog;

    @Override
    public int setview() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {

        text_user = (EditText) findViewById(R.id.activity_edit_user);
        text_pass = (EditText) findViewById(R.id.activity_edit_pass);
        ck_ivv = (CheckBox) findViewById(R.id.activity_check_iv);
        but_login = (Button) findViewById(R.id.activity_but_login);
        okHttpClient = new OkHttpClient();
        gson = new Gson();
        progressDialog = new ProgressDialog(MainActivity.this);
        verifyStoragePermissions(MainActivity.this);

    }

    @Override
    public void setbase() {
        //设置光标位置为最后一位
        text_pass.addTextChangedListener(new TextWatcher() {
            @Override
            //在文本改变
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_pass.setSelection(text_pass.getText().length());
            }

            @Override
            //文本改变
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_pass.setSelection(text_pass.getText().length());
            }

            @Override
            //文本之改变
            public void afterTextChanged(Editable editable) {
                text_pass.setSelection(text_pass.getText().length());

            }
        });
        ck_ivv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (ck_ivv.isChecked()) {
                    //设置EditText的密码为可见的
                    text_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置密码为隐藏的
                    text_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        but_login.setOnClickListener(new View.OnClickListener() {

            private AlertDialog.Builder builder;

            @Override
            public void onClick(View view) {
                final String str_text_user = text_user.getText().toString().trim();
                final String srt_text_pass = text_pass.getText().toString().trim();
                if (isboolean == false) {
                    builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("注意");
                    View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_dialog_activity, null, false);
                    builder.setView(inflate);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            isboolean = true;
                            setLand(str_text_user, srt_text_pass);
                        }
                    });
                    builder.setNeutralButton("我知道了", new DialogInterface.OnClickListener() {//设置忽略按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            isboolean = true;
                            setLand(str_text_user, srt_text_pass);
                        }
                    });

                    builder.create().show();
                    ;
                } else {
                    setLand(str_text_user, srt_text_pass);
                }


            }
        });

    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    public void setLand(String str_user, String str_pass) {
        if (!str_user.equals("")) {
            FormBody user_id = new FormBody.Builder().add("username", str_user).add("password", str_pass).build();
            Request build = new Request.Builder().url(InterfaceManagement.PathUrl.login).post(user_id).build();
            Call call = okHttpClient.newCall(build);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i(TAG, "登录错误信息: " + e.toString());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToast(MainActivity.this, "登录信息错误");
                        }
                    });

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() != 500) {
                        String string = response.body().string();

//                                Log.e(TAG, "string: " + string);
                        LoginBean loginBean = gson.fromJson(string, LoginBean.class);
                        //获取对应账户唯一KEY  （KEY不定时更换,在后边接口中对应用来判断是否合法）
                        String taoken = loginBean.getTaoken();
//                            Log.e(TAG, "main_taoken" + taoken);
                        SharedPreferencesUtils.putstring("taoken", taoken);
                        //获取对于的状态值
                        String status1 = loginBean.getStatus();
                        //娶信息
                        final String message = loginBean.getMessage();
                        if (status1.equals("0")) {
                            lgonHandler.sendEmptyMessage(1);
                        } else if (status1.equals("1")) {
                            lgonHandler.sendEmptyMessage(2);
                        } else if (status1.equals("2")) {
                            lgonHandler.sendEmptyMessage(3);
                        } else if (status1.equals("-1")) {
                            progressDialog.dismiss();
                        } else if (status1.equals("-4")) {
                            progressDialog.dismiss();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(MainActivity.this, message);
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(MainActivity.this, "哎呀！出现问题了");
                            }
                        });
                    }
//

                }
            });
        } else {
            progressDialog.dismiss();
            ToastUtils.showToast(MainActivity.this, "账号密码不能为空");
        }
    }
}
