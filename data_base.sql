DROP TABLE IF EXISTS USERAPP CASCADE;
DROP TABLE IF EXISTS BLOG CASCADE;
DROP TABLE IF EXISTS BLOG CASCADE;
DROP TABLE IF EXISTS COMMENT CASCADE;
DROP TABLE IF EXISTS TEST CASCADE;
DROP TABLE IF EXISTS TEXT_CONTENT CASCADE;
DROP TABLE IF EXISTS QUESTION CASCADE;
DROP TABLE IF EXISTS USERAPP_QUESTION CASCADE;
DROP TABLE IF EXISTS MENU CASCADE;

CREATE TABLE USERAPP(
  ID SERIAL NOT NULL,
  USERAPPNAME VARCHAR(30) NOT NULL,
  NAME VARCHAR(200) NOT NULL,
  LASTNAME VARCHAR(200) NOT NULL,
  EMAIL TEXT NOT NULL,
  PICTURE TEXT NULL,
  SEX CHAR(1) NOT NULL,
  PASSWORD VARCHAR(50000) NOT NULL
);

ALTER TABLE USERAPP ADD CONSTRAINT PK_USERAPP PRIMARY KEY(ID);
ALTER TABLE USERAPP ADD CONSTRAINT CK_USERAPP_SEX CHECK(SEX IN('M','F'));

CREATE TABLE BLOG(
  ID SERIAL NOT NULL,
  TITLE VARCHAR(500) NOT NULL,
  F_CREATE TIMESTAMP NULL DEFAULT CURRENT_DATE,
  AUTHOR_USERAPP_ID INTEGER NOT NULL,
  DESCRIPTION VARCHAR(100) NOT NULL,
  CONTENT TEXT NOT NULL
);

ALTER TABLE BLOG ALTER COLUMN DESCRIPTION TYPE VARCHAR(300);

ALTER TABLE BLOG ADD CONSTRAINT PK_BLOG PRIMARY KEY(ID);
ALTER TABLE BLOG ADD CONSTRAINT FK_BLOG_USERAPP FOREIGN KEY(AUTHOR_USERAPP_ID) REFERENCES USERAPP(ID);


CREATE TABLE COMMENT(
  ID SERIAL NOT NULL,
  CONTENT VARCHAR(1000) NOT NULL,
  AUTHOR_USERAPP_ID INTEGER NOT NULL,
  BLOG_ID INTEGER NULL,
  F_CREATE TIMESTAMP NULL DEFAULT CURRENT_DATE,
  COMMENT_ID INTEGER NULL
);
ALTER TABLE COMMENT ADD CONSTRAINT PK_COMMENT PRIMARY KEY(ID);
ALTER TABLE COMMENT ADD CONSTRAINT FK_COMMENT_BLOG FOREIGN KEY(BLOG_ID) REFERENCES BLOG(ID);
ALTER TABLE COMMENT ADD CONSTRAINT FK_COMMENT_USERAPP FOREIGN KEY(AUTHOR_USERAPP_ID) REFERENCES USERAPP(ID);
ALTER TABLE COMMENT ADD CONSTRAINT FK_COMMENT_COMMENT FOREIGN KEY(COMMENT_ID) REFERENCES COMMENT(ID);

CREATE TABLE TEST (
  ID SERIAL NOT NULL,
  NAME VARCHAR(200) NOT NULL,
  F_CREATE TIMESTAMP NULL DEFAULT CURRENT_DATE,
  BLOG_ID INTEGER NOT NULL,
  CONTENT TEXT NOT NULL
);

ALTER TABLE TEST ADD CONSTRAINT PK_TEST PRIMARY KEY(ID);
ALTER TABLE TEST ADD CONSTRAINT FK_TEST_BLOG FOREIGN KEY(BLOG_ID) REFERENCES BLOG(ID);
ALTER TABLE TEST DROP COLUMN CONTENT;

CREATE TABLE QUESTION (
  ID SERIAL NOT NULL,
  TEST_ID INTEGER NOT NULL,
  CONTENT TEXT NOT NULL,
  POINTS NUMERIC(5,2) NOT NULL,
  F_CREATE DATE NULL DEFAULT CURRENT_DATE,
  TYPE VARCHAR(150) NOT NULL
);
ALTER TABLE QUESTION ADD CONSTRAINT CK_QUESTION_TYPE CHECK(TYPE IN('OPEN','CLOSE','TRUE/FALSE'));
ALTER TABLE QUESTION ADD CONSTRAINT PK_QUESTION PRIMARY KEY(ID);
ALTER TABLE QUESTION ADD CONSTRAINT FK_QUESTION_TEST FOREIGN KEY(TEST_ID) REFERENCES TEST(ID);

CREATE TABLE USERAPP_QUESTION (
  ID SERIAL NOT NULL,
  QUESTION_ID INTEGER NOT NULL,
  USERAPP_ID INTEGER NOT NULL,
  ANSWER TEXT NULL,
  EARNED_POINTS NUMERIC(5,2) NULL,
  DATE_OF_GRADES DATE NULL
);
ALTER TABLE USERAPP_QUESTION ADD CONSTRAINT PK_USERAPP_QUESTION PRIMARY KEY(ID);
ALTER TABLE USERAPP_QUESTION ADD CONSTRAINT FK_USERAPP_QUESTION_USERAPP FOREIGN KEY(USERAPP_ID) REFERENCES USERAPP(ID);
ALTER TABLE USERAPP_QUESTION ADD CONSTRAINT FK_USERAPP_QUESTION_QUESTION FOREIGN KEY(QUESTION_ID) REFERENCES QUESTION(ID);


CREATE TABLE MENU(
  ID INTEGER NOT NULL,
  MENU_ID INTEGER NULL,
  NAME VARCHAR(100) NOT NULL,
  PAGE VARCHAR(500) NULL,
  ICON VARCHAR(100) NOT NULL,
  GROUP_ID INTEGER NOT NULL,
  POSITION INTEGER NOT NULL
);
ALTER TABLE MENU ADD CONSTRAINT PK_MENU PRIMARY KEY(ID);
ALTER TABLE MENU ADD CONSTRAINT FK_MENU_MENU FOREIGN KEY(MENU_ID) REFERENCES MENU(ID);



INSERT INTO MENU (ID,MENU_ID,NAME,PAGE,ICON,GROUP_ID,POSITION)VALUES
(1,NULL,'Inicio','inicio/inicioSistema','home',1,1),
(2,NULL,'Blogs',null,'document',1,2),
(3,2,'Nuevo blog','blog/newblog','newwin',2,1),
(4,2,'Mis blogs','blog/misblogs','document-b',2,2),
(5,NULL,'Evaluaciones',null,'copy',1,3),
(6,5,'Presentadas','test/submitted','pencil',3,1),
(7,5,'Creadas','test/created','search',3,2);
