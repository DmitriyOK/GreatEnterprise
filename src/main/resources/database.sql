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

  FOREIGN KEY (employerId) REFERENCES employer (id)
)
  ENGINE = InnoDB;

INSERT INTO department (departmentName) VALUES ('Back-End');
INSERT INTO department (departmentName) VALUES ('Front-End' );
INSERT INTO department (departmentName) VALUES ('QA');
INSERT INTO department (departmentName) VALUES ('HR');
INSERT INTO department (departmentName) VALUES ('Bookkeeping');

INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('oborisov', 'test', 'Oleg', 'Borisov',1);
INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('apetrova', 'test', 'Anna', 'Petrova',1);
INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('vpopov', 'test', 'Viktor', 'Popov',1);
INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('slisanov', 'test', 'Sergey', 'Lisanov',2);
INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('ovikhina', 'test', 'Olga', 'Vikhina',2);
INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('amororozov', 'test', 'Andrey', 'Morozov',2);
INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('skrupina', 'test', 'Svetlana', 'Krupina',3);
INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('mmorozova', 'test', 'Marina', 'Morozova',3);
INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('vorlov', 'test', 'Vladimir', 'Orlov',4);
INSERT INTO employer (login, password, firstName, lastName, departmentId) VALUES ('zivanova', 'test', 'Zoya', 'Ivanova',5);

DROP PROCEDURE IF EXISTS loadTestData;

DELIMITER #
CREATE PROCEDURE loadTestData()
  BEGIN

    DECLARE v_max INT UNSIGNED DEFAULT 11;
    DECLARE v_counter INT UNSIGNED DEFAULT 1;

    TRUNCATE TABLE employerWorkDay;
    START TRANSACTION;
    WHILE v_counter < v_max DO
      INSERT INTO employerWorkDay (employerId, startTime, unixStartTime)
      VALUES (v_counter, now(), UNIX_TIMESTAMP());
      SET v_counter = v_counter + 1;
    END WHILE;
    COMMIT;
  END #

DELIMITER ;

CALL loadTestData();

SELECT * FROM employerWorkDay;
#
# SELECT * FROM employer;
# SELECT * FROM department;
#
# #Select all columns from all tables
# SELECT
#   department.id, departmentName,
#   employer.id, login, password, firstName, lastName, departmentId,
#   employerWorkDay.id, employerId, startTime, finishTime, unixStartTime, unixFinishTime
# FROM employerWorkDay
#   JOIN department ON employerWorkDay.departmentId = department.id
#   JOIN employer ON employerWorkDay.employerId = employer.id;
#
# UPDATE employerWorkDay
# SET finishTime = now(), unixFinishTime = UNIX_TIMESTAMP()
# WHERE employerWorkDay.id = 1;
#
# #Select count employers by department
# SELECT
#   departmentName,COUNT(*) AS 'Employers'
# FROM employerWorkDay
#   JOIN employer ON employerWorkDay.employerId = employer.id
#   JOIN department ON employerWorkDay.departmentId = department.id
# GROUP BY departmentName;
#
# #Select employers for daily reports
# SELECT
#   firstName, lastName, startTime, finishTime, departmentName
# FROM employerworkday
#   JOIN department ON employerWorkDay.departmentId = department.id
#   JOIN employer ON employerWorkDay.employerId = employer.id;