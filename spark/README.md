### SPARK-SUBMIT
* Running spark applications with spark-submit

#### Test MongoDB
* Importing Data from MongoDB
* Prerequisite
  * add /scripts/python_scripts/test_mongo.py to directory /opt/workspace/scripts/ as 'test_mongo.py' in docker container spark-master
* the following command should be executed in container spark-master


```sh
$SPARK_HOME/bin/spark-submit \
--master spark://spark-master:7077 \
--jars /opt/workspace/jars/bson-4.0.5.jar,\
/opt/workspace/jars/mongo-spark-connector_2.12-3.0.1.jar,\
/opt/workspace/jars/mongodb-driver-core-4.0.5.jar,\
/opt/workspace/jars/mongodb-driver-sync-4.0.5.jar \
/opt/workspace/scripts/test_mongo.py
```

#### Test MySQL
* Import Data from MySQL and save it as a dataframe
* Add data to the dataframe
* Export the dataframe to MySQL
* Prerequisite
  * Download dept.csv and import the data as 'dept_csv' table under 'employee' database
  * add /scripts/python_scripts/test_mysql.py to directory /opt/workspace/scripts/ as 'test_mysql.py' in docker container spark-master
* the following command should be executed in container spark-master


```sh
$SPARK_HOME/bin/spark-submit \
--master spark://spark-master:7077 \
--jars /opt/workspace/jars/mysql-connector-java-8.0.21.jar \
/opt/workspace/scripts/test_mysql.py
```

#### Test MongoDB-MySQL
* Import Data from MongoDB and save it as a dataframe
* transfrom dataframe
* Export the dataframe to MySQL
* Prerequiste
  * Add database 'newsDB', and table 'news_title'
  * add /scripts/python_scripts/test_mongo_to_mysql.py to directory /opt/workspace/scripts/ as 'test_mongo_to_mysql.py' in docker container spark-master
* the following command should be executed in container spark-master

```mysql
CREATE DATABASE newsDB;
USE newsDB;

CREATE TABLE news_title (
  news_title VARCHAR(100) NOT NULL,
  news_date VARCHAR(20) NOT NULL,
  news_source VARCHAR(15) NOT NULL,
  news_url VARCHAR(100) NOT NULL
);
```

```sh
$SPARK_HOME/bin/spark-submit \
--master spark://spark-master:7077 \
--jars /opt/workspace/jars/bson-4.0.5.jar,\
/opt/workspace/jars/mongo-spark-connector_2.12-3.0.1.jar,\
/opt/workspace/jars/mongodb-driver-core-4.0.5.jar,\
/opt/workspace/jars/mongodb-driver-sync-4.0.5.jar,\
/opt/workspace/jars/mysql-connector-java-8.0.21.jar \
/opt/workspace/scripts/test_mongo_to_mysql.py
```

#### Test MongoDB-HDFS
* Import Data from MySQL and save it as a dataframe
* transfrom dataframe
* Export the dataframe to HDFS 
* Prerequiste
  * add /scripts/python_scripts/tes_hdfs.py to directory /opt/workspace/scripts/ as 'tes_hdfs.py' in docker container spark-master
* the following command should be executed in container spark-master

```sh
$SPARK_HOME/bin/spark-submit \
--master spark://spark-master:7077 \
--jars /opt/workspace/jars/bson-4.0.5.jar,\
/opt/workspace/jars/mongo-spark-connector_2.12-3.0.1.jar,\
/opt/workspace/jars/mongodb-driver-core-4.0.5.jar,\
/opt/workspace/jars/mongodb-driver-sync-4.0.5.jar \
/opt/workspace/scripts/test_hdfs.py
```

### SQOOP

* sqoop connection test
* the following command should be executed in container namenode

```sh
sqoop list-databases --connect "jdbc:mysql://mysql2:3306?serverTimezone=UTC&useSSL=false" --username "root" --password "1234"
sqoop list-tables --connect "jdbc:mysql://mysql2:3306/newsDB?serverTimezone=UTC&useSSL=false" --username "root" --password "1234"
```
* HDFS to MySQL

```sh
sqoop export \
--connect "jdbc:mysql://mysql2:3306/newsDB?serverTimezone=UTC&useSSL=false" \
--table news_title \
--input-fields-terminated-by '\t' \
--input-lines-terminated-by '\n' \
--username "root" \
--password "1234" \
--export-dir /test
```

---

spark : http://localhost:8080 <br>
jupyter lab : http://localhost:8888/lab

### mongoDB to HDFS & Spark Wordcount

#### Kid_News
- kid_wordcount.py

```bash
$SPARK_HOME/bin/spark-submit \
--executor-memory 512m \
--master spark://spark-master:7077 \
--jars /opt/workspace/jars/bson-4.0.5.jar,\
/opt/workspace/jars/mongo-spark-connector_2.12-3.0.1.jar,\
/opt/workspace/jars/mongodb-driver-core-4.0.5.jar,\
/opt/workspace/jars/mongodb-driver-sync-4.0.5.jar \
/opt/workspace/scripts/kid_wordcount.py
```

---

