-- Activate foreign key constraints
PRAGMA foreign_keys=on;

BEGIN TRANSACTION;

-- Employee population
INSERT INTO Employee VALUES('admin@tfg.net', 'admin', 'admin', 1);
INSERT INTO Employee VALUES('edu@tfg.net', 'edu', 'edu', 0);

-- Schedule population
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-01', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-02', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-03', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-06', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-07', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-08', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-09', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-10', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-13', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-14', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-15', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-16', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-17', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-20', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-21', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-22', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-23', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-24', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-27', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-28', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-29', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-30', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-06-31', '09:00:00', '17:00:00', 8);

INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-01', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-02', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-03', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-06', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-07', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-08', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-09', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-10', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-13', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-14', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-15', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-16', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-17', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-20', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-21', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-22', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-23', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-24', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-27', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-28', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-29', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-30', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-07-31', '09:00:00', '17:00:00', 8);

INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-01', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-02', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-03', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-04', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-05', '09:00:00', '17:00:00', 8);

-- Attendance population
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-01', '10:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-02', null, null, null);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-03', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-06', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-07', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-08', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-09', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-10', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-13', null, null, null);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-14', '09:00:00', '18:00:00', 9);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-15', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-16', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-17', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-20', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-21', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-22', '08:00:00', '17:00:00', 9);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-23', '08:00:00', '17:00:00', 9);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-24', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-27', null, null, null);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-28', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-29', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-30', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-06-31', '09:00:00', '17:00:00', 8);

INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-01', '09:00:00', '18:00:00', 9);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-02', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-03', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-06', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-07', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-08', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-09', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-10', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-13', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-14', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-15', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-16', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-17', '09:00:00', '19:00:00', 10);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-20', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-21', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-22', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-23', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-24', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-27', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-28', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-29', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-30', '09:00:00', '17:00:00', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-07-31', '09:00:00', '17:00:00', 8);

INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-01', '08:59:30', '17:00:30', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-02','09:30:00', '17:00:00', 7);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-03', null, null, null);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-04', '08:59:30', '17:00:30', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-05', '08:59:30', '17:00:30', 8);

COMMIT;