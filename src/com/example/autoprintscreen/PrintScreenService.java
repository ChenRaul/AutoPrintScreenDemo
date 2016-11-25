package com.example.autoprintscreen;

import java.io.File;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class PrintScreenService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("onStartCommand");
		File file = new File("/mnt/sdcard/autoprintscreen/");
		if(!file.exists()){
			file.mkdir();
		}
		System.out.println("jietu ");
		//new PrintScreen().acquireScreenshot(getApplicationContext(), new File("/mnt/sdcard/autoprintscreen/12.png"));
		System.out.println("jie tu chenggong");
		//		Intent intent2 = new Intent(getApplicationContext(), PrintScreenActivity.class);
//		System.out.println("正在启动activity");
//		getApplicationContext().startActivity(intent2);
//		System.out.println("activity启动完毕");
		while(true){
			try {
				System.out.println("发送截图广播");
				Intent intent1 = new Intent("PRINTSCREEN");
				getApplicationContext().sendBroadcast(intent1);
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("fuwutingzhi");
		Intent intent = new Intent();
		intent.setAction("PrintScreenServiceIsClose");
		sendBroadcast(intent);
		System.out.println("广播发送成功");
		super.onDestroy();
	}

}
