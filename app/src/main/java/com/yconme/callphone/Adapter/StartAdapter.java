package com.yconme.callphone.Adapter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yconme.callphone.Bean.StartBean;
import com.yconme.callphone.Beasic.MyBaseAdapter;
import com.yconme.callphone.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saksamaa on 2017/6/22.
 */

public class StartAdapter extends BaseAdapter {
    private Activity mContent;
    private MediaPlayer mPlayer = null;
    private List<StartBean.data> strings = new ArrayList<>();
    private int i = 1;
    private StartBean b;
    private List<String> string = new ArrayList<>();
    private StartViewHoler viewHoler;
    private int temp = -1; //记录已经点击的CheckBox的位置

    public StartAdapter(Activity context) {
        this.mContent = context;
    }

    public void setStartBean(StartBean s) {
        this.b = s;
        strings = s.getData();
    }


    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int i) {
        return strings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        mPlayer = new MediaPlayer();
        if (convertView == null) {
            viewHoler = new StartViewHoler();
            convertView = LayoutInflater.from(mContent).inflate(R.layout.start_activity_item, null, false);
            viewHoler.tv_start_item_title = (TextView) convertView.findViewById(R.id.start_item_title);
            viewHoler.tv_start_item_time = (TextView) convertView.findViewById(R.id.start_item_time);
            viewHoler.tv_start_item_start = (ImageView) convertView.findViewById(R.id.start_item_start);
            viewHoler.checkBox = (CheckBox) convertView.findViewById(R.id.start_check_cb);
            convertView.setTag(viewHoler);

        } else {
            viewHoler = (StartViewHoler) convertView.getTag();
        }
        StartBean.data data = strings.get(i);
        String id = data.getId();
        String title = data.getTitle();
        String created_at = data.getCreated_at();
        final String url = data.getUrl();
        viewHoler.checkBox.setId(i);
        viewHoler.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (temp != -1) {
                        CheckBox tempCheckBox = mContent.findViewById(temp);
                        if (tempCheckBox != null)
                            tempCheckBox.setChecked(false);

                    }
                    temp = compoundButton.getId();

                }
            }
        });

//        viewHoler.tv_start_item_start.setId(i);
        viewHoler.tv_start_item_title.setText(title);
        viewHoler.tv_start_item_time.setText(created_at);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });

        if (i == temp)
            viewHoler.checkBox.setChecked(true);
        else
            viewHoler.checkBox.setChecked(false);

        return convertView;
    }


    public static class StartViewHoler {
        public TextView tv_start_item_title, tv_start_item_time;
        public ImageView tv_start_item_start;
        public CheckBox checkBox;
    }


//    private void nextsong() {
//        image_play.setText("正在播放");
//        i++;
//        if (i > strings.size())
//            return;
//        if (i != 1) {
//            viewHoler.tv_start_item_start.setText("正在播放");
////            text_start_tv_current.setText("当前播放  " + i + "条");
////            image_play.setText("正在播放");
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

    private void songplay(String urk) {
//        Log.e("TAG", "songplay: ");
        try {
            mPlayer.reset();
            mPlayer.setDataSource(urk);
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

}
