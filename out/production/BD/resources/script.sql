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

INSERT INTO pedidos VALUES (1, 1, 1, 35.00, 5.00, "PIX", "FINALIZADO") ;

-- Geral
SELECT
	(SELECT COUNT(*) FROM clientes) AS TotalClientes,
    (SELECT SUM(valor_total) from pedidos) AS ValorEmPedidos,
	(SELECT COUNT(*) FROM pedidos) AS TotalPedidos,
	(SELECT COUNT(*) FROM produtos) AS TotalProdutosCadastrados,
    (SELECT COUNT(*) FROM produtos WHERE estoque > 0) AS TotalProdutosEmEstoque,
	CURDATE() AS DataGeracaoRelatorio; -- Adiciona a data de geração do relatório
      
-- SELECT simplificado para pedidos      
SELECT
    p.id_pedido AS 'Id da Venda',
    c.nome AS 'Nome do Cliente',
    CONCAT('R$ ', FORMAT(p.valor_total, 2)) AS 'Valor do Pedido',
    CONCAT('R$ ', FORMAT(p.desconto, 2)) AS 'Desconto',
    CONCAT('R$ ', FORMAT(p.valor_total - p.desconto, 2)) AS 'Valor total da compra',
    p.status AS 'Status'
   -- DATE_FORMAT(p.data_venda, '%d/%m/%Y') AS 'Data da Venda'
FROM
    pedidos p
JOIN
    clientes c ON p.id_cliente_pedido = c.id_cliente;

-- SELECT simples para obter descricao sobre produtos
SELECT
	prod.id_produto AS CodigoProduto, 
    prod.titulo AS NomeProduto, 
    prod.estoque AS QtdProdutosEstoque, 
    prod.preco AS PrecoUnitario
FROM produtos prod;
