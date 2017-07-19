package com.yconme.callphone.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yconme.callphone.Adapter.StartAdapter;
import com.yconme.callphone.Bean.StartBean;
import com.yconme.callphone.Beasic.InterfaceManagement;
import com.yconme.callphone.Beasic.MyBaseActivity;
import com.yconme.callphone.R;
import com.yconme.callphone.Utils.SharedPreferencesUtils;
import com.yconme.callphone.Utils.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by saksamaa on 2017/6/13.
 */

public class StratActivity extends MyBaseActivity {
    private static final String TAG = "TAG";
    private Handler handler = new Handler();
    private MediaPlayer mPlayer = null;
    private ArrayList<String> strings;
    private int songIndex = 0;
    private TextView text_start_tv_total;
    private TextView text_start_tv_current;
    private int i = 1;
    private OkHttpClient okHttpClient;
    private Gson gson;
    private LinearLayout layout;
    private TextView image_play;
    private ListView listView;
    private StartAdapter startAdapter;
    private TextView tv_start_activity_tv_vis;
    private boolean simCard;

    @Override
    public int setview() {
        return R.layout.start_activity;
    }

    @Override
    public void init() {
//        simCard = readSIMCard();
        Log.e(TAG, "simCard: "+simCard );
        tv_start_activity_tv_vis = (TextView) findViewById(R.id.start_activity_tv_vis);
        text_start_tv_total = (TextView) findViewById(R.id.start_tv_total);
        text_start_tv_current = (TextView) findViewById(R.id.start_tv_current);
        layout = (LinearLayout) findViewById(R.id.start_linear);
        Button button = (Button) findViewById(R.id.start_but_go);
        listView = (ListView) findViewById(R.id.start_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (simCard == true) {
                    startActivity(new Intent(StratActivity.this, PhoneListActivity.class));
                    finish();
//                } else {
//                    ToastUtils.showToast(StratActivity.this, "您没有sim卡请插入");
//                }

            }
        });

        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(new CompletionListener());
        strings = new ArrayList<>();


    }

    @Override
    public void setbase() {
        gson = new Gson();
        okHttpClient = new OkHttpClient();
        startAdapter = new StartAdapter(StratActivity.this);
        String taoken = SharedPreferencesUtils.getstring("taoken", "");
        FormBody taoken1 = new FormBody.Builder().add("taoken", taoken).build();
        Request build = new Request.Builder().url(InterfaceManagement.PathUrl.Telephonelist).post(taoken1).build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {

            private List<StartBean.data> data;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "获取录音记录错误信息: " + e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final StartBean startBean = gson.fromJson(string, StartBean.class);
                try {
                    data = startBean.getData();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (data != null) {
                            if (data.isEmpty()) {
                                tv_start_activity_tv_vis.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                            } else {
                                listView.setVisibility(View.VISIBLE);
                                tv_start_activity_tv_vis.setVisibility(View.GONE);
                                for (int i = 0; i < data.size(); i++) {
                                    StartBean.data data1 = data.get(i);
                                    String url = data1.getUrl();
                                    strings.add(url);
                                }

                            }
                        }

                    }
                });


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String message = startBean.getMessage();
                            ToastUtils.showToast(StratActivity.this, message);
                            text_start_tv_total.setText("总：" + strings.size());
                            listView.setAdapter(startAdapter);
                            startAdapter.setStartBean(startBean);
//                        int size = strings.size();
//                        text_start_tv_total.setText("总共语音  " + size + "条");
//                        text_start_tv_current.setText("当前播放  " + i + "条");
//                        Log.e("TAG", "i: " + 1);
//                        songplay();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StartAdapter.StartViewHoler startViewHoler = (StartAdapter.StartViewHoler) view.getTag();
//                startViewHoler.tv_start_item_start.setText("正在播放");
                String s = strings.get(i);
                int i1 = i + 1;
                text_start_tv_current.setText("当前:" + i1);
                songplay(s);
                startViewHoler.checkBox.toggle();

            }
        });
        text_start_tv_total = (TextView) findViewById(R.id.start_tv_total);
        text_start_tv_current = (TextView) findViewById(R.id.start_tv_current);
        text_start_tv_current.setText("当前:" + 0);

    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }


    private class CompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
//            nextsong();

        }
    }

//    private void nextsong() {
//        image_play.setText("正在播放");
//        i++;
//        if (i > strings.size())
//            return;
//        if (i != 1) {
//            text_start_tv_current.setText("当前播放  " + i + "条");
//            image_play.setText("正在播放");
//        }
//        if (songIndex < strings.size() - 1) {
//            songIndex = songIndex + 1;
//            songplay();
//        } else {
//            strings.clear();
//            songIndex = 0;
//
//        }


//    }

    private void songplay(String str) {
//        Log.e("TAG", "songplay: ");
        try {
            mPlayer.reset();
            mPlayer.setDataSource(str);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

//    @Override
//    protected void onDestroy() {
//        if (mPlayer != null) {
//            mPlayer.stop();
////            flag= false;
//            //释放资源
//            mPlayer.release();
//        }
//        super.onDestroy();
//    }

    @Override
    protected void onStop() {
        if (mPlayer != null) {
            mPlayer.stop();
//            flag= false;
            //释放资源
            mPlayer.release();
        }

        super.onStop();
    }
    //    @Override
//    protected void onPause() {
//        if (mPlayer != null) {
//            mPlayer.stop();
////            flag= false;
//            //释放资源
//            mPlayer.release();
//        }0
//        super.onPause();
//    }

    public boolean readSIMCard() {

        TelephonyManager manager = (TelephonyManager) this
                .getSystemService(TELEPHONY_SERVICE);// 取得相关系统服务
        String imsi = manager.getSubscriberId(); // 取出IMSI
        System.out.println("取出IMSI" + imsi);

        if (imsi == null || imsi.length() <= 0) {
            System.out.println("请确认sim卡是否插入或者sim卡暂时不可用！");
            //APIFailSimBuyJNI();

        } else {
            System.out.println("有SIM卡");
            return true;
        }
        return false;
    }
}
