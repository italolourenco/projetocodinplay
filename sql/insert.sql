INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Instituto Federal do Espirito Santo', 'IFES', 'ES', '2732458769', 'www.ifes.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Universidade Federal do Espirito Santo', 'UFES', 'ES', '2732458607', 'www.ufes.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Universidade Federal de Minas Gerais', 'UFMG', 'MG', '2732458678', 'www.ufmg.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Faesa', 'FAESA', 'ES', '2732458690', 'www.faesa.com.br');  

INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nivel 1', 100, 'Operadores Logicos');
INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nivel 2', 150, 'Teste de mesa');
INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nivel 3', 200, 'Estruturas Condicionais');

INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Newbie', 20, 0, 'Operadores Logicos', 1);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Amador', 30, 20, 'Operadores Logicos', 1);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Lenda', 50, 30, 'Operadores Logicos', 1);

INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Newbie', 70, 50, 'Estruturas', 2);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Amador', 90, 70, 'Estruturas', 2);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Lenda', 110, 90, 'Estruturas', 2);

INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Newbie', 130, 110, 'Repeticao', 3);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Amador', 150, 130, 'Repeticao', 3);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Lenda', 180, 150, 'Repeticao', 3);

INSERT INTO patente (nome,pontuacao_min , pontuacao_max) VALUES ('Júnior', 0, 10);
INSERT INTO patente (nome,pontuacao_min, pontuacao_max) VALUES ('Pleno',10, 20);
INSERT INTO patente (nome,pontuacao_min, pontuacao_max) VALUES ('Senior',20,30);

INSERT INTO usuario (nome, email, telefone, tipo, sexo, senha, id_patente, pontuacao, id_instituicao, id_nivel, datanascimento) VALUES ('Paulin', 'paulo@kdeanota','99990909' ,0, 2, '123', 1, 0, 1, 1, '24/04/1990');