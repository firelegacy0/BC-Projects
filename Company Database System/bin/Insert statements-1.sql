USE `Cleaning_Shop`;

# ID, First_Name, Last_Name, Email, Address, Phone, Date_Of_Birth
INSERT INTO Person VALUES (1,'Robert','Main','robertmain@gmail.com','1409 Washington South','523-503-9310','1997-03-07');
INSERT INTO Person VALUES (2,'Alex','Fonte','alexfonte@gmail.com','1450 Washington South','633-132-9240','1992-04-03');
INSERT INTO Person VALUES (3,'Josh','Lambert','joshlambert@gmail.com','4913 Washington South','511-323-9310','1983-03-25');
INSERT INTO Person VALUES (4,'Rick','Wong','rickwong@gmail.com','6033 Washington South','631-763-9310','1967-07-20');
INSERT INTO Person VALUES (5,'Martin','Luke','martinluke@gmail.com','1809 Washington South','612-883-7650','1970-06-30');
INSERT INTO Person VALUES (6,'Bob','Ross','bobross@gmail.com','1250 Washington South','809-502-4330','2000-11-27');
INSERT INTO Person VALUES (7,'Adam','Ray','adamray@gmail.com','1610 Washington South','311-603-6410','1993-04-11');
INSERT INTO Person VALUES (8,'Sam','Smith','samsmith@gmail.com','1610 Washington South','345-603-7540','1943-10-01');
INSERT INTO Person VALUES (9,'John','Smith','johnsmith@gmail.com','1410 Washington South','496-613-7210','1993-10-11');
INSERT INTO Person VALUES (10,'Jason','Lu','jasonlu@gmail.com','6210 Washington South','697-603-7610','1987-10-21');
INSERT INTO Person VALUES (11,'Lee','Fight','leefight@gmail.com','2610 Washington South','361-633-4610','1978-11-14');
INSERT INTO Person VALUES (12,'Tom','Red','tomred@gmail.com','4610 Washington South','311-603-7645','1967-12-19');
INSERT INTO Person VALUES (13,'Nell','Hill','nellhill@gmail.com','5410 Washington South','321-456-7230','1993-01-15');
INSERT INTO Person VALUES (14,'Jack','Free','jackfree@gmail.com','1610 Washington South','777-777-7777','1953-10-22');

# Gender, Hired, Terminate, Position, Employee_ID, Salary
INSERT INTO Employee VALUES('M', '2020-12-14', NULL, 'Boss', 1, 20);
INSERT INTO Employee VALUES('M', '2020-12-14', NULL, 'Staff', 2, 13);
INSERT INTO Employee VALUES('F', '2020-12-14', NULL, 'Staff', 3, 13);
INSERT INTO Employee VALUES('M', '2020-12-14', NULL, 'Staff', 4, 13);
INSERT INTO Employee VALUES('M', '2020-12-14', NULL, 'Staff', 5, 13);
INSERT INTO Employee VALUES('F', '2020-12-14', NULL, 'Staff', 6, 13);
INSERT INTO Employee VALUES('F', '2020-12-14', NULL, 'Staff', 7, 13);

# CC, Balance, Customer_ID, CC_Date, CC_Code
INSERT INTO Customer VALUES('8954-6541-8561', 654.14, 14, '05/23', 456);
INSERT INTO Customer VALUES('5791-1721-1431', 6.10, 8, '11/24', 753);
INSERT INTO Customer VALUES('6217-1724-4123', 34.01, 9, '01/26', 789);
INSERT INTO Customer VALUES('6517-7114-7184', 10.14, 10, '12/22', 521);
INSERT INTO Customer VALUES('5411-5616-2111', 72.14, 11, '03/20', 179);
INSERT INTO Customer VALUES('8951-2165-2164', 12.78, 12, '10/21', 956);
INSERT INTO Customer VALUES('9877-6211-5616', 78.97, 13, '03/54', 484);

# Schedule_ID, Start_Day, Start_Time, End_Time, Employee_ID
INSERT INTO Work_Schedule VALUES(1, '2020-12-01', '11:00:00', '22:00:00', 1);
INSERT INTO Work_Schedule VALUES(2, '2020-12-01', '11:00:00', '16:00:00', 2);
INSERT INTO Work_Schedule VALUES(3, '2020-12-01', '16:00:00', '22:00:00', 3);
INSERT INTO Work_Schedule VALUES(4, '2020-12-02', '11:00:00', '22:00:00', 4);
INSERT INTO Work_Schedule VALUES(5, '2020-12-02', '11:00:00', '16:00:00', 5);
INSERT INTO Work_Schedule VALUES(6, '2020-12-02', '16:00:00', '22:00:00', 6);
INSERT INTO Work_Schedule VALUES(7, '2020-12-03', '11:00:00', '22:00:00', 7);

