docker 
mysql image :sudo docker build -t mysql/mysql-server:latest

mysql container : sudo docker run -p 3306:3306 --name=mysqldb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ITS -e MYSQL_USER=root -e MYSQL_PASSWORD=root  mysql/mysql-server 

docker backend build image : sudo docker build -t its-be .  


