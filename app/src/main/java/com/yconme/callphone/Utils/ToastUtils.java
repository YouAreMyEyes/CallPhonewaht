package com.yconme.callphone.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/27 0027.
 * 封装toast  避免多次点击 多次显示
 */

public class ToastUtils {
    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();

    }
}
