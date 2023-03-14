package com.example.nacostest.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2023/3/14 19:25
 */
public class shiroAuth {

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","salt",3);
        System.out.println(md5Hash.toHex());
    }

}
