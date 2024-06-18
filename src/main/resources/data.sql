INSERT INTO CATEGORIA (nome) VALUES
                                 ('Suplementos'),
                                 ('Vitaminas');

INSERT INTO PRODUTO (nome, descricao, preco, estoque, categoria_id) VALUES
                                                                        ('Whey Protein', 'Proteína do soro do leite', 150.00, 100, 1),
                                                                        ('Creatina', 'Suplemento para aumento de força', 90.00, 200, 1),
                                                                        ('Vitamina C', 'Suplemento vitamínico', 30.00, 300, 2);

INSERT INTO PEDIDO (data) VALUES
                              ('2024-06-16'),
                              ('2024-06-15');

INSERT INTO PEDIDO_PRODUTO (pedido_id, produto_id) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (2, 3);

INSERT INTO ROLE(role) VALUES
                           ('ADMIM'),
                           ('USER');

INSERT INTO USUARIO(nome,email,senha,status) VALUES
                                                 ('Heitor','heitor@al.infnet.edu.br','123456',1),
                                                 ('Maria','maria@al.infnet.edu.br','654321',2);

INSERT INTO USUARIO_ROLES(ROLES_ID,USUARIO_ID) VALUES
                                                   (1,1),
                                                   (2,1),
                                                   (2,2);