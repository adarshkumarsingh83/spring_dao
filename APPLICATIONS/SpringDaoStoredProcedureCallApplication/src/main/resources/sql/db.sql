DROP TABLE IF EXISTS `employee`;
CREATE TABLE  `employee` (
  `EMP_NO` int(11) NOT NULL AUTO_INCREMENT,
  `EMP_DEPT` varchar(20) DEFAULT NULL,
  `EMP_DOB` date DEFAULT NULL,
  `EMP_EMAIL` varchar(20) NOT NULL,
  `EMP_NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`EMP_NO`),
  UNIQUE KEY `EMP_NO` (`EMP_NO`),
  UNIQUE KEY `EMP_EMAIL` (`EMP_EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

INSERT INTO employee(EMP_NO,EMP_DEPT,EMP_DOB,EMP_EMAIL,EMP_NAME)
VALUES (1,'IT','1983-09-13','adarsh@kumar','adarsh kumar')
       ,(2,'it','1986-05-05','radha@singh','radha singh')
       ,(3,'it','1986-05-05','radha@@singh','radha singh');