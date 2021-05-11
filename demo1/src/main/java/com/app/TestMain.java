package com.app;

import com.app.base.util.CryptoUtil;
import com.app.cust.util.CustUtil;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(CustUtil.nmCheck("1"));
        System.out.println(CryptoUtil.sha256("iDus1234!@#$"));
        
        String a = "ZXlKaGNtY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5.ZXlKcFpDSTZJbHBhTURBd01EQXdNREVpTENKbGJXRnBiQ0k2SWsxQlNVd3dNREF4UUdsa2RYTXVZMjl0SWl3aWFYTnpkV1ZrWDJGMElqb3hOakl3TmpRMk9EVTJNamc1TENKbGVIQnBjbVZ6WDJGMElqb3dmUT09.374ca6f088a84c914f36f7edf440f60036c15d67cc566227f133fc292c194be6";
        String b[] = a.split("\\.");
        
        System.out.println(b.length);
                
        System.out.println(b[0]);
        System.out.println(b[1]);
        System.out.println(b[2]);
    }
}
