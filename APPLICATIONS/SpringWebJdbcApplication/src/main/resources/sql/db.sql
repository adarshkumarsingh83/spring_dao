CREATE TABLE IF NOT EXISTS `person` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) DEFAULT NULL,
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `MONEY` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

INSERT INTO `person`
VALUES (1,'Adarsh','kumar',5000)
      ,(2,'Amit','kumar',3000)
      ,(3,'Radha','Singh',2000);

