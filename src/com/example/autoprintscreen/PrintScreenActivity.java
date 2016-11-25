package com.example.autoprintscreen;

import java.io.File;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PrintScreenActivity extends Activity {
	RelativeLayout relativeLayout;
	private Button button;
	SurfaceView surfaceView;
	int i = 0;
	Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		File file = new File("/mnt/sdcard/autoprintscreen/");
		if(!file.exists()){
			file.mkdir();
		}
		System.out.println(getWindow().getDecorView());
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PrintScreen.shoot(PrintScreenActivity.this, new File("/mnt/sdcard/autoprintscreen/sda.png"));
				Intent intent = new Intent();
				intent.setClass(PrintScreenActivity.this, Text.class);
				PrintScreenActivity.this.startActivity(intent);
			}
		});
		new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(10000);
						System.out.println("¿ªÊ¼½ØÍ¼");
						PrintScreen.shoot(PrintScreenActivity.this, new File("/mnt/sdcard/autoprintscreen/"+i+".png"));
						i++;
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
		}.start();
	}

}
