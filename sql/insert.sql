INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Instituto Federal do Espirito Santo', 'IFES', 'ES', '2732458769', 'www.ifes.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Universidade Federal do Espirito Santo', 'UFES', 'ES', '2732458607', 'www.ufes.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Universidade Federal de Minas Gerais', 'UFMG', 'MG', '2732458678', 'www.ufmg.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Faesa', 'FAESA', 'ES', '2732458690', 'www.faesa.com.br');  

INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nivel 1', 100, 'Operadores Logicos');
INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nivel 2', 150, 'Teste de mesa');
INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nivel 3', 200, 'Estruturas Condicionais');

INSERT INTO tarefa (nome, pontuacao_max, descricao, id_nivel) VALUES ('Newbie', 20, 'Operadores Logicos', 1);
INSERT INTO tarefa (nome, pontuacao_max, descricao, id_nivel) VALUES ('Amador', 30, 'Operadores Logicos', 1);
INSERT INTO tarefa (nome, pontuacao_max, descricao, id_nivel) VALUES ('Lenda', 50, 'Operadores Logicos', 1);

INSERT INTO tarefa (nome, pontuacao_max, descricao, id_nivel) VALUES ('Newbie', 20, 'Estruturas', 2);
INSERT INTO tarefa (nome, pontuacao_max, descricao, id_nivel) VALUES ('Amador', 30, 'Estruturas', 2);
INSERT INTO tarefa (nome, pontuacao_max, descricao, id_nivel) VALUES ('Lenda', 50, 'Estruturas', 2);

INSERT INTO tarefa (nome, pontuacao_max, descricao, id_nivel) VALUES ('Newbie', 20, 'Repeticao', 3);
INSERT INTO tarefa (nome, pontuacao_max, descricao, id_nivel) VALUES ('Amador', 30, 'Repeticao', 3);
INSERT INTO tarefa (nome, pontuacao_max, descricao, id_nivel) VALUES ('Lenda', 50, 'Repeticao', 3);
