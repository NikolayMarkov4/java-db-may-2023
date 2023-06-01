-- EXCERCISE 1 
SELECT 
    COUNT(`id`) AS 'count'
FROM
    wizzard_deposits;
    
-- ------------------------
-- EXCERCISE 2
SELECT 
    MAX(magic_wand_size) AS 'longest_magic_wand'
FROM
    wizzard_deposits; 

-- ---------------------------------------
-- EXCERCISE 3
SELECT 
    deposit_group,
    MAX(magic_wand_size) AS 'longest_magic_wand'
FROM
    wizzard_deposits
GROUP BY deposit_group
ORDER BY longest_magic_wand , deposit_group;

-- ---------------------------------------
-- EXCERCISE 4
SELECT 
    deposit_group
FROM
    wizzard_deposits
GROUP BY deposit_group
ORDER BY AVG(magic_wand_size)
LIMIT 1;

-- ---------------------------------------
-- EXCERCISE 5
SELECT 
    deposit_group, SUM(deposit_amount) AS total_sum
FROM
    wizzard_deposits
GROUP BY deposit_group
ORDER BY total_sum;

-- ---------------------------------------
-- EXCERCISE 6
SELECT 
    deposit_group, SUM(deposit_amount) as total_sum
FROM
    wizzard_deposits
WHERE
    magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
ORDER BY deposit_group;

-- ---------------------------------------
-- EXCERCISE 7
SELECT 
    deposit_group, SUM(deposit_amount) as total_sum
FROM
    wizzard_deposits
WHERE
    magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
HAVING total_sum < 150000
ORDER BY total_sum DESC;

-- ---------------------------------------
-- EXCERCISE 8
SELECT 
    deposit_group, magic_wand_creator, MIN(deposit_charge)
FROM
    wizzard_deposits
GROUP BY deposit_group , magic_wand_creator
ORDER BY magic_wand_creator , deposit_group;

-- ---------------------------------------
-- EXCERCISE 9
SELECT 
    CASE
        WHEN age <= 10 THEN '[0-10]'
        WHEN age <= 20 THEN '[11-20]'
        WHEN age <= 30 THEN '[21-30]'
        WHEN age <= 40 THEN '[31-40]'
        WHEN age <= 50 THEN '[41-50]'
        WHEN age <= 60 THEN '[51-60]'
        ELSE '[61+]'
    END AS 'age_group',
    COUNT(*) AS 'wizard_count'
FROM
    wizzard_deposits
GROUP BY age_group
ORDER BY wizard_count;

-- ---------------------------------------
-- EXCERCISE 10
SELECT 
    LEFT(first_name, 1) AS 'first_letter'
FROM
    wizzard_deposits
WHERE
    deposit_group = 'Troll Chest'
GROUP BY first_letter
ORDER BY first_letter;

-- ---------------------------------------
-- EXCERCISE 11
SELECT 
    deposit_group,
    is_deposit_expired,
    AVG(deposit_interest) AS deposit_interest
FROM
    wizzard_deposits
WHERE
    deposit_start_date > '1985-01-01'
GROUP BY deposit_group , is_deposit_expired
ORDER BY deposit_group DESC , is_deposit_expired;

-- ---------------------------------------
-- EXCERCISE 12
SELECT 
    department_id, MIN(salary)
FROM
    employees
WHERE
    department_id IN (2 , 5, 7)
        AND hire_date > '2000-01-01'
GROUP BY department_id
ORDER BY department_id;

-- ---------------------------------------
-- EXCERCISE 13
SELECT 
    department_id,
    IF(department_id = 1,
        AVG(salary) + 5000,
        AVG(salary)) AS 'avg_salary'
FROM
    employees
WHERE
    salary > 30000 AND manager_id != 42
GROUP BY department_id
ORDER BY department_id;

-- ---------------------------------------
-- EXCERCISE 14
SELECT 
    department_id, MAX(salary) AS 'max_salary'
FROM
    employees
GROUP BY department_id
HAVING NOT max_salary BETWEEN 30000 AND 70000
ORDER BY department_id;

-- ---------------------------------------
-- EXCERCISE 15
SELECT 
    COUNT(salary) as ''
FROM
    employees
WHERE
    ISNULL(manager_id);
    
-- ---------------------------------------
-- EXCERCISE 16
SELECT 
    e1.department_id,
    (SELECT DISTINCT
            e2.salary
        FROM
            employees AS e2
        WHERE
            e2.department_id = e1.department_id
        ORDER BY e2.salary DESC
        LIMIT 2 , 1) AS third_highest_salary
FROM
    employees AS e1
GROUP BY e1.department_id
HAVING third_highest_salary IS NOT NULL
ORDER BY e1.department_id;

-- ---------------------------------------
-- EXCERCISE 17
SELECT 
    e1.first_name, e1.last_name, e1.department_id
FROM
    employees AS e1
        JOIN
    (SELECT 
        e2.department_id, AVG(e2.salary) AS 'dep_avg_salary'
    FROM
        employees AS e2
    GROUP BY e2.department_id) AS avrg ON e1.department_id = avrg.department_id
WHERE e1.salary > avrg.dep_avg_salary
ORDER BY e1.department_id
LIMIT 10;

-- ---------------------------------------
-- EXCERCISE 18
SELECT 
    department_id, SUM(salary) AS 'total_salary'
FROM
    employees
GROUP BY department_id
ORDER BY department_id;