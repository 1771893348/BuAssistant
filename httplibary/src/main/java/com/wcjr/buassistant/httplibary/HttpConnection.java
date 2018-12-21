package com.wcjr.buassistant.httplibary;

import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author wgw
 * @time 2018/12/21 9:22
 * @class describe
 */
public class HttpConnection {

    private void HttpQuestPost(String path,byte[] params,String httpType){
        try {
            HttpURLConnection connection = getConnection(path,httpType);
            connection.connect();
            if (params!=null){
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.write(params);
                dataOutputStream.flush();
                dataOutputStream.close();
            }

            if (connection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();

                String str = turnStreamToString(inputStream);
                inputStream.close();

            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private String turnStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes))!=-1){
            byteArrayOutputStream.write(bytes,0,len);
        }
        String reponse = byteArrayOutputStream.toString("utf-8");
        byteArrayOutputStream.close();
        return reponse;
    }
    private HttpURLConnection getConnection(String path,String type) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (type.toUpperCase().equals("POST")){
            connection.setRequestMethod("POST");
        }else {
            connection.setRequestMethod("GET");
        }
        connection.setRequestProperty("Content-type","application/json");
        connection.addRequestProperty("Connection","Keep-Alive");
        connection.setConnectTimeout(10*1000);
        connection.setReadTimeout(10*1000);
        connection.setUseCaches(true);
        return connection;
    }
}
