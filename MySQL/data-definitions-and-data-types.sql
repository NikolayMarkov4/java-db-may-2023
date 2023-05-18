CREATE database `minions`;
USE `minions`;

-- Exercise 01 Create Tables
CREATE TABLE `minions` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) ,
    `age` INT
);

CREATE TABLE `towns` (
	`town_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

-- Exercise 02 Alter Minions Table
ALTER TABLE `minions`
ADD COLUMN `town_id` INT;

ALTER TABLE `minions` 
ADD CONSTRAINT `fk_minions_towns`
  FOREIGN KEY `minions`(`town_id`)
  REFERENCES `towns` (`id`);
  
-- Exercise 03 Insert Records in Both Tables

INSERT INTO `towns` (`id`, `name`)
VALUES 
( 1, 'Sofia'),
( 2, 'Plovdiv'),
( 3, 'Varna');

INSERT INTO `minions` (`id`, `name`, `age`, `town_id`)
VALUES 
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', NULL , 2);

-- Exercise 04 Truncate Table Minions
TRUNCATE TABLE `minions`;

-- Exercise 05 Drop All Tables
DROP TABLE `minions`;
DROP TABLE `towns`;

-- Exercise 06 Create Table People
CREATE TABLE `people`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `picture` BLOB ,
    `height` DOUBLE(10,2),
    `weight` DOUBLE(10,2),
    `gender` CHAR(1) NOT NULL,
    `birthdate` DATE NOT NULL,
    `biography` TEXT
);

INSERT INTO `people` (`name`, `gender`, `birthdate`)
VALUES 
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW())),
('Test', 'M', DATE(NOW()));

-- Exercise 07 Create Table Users
CREATE TABLE `users` (
	`id` INT AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL,
    `password` VARCHAR(26) NOT NULL,
    `profile_picture` BLOB,
    `last_login_time` TIME,
    `is_deleted` BOOLEAN, 
	CONSTRAINT pk_users 
    PRIMARY KEY `users`(`id`)
);

INSERT INTO `users` (`username`, `password`)
VALUES 
('Test1', 'Pass'),
('Test2', 'Pass'),
('Test3', 'Pass'),
('Test4', 'Pass'),
('Test5', 'Pass');

-- Exercise 08 Change Primary Key
ALTER TABLE `users` 
DROP PRIMARY KEY, 
ADD CONSTRAINT pk_users2
PRIMARY KEY `users`(`id`, `username`);

-- Exercise 09 Set Default Value of a Field
ALTER TABLE `users`
CHANGE COLUMN `last_login_time` `last_login_time` DATETIME  DEFAULT NOW();

-- Exercise 10 Set Unique Field
ALTER TABLE `users`
DROP PRIMARY KEY, 
ADD CONSTRAINT pk_users 
PRIMARY KEY `users`(`id`), 
CHANGE COLUMN `username` `username` VARCHAR(50) UNIQUE;
 
 -- Exercise 11 Movies Database
CREATE DATABASE `movies`; 
USE `movies`;

CREATE TABLE `directors` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `director_name` VARCHAR(50) NOT NULL , 
    `notes` TEXT
); 

INSERT INTO `directors`(`director_name`, `notes`)
VALUES 
('TestName1', 'TestNotes'),
('TestName2', 'TestNotes'),
('TestName3', 'TestNotes'),
('TestName4', 'TestNotes'),
('TestName5', 'TestNotes');

CREATE TABLE `genres` (
	`id` INT PRIMARY KEY AUTO_INCREMENT , 
    `genre_name` VARCHAR(20) NOT NULL,
    `notes` TEXT
);

INSERT INTO `genres`(`genre_name`, `notes`)
VALUES 
('TestName1', 'TestNotes'),
('TestName2', 'TestNotes'),
('TestName3', 'TestNotes'),
('TestName4', 'TestNotes'),
('TestName5', 'TestNotes');

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `category_name` VARCHAR(20) NOT NULL,
    `notes` TEXT
);

INSERT INTO `categories`(`category_name`, `notes`)
VALUES 
('TestName1', 'TestNotes'),
('TestName2', 'TestNotes'),
('TestName3', 'TestNotes'),
('TestName4', 'TestNotes'),
('TestName5', 'TestNotes');

CREATE TABLE `movies` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(40) NOT NULL, 
    `director_id` INT,
    `copyright_year` INT,
    `length` INT,
    `genre_id` INT,
    `category_id` INT,
    `rating` DOUBLE, 
    `notes` TEXT
);

INSERT INTO `movies` (`title`)
VALUES 
('TestMovie1'),
('TestMovie2'),
('TestMovie3'),
('TestMovie4'),
('TestMovie5');
 
 
--  Exercise 12 Car Rental Database
CREATE DATABASE car_rental;
USE car_rental;

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `category` VARCHAR(20), 
    `daily_rate` DOUBLE, 
    `weekly_rate` DOUBLE, 
    `monthly_rate` DOUBLE, 
    `weekend_rate` DOUBLE    
);

INSERT INTO `categories` (`category`)
VALUES 
('TestName1'),
('TestName2'),
('TestName3');

CREATE TABLE `cars` (
	`id` INT PRIMARY KEY AUTO_INCREMENT, 
    `plate_number` VARCHAR(20),
    `make` VARCHAR(20),
    `model` VARCHAR(20),
    `car_year` INT,
    `category_id` INT,
    `doors` INT,
    `picture` BLOB,
    `car_condition` VARCHAR(30),
    `available` BOOLEAN   
);

INSERT INTO `cars` (`plate_number`)
VALUES 
('TestName1'),
('TestName2'),
('TestName3');

CREATE TABLE `employees` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50),
    `last_name` VARCHAR(50),
    `title` VARCHAR(50),
    `notes` TEXT
);

INSERT INTO `employees` (`first_name`, `last_name`)
VALUES 
('TestName1', 'TestName1'),
('TestName2', 'TestName2'),
('TestName3', 'TestName3');

CREATE TABLE `customers` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `driver_license` VARCHAR(20),
	`full_name` VARCHAR(50),
	`address` VARCHAR(50),
	`city` VARCHAR(10),
	`zip_code` VARCHAR(10),
	`notes` TEXT
);

INSERT INTO `customers` (`driver_license`, `full_name`)
VALUES 
('TestName1', 'TestName1'),
('TestName2', 'TestName2'),
('TestName3', 'TestName3');

CREATE TABLE `rental_orders` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `employee_id` INT,
    `customer_id` INT,
    `car_id` INT,
    `car_condition` VARCHAR(50),
    `tank_level` VARCHAR(20),
	`kilometrage_start` INT,
    `kilometrage_end` INT,
    `total_kilometrage` INT,
    `start_date` DATE, 
    `end_date` DATE, 
    `total_days` INT,
    `rate_applied` DOUBLE,
    `tax_rate` DOUBLE,
    `order_status` VARCHAR(20),
    `notes` TEXT
);

INSERT INTO `rental_orders` (`employee_id`, `customer_id`)
VALUES 
(1, 2),
(2, 3),
(3, 1);


--  Exercise 13 Hotel Database // REMOVED
CREATE DATABASE hotel;
USE hotel;

CREATE table `employees`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20), 
    `last_name` VARCHAR(20), 
    `title` VARCHAR(20), 
    `notes` TEXT 
);

INSERT INTO `employees` (`first_name`, `last_name` )
VALUES 
('Test1', 'Test1'),
('Test2', 'Test2'),
('Test3', 'Test3');

CREATE TABLE `customers` (
	`account_number` INT PRIMARY KEY, 
    `first_name` VARCHAR(45),
    `last_name` VARCHAR(45),
    `phone_number` INT,
    `emergency_name` VARCHAR(30),
    `emergency_number` VARCHAR(30),
	`notes` TEXT
);

INSERT INTO `customers` (`account_number`, `first_name`, `last_name` )
VALUES 
(1, 'Test1', 'Test1'),
(2, 'Test2', 'Test2'),
(3, 'Test3', 'Test3');


CREATE TABLE `room_status` (
	`room_status` VARCHAR(20), 
    `notes` TEXT
);

INSERT INTO `room_status` 
VALUES 
('Test1', 'Test1'),
('Test2', 'Test2'),
('Test3', 'Test3');

CREATE TABLE `room_types` (
	`room_type` VARCHAR(20), 
    `notes` TEXT
);

INSERT INTO `room_types` 
VALUES 
('Test1', 'Test1'),
('Test2', 'Test2'),
('Test3', 'Test3');

CREATE TABLE `bed_types` (
	`bed_type` VARCHAR(20), 
    `notes` TEXT
);

INSERT INTO `bed_types` 
VALUES 
('Test1', 'Test1'),
('Test2', 'Test2'),
('Test3', 'Test3');

CREATE TABLE `rooms` (
	`room_number` INT, 
	`room_type` INT, 
	`bed_type` INT, 
	`rate` DOUBLE, 
    `room_status` INT,
    `notes` TEXT
);

INSERT INTO `rooms`(`room_number`, `room_type`, `bed_type`)
VALUES
(1, 1, 2),
(2, 3, 2),
(3, 1, 3);

CREATE TABLE `payments` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `employee_id` INT, 
    `payment_date` DATE, 
    `account_number` INT,
    `first_date_occupied` DATE,
    `last_date_occupied` DATE,
    `total_days` INT,
    `amount_charged` DOUBLE, 
    `tax_rate` DOUBLE,
    `tax_amount` DOUBLE,
    `payment_total` DECIMAL,
    `notes` TEXT
);

INSERT INTO `payments` (`employee_id`, `payment_date`)
VALUES
(1, DATE(NOW())),
(2, DATE(NOW())),
(3, DATE(NOW()));

CREATE TABLE `occupancies` (
	`id` INT PRIMARY KEY AUTO_INCREMENT, 
    `employee_id` INT, 
    `date_occupied` DATE, 
    `account_number` INT,
    `room_number` INT,
    `rate_applied` DOUBLE,
    `phone_charge` DOUBLE,
    `notes` TEXT
); 

INSERT INTO `occupancies` (`employee_id`, `date_occupied`)
VALUES
(1, DATE(NOW())),
(2, DATE(NOW())),
(3, DATE(NOW()));



--  Exercise 13 Basic Insert
CREATE DATABASE soft_uni;
USE soft_uni;

CREATE TABLE `towns`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL
);

CREATE TABLE `addresses` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `address_text` VARCHAR(45) NOT NULL, 
    `town_id` INT 
);

CREATE TABLE `departments`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL
);

CREATE TABLE `employees` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `middle_name` VARCHAR(45),
    `last_name` VARCHAR(45) NOT NULL,
    `job_title` VARCHAR(45),
    `department_id` INT NOT NULL,
    `hire_date` DATE, 
    `salary` DECIMAL(19,2), 
    `address_id` INT, 
    CONSTRAINT fk_employees_departments 
    FOREIGN KEY `employees`(`department_id`) 
    REFERENCES `departments`(`id`),
    CONSTRAINT fk_employees_addresses 
    FOREIGN KEY `employees`(`address_id`)
    REFERENCES `addresses`(`id`)
);

INSERT INTO `towns` (`name`)
VALUES
('Sofia'),
('Plovdiv'),
('Varna'),
('Burgas');

INSERT INTO `departments` (`name`)
VALUES
('Engineering'),
('Sales'),
('Marketing'),
('Software Development'),
('Quality Assurance');

INSERT INTO `employees` (`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`,`salary`)
VALUES
('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00),
('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);

--  Exercise 14 Basic Select All Fields
SELECT * FROM `towns`;
SELECT * FROM `departments`;
SELECT * FROM `employees`;

--  Exercise 15 Basic Select All Fields and Order Them
SELECT * FROM `towns`
ORDER BY `name`;
SELECT * FROM `departments`
ORDER BY `name`;
SELECT * FROM `employees`
ORDER BY `salary` DESC;

--  Exercise 16 Basic Select Some Fields 
SELECT `name` FROM `towns`
ORDER BY `name`;
SELECT `name` FROM `departments`
ORDER BY `name`;
SELECT `first_name`, `last_name`, `job_title`, `salary` FROM `employees`
ORDER BY `salary` DESC;

--  Exercise 17 Increase Employees Salary 
UPDATE `employees`
SET `salary` = `salary` * 1.1; 
SELECT `salary` FROM `employees`;

--  Exercise 18 Delete All Records
DELETE FROM occupancies;