CREATE TABLE USERACCOUNT(
    id INT AUTO_INCREMENT PRIMARY KEY,
       username varchar(50) UNIQUE NOT NULL,
    password varchar(50) NOT NULL,
    authentication varchar(255)
);