drop user if exists 'springdemouser'@'localhost';

create user 'springdemouser'@'localhost' identified by 'damilare';

drop database if exists springDemoDB;

create database springDemoDB;

use springDemoDB;

grant all privileges on springDemoDB.* to 'springdemouser'@'localhost';

flush privileges;