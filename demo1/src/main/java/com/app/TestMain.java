package com.app;

import com.app.base.util.CryptoUtil;
import com.app.cust.util.CustUtil;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(CustUtil.nmCheck("1"));
        System.out.println(CryptoUtil.sha256("iDus1234!@#$"));
    }
}
