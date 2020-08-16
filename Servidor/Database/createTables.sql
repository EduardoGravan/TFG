-- Table Employee
CREATE TABLE Employee
(
    email TEXT NOT NULL,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    is_admin boolean NOT NULL,
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY (email)
);

-- Table Schedule
CREATE TABLE Schedule
(
    email TEXT NOT NULL,
    date date NOT NULL,
    start_time time NOT NULL,
    end_time time NOT NULL,
    hours INTEGER NOT NULL,
    CONSTRAINT PK_Schedule PRIMARY KEY (email, date),
    CONSTRAINT FK_Schedule_Employee FOREIGN KEY (email) REFERENCES Employee (email)
);

-- Table Attendance
CREATE TABLE Attendance
(
    email TEXT NOT NULL,
    date date NOT NULL,
    arrived_time time,
    left_time time,
    hours_worked INTEGER,
    CONSTRAINT PK_Attendance PRIMARY KEY (email, date),
    CONSTRAINT FK_Attendance_Schedule FOREIGN KEY (email, date) REFERENCES Schedule (email, date)
);
