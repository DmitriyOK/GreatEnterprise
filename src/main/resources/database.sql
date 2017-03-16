DROP DATABASE IF EXISTS worktime;
CREATE DATABASE worktime;
USE worktime;

CREATE TABLE department (

  id             INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  departmentName VARCHAR(256) NOT NULL UNIQUE
)
  ENGINE = InnoDB;

CREATE TABLE employer (

  id        INT(11) AUTO_INCREMENT PRIMARY KEY,
  login     VARCHAR(32) NOT NULL UNIQUE,
  password  VARCHAR(32) NOT NULL,
  firstName VARCHAR(32) NOT NULL,
  lastName  VARCHAR(32) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE employerWorkDay (

  id             INT(11) AUTO_INCREMENT PRIMARY KEY,
  employerId     INT(11) NOT NULL,
  departmentId   INT(11) NOT NULL,
  startTime      DATETIME,
  finishTime     DATETIME,
  unixStartTime  INT(11),
  unixFinishTime INT(11),

  FOREIGN KEY (employerId) REFERENCES employer (id),
  FOREIGN KEY (departmentId) REFERENCES department (id)
)
  ENGINE = InnoDB;

INSERT INTO department (departmentName) VALUES ('Back - END ');
INSERT INTO department (departmentName) VALUES ('Front - END' );
INSERT INTO department (departmentName) VALUES ('QA');
INSERT INTO department (departmentName) VALUES ('HR');
INSERT INTO department (departmentName) VALUES ('Bookkeeping');

INSERT INTO employer (login, password, firstName, lastName) VALUES ('oborisov', 'test', 'Oleg', 'Borisov');
INSERT INTO employer (login, password, firstName, lastName) VALUES ('apetrova', 'test', 'Anna', 'Petrova');
INSERT INTO employer (login, password, firstName, lastName) VALUES ('vpopov', 'test', 'Viktor', 'Popov');
INSERT INTO employer (login, password, firstName, lastName) VALUES ('slisanov', 'test', 'Sergey', 'Lisanov');
INSERT INTO employer (login, password, firstName, lastName) VALUES ('ovikhina', 'test', 'Olga', 'Vikhina');
INSERT INTO employer (login, password, firstName, lastName) VALUES ('amororozov', 'test', 'Andrey', 'Morozov');
INSERT INTO employer (login, password, firstName, lastName) VALUES ('skrupina', 'test', 'Svetlana', 'Krupina');
INSERT INTO employer (login, password, firstName, lastName) VALUES ('mmorozova', 'test', 'Marina', 'Morozova');
INSERT INTO employer (login, password, firstName, lastName) VALUES ('vorlov', 'test', 'Vladimir', 'Orlov');
INSERT INTO employer (login, password, firstName, lastName) VALUES ('zivanova', 'test', 'Zoya', 'Ivanova');

DROP PROCEDURE IF EXISTS loadTestData;

DELIMITER #
CREATE PROCEDURE loadTestData()
  BEGIN

    DECLARE v_max INT UNSIGNED DEFAULT 11;
    DECLARE v_counter INT UNSIGNED DEFAULT 1;

    TRUNCATE TABLE employerWorkDay;
    START TRANSACTION;
    WHILE v_counter < v_max DO
      INSERT INTO employerWorkDay (employerId, departmentId, startTime, unixStartTime)
      VALUES (v_counter, 1 + (FLOOR(RAND() * 5)), now(), UNIX_TIMESTAMP());
      SET v_counter = v_counter + 1;
    END WHILE;
    COMMIT;
  END #

DELIMITER ;

CALL loadTestData();

SELECT * FROM employerWorkDay;

SELECT * FROM employer;
SELECT * FROM department;

#Select all columns from all tables
SELECT
  department.id, departmentName,
  employer.id, login, password, firstName, lastName, departmentId,
  employerWorkDay.id, employerId, startTime, finishTime, unixStartTime, unixFinishTime
FROM employerWorkDay
  JOIN department ON employerWorkDay.departmentId = department.id
  JOIN employer ON employerWorkDay.employerId = employer.id;

UPDATE employerWorkDay
SET finishTime = now(), unixFinishTime = UNIX_TIMESTAMP()
WHERE employerWorkDay.id = 1;

#Select count employers by department
SELECT
  departmentName,COUNT(*) AS 'Employers'
FROM employerWorkDay
  JOIN employer ON employerWorkDay.employerId = employer.id
  JOIN department ON employerWorkDay.departmentId = department.id
GROUP BY departmentName;

#Select employers for daily reports
SELECT
  firstName, lastName, startTime, finishTime, departmentName
FROM employerworkday
  JOIN department ON employerWorkDay.departmentId = department.id
  JOIN employer ON employerWorkDay.employerId = employer.id;