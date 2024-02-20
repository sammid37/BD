CREATE DATABASE projetoBD;
USE projetoBD;

-- DROP TABLE lojas;
-- CREATE TABLE lojas (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     nome VARCHAR(255)
-- );

-- DROP TABLE clientes;
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT  PRIMARY KEY,
    nome VARCHAR(255),
    data_nasc DATE,
    tel VARCHAR(20),
    email VARCHAR(255),
    senha VARCHAR(255),
    time VARCHAR(255),
    one_piece BOOLEAN,
    cidade VARCHAR(255),
    estado VARCHAR(255)
);

SELECT * FROM clientes; 
-- DROP TABLE vendedores;
-- CREATE TABLE vendedores (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     nome VARCHAR(255),
--     data_nasc DATE,
--     tel VARCHAR(20),
--     email VARCHAR(255),
--     senha VARCHAR(255),
--     id_vendedor INT,
--     loja_id INT,
--     FOREIGN KEY (loja_id) REFERENCES lojas(id)
-- );
select * from vendedores;