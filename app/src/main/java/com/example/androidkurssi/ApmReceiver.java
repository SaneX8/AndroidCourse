package com.example.androidkurssi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ApmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        boolean state = intent.getBooleanExtra("state", false);

        if(state == false){
            Toast.makeText(context,"Airplane MODE OFF",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,"Airplane MODE ON",Toast.LENGTH_LONG).show();
        }


    }


}