package com.example.autoprintscreen;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ScrollView;

public class PrintScreen {  
    private static Bitmap takeScreenShot(Activity activity) {  
        // View������Ҫ��ͼ��View  
        View view = activity.getWindow().getDecorView();
        System.out.println(view);
        view.setDrawingCacheEnabled(true);  
        view.buildDrawingCache();  
        Bitmap b1 = view.getDrawingCache();  
   
        // ��ȡ״̬���߶�  
        Rect frame = new Rect();  
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);  
        int statusBarHeight = frame.top;  
        System.out.println(statusBarHeight);
        // ��ȡ��Ļ���͸�  
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();  
        int height = activity.getWindowManager().getDefaultDisplay()  
                .getHeight();  
        // ȥ��������/״̬��  
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height  
                - statusBarHeight);  
        view.destroyDrawingCache();  
        return b;  
    }  
    /**
     * ��View����ת����bitmap
     * */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (bitmap != null) {
            System.out.println("�ⲻ��nullde1");
            Log.d("nullde1", "nullde1");
        } else {
            System.out.println("��nullnulllnulnlul");
        }
        return bitmap;
    }
    private static void savePic(Bitmap b, File filePath) {  
        FileOutputStream fos = null;  
        try {  
            fos = new FileOutputStream(filePath);  
            if (null != fos) {  
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);  
                fos.flush();
            }  
            fos.close();
        } catch (FileNotFoundException e) {  
            // e.printStackTrace();  
        } catch (IOException e) {  
            // e.printStackTrace();  
        }  
    } 
    public static void shootView(View view, File filePath) {
    	PrintScreen.savePic(PrintScreen.convertViewToBitmap(view),
    			filePath);
    }
    public static void shoot(Activity a, File filePath) {  
        if (filePath == null) {  
            return;  
        }  
        PrintScreen.savePic(PrintScreen.takeScreenShot(a), filePath);  
    }  
    /**
     * ��ȡscrollview����Ļ
     * **/
    public static Bitmap getBitmapByView(ScrollView scrollView, File filePath) {
        int h = 0;
        Bitmap bitmap = null;
        // ��ȡlistViewʵ�ʸ߶�
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundResource(R.drawable.ic_launcher);
        }
        Log.d(TAG, "ʵ�ʸ߶�:" + h);
        Log.d(TAG, " �߶�:" + scrollView.getHeight());
        // ������Ӧ��С��bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        // �������
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        return bitmap;
    }
 
    private static String TAG = "Listview and ScrollView item ��ͼ:";
 
    /**
     *  ��ͼlistview
     * **/
    public static Bitmap getbBitmap(ListView listView, File filePath) {
        int h = 0;
        Bitmap bitmap = null;
        // ��ȡlistViewʵ�ʸ߶�
        for (int i = 0; i < listView.getChildCount(); i++) {
            h += listView.getChildAt(i).getHeight();
        }
        Log.d(TAG, "ʵ�ʸ߶�:" + h);
        Log.d(TAG, "list �߶�:" + listView.getHeight());
        // ������Ӧ��С��bitmap
        bitmap = Bitmap.createBitmap(listView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        listView.draw(canvas);
        // �������
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        return bitmap;
    }
}
