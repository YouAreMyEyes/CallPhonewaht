package com.yconme.callphone.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.internal.telephony.ITelephony;
import com.google.gson.Gson;
import com.yconme.callphone.Adapter.NewPhoneListAdapter;
import com.yconme.callphone.Bean.PhoneBean;
import com.yconme.callphone.Bean.PhoneListIsaboolean;
import com.yconme.callphone.Bean.PhonelistRecordBean;
import com.yconme.callphone.Bean.SubmitBean;
import com.yconme.callphone.Beasic.InterfaceManagement;
import com.yconme.callphone.Beasic.MyBaseActivity;
import com.yconme.callphone.R;
import com.yconme.callphone.Utils.CheckAudioPermission;
import com.yconme.callphone.Utils.MyLlistview;
import com.yconme.callphone.Utils.RecorderUtil;
import com.yconme.callphone.Utils.SharedPreferencesUtils;
import com.yconme.callphone.Utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * Created by saksamaa on 2017/6/13.
 */

public class PhoneListActivity extends MyBaseActivity {
    private static final String TAG = "Phone";
    private static MediaRecorder mRecorder = null;
    private static String path;
    private Activity activity = PhoneListActivity.this;
    private LinearLayout view_phonelist_btn_success;
    private MyLlistview listView;
    private Gson gson;
    private TextView text_phonelist_phonenumber;
    private TextView text_phonelist_phonename;
    private int i = 0;
    private LinearLayout view_phonelist_btn_missed;
    private LinearLayout view_phonelist_btn_hangup;
    private OkHttpClient okHttpClient;
    private String taoken;
    private int StateJ = 0;
    private LinearLayout layout;
    private PhoneBean.DataEntity data;
    private LinearLayout layout_vg;
    private ArrayList<String> strings = new ArrayList<>();
    private List<String> basis;
    private int id1;
    private HashMap<String, Object> stringObjectHashMap;
    private static File file_path;
    private static final MediaType STREAM = MediaType.parse("multipart/form-data");
    private EditText text_remarks;
    //继续 暂停
    private boolean aBoolean = false;
    private boolean ztboolean = false;
    private File apkFile;
    private NewPhoneListAdapter newPhoneListAdapter = new NewPhoneListAdapter(PhoneListActivity.this);
    //成功上传录音  。的判断 为false上传
    private boolean statusPhone = false;
    private static boolean orBoolen = false;
    private boolean getboolean = false;
    private LinearLayout view_phonelist_linear_fail;
    private ImageView iv_phonelist_btn_missed_dian;
    private ImageView iv_phonelist_btn_hangup_dian;
    private ImageView iv_phonelist_btn_success_dian;
    private ImageView iv_phonelist_btn_fall_dian;
    private ImageView iv_phonelist_iv_suspend;
    private ImageView iv_phonelist_iv_continue;
    private PhoneBean phoneBean1;
    private List<String> basis1;
    private String itemTexit;
    private TextView tv_phonelist_tv_dialed;
    private TextView tv_phonelist_tv_success;
    private ImageView iv_phonelist_details;
    private String total;
    private Date curDate;
    private static Context content;
    private boolean upboolean = false;
    private boolean isboolean = false;
    private String againstring;
    private int postition;
    private String mobile;
    private boolean hasPermission;
    private static RecorderUtil recorderUtil;
    //    private static NewDialog newDialog;
    //获取单个号码 进行拨打电话功能
    private Handler CallHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    phoneBean1 = gson.fromJson(againstring, PhoneBean.class);
                    String status = phoneBean1.getStatus();
                    final String message = phoneBean1.getMessage();
                    if (status.equals("1")) {
                        data = phoneBean1.getData();
                        mobile = data.getMobile();
                        SharedPreferencesUtils.putstring("mob_key", mobile);
                        //ID
                        id1 = data.getId();
                        //
                        String desc = data.getDesc();
                        //区域
                        String area = data.getArea();
                        //
                        PhoneBean.DataEntity.PackageEntity aPackage = data.get_package();
                        basis = aPackage.getBasis();
                        //套餐
                        basis1 = aPackage.getBasis();
                        //1输入  0不输入
                        int is_memo = PhoneListActivity.this.data.getIs_memo();
//                        if (is_memo == 0) {
//                            layout_vg.setVisibility(View.GONE);
//                        } else if (is_memo == 1) {
//                            layout_vg.setVisibility(View.VISIBLE);
//                        }
                        text_phonelist_phonenumber.setText(mobile);
                        text_phonelist_phonename.setText(desc);
                        //***************************************************
                        newPhoneListAdapter.setPhoneBean(phoneBean1);
                        listView.setAdapter(newPhoneListAdapter);
                        newPhoneListAdapter.notifyDataSetChanged();
                        //***************************************************

//                        if (getboolean != true) {
                        //继续 or 暂时
                        if (aBoolean != true) {
                            //获取数据后开始打电话
                            String mob_key = SharedPreferencesUtils.getstring("mob_key", "");
                            callphonee(mob_key);
                        }
//                        }

                        statusPhone = false;

                    } else if (status.equals("-1")) {
                        startActivity(new Intent(PhoneListActivity.this, CompletedActivity.class));
                        finish();
                    }

