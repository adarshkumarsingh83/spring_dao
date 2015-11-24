DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS employee (
  EMP_NO int(11) NOT NULL auto_increment,
  EMP_DEPT varchar(20) NULL,
  EMP_DOB date  NULL,
  EMP_EMAIL varchar(20) NOT NULL,
  EMP_NAME varchar(20) NOT NULL,
  PRIMARY KEY  (EMP_NO),
  UNIQUE KEY EMP_NO (EMP_NO),
  UNIQUE KEY EMP_EMAIL (EMP_EMAIL)
);

INSERT INTO employee VALUES (1,'IT','1983-09-13','adarsh@kumar','adarsh kumar'),(2,'it','1986-05-05','radha@singh','radha singh');