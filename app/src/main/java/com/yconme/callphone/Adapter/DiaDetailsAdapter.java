package com.yconme.callphone.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yconme.callphone.Bean.DialDetailslistBean;
import com.yconme.callphone.Beasic.MyBaseAdapter;
import com.yconme.callphone.R;

/**
 * Created by saksamaa on 2017/6/23.
 */

public class DiaDetailsAdapter extends MyBaseAdapter<DialDetailslistBean.DataEntity> {
    private Context mContent;

    public DiaDetailsAdapter(Context context) {
        this.mContent = context;
    }

    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        DiaDetails_ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new DiaDetails_ViewHolder();
            convertView = LayoutInflater.from(mContent).inflate(R.layout.diadetails_list_item, null, false);
            viewHolder.tv_diadetails_list_item_tv = (TextView) convertView.findViewById(R.id.diadetails_list_item_tv);
            viewHolder.tv_diadetails_list_item_tc = (TextView) convertView.findViewById(R.id.diadetails_list_item_tc);
            viewHolder.tv_diadetails_list_item_status = (TextView) convertView.findViewById(R.id.diadetails_list_item_status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DiaDetails_ViewHolder) convertView.getTag();
        }

        DialDetailslistBean.DataEntity dataEntity = list.get(position);
        //手机号
        String mobile = dataEntity.getMobile();
        //套餐
        String packageX = dataEntity.getPackageX();
        //状态
        String status = dataEntity.getStatus();
        viewHolder.tv_diadetails_list_item_tv.setText(mobile);
        viewHolder.tv_diadetails_list_item_tc.setText(packageX);
//        Log.e("TAG", "adapter_status: " + status);
//        if (status.equals("2")) {
            viewHolder.tv_diadetails_list_item_status.setText("成功");
//        }
//        if (status.equals("1")) {
//            viewHolder.tv_diadetails_list_item_status.setText("成功");
//        } else if (status.equals("-1")) {
//            viewHolder.tv_diadetails_list_item_status.setText("失败");
//        } else if (status.equals("0")) {
//            viewHolder.tv_diadetails_list_item_status.setText("未接通");
//        } else if (status.equals("-2")) {
//            viewHolder.tv_diadetails_list_item_status.setText("挂断");
//        }


        return convertView;
    }

    class DiaDetails_ViewHolder {
        TextView tv_diadetails_list_item_tv, tv_diadetails_list_item_tc, tv_diadetails_list_item_status;

    }
}
