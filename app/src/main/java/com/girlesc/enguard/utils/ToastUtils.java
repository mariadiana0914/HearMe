package com.girlesc.enguard.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void showToast(Context context, String toastText) {
        if(toastText == null) {
            return;
        }
        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();
    }
}
