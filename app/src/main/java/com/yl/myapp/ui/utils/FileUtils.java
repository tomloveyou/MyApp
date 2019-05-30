package com.yl.myapp.ui.utils;

import android.content.Context;
import android.text.TextUtils;

import com.yl.myapp.bean.ControlBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;

/**
 * Created by LC on 2017/2/23.
 */

public class FileUtils {
    public static MediaType getImageMediaTypeFromFileName(String fileName) {
        if (!TextUtils.isEmpty(fileName)) {
            int index = fileName.lastIndexOf(".");
            if (index > 0) {
                String fileType = fileName.substring(index, fileName.length());
                switch (fileType) {
                    case ".png":
                        return MediaType.parse("image/png");
                    case ".jpg":
                    case ".jpeg":
                        return MediaType.parse("image/jpeg");
                    case ".gif":
                        return MediaType.parse("image/gif");
                    case ".bmp":
                        return MediaType.parse("image/bmp");
                    case ".wbmp":
                        return MediaType.parse("image/vnd.wap.wbmp");
                }
            }
        }
        return MediaType.parse("application/octet-stream");
    }
    public static List<ControlBean> TxtToBean(Context context) {


        List newList=new ArrayList<ControlBean>();
        try {

            InputStreamReader isr = new InputStreamReader(context.getResources().getAssets().open("cgpermisison.txt"));
            BufferedReader br = new BufferedReader(isr);
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                if (!"".equals(lineTxt)) {
                    String reds[] = lineTxt.split("\\+");  //java 正则表达式
                    ControlBean controlBean=new ControlBean();
                    controlBean.setRESOURCE_ID(reds[0]);
                    controlBean.setRESOURCE_NAME(reds[1]);
                    controlBean.setRESOURCE_CODE(reds[2]);
                    controlBean.setRESOURCE_TYPE(reds[3]);
                    controlBean.setURL(reds[4]);
                    controlBean.setPID(reds[5]);
                    controlBean.setLEVEL_NO(reds[6]);
                    controlBean.setSORT_NO(reds[7]);
                    controlBean.setDESCRIPTION(reds[8]);
                    controlBean.setPNAME(reds[9]);
                    controlBean.setLOCATION_FLAG(reds[10]);
                    controlBean.setRESOURCE_CLASS(reds[11]);
                    controlBean.setENABLED(reds[12]);
                    controlBean.setAPP_ID(reds[13]);

                    controlBean.setRRID(reds[14]);
                    controlBean.setROLE_ID(reds[15]);
                    controlBean.setRESOURCE_ID(reds[16]);
                    controlBean.setURID(reds[17]);
                    controlBean.setUSER_ID(reds[18]);
                    controlBean.setROLE_ID(reds[19]);
                    newList.add(controlBean);
                }
            }
            isr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newList;
    }
}
