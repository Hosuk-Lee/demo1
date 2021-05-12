/* DATA BASE 생성 */
/*
show databases;  
create database HOSUK default character set utf8;  -- schema or database
show databases;  
*/

/* USER 생성 */
/*
create user 'hosuk'@'localhost' identified by 'hosuk';
*/

/* 권한 추가 */
/*
grant all on hosuk.* to hosuk@localhost;
FLUSH PRIVILEGES;
*/

select * from mysql.user;