# Supplier_ID, Supplier_Name, Address, Phone, Balance, Products
INSERT INTO Supplier VALUES(111, 'Cleaning CO', '6517 145 Street', '564-546-5046', 2101.10, "Bleach, Sanitizer, Solvent");
INSERT INTO Supplier VALUES(222, 'White Sheets', '5431 200 Street', '515-164-7414', 5442.12, "Solvent, Soap, Iron, Ironing Board");
INSERT INTO Supplier VALUES(333, 'Splended Cleaning', '7862 723 Street', '541-211-2212', 45312.00, "Soap, Bleach");
INSERT INTO Supplier VALUES(444, 'City of Laundry', '6782 343 Street', '981-219-0100', 453.04, "Vaccum, Bags, Detergent, Hanger");
INSERT INTO Supplier VALUES(555, 'Task 3', '6721 760 Street', '741-512-2184', 2341.27, "Vaccum, Resin, Wood");
INSERT INTO Supplier VALUES(666, 'Laundry Parts', '8576 111 Street', '120-120-2101', 423.56, "Fan, Broom, Mop");
INSERT INTO Supplier VALUES(777, 'Linen Star', '6876 222 Street', '510-120-8188', 564.54, "Washer, Dryer, Sheets");

#Equipment_ID, Brand, Equipment_Type, Quantity
INSERT INTO Equipment VALUES(101, 'Bosh', 'Electric Iron', 6);
INSERT INTO Equipment VALUES(102, 'Dyson', 'Vacuum Cleaner', 3);
INSERT INTO Equipment VALUES(103, 'LG', 'Washer', 10);
INSERT INTO Equipment VALUES(104, 'LG', 'Dryer', 10);
INSERT INTO Equipment VALUES(105, 'Kleenex', 'Hanger', 4);
INSERT INTO Equipment VALUES(106, 'Bosh', 'Ironing Board', 2);
INSERT INTO Equipment VALUES(107, 'Scotch', 'Mop', 10);
INSERT INTO Equipment VALUES(108, 'Arm & Hammer', 'Fan', 10);

#Supplies_ID, Name, Description, Inventory, Required_Inventory, Supplies_Usage
INSERT INTO Cleaning_Supplies VALUES(1001, 'Bleach', 'Clothing Bleach', 15, 10);
INSERT INTO Cleaning_Supplies VALUES(1002, 'Sanitizer', 'Hand Sanitizer', 20, 10);
INSERT INTO Cleaning_Supplies VALUES(1003, 'Solvent', 'Wood Polish', 8, 5);
INSERT INTO Cleaning_Supplies VALUES(1004, 'Baking Soda', 'Cleaning Soda', 12, 10);
INSERT INTO Cleaning_Supplies VALUES(1005, 'Detergent', 'Clothing Detergent', 25, 20);
INSERT INTO Cleaning_Supplies VALUES(1006, 'Soap', 'Hand Soap', 5, 20);
INSERT INTO Cleaning_Supplies VALUES(1007, 'Laundry Sheet', 'Dryer Laundry Sheets', 12, 10);

# Year, Month, Monthly_Usage, Equipment_ID, Supplies_ID
INSERT INTO Total_Usage VALUES("2020", "Jan", 30, 101, NULL);
INSERT INTO Total_Usage VALUES("2020", "Feb", 50, 101, NULL);
INSERT INTO Total_Usage VALUES("2020", "July", 56, 105, NULL);
INSERT INTO Total_Usage VALUES("2020", "June", 156, 108, NULL);
INSERT INTO Total_Usage VALUES("2020", "Aprl", 765, NULL, 1003);
INSERT INTO Total_Usage VALUES("2020", "Mar", 78, NULL, 1004);
INSERT INTO Total_Usage VALUES("2020", "May", 41, NULL, 1007);
INSERT INTO Total_Usage VALUES("2020", "Oct", 69, NULL, 1001);

