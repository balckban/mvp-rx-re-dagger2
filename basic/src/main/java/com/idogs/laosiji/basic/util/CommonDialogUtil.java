package com.idogs.laosiji.basic.util;


import android.content.Context;
import android.content.DialogInterface;


import com.idogs.laosiji.basic.R;
import com.idogs.laosiji.widget.dialog.BaseDialog;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class CommonDialogUtil {

    static BaseDialog baseDialog = null;
    public static AtomicBoolean isShowing = new AtomicBoolean(false);

    /**
     * 成功dilog
     * context
     * message 提示内容
     * lcontent 左边按钮内容
     * rcontent 右边按钮内容
     * iclistener 左边按钮监听事件
     * listener 右边按钮监听事件
     **/
    public static void successDialog(Context context, String message) {
        dismiss();
        baseDialog = new BaseDialog(context, false, false, false, true, true, R.drawable.success, true
                , "success", false, message, null, null, null, null, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isShowing.set(false);
            }
        });
        baseDialog.showCustomDialog();
    }

    public static void successDialog(Context context, String message, String rcontent, BaseDialog.SureInterfance listener) {
        dismiss();
        baseDialog = new BaseDialog(context, false, false, true, false, false, R.drawable.success, true
                , "success", false, message, null, rcontent, listener, null, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isShowing.set(false);
            }
        });
        baseDialog.showCustomDialog();
    }

    public static void successDialog(Context context, String message, String lcontent, String rcontent, BaseDialog.ICancelListener iCancelListener, BaseDialog.SureInterfance listener) {
        dismiss();
        baseDialog = new BaseDialog(context, false, true, true, false, false, R.drawable.success, true
                , "success", false, message, lcontent, rcontent, listener, iCancelListener, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isShowing.set(false);
            }
        });
        baseDialog.showCustomDialog();
    }

    /**
     * 失败
     * context
     * message 提示内容
     * lcontent 左边按钮内容
     * rcontent 右边按钮内容
     * iclistener 左边按钮监听事件
     * listener 右边按钮监听事件
     **/
    public static void errorDialog(Context context, String message) {
        dismiss();
        baseDialog = new BaseDialog(context, false, false, true, false, false, R.drawable.error, true
                , "fail", false, message, null, null, null, null, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
        baseDialog.showCustomDialog();

    }

    public static void errorDialog(Context context, String message, String rcontent, BaseDialog.SureInterfance listener) {
        dismiss();
        baseDialog = new BaseDialog(context, false, true, true, false, false, R.drawable.error, true
                , "fail", false, message, null, rcontent, listener, null, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
        baseDialog.showCustomDialog();

    }

    public static void errorDialog(Context context, String message, String lcontent, String rcontent, BaseDialog.ICancelListener iCancelListener, BaseDialog.SureInterfance listener) {
        dismiss();
        baseDialog = new BaseDialog(context, false, true, true, false, false, R.drawable.error, true
                , "fail", false, message, null, rcontent, listener, iCancelListener, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
        baseDialog.showCustomDialog();

    }

    /**
     * context
     * 等待Dialog
     * message 提示内容
     **/
    public static void waitingDialog(Context context, String message) {
        dismiss();
        baseDialog = new BaseDialog(context, true, false, false, false, false, R.drawable.loading
                , true, "waiting", false, message, null, null, null, null, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        baseDialog.showCustomDialog();
    }

    /**
     * context 警告dilog
     * message 提示内容
     * lcontent 左边按钮内容
     * rcontent 右边按钮内容
     * iclistener 左边按钮监听事件
     * listener 右边按钮监听事件
     **/
    public static void warningDialog(Context context, String message) {
        dismiss();
        baseDialog = new BaseDialog(context, false, false, false, false, false, R.drawable.warn
                , true, "waiting", false, message, null, null, null, null, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        baseDialog.showCustomDialog();
    }

    public static void warningDialog(Context context, String message, String rcontent, BaseDialog.SureInterfance listener) {
        dismiss();
        baseDialog = new BaseDialog(context, false, false, true, false, false, R.drawable.warn
                , true, "waiting", false, message, null, rcontent, listener, null, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        baseDialog.showCustomDialog();
    }

    public static void warningDialog(Context context, String message, String lcontent, String rcontent, BaseDialog.ICancelListener iCancelListener, BaseDialog.SureInterfance listener) {
        dismiss();
        baseDialog = new BaseDialog(context, false, true, true, false, false, R.drawable.warn
                , true, "waiting", false, message, lcontent, rcontent, listener, iCancelListener, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        baseDialog.showCustomDialog();
    }

    /**
     * context
     * message 提示内容
     * lcontent 左边按钮内容
     * rcontent 右边按钮内容
     * iclistener 左边按钮监听事件
     * listener 右边按钮监听事件
     **/
    public static void remindDialog(Context context, String message) {
        dismiss();
        baseDialog = new BaseDialog(context, false, false, false, true, true, R.drawable.roup
                , true, "waiting", false, message, null, null, null, null, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        baseDialog.showCustomDialog();
    }

    public static void remindDialog(Context context, String message, String rcontent, BaseDialog.SureInterfance listener) {
        dismiss();
        baseDialog = new BaseDialog(context, false, false, true, true, true, R.drawable.roup
                , true, "waiting", false, message, null, rcontent, listener, null, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        baseDialog.showCustomDialog();
    }

    public static void remindDialog(Context context, String message, String lcontent, String rcontent, BaseDialog.ICancelListener iCancelListener, BaseDialog.SureInterfance listener) {
        dismiss();
        baseDialog = new BaseDialog(context, false, true, true, false, false, R.drawable.roup
                , true, "waiting", false, message, lcontent, rcontent, listener, iCancelListener, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        baseDialog.showCustomDialog();
    }

    /**
     * 关闭Dialog
     */
    public static void dismiss() {
        if (baseDialog != null && baseDialog.isShowing())
            baseDialog.dismiss();
    }
}


