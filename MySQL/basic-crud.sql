-- Exercise 01
SELECT * FROM departments;

-- Exercise 02
SELECT `name` FROM departments
ORDER BY department_id;

-- Exercise 03
SELECT `first_name`, `last_name`, `salary` 
FROM employees
ORDER BY employee_id;

-- Exercise 04
SELECT `first_name`, `middle_name`,`last_name`
FROM employees
ORDER BY employee_id;

-- Exercise 05
SELECT concat(`first_name`, '.', `last_name`, '@softuni.bg') as `full_email_address` 
from employees;

-- Exercise 06
SELECT DISTINCT salary from employees;

-- Exercise 07
SELECT * FROM employees
WHERE job_title = 'Sales Representative'
ORDER BY employee_id;

-- Exercise 08
SELECT `first_name`, `last_name`, `job_title` FROM employees
WHERE salary BETWEEN 20000 AND 30000
ORDER BY employee_id;

-- Exercise 09
SELECT 
    CONCAT_WS(' ',
            `first_name`,
            `middle_name`,
            `last_name`) AS 'Full Name'
FROM
    employees
WHERE
    salary = 25000 OR salary = 14000
        OR salary = 12500
        OR salary = 23600;
        
-- Exercise 10
SELECT first_name, last_name FROM employees
WHERE manager_id IS NULL;

-- Exercise 11
SELECT first_name, last_name, salary FROM employees
WHERE salary > 50000
ORDER BY salary DESC;

-- Exercise 12
SELECT first_name, last_name FROM employees
ORDER BY salary DESC
LIMIT 5;

-- Exercise 13
SELECT first_name, last_name FROM employees
WHERE department_id != '4';

-- Exercise 14
SELECT * FROM employees
ORDER BY salary DESC, 
first_name ASC, 
last_name DESC, 
middle_name ASC;

-- Exercise 15
CREATE VIEW v_employees_salaries as 
SELECT 
    `first_name`, `last_name`, `salary`
FROM
    employees;
    
-- Exercise 16
CREATE VIEW `v_employees_job_titles` AS
    SELECT 
        CONCAT_WS(' ', first_name, middle_name, last_name) AS 'full_name',
        job_title
    FROM
        employees;
        
-- Exercise 17
SELECT DISTINCT
    job_title
FROM
    employees
ORDER BY job_title;

-- Exercise 18
SELECT 
    *
FROM
    projects
ORDER BY `start_date`,
`name`
-- `project_id`
LIMIT 10;

-- Exercise 19
SELECT 
    `first_name`, `last_name`, `hire_date`
FROM
    employees
ORDER BY `hire_date` DESC
LIMIT 7;

-- Exercise 20
UPDATE `employees` 
SET 
    `salary` = `salary` * 1.12
WHERE
    `department_id` IN (1 , 2, 4, 11);

SELECT 
    `salary`
FROM
    `employees`;
    
-- Exercise 21
SELECT 
    peak_name
FROM
    peaks
ORDER BY peak_name;

-- Exercise 22
SELECT 
    country_name, population
FROM
    countries
WHERE
    continent_code = 'EU'
ORDER BY population DESC , country_name ASC
LIMIT 30;

-- Exercise 23
SELECT 
    `country_name`,
    `country_code`,
    IF(`currency_code` = 'EUR',
        'Euro',
        'Not Euro') AS 'currency'
FROM
    `countries`
ORDER BY `country_name`;

-- Exercise 24
SELECT 
    c.name
FROM
    characters AS c
ORDER BY c.name ASC;