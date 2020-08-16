-- Activate foreign key constraints
PRAGMA foreign_keys=on;

BEGIN TRANSACTION;

-- Employee population
INSERT INTO Employee VALUES('admin@tfg.net', 'admin', 'admin', 1);
INSERT INTO Employee VALUES('edu@tfg.net', 'edu', 'edu', 0);

-- Schedule population
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-01', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-02', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-03', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-04', '09:00:00', '17:00:00', 8);
INSERT INTO Schedule VALUES('edu@tfg.net', '2020-08-05', '09:00:00', '17:00:00', 8);

-- Attendance population
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-01', '08:59:30', '17:00:30', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-02','09:30:00', '17:00:00', 7);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-03', null, null, null);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-04', '08:59:30', '17:00:30', 8);
INSERT INTO Attendance VALUES('edu@tfg.net', '2020-08-05', '08:59:30', '17:00:30', 8);

COMMIT;