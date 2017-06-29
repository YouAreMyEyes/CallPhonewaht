package com.yconme.callphone.Beasic;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yconme.callphone.R;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.effortlesspermissions.AfterPermissionDenied;
import me.zhanghai.android.effortlesspermissions.EffortlessPermissions;
import me.zhanghai.android.effortlesspermissions.OpenAppDetailsDialogFragment;
import pub.devrel.easypermissions.AfterPermissionGranted;

/**
 * Created by LDX on 2016/11/7.
 */

public abstract class MyBaseActivity extends AppCompatActivity {
    protected Context context = this;
    protected static List<Activity> allactivity = new ArrayList<>();
    private static final int REQUEST_CODE_SAVE_FILE_PERMISSION = 1;
    private static final String[] PERMISSIONS_SAVE_FILE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖
        setContentView(setview());
        allactivity.add(getactivity());
        init();
        setbase();
        saveFile();
    }

    /**
     * 进行布局的添加
     *
     * @return
     */
    public abstract int setview();

    /**
     * 对控件进行初始化
     */
    public abstract void init();

    /**
     * 对数据进行操作
     */
    public abstract void setbase();

    /**
     * 将activity封装进集合里方便遍历杀死
     */
    public abstract MyBaseActivity getactivity();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Dispatch to our library.
        EffortlessPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults,
                this);
    }

    // Call back to the same method so that we'll check and proceed.
    @AfterPermissionGranted(REQUEST_CODE_SAVE_FILE_PERMISSION)
    private void saveFile() {
        if (EffortlessPermissions.hasPermissions(this, PERMISSIONS_SAVE_FILE)) {
            // We've got the permission.
            saveFileWithPermission();
        } else if (EffortlessPermissions.somePermissionPermanentlyDenied(this,
                PERMISSIONS_SAVE_FILE)) {
            // Some permission is permanently denied so we cannot request them normally.
//            OpenAppDetailsDialogFragment.show(
//                    R.string.save_file_permission_permanently_denied_message,
//                    R.string.open_settings, this);
        } else {
            // Request the permissions.
            EffortlessPermissions.requestPermissions(this,
                    R.string.save_file_permission_request_message,
                    REQUEST_CODE_SAVE_FILE_PERMISSION, PERMISSIONS_SAVE_FILE);
        }
    }

    @AfterPermissionDenied(REQUEST_CODE_SAVE_FILE_PERMISSION)
    private void onSaveFilePermissionDenied() {
        // User denied at least some of the required permissions, report the error.
        Toast.makeText(this, R.string.save_file_permission_denied, Toast.LENGTH_SHORT).show();
    }

    private void saveFileWithPermission() {
        // It's show time!
//        Toast.makeText(this, R.string.save_file_show_time, Toast.LENGTH_SHORT).show();
    }

}
