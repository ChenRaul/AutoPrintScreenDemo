package com.example.autoprintscreen;

import java.util.Timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.Toast;

public class LaunchBroadcastReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "定时截图程序已经启动", 1).show();
		System.out.println("dong shi jie tu cehng xu qi dong ");
		 if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
				Intent intent1=new Intent();
				intent1.setClass(context, PrintScreenActivity.class);
				intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent1);
		 }
	}
}
