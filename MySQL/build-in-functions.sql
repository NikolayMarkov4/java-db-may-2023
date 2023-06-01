USE soft_uni;
-- Exercise 01
SELECT 
    `first_name`, `last_name`
FROM
    employees
WHERE
    LOWER(`first_name`) like  LOWER('sa%')
ORDER BY `employee_id`;
-- ---------------------------------------
SELECT 
    `first_name`, `last_name`
FROM
    employees
WHERE
    `first_name` REGEXP '^Sa'
ORDER BY `employee_id`;

-- Exercise 02
SELECT 
    `first_name`, `last_name`
FROM
    employees
WHERE
    `last_name` LIKE '%ei%'
ORDER BY `employee_id`;

-- Exercise 03
SELECT 
    `first_name`
FROM
    employees
WHERE
    `department_id` IN (3 , 10)
        AND YEAR(`hire_date`) BETWEEN 1995 AND 2005
ORDER BY `employee_id`;

-- Exercise 04
SELECT 
    `first_name`, `last_name`
FROM
    employees
WHERE
    `job_title` NOT LIKE '%engineer%'
ORDER BY `employee_id`;

-- Exercise 05
SELECT 
    `name`
FROM
    towns
WHERE
    LENGTH(`name`) = 5 OR LENGTH(`name`) = 6
ORDER BY `name`;

-- Exercise 06
SELECT 
    *
FROM
    `towns`
WHERE
    `name` REGEXP '^[MmKkBbEe]'
ORDER BY `name`;
-- ---------------------------
SELECT 
    *
FROM
    `towns`
WHERE
    LOWER(`name`) LIKE 'm%'
        OR LOWER(`name`) LIKE 'k%'
        OR LOWER(`name`) LIKE 'b%'
        OR LOWER(`name`) LIKE 'e%'
ORDER BY `name`;

-- Exercise 07
SELECT 
    *
FROM
    `towns`
WHERE
    `name` REGEXP '^[^RrBbDd]'
ORDER BY `name`;
-- ---------------------------
SELECT 
    *
FROM
    `towns`
WHERE
    LOWER(`name`) NOT LIKE 'b%'
        and LOWER(`name`) NOT LIKE 'd%'
        and LOWER(`name`) NOT LIKE 'r%'
ORDER BY `name`;

-- Exercise 08
CREATE VIEW `v_employees_hired_after_2000` AS
    SELECT 
        `first_name`, `last_name`
    FROM
        `employees`
    WHERE
        YEAR(`hire_date`) > 2000;

-- Exercise 09
SELECT 
    `first_name`, `last_name`
FROM
    employees
WHERE
    LENGTH(`last_name`) = 5;
    
-- Exercise 10
SELECT 
    `country_name`, `iso_code`
FROM
    countries
WHERE
    `country_name` LIKE '%a%a%a%'
ORDER BY `iso_code`;
-- -----------------------------
SELECT 
    `country_name`, `iso_code`
FROM
    countries
WHERE
    (CHAR_LENGTH(`country_name`) - CHAR_LENGTH(REPLACE(LOWER(`country_name`), 'a', ''))) >= 3
ORDER BY `iso_code`;

-- Exercise 11
USE geography;
SELECT 
    `peak_name`,
    `river_name`,
    LOWER(CONCAT(LEFT(p.`peak_name`,
                        LENGTH(p.peak_name) - 1),
                    r.`river_name`)) AS 'mix'
FROM
    peaks AS p,
    rivers AS r
WHERE
    LOWER(RIGHT(p.`peak_name`, 1)) = LOWER(LEFT(r.`river_name`, 1))
ORDER BY `mix`;
-- ---------------------------------------------------------------
SELECT 
    `peak_name`,
    `river_name`,
    LOWER(CONCAT(p.`peak_name`,
                    SUBSTRING(r.`river_name`, 2))) AS 'mix'
FROM
    peaks AS p,
    rivers AS r
WHERE
    LOWER(RIGHT(p.`peak_name`, 1)) = LOWER(LEFT(r.`river_name`, 1))
ORDER BY `mix`;

-- Exercise 12
USE diablo;

SELECT 
    `name`, DATE_FORMAT(`start`, '%Y-%m-%d')
FROM
    games
WHERE
    YEAR(`start`) IN (2011 , 2012)
ORDER BY `start`
LIMIT 50;

-- Exercise 13
SELECT 
    `user_name`,
    REGEXP_REPLACE(`email`, '.*@', '') AS 'email provider'
FROM
    users
ORDER BY `email provider` , `user_name`;
-- ------------------------------------------------------
SELECT 
    `user_name`,
    SUBSTRING_INDEX(`email`, '@', - 1) AS 'email provider'
FROM
    users
ORDER BY `email provider` , `user_name`;

-- Exercise 14
SELECT 
    `user_name`, `ip_address`
FROM
    users
WHERE
    `ip_address` LIKE '___.1%.%.___'
ORDER BY `user_name`;

-- Exercise 15
SELECT 
    `name` AS game,
    CASE
        WHEN HOUR(`start`) BETWEEN 0 AND 11 THEN 'Morning'
        WHEN HOUR(`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
        ELSE 'Evening'
    END AS 'Part of the Day',
    CASE
        WHEN `duration` <= 3 THEN 'Extra Short'
        WHEN `duration` BETWEEN 4 AND 6 THEN 'Short'
        WHEN `duration` BETWEEN 7 AND 10 THEN 'Long'
        ELSE 'Extra Long'
    END AS 'Duration'
FROM
    games
ORDER BY `name`;

-- Exercise 16
SELECT 
    `product_name`,
    `order_date`,
    DATE_ADD(`order_date`, INTERVAL 3 DAY) AS 'pay_due',
    DATE_ADD(`order_date`, INTERVAL 1 MONTH) AS 'deliver_due'
FROM
    orders;