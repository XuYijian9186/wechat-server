package com.yijian.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yijian on 2017/11/14.
 */
public class MessageUtils {

    public static String sha1(String string) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(string.getBytes());

        String content = byteToStr(digest);
        return content;
    }

    private static String byteToStr(byte[] digest){
        String strDigest="";
        for(int i=0;i<digest.length;i++){
            //将取得字符的二进制码转化为16进制码的的码数字符串
            strDigest+=byteToHexStr(digest[i]);
        }
        return strDigest;
    }

    public static String byteToHexStr(byte b){
        char[] Digit= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        char[] tempArr=new char[2];
        tempArr[0]=Digit[(b>>>4)&0X0F];//XXXX&1111那么得到的还是XXXX
        tempArr[1]=Digit[b&0X0F];//XXXX&1111那么得到的还是XXXX

        String s=new String(tempArr);
        return s;
    }

}
