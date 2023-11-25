CREATE DATABASE projetoBD;
USE projetoBD;

CREATE TABLE lojas (
    id INT PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE clientes (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    data_nasc DATE,
    tel VARCHAR(20),
    email VARCHAR(255),
    senha VARCHAR(255),
    id_cliente INT,
    time VARCHAR(255),
    one_piece BOOLEAN,
    uf VARCHAR(255),
    cidade VARCHAR(255)
);

CREATE TABLE vendedores (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    data_nasc DATE,
    tel VARCHAR(20),
    email VARCHAR(255),
    senha VARCHAR(255),
    id_vendedor INT,
    loja_id INT,
    FOREIGN KEY (loja_id) REFERENCES lojas(id)
);
