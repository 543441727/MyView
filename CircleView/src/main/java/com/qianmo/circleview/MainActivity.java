package com.qianmo.circleview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * bitmap 的二次采样
     *
     * @param filePath
     * @param newWidth
     * @param newHeight
     * @return
     */
    private Bitmap createImageThumbnail(String filePath, int newWidth, int newHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int oldWidth = options.outWidth;
        int oldHeight = options.outHeight;
        int ratioWidth = oldWidth / newWidth;
        int ratioHeight = oldHeight / newHeight;
        int ratioSize = ratioHeight > ratioWidth ? ratioWidth : ratioHeight;
        options.inSampleSize = ratioSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bm = BitmapFactory.decodeFile(filePath, options);
        return bm;
    }
}
