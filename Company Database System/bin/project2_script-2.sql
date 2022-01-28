CREATE SCHEMA `Cleaning_Shop`;

USE `Cleaning_Shop`;

CREATE TABLE if not exists Person
(
	ID INT NOT NULL,
    First_Name VARCHAR(45),
    Last_Name VARCHAR(45),
    Email VARCHAR(45),
    Address VARCHAR(45),
    Phone VARCHAR(45),
    Date_Of_Birth DATE,
    
    PRIMARY KEY (ID)
);	

CREATE TABLE if not exists Employee
(
	Gender VARCHAR(1),
    Hired DATE,
    Terminate DATE,
    Position VARCHAR(45),
    Employee_ID INT NOT NULL,
    Salary DOUBLE,
    
    PRIMARY KEY (Employee_ID),
    FOREIGN KEY (Employee_ID) REFERENCES Person (ID)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists Customer
(
	CC VARCHAR(45),
    Balance DOUBLE,
    Customer_ID INT NOT NULL,
    CC_Date VARCHAR(45),
    CC_Code INT,
    
    PRIMARY KEY (Customer_ID)
);

ALTER TABLE Customer 
ADD FOREIGN KEY (Customer_ID) REFERENCES Person (ID)
ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE if not exists Work_Schedule
(
	Schedule_ID INT NOT NULL,
    Start_Day DATE,
    Start_Time TIME,
    End_Time TIME,
    Employee_ID INT NOT NULL,
    
    PRIMARY KEY (Schedule_ID, Employee_ID)
);

ALTER TABLE Work_Schedule 
ADD FOREIGN KEY (Employee_ID) REFERENCES Employee (Employee_ID)
ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE if not exists Supplier
(
	Supplier_ID INT NOT NULL,
    Supplier_Name VARCHAR(45),
    Address VARCHAR(45),
    Phone VARCHAR(45),
    Balance DOUBLE (10,2),
    Products VARCHAR(99),
    
    PRIMARY KEY (Supplier_ID)
);

CREATE TABLE if not exists Equipment
(
	Equipment_ID INT NOT NULL,
    Brand VARCHAR(45),
    Equipment_Type VARCHAR(45),
    Quantity INT,
    
    PRIMARY KEY (Equipment_ID)
);

CREATE TABLE if not exists Cleaning_Supplies
(
	Supplies_ID INT NOT NULL,
    Name VARCHAR(45),
    Description VARCHAR(45),
    Inventory INT,
    Required_Inventory INT,
    
	PRIMARY KEY (Supplies_ID)
);

CREATE TABLE if not exists Total_Usage
(
Year VARCHAR(4) NOT NULL,
Month VARCHAR(4) NOT NULL,
Usage_Hours INT,

Equipment_ID INT,
Supplies_ID INT,

PRIMARY KEY(Year, Month),
FOREIGN KEY (Equipment_ID) REFERENCES Equipment (Equipment_ID)
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (Supplies_ID) REFERENCES Cleaning_Supplies (Supplies_ID)
ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists Purchase
(
	Transaction_ID INT NOT NULL,
    Purchase_Date DATE,
    Quantity INT,
    Amount_Due DOUBLE (10,2),
    Due_Date DATE,
    Description VARCHAR(45),
    Delivery_Date DATE,
    Supplier_ID INT NOT NULL,
    Equipment_ID INT,
    Supplies_ID INT,
    
    PRIMARY KEY (Transaction_ID, Supplier_ID),
	FOREIGN KEY (Supplier_ID) REFERENCES Supplier (Supplier_ID)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Equipment_ID) REFERENCES Equipment (Equipment_ID)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Supplies_ID) REFERENCES Cleaning_Supplies (Supplies_ID)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists Maintenance_Schedule
(
	Maintenance_ID INT NOT NULL,
    Start_Day DATE,
    Start_Time TIME,
    Equipment_ID INT NOT NULL,
    Cost DOUBLE,
    
    PRIMARY KEY (Maintenance_ID),
    FOREIGN KEY (Equipment_ID) REFERENCES Equipment (Equipment_ID)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists Service_Request
(
	Request_ID INT NOT NULL,
    Date DATE,
    Amount DOUBLE(10,2),
    Notes VARCHAR(45),
    Satisfaction VARCHAR(45),
    Customer_ID INT,
    
    PRIMARY KEY (Request_ID)
);

ALTER TABLE Service_Request 
ADD FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID)
ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE if not exists Service
(
	Service_ID INT NOT NULL,
    Name VARCHAR(45),
    Description VARCHAR(45),
    Rate DOUBLE (10,2),
    Duration_Hours INT,
    Request_ID INT NOT NULL,
    Equipment_ID INT,
    Supplies_ID INT,
    
    PRIMARY KEY (Service_ID, Request_ID),
    FOREIGN KEY (Request_ID) REFERENCES Service_Request (Request_ID)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Equipment_ID) REFERENCES Equipment (Equipment_ID)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Supplies_ID) REFERENCES Cleaning_Supplies (Supplies_ID)
    ON DELETE CASCADE ON UPDATE CASCADE
);

/*
DROP TABLE Work_Schedule;
DROP TABLE Maintenance_Schedule;
DROP TABLE Employee;

DROP TABLE Service;
DROP TABLE Service_Request;

DROP TABLE Purchase;
DROP TABLE Total_Usage;
DROP TABLE Equipment;
DROP TABLE Cleaning_Supplies;

DROP TABLE Supplier;
DROP TABLE Customer;
DROP TABLE Person;

DROP SCHEMA Cleaning_Shop;
*/