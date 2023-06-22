
CREATE DATABASE dblogin;
USE dblogin;

DROP TABLE IF EXISTS prodotti;

CREATE TABLE prodotti (
  ID int primary key AUTO_INCREMENT,
  NOME varchar(45) NOT NULL,
  CATEGORIA varchar(45) NOT NULL,
  QTA_DISP int NOT NULL,
  MARCA_MOD varchar(45) NOT NULL,
  PREZZO float NOT NULL
  );
  
  CREATE TABLE USERS (	
  ID INT PRIMARY KEY AUTO_INCREMENT,
  SURNAME CHAR(20) NOT NULL,
  USERNAME CHAR(20) NOT NULL,
  PASSWORD CHAR(20) NOT NULL,
  ISADMIN INT NOT NULL
);


INSERT INTO USERS VALUES (1,"Dylan","root","root", 1);
INSERT INTO USERS VALUES (2,"Eva","EK67","walter", 0);
  
INSERT INTO prodotti values (1, "revolver", "pistola", 5, "Wesson", 40.50);
INSERT INTO prodotti values (2, "ascia", "arma_bianca", 6, "tomahok", 30.40);
INSERT INTO prodotti values (3, "proiettili4.5mm", "munizioni", 5, "Wesson", 16);
