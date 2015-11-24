DROP TABLE IF EXISTS `employee`;
CREATE TABLE  `employee` (
  `Emp_no` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Emp_Name` varchar(45) NOT NULL,
  `Emp_Email` varchar(45) NOT NULL,
  PRIMARY KEY (`Emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `employee` VALUES (1,'Adarsh','adarsh@kumar'),(2,'Radha','radha@singh');
