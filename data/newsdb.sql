-- Create DataBase
show databases;
DROP DATABASE news_db;
CREATE DATABASE IF NOT EXISTS news_db;
USE news_db;

-- Create Table kid_news
DROP TABLE kid_news;
CREATE TABLE IF NOT EXISTS kid_news(
	kid_news_id INT AUTO_INCREMENT PRIMARY KEY,
	kid_news_url VARCHAR(200) NOT NULL,
	kid_news_title VARCHAR(200) NOT NULL,
	kid_news_subtitle VARCHAR(200),
	kid_news_writer VARCHAR(50) NOT NULL,
	kid_news_date DATETIME NOT NULL,
	kid_news_article TEXT NOT NULL,
	kid_news_img VARCHAR(200),
	kid_news_source  VARCHAR(50) NOT NULL
);

DESC kid_news;
SELECT * FROM kid_news;

-- Create Table adult_news
CREATE TABLE IF NOT EXISTS adult_news(
	adult_news_id INT AUTO_INCREMENT PRIMARY KEY,
	adult_news_url VARCHAR(200) NOT NULL,
	adult_news_title VARCHAR(200) NOT NULL,
	adult_news_subtitle VARCHAR(200),
	adult_news_writer VARCHAR(50) NOT NULL,
	adult_news_date DATETIME NOT NULL,
	adult_news_article TEXT NOT NULL,
	adult_news_img VARCHAR(200),
	adult_news_source  VARCHAR(50) NOT NULL
);

DESC adult_news;
SELECT * FROM adult_news;

-- Create Table kid_word_count
DROP TABLE kid_word_count;
CREATE TABLE IF NOT EXISTS kid_word_count(
	kid_count_id INT AUTO_INCREMENT PRIMARY KEY,
	kid_count_date DATE NOT NULL,
	kid_count_word VARCHAR(100) NOT NULL,
	kid_count_value INT NOT NULL
);
DESC kid_word_count;
SELECT * FROM kid_word_count ORDER BY kid_count_value DESC;

-- Create Table adult_word_count
DROP TABLE adult_word_count;
CREATE TABLE IF NOT EXISTS adult_word_count(
	adult_count_id INT AUTO_INCREMENT PRIMARY KEY,
	adult_count_date DATE NOT NULL,
	adult_count_word VARCHAR(100) NOT NULL,
	adult_count_value INT NOT NULL
);
DESC adult_word_count;
SELECT * FROM adult_word_count ORDER BY adult_count_value DESC;


-- DAO test
SELECT *, WEEK(kid_count_date) FROM kid_word_count;
SELECT WEEK("2020-04-01") FROM kid_word_count;

SELECT YEAR(NOW()), WEEK(NOW());

SELECT kid_count_word, COUNT(kid_count_value) 
FROM kid_word_count
WHERE WEEK(kid_count_date) = 16
GROUP BY kid_count_word ;

SELECT kid_count_word, kid_count_value 
FROM kid_word_count
WHERE WEEK(kid_count_date) = 16
ORDER BY kid_count_word DESC
LIMIT 200;



-- localhost/kid_word_count/thisweek
SELECT WEEK('2022-01-09');

SELECT kid_count_word, SUM(kid_count_value) AS kid_count_value
FROM kid_word_count
WHERE 
	YEAR(kid_count_date) = YEAR(NOW()) AND
	WEEK(kid_count_date) = WEEK(NOW()) AND 
	kid_count_value > 1
GROUP BY kid_count_word 
ORDER BY kid_count_value DESC
LIMIT 200;



-- localhost/kid_word_count/lastweek?date=2022-01-05
--     SELECT YEAR("2022-01-05");
SELECT kid_count_word, SUM(kid_count_value) AS kid_count_value
FROM kid_word_count
WHERE 
	YEAR(kid_count_date) = YEAR('2022-01-05') AND
	WEEK(kid_count_date) = WEEK('2022-01-05') AND 
	kid_count_value > 1
GROUP BY kid_count_word 
ORDER BY kid_count_value DESC
LIMIT 200;


-- localhost/kid_word_count/lastweek?date=2022-01-05&word=호랑이
SELECT kid_news_url, kid_news_title, kid_news_date, kid_news_img, kid_news_source 
FROM kid_news
WHERE 
	YEAR(kid_news_date) = YEAR("2022-01-05") AND
	WEEK(kid_news_date) = WEEK("2022-01-05") AND
	kid_news_article LIKE '%호랑이%'
ORDER BY kid_news_date DESC;

SELECT * FROM kid_news where kid_news_article LIKE '%호랑이%';
