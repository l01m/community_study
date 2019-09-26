## 社区

## 资料
[Spring 文档](https://spring.io/guides)  
[Spring web文档](https://spring.io/guides/gs/serving-web-content/)  
[配置SSH key](https://elasticsearch.cn/https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)  
[bootstrap 文档](https://v3.bootcss.com/)  
[GitHub OAuth DOC](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
## 工具
[Git](https://git-scm.com/downloads)  
[visual Paradigm](https://www.visual-paradigm.com)  
[H2 DataBase](http://h2database.com/html/main.html)  
[maven repository](https://mvnrepository.com/)  
[mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)  
[flyway](flywaydb.org/getstarted/why)
## 脚本
```sql
create table USER
(
	ID INTEGER default (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_6A50173C_2EF2_4385_A7F9_BEA8B0A8FBBF) auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	BIO VARCHAR(256),
	constraint USER_PK
		primary key (ID)
);
```