                    break;
                case 2:
                    startActivity(new Intent(PhoneListActivity.this, CompletedActivity.class));
                    finish();
                    break;
            }


        }
    };
    //开始录音和结束录音
    static public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    tv_call_stats.setText("正在拨打");
                    String mob_key = SharedPreferencesUtils.getstring("mob_key", "");
                    if (mob_key != null) {
                        boolean hasPermission = CheckAudioPermission.isHasPermission(content);
                        if (hasPermission == true) {
                            recorderUtil = new RecorderUtil(mob_key + ".mp3");
                            recorderUtil.startRecording();
                            Log.e("TAG", "开始录音: ");
                        } else {
                            ToastUtils.showToast(content, "为空");
                        }
                    }

//                    try {
//                        newDialog.show();
//                        Button view_button = newDialog.getView_button();
//                        view_button.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                newDialog.dismiss();
//                                rejectCall();
//                            }
//                        });
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                    break;
            }
        }
    };
    private static TextView tv_call_stats;


    /**
     * 加载视图
     */
    @Override
    public int setview() {
        return R.layout.new_phonelist_activity;
    }

    //初始化数据
    @Override
    public void init() {
        content = PhoneListActivity.this;
//        newDialog = new NewDialog(PhoneListActivity.this);
        gson = new Gson();
        hasPermission = CheckAudioPermission.isHasPermission(content);
        //请求服务器电话列表
        okHttpClient = new OkHttpClient();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        text_phonelist_phonenumber = (TextView) findViewById(R.id.phonelist_phonenumber);
        text_phonelist_phonename = (TextView) findViewById(R.id.phonelist_phonename);
        //成功
        view_phonelist_btn_success = (LinearLayout) findViewById(R.id.phonelist_btn_success);
        iv_phonelist_btn_missed_dian = (ImageView) findViewById(R.id.phonelist_btn_missed_dian);
        //未接
        view_phonelist_btn_missed = (LinearLayout) findViewById(R.id.phonelist_btn_missed);
        iv_phonelist_btn_hangup_dian = (ImageView) findViewById(R.id.phonelist_btn_hangup_dian);
        //挂断
        view_phonelist_btn_hangup = (LinearLayout) findViewById(R.id.phonelist_btn_hangup);
        iv_phonelist_btn_success_dian = (ImageView) findViewById(R.id.phonelist_btn_success_dian);
        //失败
        view_phonelist_linear_fail = (LinearLayout) findViewById(R.id.phonelist_linear_fail);
        iv_phonelist_btn_fall_dian = (ImageView) findViewById(R.id.phonelist_btn_fall_dian);
        //动态添加布局
//        layout = (LinearLayout) findViewById(R.id.phonelist_linear);
        //显示隐藏的布局
        layout_vg = (LinearLayout) findViewById(R.id.phone_linear_vg);
        //备注信息
        text_remarks = (EditText) findViewById(R.id.phone_edit_remarks);
        listView = (MyLlistview) findViewById(R.id.phonelist_list);
        //继续
        iv_phonelist_iv_suspend = (ImageView) findViewById(R.id.phonelist_iv_suspend);
        //暂停
        iv_phonelist_iv_continue = (ImageView) findViewById(R.id.phonelist_iv_continue);
        //总共拨打电话
        tv_phonelist_tv_dialed = (TextView) findViewById(R.id.phonelist_tv_dialed);
        //已拨打
        tv_phonelist_tv_success = (TextView) findViewById(R.id.phonelist_tv_success);
        //详情
        iv_phonelist_details = (ImageView) findViewById(R.id.phonelist_details);

        tv_phonelist_tv_dialed.setText("今天已拨打:" + "0" + "个");

        tv_phonelist_tv_success.setText("成功" + "0" + "个");

        tv_call_stats = (TextView)findViewById(R.id.phonelist_call_stats);

    }

    /**
     * 加载数据
     */
    @Override
    public void setbase() {

        taoken = SharedPreferencesUtils.getstring("taoken", "");
        //获取电话数据
        if (hasPermission == true) {
            againData(taoken);
        } else {
            ToastUtils.showToast(context, "您没有录音权限请开启！！！");
        }
        /**
         * listview 实现不失去焦点的单选
         */
        tv_call_stats.setText("");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewPhoneListAdapter adapter = (NewPhoneListAdapter) adapterView.getAdapter();
                List<PhoneListIsaboolean> phoneListIsabooleen = adapter.getlistIsabooleen();
                PhoneListIsaboolean phoneListIsaboolean = phoneListIsabooleen.get(i);
                boolean isdel = phoneListIsaboolean.isdel();
                if (!isdel) {
                    for (PhoneListIsaboolean a : phoneListIsabooleen) {
                        a.setIsdel(false);
                    }
                    phoneListIsabooleen.get(i).setIsdel(true);
                    listView.requestLayout();
                    newPhoneListAdapter.notifyDataSetChanged();
                    itemTexit = basis1.get(i);
                }
            }
        });

        /**
         * 跳转电话详情
         */
        iv_phonelist_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PhoneListActivity.this, DialDetailsActivity.class));
            }
        });

        /**
         * 成功
         */
        view_phonelist_btn_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StateJ = 1;

                iv_phonelist_btn_success_dian.setImageResource(R.mipmap.new_gree);
                iv_phonelist_btn_missed_dian.setImageResource(R.mipmap.new_hs);
                iv_phonelist_btn_hangup_dian.setImageResource(R.mipmap.new_hs);
                iv_phonelist_btn_fall_dian.setImageResource(R.mipmap.new_hs);
            }
        });
        /***
         * 未接
         */
        view_phonelist_btn_missed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                StateJ = 0;

                iv_phonelist_btn_success_dian.setImageResource(R.mipmap.new_hs);
                iv_phonelist_btn_missed_dian.setImageResource(R.mipmap.new_gree);
                iv_phonelist_btn_hangup_dian.setImageResource(R.mipmap.new_hs);
                iv_phonelist_btn_fall_dian.setImageResource(R.mipmap.new_hs);
            }
        });
        /**
         * 挂断
         */
        view_phonelist_btn_hangup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StateJ = -2;

                iv_phonelist_btn_success_dian.setImageResource(R.mipmap.new_hs);
                iv_phonelist_btn_missed_dian.setImageResource(R.mipmap.new_hs);
                iv_phonelist_btn_hangup_dian.setImageResource(R.mipmap.new_gree);
                iv_phonelist_btn_fall_dian.setImageResource(R.mipmap.new_hs);


            }
        });
        /**
         * 失败
         */
        view_phonelist_linear_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StateJ = -1;

                iv_phonelist_btn_success_dian.setImageResource(R.mipmap.new_hs);
                iv_phonelist_btn_missed_dian.setImageResource(R.mipmap.new_hs);
                iv_phonelist_btn_hangup_dian.setImageResource(R.mipmap.new_hs);
                iv_phonelist_btn_fall_dian.setImageResource(R.mipmap.new_gree);
            }
        });
        /**
         * 暂停
         */
        iv_phonelist_iv_suspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strings.clear();
