-- Create DataBase
show databases;
DROP DATABASE newsDB;
CREATE DATABASE IF NOT EXISTS newsDB;
USE newsDB;



-- Create Table kidNews
DROP TABLE kidNews;
CREATE TABLE IF NOT EXISTS kidNews(
	news_id INT AUTO_INCREMENT PRIMARY KEY,
	news_url VARCHAR(200) NOT NULL,
	news_title VARCHAR(200) NOT NULL,
	news_subtitle VARCHAR(200),
	news_author VARCHAR(50) NOT NULL,
	news_date DATETIME NOT NULL,
	news_article TEXT NOT NULL,
	news_img_path VARCHAR(200),
	news_source  VARCHAR(50) NOT NULL
);
DESC kidNews;
SELECT * FROM kidNews;
-- LOAD DATA LOCAL INFILE "/Users/jeongseungheon/PlayData-SemiPrj/data/news_data.csv" 
-- INTO TABLE kidNews
-- CHARACTER SET utf8
-- 	FIELDS TERMINATED BY "\t"
-- 	-- OPTIONALLY ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 LINES(
-- 	'news_url', 'news_title', 'news_subtitle', 'news_author', 
-- 	'news_date', 'news_article', 'news_img_path', 'news_source'
-- );



-- Create Table wordCount
DROP TABLE wordCount;
CREATE TABLE IF NOT EXISTS wordCount(
	wordCount_id INT AUTO_INCREMENT PRIMARY KEY,
	news_date_short DATE NOT NULL,
	word_list VARCHAR(100) NOT NULL,
	num INT NOT NULL
);
DESC wordCount;
SELECT * FROM wordCount ORDER BY num DESC;



-- DAO test
SELECT *, WEEK(news_date_short) FROM wordCount;
SELECT WEEK("2020-04-01") FROM wordCount;

SELECT YEAR(NOW()), WEEK(NOW());

SELECT word_list, COUNT(num) 
FROM wordCount
WHERE WEEK(news_date_short) = 16
GROUP BY word_list ;

SELECT word_list, num 
FROM wordCount
WHERE WEEK(news_date_short) = 16
ORDER BY word_list DESC
LIMIT 200;



-- localhost/wordcount/thisweek
SELECT WEEK('2022-01-09');

SELECT word_list, SUM(num) AS num
FROM wordCount
WHERE 
	YEAR(news_date_short) = YEAR(NOW()) AND
	WEEK(news_date_short) = WEEK(NOW()) AND 
	num > 1
GROUP BY word_list 
ORDER BY num DESC
LIMIT 200;



-- localhost/wordcount/lastweek?date=2022-01-05
--     SELECT YEAR("2022-01-05");
SELECT word_list, SUM(num) AS num
FROM wordCount
WHERE 
	YEAR(news_date_short) = YEAR('2022-01-05') AND
	WEEK(news_date_short) = WEEK('2022-01-05') AND 
	num > 1
GROUP BY word_list 
ORDER BY num DESC
LIMIT 200;



-- localhost/wordcount/lastweek?date=2022-01-05&word=호랑이
SELECT news_url, news_title, news_date, news_img_path, news_source 
FROM kidNews
WHERE 
	YEAR(news_date) = YEAR("2022-01-05") AND
	WEEK(news_date) = WEEK("2022-01-05") AND
	news_article LIKE '%호랑이%'
ORDER BY news_date DESC;
