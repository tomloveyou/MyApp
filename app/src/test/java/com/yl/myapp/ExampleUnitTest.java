package com.yl.myapp;

import com.standards.library.util.LogUtil;
import com.standards.library.util.NumberUtils;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.print(getMaxLimitValue(new BigDecimal("113000000")));
    }

    private String getMaxLimitValue(BigDecimal vale) {
        BigDecimal bigDecimal = vale.divide(new BigDecimal("10000"));
        double maxYValue = bigDecimal.doubleValue();
        if (maxYValue > 0 && maxYValue < 10) {
            bigDecimal = new BigDecimal("10");
        } else if (maxYValue < 100) {
            bigDecimal = bigDecimal.add(new BigDecimal("10"));
        }else {
            int zeo = String.valueOf(bigDecimal.intValue()).length() - 2;
            String ad = "1";
            for (int i = 0; i < zeo; i++) {
                ad += "0";

            }
            bigDecimal = bigDecimal.add(new BigDecimal(ad));
        }
        String ad = bigDecimal.toString();
        if (ad.contains(".")){
            ad=ad.substring(0,ad.indexOf("."));
        }
        if (ad.length() > 3) {
            String pre = ad.substring(0, 2);
            String nd = ad.substring(2, ad.length());
            String ll="";
            for (int i = 0; i < nd.length(); i++) {
                ll+="0";
            }
            String str=pre+(nd.replace(nd,ll));
            return str;
        }else if (ad.length()>0&&ad.length()<=3){
            String pre = ad.substring(0, ad.length()-1);
            String nd = ad.substring(ad.length()-1, ad.length());
            String ll="";
            for (int i = 0; i < nd.length(); i++) {
                ll+="0";
            }
            String str=pre+(nd.replace(nd,ll));
            return str;
        }
        return (bigDecimal.toString());

    }

    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            sb.append("0").append(str);// 左补0
// sb.append(str).append("0");//右补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

}