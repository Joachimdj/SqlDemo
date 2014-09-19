DROP TABLE Person;
CREATE TABLE PERSON (
 ID INTEGER NOT NULL,
 FIRST_NAME VARCHAR(50),
 LAST_NAME VARCHAR(50),
 PRIMARY KEY (ID)
);
insert into Person values(100,'Peter','Hansen');
insert into Person values(110,'Lone','Hansen');
insert into Person values(120,'John','McDonald');
insert into Person values(130,'Peter','Jensen');