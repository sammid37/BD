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