SET GLOBAL log_bin_trust_function_creators = 1;
SET SQL_SAFE_UPDATES = 0;
-- 01 Exercise
-- --------------------------
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN 
	SELECT e.first_name, e.last_name
	FROM `employees` AS e
	WHERE e.salary > 35000
	ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above_35000();

DROP PROCEDURE IF EXISTS usp_get_employees_salary_above_35000;

-- 02 Exercise
-- --------------------------
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(salary_limit DOUBLE(19,4))
BEGIN
    SELECT e.first_name, e.last_name
    FROM `employees` AS e
    WHERE e.salary >= salary_limit
    ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above(48100);

DROP PROCEDURE IF EXISTS usp_get_employees_salary_above;

-- 03 Exercise
-- --------------------------
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(name_start TEXT)
BEGIN
    SELECT t.name AS 'town_name'
    FROM `towns` AS t
    WHERE t.name LIKE concat(name_start,'%')
    ORDER BY t.name;
END $$
DELIMITER ;

CALL usp_get_towns_starting_with('b');
CALL usp_get_towns_starting_with('be');
CALL usp_get_towns_starting_with('berlin');

DROP PROCEDURE IF EXISTS usp_get_towns_starting_with;

-- 04 Exercise
-- --------------------------
DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name TEXT)
BEGIN
    SELECT e.first_name, e.last_name
    FROM `employees` AS e
    JOIN `addresses` AS a ON e.address_id = a.address_id
    JOIN `towns` AS t ON a.town_id = t.town_id 
    WHERE t.name = town_name
    ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_from_town('Sofia');

DROP PROCEDURE IF EXISTS usp_get_employees_from_town;

-- 05 Exercise
-- -------------------------
DELIMITER %%
CREATE FUNCTION ufn_get_salary_level(salary DOUBLE(19,4))
RETURNS VARCHAR(7)
RETURN (
    CASE 
        WHEN salary < 30000 THEN 'Low'
        WHEN salary <= 50000 THEN 'Average'
        ELSE 'High'
    END
)
DELIMITER ;

SELECT ufn_get_salary_level(13500);
SELECT ufn_get_salary_level(43300);
SELECT ufn_get_salary_level(125500);

DROP FUNCTION IF EXISTS ufn_get_salary_level;

-- 06 Exercise
-- -------------------------
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(7))
BEGIN
    SELECT e.first_name, e.last_name
    FROM `employees` AS e
    WHERE e.salary < 30000 AND salary_level = 'low'
        OR e.salary >= 30000 AND e.salary <= 50000 AND salary_level = 'average'
        OR e.salary > 50000 AND salary_level = 'high'
    ORDER BY e.first_name DESC, e.last_name DESC;
END $$
DELIMITER ;
-- ---------------------------------------------------------------------------
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(7))
BEGIN
    SELECT e.first_name, e.last_name
    FROM `employees` AS e
    WHERE ufn_get_salary_level(e.salary) = salary_level
    ORDER BY e.first_name DESC, e.last_name DESC;
END $$
DELIMITER ;

CALL usp_get_employees_by_salary_level('low');
CALL usp_get_employees_by_salary_level('average');
CALL usp_get_employees_by_salary_level('high');

DROP PROCEDURE IF EXISTS usp_get_employees_by_salary_level;

-- 07 Exercise
-- -------------------------
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
RETURNS BIT
RETURN word REGEXP (concat('^[', set_of_letters, ']+$'));

SELECT ufn_is_word_comprised('oistmiahf', 'Sofia');
SELECT ufn_is_word_comprised('oistmiahf', 'halves');
SELECT ufn_is_word_comprised('bobr', 'Rob');
SELECT ufn_is_word_comprised('pppp', 'Guy');

DROP FUNCTION IF EXISTS ufn_is_word_comprised;
        
-- 08 Exercise
-- -------------------------
DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
    SELECT 
        CONCAT_WS(' ', h.first_name, h.last_name) AS 'full_name'
    FROM
        `account_holders` AS h
            JOIN
        (SELECT DISTINCT
            a.account_holder_id
        FROM
            `accounts` AS a) as a ON h.id = a.account_holder_id
    ORDER BY `full_name`;
END $$
DELIMITER ;

CALL usp_get_holders_full_name();

DROP PROCEDURE IF EXISTS usp_get_holders_full_name;

-- 09 Exercise
-- -------------------------
DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than (money DECIMAL(19,4))
BEGIN
SELECT first_name, last_name FROM account_holders as ah
	RIGHT JOIN accounts as ac ON ac.account_holder_id = ah.id
    GROUP BY ah.id
    HAVING sum(balance) > money
    ORDER BY ah.id;
END $$
DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(7000);

DROP PROCEDURE IF EXISTS usp_get_holders_with_balance_higher_than;

-- 10 Exercise
-- -------------------------
DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(
    initial_sum DECIMAL(19, 4), interest_rate DECIMAL(19, 4), years INT)
RETURNS DECIMAL(19, 4)
-- RETURNS DOUBLE(19, 2) -- Judge
BEGIN
    RETURN initial_sum * POW((1 + interest_rate), years);
END $$
DELIMITER ;

SELECT ufn_calculate_future_value(1000, 0.1, 5); -- Expected result: 1610.51

DROP FUNCTION IF EXISTS ufn_calculate_future_value;

-- 11 Exercise
-- -------------------------
DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(
    account_id INT, interest_rate DECIMAL(19, 4))
BEGIN
    SELECT 
         a.id AS 'account_id', h.first_name, h.last_name, a.balance AS 'current_balance',
         ufn_calculate_future_value(a.balance, interest_rate, 5) AS 'balance_in_5_years'
    FROM
        `account_holders` AS h
            JOIN
        `accounts` AS a ON h.id=a.account_holder_id
    WHERE a.id = account_id;
