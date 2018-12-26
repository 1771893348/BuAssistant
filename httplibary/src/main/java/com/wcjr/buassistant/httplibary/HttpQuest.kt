package com.wcjr.buassistant.httplibary

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.lang.StringBuilder


/**
 * @author wgw
 * @time 2018/12/21 9:19
 * @class describe
 */
class HttpQuest(){
    fun httpConnection(path:String,param:ByteArray){
        var url: URL = URL(path)
        var urlConnection:HttpURLConnection = url.openConnection() as HttpURLConnection
        urlConnection.connectTimeout=10*10000
        urlConnection.readTimeout = 10*1000
        urlConnection.useCaches = true
        urlConnection.requestMethod = "POST"
        urlConnection.setRequestProperty("Content-type","application/json")
        urlConnection.addRequestProperty("Connection","Keep-Alive")
        urlConnection.doInput=true
        urlConnection.doOutput = true
        urlConnection.connect()
        var inputString = urlConnection.getInputStream()
        var outputStream = urlConnection.getOutputStream()
        outputStream.write(param)
        if (urlConnection.responseCode == 200){
            var reponse = streamToString(inputString)
        }
    }

    fun streamToString(inputStream: InputStream):String{

        var sb = StringBuilder()
        var bsr = InputStreamReader(inputStream)
        var br = BufferedReader(bsr)
        var line = br.readLine()
        while (line != null){
            sb.append(line)
        }
        return sb.toString()
    }
    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
//    fun streamToString(ls: InputStream): String? {
//        try {
//            val baos = ByteArrayOutputStream()
//            val buffer = ByteArray(1024)
//            var len = 0
//            while ((len = ls.read(buffer)) != -1) {
//                baos.write(buffer, 0, len)
//            }
//            baos.close()
//            ls.close()
//            val byteArray = baos.toByteArray()
//            return String(byteArray)
//        } catch (e: Exception) {
////            Log.e(TAG, e.toString())
//            return null
//        }
//
//    }
}