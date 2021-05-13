# demo1
idus demo1

1. demo1 실행방법
<br>
2. Code Download Zip 
<br>
3. 원하는 경로에 압축파일 해제.
<br>
4. DB 초기작업 진행
<br> 4-1. demo1/db_init 경로로 이동하여다 4-2,4-3 수행.
<br> 4-2. DDL.sql : Table 생성 script 실행.
<br> 4-3. make_data.sql : 샘플 Data를 적재.
<br> 4-4. DML.sql : 테이블 데이터 확인용, DCL.sql : 계정생성, 권한용 )
<br>
5. DB 설정 변경
<br> 5-1. /demo1/src/main/resources/application.properties 파일열어 id/password 수정
<br>
6. 실행방법 (압축해제 실행디렉토리 이동 -> /demo1-ho/demo1/gradlew.bat bootRun -> http://localhost:8080/ -> Api Test
<br> 6-1. demo1/ 밑에 gradlew(.bat) 파일 확인
<br> 6-2. gradlew.bat bootRun
<br> 6-3. 브라우저를 열어 http://localhost:8080/ 접속
<br> 6-4. swagger-ui를 통해 API 테스트 가능