//                if (orBoolen == false) {
                recorderUtil.stopRecording();
                rejectCall();
                tv_call_stats.setText("以挂断");
                String filePath = recorderUtil.getFilePath();
//                Log.e("TAG", "新的路径+filePath: " + "-----" + filePath);
//                }
                //计时结束-------------------------------
                Log.e(TAG, "计时结束: ");
                Date endDate = new Date(System.currentTimeMillis());
                long diff = endDate.getTime() - curDate.getTime();
                String s = String.valueOf(diff);
                ztboolean = false;
                if (upboolean == false) {
                    String srt_remarks = text_remarks.getText().toString().trim();
                    String s1 = String.valueOf(StateJ);
                    submitDate(taoken, valueOf(id1), s1, itemTexit, srt_remarks, s);

                    getboolean = true;
                    isboolean = true;
                    upboolean = false;
                    ToastUtils.showToast(PhoneListActivity.this, "已暂停");

                }
            }
        });

        /**
         * 继续
         */
        iv_phonelist_iv_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strings.clear();
                recorderUtil.stopRecording();
                String filePath = recorderUtil.getFilePath();
                rejectCall();
                tv_call_stats.setText("以挂断");
//                Log.e("TAG", "新的路径+filePath: " + "-----" + filePath);
                //计时结束-------------------------------
                Log.e(TAG, "计时结束: ");
                Date endDate = new Date(System.currentTimeMillis());
                long diff = endDate.getTime() - curDate.getTime();
                String s = String.valueOf(diff);
                int second = Integer.parseInt(s);
                ztboolean = true;
