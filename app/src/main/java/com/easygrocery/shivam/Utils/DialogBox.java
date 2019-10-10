package com.easygrocery.shivam.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.easygrocery.shivam.easygrocery.R;

public class DialogBox {

    public static Dialog getDialogbox(Context c1){
        Dialog mDialog;
        mDialog=new Dialog(c1);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setContentView(R.layout.loading);
        mDialog.setCancelable(false);
        return mDialog;
    }
}
