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