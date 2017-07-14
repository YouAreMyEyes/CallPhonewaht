package com.yconme.callphone.Beasic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LDX on 2016/11/7.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private Context context;

    //封装继承多态
    public List<T> list = new ArrayList<>();

    //添加一条数据
    public void addlistitem(T t) {
        list.add(t);
        notifyDataSetChanged();
    }

    //获取获取List集合
    public List<T> getdatalist() {
        return list;
    }

    // 添加全部数据
    public void addall(List<T> t) {
        list.addAll(t);
        notifyDataSetChanged();
    }
//    //添加一条内容
//    public void addDataItem(T t) {
//        if (t != null) {
//            list.add(t);
//        } else {
//            Toast.makeText(context, t + "是空的", Toast.LENGTH_SHORT).show();
//        }
//        notifyDataSetChanged();
//    }

    //为list赋值
    public void setdata(List<T> ts) {
        list = ts;
        notifyDataSetChanged();
    }

    //获取单个数据
    public T getitem(int index) {

        return list.get(index);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getview(position, convertView, parent);
    }

    public abstract View getview(int position, View convertView, ViewGroup parent);


    public static abstract class ViewHolder {
        public ViewHolder(View view) {
        }
    }
}
