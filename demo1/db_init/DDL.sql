CREATE TABLE `tb_cust_base` (
  `CUST_NO` char(10) NOT NULL COMMENT '고객번호',
  `CUST_GB` char(1) NOT NULL DEFAULT 'Z' COMMENT '고객구분 (1:개인,2:기업,3:외국인,Z:Default)',
  `NM` varchar(20) NOT NULL COMMENT '이름',
  `ALIAS` varchar(30) NOT NULL COMMENT '별칭',
  `PWD` varchar(200) NOT NULL COMMENT '비밀번호',
  `TEL_NO` varchar(20) NOT NULL COMMENT '전화번호',
  `EMAIL` varchar(100) NOT NULL COMMENT '이메일',
  `SEX` char(1) DEFAULT NULL COMMENT '성별',
  PRIMARY KEY (`CUST_NO`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='고객정보'
;

CREATE TABLE `tb_setl_base` (
  `ORD_NO` char(12) NOT NULL COMMENT '주문번호',
  `SEL_CUST_NO` varchar(10) NOT NULL COMMENT '판매고객번호',
  `BUY_CUST_NO` varchar(10) NOT NULL COMMENT '구매고객번호',
  `PDT_NM` varchar(100) NOT NULL COMMENT '제품명',
  `STL_DATE` datetime NOT NULL COMMENT '결제시간 (GMT 표준시)',
  PRIMARY KEY (`ORD_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='결제정보'
;

CREATE TABLE `tb_base_key_make` (
  `KEY_CD` char(8) NOT NULL COMMENT 'KEY 코드',
  `KEY_TXT` varchar(50) NOT NULL COMMENT 'KEY 텍스트',
  `SER_NO` int NOT NULL COMMENT '일련번호',
  `TBL_NM` varchar(30) DEFAULT NULL COMMENT '테이블 이름',
  PRIMARY KEY (`KEY_CD`,`KEY_TXT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='기본KEY생성'
;