package com.itheia.ssm.utils;

import sun.security.provider.MD5;

import java.util.Arrays;
import java.util.Base64;

public class Md5 {
    public static void main(String[] args) {
        String hei = MD5Utils.getMD5("你好");
        System.out.println(hei);
    }
}
