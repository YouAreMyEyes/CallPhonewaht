package com.yconme.callphone.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yconme.callphone.Bean.PhoneBean;
import com.yconme.callphone.Bean.PhoneListIsaboolean;
import com.yconme.callphone.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.ButterKnife;

/**
 * Created by saksamaa on 2017/6/21.
 */

public class NewPhoneListAdapter extends BaseAdapter {
    private Activity mContent;
    private PhoneBean phoneBean;
    private List<String> basis = new ArrayList<>();
    private List<PhoneListIsaboolean> listIsabooleen = new ArrayList<>();
    private String s;

    public NewPhoneListAdapter(Activity context) {
        this.mContent = context;
    }

    public void setPhoneBean(PhoneBean phoneBean) {
        this.phoneBean = phoneBean;
        basis = phoneBean.getData().get_package().getBasis();
        listIsabooleen.clear();
        for (int i = 0; i < basis.size(); i++) {
            listIsabooleen.add(new PhoneListIsaboolean());

        }
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return basis.size();
    }

    @Override
    public Object getItem(int i) {
        return basis.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup viewGroup) {
        final NewPhoneList_ViewHoler viewHoler;
        if (convertView == null) {
            viewHoler = new NewPhoneList_ViewHoler();
            convertView = LayoutInflater.from(mContent).inflate(R.layout.phone_list_item, null, false);
            viewHoler.textView_phone_list_rela_tv = (TextView) convertView.findViewById(R.id.phone_list_rela_tv);
            viewHoler.imageView_phone_list_iv_dian = (ImageView) convertView.findViewById(R.id.phone_list_iv_dian);
            viewHoler.checkBox = (CheckBox) convertView.findViewById(R.id.phone_list_checkbox);
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (NewPhoneList_ViewHoler) convertView.getTag();
        }
        try {
            s = basis.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewHoler.textView_phone_list_rela_tv.setText(s);
        viewHoler.checkBox.setChecked(listIsabooleen.get(position).isdel());
        viewHoler.checkBox.setTag(position);
        return convertView;
    }


    public List<PhoneListIsaboolean> getlistIsabooleen() {
        return listIsabooleen;
    }


      public static class NewPhoneList_ViewHoler {
        public ImageView imageView_phone_list_iv_dian;
        public TextView textView_phone_list_rela_tv;
        public CheckBox checkBox;


    }
}
