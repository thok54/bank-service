DROP DATABASE bank_service;
CREATE DATABASE bank_service;
USE bank_service;
    CREATE USER 'user' IDENTIFIED WITH mysql_native_password BY 'user';
    GRANT ALL on bank_project.* TO 'user';

     CREATE TABLE BANKS
       (
          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(20),
          address VARCHAR(50)
       );