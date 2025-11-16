

```MYSQL
CREATE DATABASE train_reservation;
USE train_reservation;


-- CUSTOMER TABLE
CREATE TABLE customer (
  mailid VARCHAR(40) PRIMARY KEY,
  pword VARCHAR(20) NOT NULL,
  fname VARCHAR(20) NOT NULL,
  lname VARCHAR(20),
  addr VARCHAR(100),
  phno BIGINT NOT NULL
);

-- ADMIN TABLE
CREATE TABLE admin (
  mailid VARCHAR(40) PRIMARY KEY,
  pword VARCHAR(20) NOT NULL,
  fname VARCHAR(20) NOT NULL,
  lname VARCHAR(20),
  addr VARCHAR(100),
  phno BIGINT NOT NULL
);

-- TRAIN TABLE
CREATE TABLE train (
  tr_no INT PRIMARY KEY,
  tr_name VARCHAR(70) NOT NULL,
  from_stn VARCHAR(20) NOT NULL,
  to_stn VARCHAR(20) NOT NULL,
  seats INT NOT NULL,
  fare DECIMAL(8,2) NOT NULL
);

-- HISTORY TABLE
CREATE TABLE history (
  transid VARCHAR(36) PRIMARY KEY,
  mailid VARCHAR(40),
  tr_no INT,
  date DATE,
  from_stn VARCHAR(20) NOT NULL,
  to_stn VARCHAR(20) NOT NULL,
  seats INT NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (mailid) REFERENCES customer(mailid),
  FOREIGN KEY (tr_no) REFERENCES train(tr_no)
);

```
