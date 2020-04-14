
DROP TABLE IF EXISTS `development`.`employee`;
CREATE TABLE  `development`.`employee` (
  `EMP_NO` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `EMP_NAME` varchar(45) NOT NULL,
  `EMP_EMAIL` varchar(45) NOT NULL,
  PRIMARY KEY (`EMP_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


===========================================================
DELIMITER $$

DROP PROCEDURE IF EXISTS `EMPLOYEE_INSERT` $$
CREATE DEFINER=`root`@`%` PROCEDURE `EMPLOYEE_INSERT`(IN EMP_NO INT(3)
                                                  ,IN EMP_NAME VARCHAR(45)
                                                  ,IN EMP_EMAIL VARCHAR(45)
                                                 )
BEGIN
   INSERT INTO EMPLOYEE(EMP_NO,EMP_NAME,EMP_EMAIL)VALUES(EMP_NO,EMP_NAME,EMP_EMAIL);
END $$

DELIMITER ;
=================================================================
DELIMITER $$

DROP PROCEDURE IF EXISTS `EMPLOYEE_SELECT` $$
CREATE DEFINER=`root`@`%` PROCEDURE `EMPLOYEE_SELECT`(IN VAR_EMP_ID INT(3)
                                                     ,OUT VAR_EMP_NO BIGINT(3)
                                                    ,OUT VAR_EMP_NAME VARCHAR(45)
                                                   ,OUT VAR_EMP_EMAIL VARCHAR(45)
                                                 )
BEGIN
       SELECT EMP_NO ,EMP_NAME ,EMP_EMAIL
       INTO VAR_EMP_NO ,VAR_EMP_NAME ,VAR_EMP_EMAIL
       FROM EMPLOYEE
       WHERE EMP_NO =  VAR_EMP_ID;
END $$

DELIMITER ;
===============================================================
DELIMITER $$

DROP PROCEDURE IF EXISTS `EMPLOYEE_UPDATE` $$
CREATE DEFINER=`root`@`%` PROCEDURE `EMPLOYEE_UPDATE`(IN EMP_ID INT(3)
                                                  ,IN EMP_NAME VARCHAR(45)
                                                  ,IN EMP_EMAIL VARCHAR(45)
                                                 )
BEGIN
         UPDATE EMPLOYEE SET EMP_NAME= EMP_NAME ,EMP_EMAIL=EMP_EMAIL WHERE EMP_NO = EMP_ID;
END $$

DELIMITER ;
===========================================================
DELIMITER $$

DROP PROCEDURE IF EXISTS `EMPLOYEE_DELETE` $$
CREATE DEFINER=`root`@`%` PROCEDURE `EMPLOYEE_DELETE`(IN EMP_ID INT(3))
BEGIN
       DELETE FROM EMPLOYEE WHERE EMP_NO = EMP_ID;
END $$

DELIMITER ;