#### Adult_News
- adult_wordcount.py

```bash
$SPARK_HOME/bin/spark-submit \
--master spark://spark-master:7077 \
--num-executors 2 \
--executor-cores 2 \
--executor-memory 1536m \
--jars /opt/workspace/jars/bson-4.0.5.jar,\
/opt/workspace/jars/mongo-spark-connector_2.12-3.0.1.jar,\
/opt/workspace/jars/mongodb-driver-core-4.0.5.jar,\
/opt/workspace/jars/mongodb-driver-sync-4.0.5.jar \
/opt/workspace/scripts/adult_wordcount.py
```

---


### local to MongoDB

#### Kid_News
```bash
#local
$docker cp kid_news.json mongodb2:/opt/
#mongodb
$mongoimport --uri="mongodb://root:1234@localhost:27017/news_db" --authenticationDatabase=admin  -c=kid_news --file=kid_news.json
```

```
use news_db;
show collections;
news_db> db.adult_news.countDocuments({})
100856
news_db> db.adult_news.countDocuments({})
124852
```

#### Adult_News
```bash
#local
$docker cp adult_news.json mongodb2:/opt/
#mongodb
$mongoimport --uri="mongodb://root:1234@localhost:27017/news_db" --authenticationDatabase=admin  -c=adult_news --file=adult_news.json
```

---

### HDFS to MySQL use Sqoop

#### Kid_News

```bash
sqoop export \
--connect "jdbc:mysql://mysql2:3306/news_db?serverTimezone=UTC&useSSL=false" \
--table kid_word_count \
--input-fields-terminated-by '\t' \
--input-lines-terminated-by '\n' \
--username "root" \
--password "1234" \
--export-dir /kid_word_count
```

#### Adult_News
```bash
sqoop export \
--connect "jdbc:mysql://mysql2:3306/news_db?serverTimezone=UTC&useSSL=false" \
--table adult_word_count \
--input-fields-terminated-by '\t' \
--input-lines-terminated-by '\n' \
--username "root" \
--password "1234" \
--export-dir /adult_word_count
```

---

### MongoDB to Mysql Direct

#### Kid_News
- kid_transfer_direct.py

```bash
$SPARK_HOME/bin/spark-submit \
--master spark://spark-master:7077 \
--num-executors 2 \
--executor-cores 2 \
--executor-memory 1536m \
--jars /opt/workspace/jars/bson-4.0.5.jar,\
/opt/workspace/jars/mongo-spark-connector_2.12-3.0.1.jar,\
/opt/workspace/jars/mongodb-driver-core-4.0.5.jar,\
/opt/workspace/jars/mongodb-driver-sync-4.0.5.jar,\
/opt/workspace/jars/mysql-connector-java-8.0.21.jar \
/opt/workspace/scripts/kid_transfer_direct.py
```

#### Adult_News
- adult_transfer_direct.py

```bash
$SPARK_HOME/bin/spark-submit \
--master spark://spark-master:7077 \
--num-executors 2 \
--executor-cores 2 \
--executor-memory 1536m \
--jars /opt/workspace/jars/bson-4.0.5.jar,\
/opt/workspace/jars/mongo-spark-connector_2.12-3.0.1.jar,\
/opt/workspace/jars/mongodb-driver-core-4.0.5.jar,\
/opt/workspace/jars/mongodb-driver-sync-4.0.5.jar,\
/opt/workspace/jars/mysql-connector-java-8.0.21.jar \
/opt/workspace/scripts/adult_transfer_direct.py
```

---

### Spark to Mysql With Wordcount

- HDFS 사용 안할 때
- spark 에서 word count 하고 mysql 로 옮기기

#### Kid_News
- kid_wordcount_mysql_direct.py

```bash
$SPARK_HOME/bin/spark-submit \
--master spark://spark-master:7077 \
--num-executors 2 \
--executor-cores 2 \
--executor-memory 1536m \
--jars /opt/workspace/jars/bson-4.0.5.jar,\
/opt/workspace/jars/mongo-spark-connector_2.12-3.0.1.jar,\
/opt/workspace/jars/mongodb-driver-core-4.0.5.jar,\
/opt/workspace/jars/mongodb-driver-sync-4.0.5.jar,\
/opt/workspace/jars/mysql-connector-java-8.0.21.jar \
/opt/workspace/scripts/kid_wordcount_mysql_direct.py
```


#### Adult_News
- adult_wordcount_mysql_direct.py

```bash
$SPARK_HOME/bin/spark-submit \
--master spark://spark-master:7077 \
--num-executors 2 \
--executor-cores 2 \
--executor-memory 1536m \
--jars /opt/workspace/jars/bson-4.0.5.jar,\
/opt/workspace/jars/mongo-spark-connector_2.12-3.0.1.jar,\
/opt/workspace/jars/mongodb-driver-core-4.0.5.jar,\
/opt/workspace/jars/mongodb-driver-sync-4.0.5.jar,\
/opt/workspace/jars/mysql-connector-java-8.0.21.jar \
/opt/workspace/scripts/adult_wordcount_mysql_direct.py
```
