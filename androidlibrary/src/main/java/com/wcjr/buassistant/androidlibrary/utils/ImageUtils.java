package com.wcjr.buassistant.androidlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author wgw
 * @time 2018/10/25 14:32
 * @class describe
 */
public class ImageUtils {
    public static Bitmap bitmapCompress(String imagePath){
        //设置参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只获取图片大小信息,而不是将整张图片载入在内存中，避免内存溢出
        BitmapFactory.decodeFile(imagePath,options);
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 2;//默认像素压缩比例，压缩为原图的1/2
        int maxLen = Math.max(height, width);// 原图的最小边长
        if (maxLen >100){
            float ratio = (float)maxLen/100.0f;// 计算像素压缩比例
            inSampleSize = (int)ratio;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap bm = BitmapFactory.decodeFile(imagePath,options);
        return bm;

    }
    public static void btmCompress(String imagePath,ImageView imageView){
        // 设置参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 只获取图片的大小信息，而不是将整张图片载入在内存中，避免内存溢出
        BitmapFactory.decodeFile(imagePath, options);
        int height = options.outHeight;
        int width= options.outWidth;
        int inSampleSize = 2; // 默认像素压缩比例，压缩为原图的1/2
        int minLen = Math.min(height, width); // 原图的最小边长
        if(minLen > 100) { // 如果原始图像的最小边长大于100dp（此处单位我认为是dp，而非px）
            float ratio = (float)minLen / 100.0f; // 计算像素压缩比例
            inSampleSize = (int)ratio;
        }
        options.inJustDecodeBounds = false; // 计算好压缩比例后，这次可以去加载原图了
        options.inSampleSize = inSampleSize; // 设置为刚才计算的压缩比例
        Bitmap bm = BitmapFactory.decodeFile(imagePath, options); // 解码文件
        Log.w("TAG", "size: " + bm.getByteCount() + " width: " + bm.getWidth() + " heigth:" + bm.getHeight()); // 输出图像数据
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageBitmap(bm);
    }
}
