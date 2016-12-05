INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Instituto Federal do Espirito Santo', 'IFES', 'ES', '2732458769', 'www.ifes.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Universidade Federal do Espirito Santo', 'UFES', 'ES', '2732458607', 'www.ufes.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Universidade Federal de Minas Gerais', 'UFMG', 'MG', '2732458678', 'www.ufmg.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Faesa', 'FAESA', 'ES', '2732458690', 'www.faesa.com.br');  

INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nível 1', 80, 'Programação Básica');
INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nível 2', 80, 'Programação Intermediária');
INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nível 3', 140, 'Programação Avançada');

INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Newbie', 30, 0, 'Nesta Etapa você vai encontra atividades que abordam conceitos gerais sobre a programação', 1);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Amador', 50, 30, 'Nesta Etapa você vai encontra atividades sobre operadores lógicos', 1);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Lenda', 80, 50, 'Nesta etapa você colocará seu conhecimento em prova com exercícios das tarefas anteriores ! ', 1);

INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Newbie', 70, 50, 'Nesta Etapa você vai encontrar desafios envolvendo testes de mesa', 2);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Amador', 90, 70, 'Agora é hora de solucionar algumas questões de lógica !', 2);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Lenda', 110, 90, 'Esta preparado para resolver questões mais dificies sobre teste de mesa e lógica ?', 2);

INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Newbie', 130, 110, 'Nesta etapa você encontrara questões sobre estruturas de condicionais', 3);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Amador', 150, 130, 'Nesta etapa você encontrara questões sobre estruturas de repetição', 3);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Lenda', 180, 150, 'Nesta etapa é hora de provar que é a Lenda do CodinPlay! ', 3);

INSERT INTO patente (nome,pontuacao_min , pontuacao_max) VALUES ('Júnior', 0, 80);
INSERT INTO patente (nome,pontuacao_min, pontuacao_max) VALUES ('Pleno', 81, 130);
INSERT INTO patente (nome,pontuacao_min, pontuacao_max) VALUES ('Senior',131,200);

INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Você conhece as Variáveis?','Qual das seguintes variáveis só aceita números inteiros?',15,'Single','String','Integer','Double','Long',3,1,1,1);
INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Você conhece essa propriedade?','Qual das seguintes variáveis só aceita valores verdadeiro ou falso?',15,'Boolean','String','Integer','Double','Long',1,1,1,1);


INSERT INTO usuario (nome, email, telefone, tipo, sexo, senha, id_patente, pontuacao, id_instituicao, id_nivel, datanascimento) VALUES ('Paulin', 'paulo@kdeanota','99990909' ,0, 2, '123', 1, 0, 1, 1, '24/04/1990');