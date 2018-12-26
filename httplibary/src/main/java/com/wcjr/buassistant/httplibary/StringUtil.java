package com.wcjr.buassistant.httplibary;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author wgw
 * @time 2018/12/21 12:24
 * @class describe
 */
public class StringUtil {
    /**
     * 输入流转字符串（速度最快）
     * @param inputStream
     * @return
     */
    public static String getStringFromStream(InputStream inputStream){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String str = "";
        try {
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = inputStream.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes,0,len);
            }
            str = byteArrayOutputStream.toString("utf-8");
        }catch (Exception e){

        }
        return str;
    }
}