//                if (upboolean == false) {
                String srt_remarks = text_remarks.getText().toString().trim();
                String s1 = String.valueOf(StateJ);
                double v = second / 1000.0;
                String s2 = String.valueOf(v);
                submitDate(taoken, valueOf(id1), s1, itemTexit, srt_remarks, s2);
//                }
                getboolean = false;
                aBoolean = false;
                upboolean = false;
//                CallHandler.sendEmptyMessage(1);
                againData(taoken);

//                }


            }
        });
    }


    @Override
    public MyBaseActivity getactivity() {
        return null;
    }


    /**
     * 拨打电话
     *
     * @param phoneNumber
     */
    public void callphonee(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        startActivity(intent);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        curDate = new Date(System.currentTimeMillis());
    }

    /**
     * 0 未接通 1成功 -1失败
     *
     * @param taoken
     * @param id
     * @param status
     * @param packagee
     * @param memo
     */
    public void submitDate(final String taoken, String id, final String status, String packagee, String memo, String stime) {
        //status = 0 未接通 1成功 -1失败
        Log.e("TAG", "--" + taoken + "---" + id + "---" + status + "---" + packagee + "---" + memo + "---" + stime);
        if (!(taoken != null && id != null && status != null && packagee != null && memo != null && stime != null)) {
            ToastUtils.showToast(PhoneListActivity.this, "有数值为空，请留意");

            return;
        }
        FormBody build = new FormBody.Builder().add("taoken", taoken).add("id", id).add("status", status).add("_package", packagee).add("memo", memo).add("time", stime).build();
        Request build1 = new Request.Builder().url(InterfaceManagement.PathUrl.Uploadcalllog).post(build).build();
        Call call = okHttpClient.newCall(build1);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "getDate_onFailure: " + e.toString());

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                String string = response.body().string();
//                Log.e(TAG, "通话记录: " + string);
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    String status1 = jsonObject.getString("status");
                    final String message = jsonObject.getString("message");
                    if (status1.equals("1")) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
//                                  ***************************************

                                List<PhoneListIsaboolean> phoneListIsabooleen = newPhoneListAdapter.getlistIsabooleen();
                                for (int i = 0; i < basis1.size(); i++) {
                                    PhoneListIsaboolean phoneListIsaboolean = phoneListIsabooleen.get(i);
                                    boolean isdel = phoneListIsaboolean.isdel();
                                    if (!isdel) {
                                        for (PhoneListIsaboolean a : phoneListIsabooleen) {
                                            a.setIsdel(false);
                                        }
                                        listView.requestLayout();
                                        newPhoneListAdapter.notifyDataSetChanged();
                                    }
                                }

                                iv_phonelist_btn_success_dian.setImageResource(R.mipmap.new_hs);
                                iv_phonelist_btn_missed_dian.setImageResource(R.mipmap.new_hs);
                                iv_phonelist_btn_hangup_dian.setImageResource(R.mipmap.new_hs);
                                iv_phonelist_btn_fall_dian.setImageResource(R.mipmap.new_hs);
                                text_remarks.setText("");
