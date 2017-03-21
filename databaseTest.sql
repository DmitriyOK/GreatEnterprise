USE worktime;

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