DROP DATABASE IF EXISTS worktime;
CREATE DATABASE worktime;
USE worktime;

CREATE TABLE department (

  id             INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  departmentName VARCHAR(32)  NOT NULL UNIQUE
)
  ENGINE = InnoDB;

CREATE TABLE employer (

  id           INT(11)     AUTO_INCREMENT PRIMARY KEY,
  departmentId INT(11)     NOT NULL,
  login        VARCHAR(32) NOT NULL UNIQUE,
  password     VARCHAR(32) NOT NULL,
  firstName    VARCHAR(32) NOT NULL,
  lastName     VARCHAR(32) NOT NULL,

  FOREIGN KEY (departmentId) REFERENCES department (id)
)
  ENGINE = InnoDB;

CREATE TABLE employerWorkDay (

  id             INT(11) AUTO_INCREMENT PRIMARY KEY,
  employerId     INT(11) NOT NULL,
  startTime      DATETIME,
  finishTime     DATETIME,
  unixStartTime  INT(11),
  unixFinishTime INT(11),
  isOnline         TINYINT DEFAULT FALSE,

  FOREIGN KEY (employerId) REFERENCES employer (id)
)
  ENGINE = InnoDB;

CREATE INDEX employer_login_index ON employer (login);

SET GLOBAL event_scheduler = ON;
DELIMITER $$

CREATE
EVENT `startEmployerWorkDay`
  ON SCHEDULE EVERY 1 DAY STARTS '2017-03-16 03:00:00'
DO BEGIN
  INSERT INTO employerworkday(employerId, startTime, unixStartTime)
    SELECT employer.id, now(), unix_timestamp(now())
    FROM employer;

END $$

DELIMITER ;
