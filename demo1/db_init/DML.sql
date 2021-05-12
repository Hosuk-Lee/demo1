SELECT  CUST_NO
        ,CUST_GB
        ,NM
        ,ALIAS
        ,PWD
        ,TEL_NO
        ,EMAIL
        ,SEX
FROM    TB_CUST_BASE CUST
WHERE   1=1
;

 SELECT ORD_NO
	   ,SEL_CUST_NO
	   ,BUY_CUST_NO
	   ,PDT_NM
	   ,STL_DATE
FROM   TB_SETL_BASE
;


SELECT  KEY_CD 
		,KEY_TXT
        ,SER_NO
        ,TBL_NM
FROM    TB_BASE_KEY_MAKE
WHERE   1=1
;
