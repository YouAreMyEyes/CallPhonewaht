package com.yconme.callphone.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LDX on 2016/11/7.
 * 共享参数 用来判断是不是第一次加载app
 */

public class SharedPreferencesUtils {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor edit;

    public static void init(Context context, String name) {
        sharedPreferences = context.getSharedPreferences(name, context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
       edit.commit();
    }

    public static void putstring(String key, String value) {
        if (edit != null) {
            edit.putString(key, value);
            edit.commit();
        }
    }


    public static void putboolean(String key, boolean value) {
        if (edit != null) {
            edit.putBoolean(key, value);
            edit.commit();
        }
    }

    /**
     * 取得方法
     */

    public static String getstring(String key, String deaflut) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, deaflut);
        }
        return "";
    }
    public static boolean getboolean(String key, boolean aaa) {
        if (sharedPreferences != null) {
            boolean aBoolean = sharedPreferences.getBoolean(key, aaa);
            return aBoolean;
        }
        return false;
    }

}
