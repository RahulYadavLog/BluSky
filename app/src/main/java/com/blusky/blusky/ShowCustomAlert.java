package com.blusky.blusky;

import android.app.Activity;

import com.crowdfire.cfalertdialog.CFAlertDialog;

public class ShowCustomAlert {

    public static void showErrorAlert(Activity activity,String title,String message,String buttonText){

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(activity)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.NOTIFICATION)
                .setTitle(title)
                .setMessage(message)
                .addButton(buttonText, -1, -1, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.END, (dialog, which) -> {

                    dialog.dismiss();
                });
        builder.show();
    }

    public static void showPositiveAlert(Activity activity,String title,String message,String buttonText){

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(activity)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.NOTIFICATION)
                .setTitle(title)
                .setMessage(message)
                .addButton(buttonText, -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END, (dialog, which) -> {

                    dialog.dismiss();
                });
        builder.show();
    }

}