END $$
DELIMITER ;

CALL usp_calculate_future_value_for_account(1, 0.1);

DROP PROCEDURE IF EXISTS usp_calculate_future_value_for_account;

-- 12 Exercise
-- -------------------------
DELIMITER $$
CREATE PROCEDURE usp_deposit_money(
    account_id INT, money_amount DECIMAL(19, 4))
BEGIN
    IF money_amount > 0 THEN
        START TRANSACTION;
        
        UPDATE `accounts` AS a 
        SET 
            a.balance = a.balance + money_amount
        WHERE
            a.id = account_id;
        
        IF (SELECT a.balance 
            FROM `accounts` AS a 
            WHERE a.id = account_id) < 0
            THEN ROLLBACK;
        ELSE
            COMMIT;
        END IF;
    END IF;
END $$
DELIMITER ;

CALL usp_deposit_money(1, 10);

SELECT 
    a.id AS 'account_id', a.account_holder_id, a.balance
FROM
    `accounts` AS a
WHERE
    a.id = 1;
            
DROP PROCEDURE IF EXISTS usp_deposit_money;

-- 13 Exercise
-- -------------------------
DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(
    account_id INT, money_amount DECIMAL(19, 4))
BEGIN
    IF money_amount > 0 THEN
        START TRANSACTION;
        
        UPDATE `accounts` AS a 
        SET 
            a.balance = a.balance - money_amount
        WHERE
            a.id = account_id;
        
        IF (SELECT a.balance 
            FROM `accounts` AS a 
            WHERE a.id = account_id) < 0
            THEN ROLLBACK;
        ELSE
            COMMIT;
        END IF;
    END IF;
END $$
DELIMITER ;

CALL usp_withdraw_money(1, 10);

SELECT 
    a.id AS 'account_id', a.account_holder_id, a.balance
FROM
    `accounts` AS a
WHERE
    a.id = 1;

DROP PROCEDURE IF EXISTS usp_withdraw_money;

-- 14 Exercise
-- -------------------------
DELIMITER $$
CREATE PROCEDURE usp_transfer_money(
    from_account_id INT, to_account_id INT, money_amount DECIMAL(19, 4))
BEGIN
    IF money_amount > 0 
        AND from_account_id <> to_account_id 
        AND (SELECT a.id 
            FROM `accounts` AS a 
            WHERE a.id = to_account_id) IS NOT NULL
        AND (SELECT a.id 
            FROM `accounts` AS a 
            WHERE a.id = from_account_id) IS NOT NULL
        AND (SELECT a.balance 
            FROM `accounts` AS a 
            WHERE a.id = from_account_id) >= money_amount
    THEN
        START TRANSACTION;
        
        UPDATE `accounts` AS a 
        SET 
            a.balance = a.balance + money_amount
        WHERE
            a.id = to_account_id;
            
        UPDATE `accounts` AS a 
        SET 
            a.balance = a.balance - money_amount
        WHERE
            a.id = from_account_id;
        
        IF (SELECT a.balance 
            FROM `accounts` AS a 
            WHERE a.id = from_account_id) < 0
            THEN ROLLBACK;
        ELSE
            COMMIT;
        END IF;
    END IF;
END $$
DELIMITER ;

CALL usp_transfer_money(1, 2, 10);
CALL usp_transfer_money(2, 1, 10);

SELECT 
    a.id AS 'account_id', a.account_holder_id, a.balance
FROM
    `accounts` AS a
WHERE
    a.id IN (1 , 2);
            
DROP PROCEDURE IF EXISTS usp_transfer_money;

-- 15 Exercise
-- -------------------------
CREATE TABLE `logs` (
    log_id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_id INT(11) NOT NULL,
    old_sum DECIMAL(19, 4) NOT NULL,
    new_sum DECIMAL(19, 4) NOT NULL
);

DELIMITER $$
CREATE TRIGGER `tr_balance_updated`
AFTER UPDATE ON `accounts`
FOR EACH ROW
BEGIN
    IF OLD.balance <> NEW.balance THEN
        INSERT INTO `logs` 
            (`account_id`, `old_sum`, `new_sum`)
        VALUES (OLD.id, OLD.balance, NEW.balance);
    END IF;
END $$
DELIMITER ;

CALL usp_transfer_money(1, 2, 10);
CALL usp_transfer_money(2, 1, 10);

SELECT * FROM `logs`;

DROP TRIGGER IF EXISTS `bank`.tr_balance_updated;
DROP TABLE IF EXISTS `logs`;

-- 16 
-- ----------------------------------------
CREATE TABLE `notification_emails` (
    `id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `recipient` INT(11) NOT NULL,
    `subject` VARCHAR(50) NOT NULL,
    `body` VARCHAR(255) NOT NULL
);

DELIMITER $$
CREATE TRIGGER `tr_notification_emails`
AFTER INSERT ON `logs`
FOR EACH ROW
BEGIN
    INSERT INTO `notification_emails` 
        (`recipient`, `subject`, `body`)
    VALUES (
        NEW.account_id, 
        CONCAT('Balance change for account: ', NEW.account_id), 
        CONCAT('On ', DATE_FORMAT(NOW(), '%b %d %Y at %r'), ' your balance was changed from ', ROUND(NEW.old_sum, 2), ' to ', ROUND(NEW.new_sum, 2), '.'));
END $$
DELIMITER ;

SELECT * FROM `notification_emails`;

DROP TRIGGER IF EXISTS `bank`.tr_notification_emails;
DROP TABLE IF EXISTS `notification_emails`;