# Transaction_ID, Purchase_Date, Quantity, Amount_Due, Due_Date, Description, Delivery_Date, Supplier_ID, Equipment_ID, Supplies_ID
INSERT INTO Purchase VALUES (1, '2020-01-01', 20, 80.00, '2020-01-01', 'Cleaning Supplies - Bleach', '2020-01-04', 111, NULL, 1001);
INSERT INTO Purchase VALUES (2, '2020-01-01', 20, 50.00, '2020-01-01', 'Cleaning Supplies - Sanitizer', '2020-01-04', 111, NULL, 1002);
INSERT INTO Purchase VALUES (3, '2020-01-04', 10, 130.00, '2020-01-04', 'Cleaning Supplies - Solvent', '2020-01-06', 222, NULL, 1003);
INSERT INTO Purchase VALUES (4, '2020-01-02', 3, 1800.00, '2020-01-30', 'Equipment: Vacuum', '2020-01-07', 444, 102, NULL);
INSERT INTO Purchase VALUES (5, '2020-01-02', 10, 6000.00, '2020-01-25', 'Equipment: Washer', '2020-01-08', 777, 103, NULL);
INSERT INTO Purchase VALUES (6, '2020-01-02', 10, 5800.00, '2020-01-25', 'Equipment: Dryer', '2020-01-08', 777, 104, NULL);
INSERT INTO Purchase VALUES (7, '2020-01-03', 10, 800.00, '2020-01-26', 'Equipment: Fan', '2020-01-05', 666, 108, NULL);

#Maintenance_ID, Start_Day, Start_Time, Equipment_ID, Cost
INSERT INTO Maintenance_Schedule VALUES(1, NULL, NULL, 101, NULL);
INSERT INTO Maintenance_Schedule VALUES(2, NULL, NULL, 102, NULL);
INSERT INTO Maintenance_Schedule VALUES(3, '2020-01-10', '09:00:00', 103, 100.21);
INSERT INTO Maintenance_Schedule VALUES(4, '2020-01-10', '09:00:00', 104, 50.0);
INSERT INTO Maintenance_Schedule VALUES(5, NULL, NULL, 105, NULL);
INSERT INTO Maintenance_Schedule VALUES(6, NULL, NULL, 106, NULL);
INSERT INTO Maintenance_Schedule VALUES(7, '2020-05-25', '12:00:00', 107, 30);
INSERT INTO Maintenance_Schedule VALUES(8, '2019-09-30', '11:00:00', 108, 80.5);

# Request_ID, Date, Amount, Notes, Satisfaction, Customer_ID
INSERT INTO Service_Request VALUES(1, '2020-12-24', 10.45, 'Pick up at 3', 'good', 8);
INSERT INTO Service_Request VALUES(2, '2020-12-24', 21.10, 'Pick up at 5', 'good', 9);
INSERT INTO Service_Request VALUES(3, '2020-10-24', 50.00, 'Priority', 'bad', 10);
INSERT INTO Service_Request VALUES(4, '2020-04-24', 11.99, 'No soap', 'excellent', 11);
INSERT INTO Service_Request VALUES(5, '2020-01-24', 30.55, 'Wash twice', 'okay', 12);
INSERT INTO Service_Request VALUES(6, '2020-11-24', 11.40, 'Iron', 'great', 13);
INSERT INTO Service_Request VALUES(7, '2020-01-24', 45.45, 'Fluff', 'amazing', 14);

# Service_ID, Name, Description, Rate, Duration_Hours, Request_ID, Equipment_ID, Supplies_ID
INSERT INTO Service VALUES(10000, 'Washing', 'Laundry Service', 5.50, 1, 1, 103, 1005);
INSERT INTO Service VALUES(10001, 'Drying', 'Drying Service', 3.50, 1, 2, 104, 1007);
INSERT INTO Service VALUES(10002, 'Folding', 'Folding Service', 4.99, 1, 3, NULL, NULL);
INSERT INTO Service VALUES(10003, 'Ironing', 'Ironing Service', 5.50, 1, 4, 101, NULL);
INSERT INTO Service VALUES(10004, 'Hang Dry', 'Drying Service', 10.00, 3, 5, 105, NULL);
INSERT INTO Service VALUES(10005, 'Delicates', 'Laundry Service', 6.99, 1, 6, 103, 1005);
INSERT INTO Service VALUES(10006, 'Perm Press', 'Laundry Service', 4.50, 1, 7, 103, 1005);
