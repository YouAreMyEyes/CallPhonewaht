package com.yconme.callphone.Activity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.yconme.callphone.Adapter.DiaDetailsAdapter;
import com.yconme.callphone.Bean.DialDetailslistBean;
import com.yconme.callphone.Beasic.InterfaceManagement;
import com.yconme.callphone.Beasic.MyBaseActivity;
import com.yconme.callphone.R;
import com.yconme.callphone.Utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by saksamaa on 2017/6/23.
 */

public class DialDetailsActivity extends MyBaseActivity {

    private static final String TAG = "TAG";
    private Handler handler = new Handler();
    private ListView listView;
    private PullToRefreshLayout pullToRefreshLayout;
    private OkHttpClient okHttpClient;
    private Gson gson;
    private String taoken;
    private DiaDetailsAdapter diaDetailsAdapter;
    private TextView textview;
    private ImageView iv_diadetails_iv_back;
    private int pageI = 1;
    private List<DialDetailslistBean.DataEntity> data;
    @Override
    public int setview() {
        return R.layout.diadetails_activity;
    }

    @Override
    public void init() {
        okHttpClient = new OkHttpClient();
        gson = new Gson();
        listView = (ListView) findViewById(R.id.diadetails_list);
        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.diadetails_list_ptr);
        textview = (TextView) findViewById(R.id.diadetails_tv_tv);
        iv_diadetails_iv_back = (ImageView) findViewById(R.id.diadetails_iv_back);


    }

    @Override
    public void setbase() {
        pullToRefreshLayout.setCanLoadMore(true);
        iv_diadetails_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        taoken = SharedPreferencesUtils.getstring("taoken", "");
        Log.e(TAG, "dial_page: " + pageI);
        FormBody build = new FormBody.Builder().add("taoken", taoken).add("page", String.valueOf(pageI)).add("size", "14").build();
        Request build1 = new Request.Builder().url(InterfaceManagement.PathUrl.Listdetails).post(build).build();
        Call call = okHttpClient.newCall(build1);
        call.enqueue(new Callback() {



            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "电话明细错误 " + e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
//                Log.e(TAG, "电话明细详情: " + string);
                DialDetailslistBean dialDetailslistBean = gson.fromJson(string, DialDetailslistBean.class);
                data = dialDetailslistBean.getData();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (data.isEmpty()) {
                            textview.setVisibility(View.VISIBLE);
                            pullToRefreshLayout.setVisibility(View.GONE);
                        } else {
                            diaDetailsAdapter = new DiaDetailsAdapter(DialDetailsActivity.this);
                            diaDetailsAdapter.addall(data);
                            listView.setAdapter(diaDetailsAdapter);
                            pullToRefreshLayout.setVisibility(View.VISIBLE);
                            textview.setVisibility(View.GONE);

                        }
//                        ListAdapter listAdapter = listView.getAdapter();
//                        if (listAdapter == null) {
//                            return;
//                        }
//                        View listItem = listAdapter.getView(0, null, listView);
//                        listItem.measure(0, 0);
//                        int measuredHeight = listItem.getMeasuredHeight();
//                        Log.e(TAG, "高度: " + measuredHeight);
                    }
                });
            }
        });


        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束刷新
                        pullToRefreshLayout.finishRefresh();
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束刷新
                        pageI++;
                        Log.e(TAG, "run: " + pageI);
                        getHttpdata(pageI);
                        pullToRefreshLayout.finishLoadMore();
                    }
                }, 2000);

            }
        });
    }

    public void getHttpdata(int page) {
        FormBody build = new FormBody.Builder().add("taoken", taoken).add("page", String.valueOf(page)).add("size", "14").build();
        Request build1 = new Request.Builder().url(InterfaceManagement.PathUrl.Listdetails).post(build).build();
        Call call = okHttpClient.newCall(build1);
        call.enqueue(new Callback() {

            private List<DialDetailslistBean.DataEntity> data1;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "电话明细错误 " + e.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
//                Log.e(TAG, "电话明细详情: " + string);
                DialDetailslistBean dialDetailslistBean = gson.fromJson(string, DialDetailslistBean.class);
                data = dialDetailslistBean.getData();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        diaDetailsAdapter.addall(data);
                    }
                });
            }
        });

    }

    @Override
    public MyBaseActivity getactivity() {
        return null;
    }
}
