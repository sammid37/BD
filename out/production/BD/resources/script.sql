CREATE DATABASE projetoBD;
USE projetoBD;

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

-- DROP TABLE vendedores;
CREATE TABLE vendedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    data_nasc DATE,
    tel VARCHAR(20),
    email VARCHAR(255),
    senha VARCHAR(255)
);

CREATE TABLE produtos (
    id_produto INT AUTO_INCREMENT  PRIMARY KEY,
    tipo VARCHAR(20),
    titulo VARCHAR(20),
    descricao VARCHAR(255),
    estoque INT,
    preco DOUBLE
);

CREATE TABLE pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente_pedido INT,
    id_vendedor_pedido INT,
    valor_total DOUBLE,
    desconto DOUBLE,
    forma_pagamento VARCHAR(20),
    status VARCHAR(20),
    FOREIGN KEY (id_cliente_pedido) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_vendedor_pedido) REFERENCES vendedores(id)
);

SELECT * FROM produtos;
SELECT * FROM clientes;
SELECT * FROM vendedores;
SELECT * FROM produtos;
SELECT * FROM pedidos;