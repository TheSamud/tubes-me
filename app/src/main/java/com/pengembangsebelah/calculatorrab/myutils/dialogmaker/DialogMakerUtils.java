package com.pengembangsebelah.calculatorrab.myutils.dialogmaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public class DialogMakerUtils {
    public interface Listener{
        void OnDismis();
    }

    Listener listenerDismis;
AlertDialog dialog;
View view;
LayoutInflater inflater;

public DialogMakerUtils(Activity activity, int id, final DialogMakerHandler listener){
    dialog = new AlertDialog.Builder(activity).create();
    inflater = activity.getLayoutInflater();
    view = inflater.inflate(id,null);
    dialog.setView(view);
    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            if(listenerDismis!=null) {
                listenerDismis.OnDismis();
            }
        }
    });
    listener.handler(view);
}
public void IsCancelable(boolean iscancelable){
    dialog.setCancelable(iscancelable);
}
public void SetTitle(String string){
    dialog.setTitle(string);
}

public void Show(){
    dialog.show();
}

public void Dismiss(){
    dialog.dismiss();
}
public void SetOnDismis(Listener listener){
    this.listenerDismis = listener;
}

}
