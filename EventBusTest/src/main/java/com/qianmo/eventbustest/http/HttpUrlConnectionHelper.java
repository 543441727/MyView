package com.qianmo.eventbustest.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wangjitao on 2016/10/18 0018.
 * HttpUrlConnection链接
 */
public class HttpUrlConnectionHelper {

    /**
     * UrlConnection类
     *
     * @param url
     * @return
     */
    public byte[] getUrlConnectionInfor(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);

            InputStream isr = conn.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = isr.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }
}