//                                   ***************************************
                                ToastUtils.showToast(PhoneListActivity.this, message);
                                String mob_key = SharedPreferencesUtils.getstring("mob_key", "");

                                if (mob_key != null) {
                                    String filePath = recorderUtil.getFilePath();
                                    //上传文件  名字     路径
//                                    Log.e("TAG", "上传之前的内容: " + filePath + "-----------" + mob_key);
                                    File file = new File(filePath);
                                    Submitaudio(mob_key, file);
                                } else {
                                    ToastUtils.showToast(PhoneListActivity.this, "路径为空");
                                }


                            }
                        });

                    } else if (status1.equals("-1")) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(PhoneListActivity.this, message);
                                startActivity(new Intent(PhoneListActivity.this, CompletedActivity.class));
                                finish();
                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    /**
     * @param taoken
     */

    public void againData(String taoken) {
        FormBody staff_id = new FormBody.Builder().add("taoken", taoken).build();
        Request build = new Request.Builder().url(InterfaceManagement.PathUrl.Telephonedata).post(staff_id).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", "onFailure: " + e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    againstring = response.body().string();
                    if (againstring != null) {

                        CallHandler.sendEmptyMessage(1);
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(PhoneListActivity.this, "数据为空");
                            }
                        });

                    }
                }
            }
        });
    }

    public void Submitaudio(String luname, final File file) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("taoken", taoken)
                .addFormDataPart("id", valueOf(id1))
                .addFormDataPart("tape", "/" + luname + ".mp3", RequestBody.create(STREAM, file))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "...")
                .url(InterfaceManagement.PathUrl.Uploadrecording)
                .post(requestBody)
                .build();
        Call call1 = okHttpClient.newCall(request);
        call1.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Submitaudio", "提交通话录音错误: " + e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string1 = response.body().string();
//                Log.e("TAG", "上传录音的: " + string1);
                SubmitBean submitBean = gson.fromJson(string1, SubmitBean.class);
                String status = submitBean.getStatus();
                final String message = submitBean.getMessage();
                if (status.equals("1")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            basis1.clear();
                            getboolean = true;
                            upboolean = true;
//                            Log.e("TAG", "上传成功: ");
                            newPhoneListAdapter.notifyDataSetChanged();
//                            againData(taoken);
                        }
                    });

                    if (ztboolean == true) {
                        mobile = null;
                        /**
                         * 当上传结束后，回调一次请求电话列表，然后继续拨打电话
                         */
//                        againData(taoken);
                    }

                } else if (status.equals("-1")) {

                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(PhoneListActivity.this, message);
                    }
                });

            }
        });
    }


    private void getRecord(String taoken) {
        final FormBody taoken1 = new FormBody.Builder().add("taoken", taoken).build();
        Request build = new Request.Builder().url(InterfaceManagement.PathUrl.Telephonerecording).post(taoken1).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
//                Log.e(TAG, "获取电话记录: " + string);
                final PhonelistRecordBean phonelistRecordBean = gson.fromJson(string, PhonelistRecordBean.class);
                String status = phonelistRecordBean.getStatus();
                if (status.equals("1")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            PhonelistRecordBean.DataEntity data = phonelistRecordBean.getData();
                            //总数
                            total = data.getTotal();

                            tv_phonelist_tv_dialed.setText("今天已拨打:" + total + "个");
                            //
                            List<PhonelistRecordBean.DataEntity.RecordsEntity> records = data.getRecords();
                            for (int i = 0; i < records.size(); i++) {
                                PhonelistRecordBean.DataEntity.RecordsEntity recordsEntity = records.get(i);
                                String status1 = recordsEntity.getStatus();
                                String count = recordsEntity.getCount();
                                if (status1.equals("1")) {
                                    tv_phonelist_tv_success.setText("成功" + count + "个");
                                }
                            }

                        }
                    });


                }

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (taoken != null) {
            //获取通话记录
            getRecord(taoken);
        }
    }

    public static void stop() {
        if (mRecorder != null) {
            try {
                mRecorder.stop();
            } catch (IllegalStateException e) {
                mRecorder = null;
                mRecorder = new MediaRecorder();
            }
            mRecorder.release();
            mRecorder = null;
        }
    }


    /**
     * 停止打电话
     */
    public static void rejectCall() {
        try {
            Method method = Class.forName("android.os.ServiceManager")
                    .getMethod("getService", String.class);
            IBinder binder = (IBinder) method.invoke(null, new Object[]{Context.TELEPHONY_SERVICE});
            ITelephony telephony = ITelephony.Stub.asInterface(binder);
            telephony.endCall();
        } catch (NoSuchMethodException e) {
            Log.d("TAG", "", e);
        } catch (ClassNotFoundException e) {
            Log.d("TAG", "", e);
        } catch (Exception e) {
        }
    }


}

