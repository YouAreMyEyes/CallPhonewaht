package com.yconme.callphone.Activity;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yconme.callphone.R;

/**
 * Created by samksaa on 2017/7/6.
 */

public class NewDialog extends Dialog {
    private final LayoutInflater from;
    private Button view_button;
    private WindowManager.LayoutParams layoutParams;
    public NewDialog(@NonNull Context context) {
        super(context, R.style.new_dialog);
        from = LayoutInflater.from(context);
        init();
    }

    private void init() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        getWindow().setAttributes(params);
        getWindow().getAttributes().gravity = Gravity.CENTER;
        View inflate = from.inflate(R.layout.activity_dialog, null, false);
        view_button = (Button) inflate.findViewById(R.id.activity_dialog_button);
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels = displayMetrics.widthPixels;
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.dialog_lin);
//        linearLayout.removeView(view_button);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(widthPixels, heightPixels - 66));
        setContentView(inflate);
        Window window = getWindow();
        window.setGravity(Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.8f;
//

    }

    public Button getView_button() {
        return view_button;
    }
}



