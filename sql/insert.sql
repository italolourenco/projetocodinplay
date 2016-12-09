INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Instituto Federal do Espirito Santo', 'IFES', 'ES', '2732458769', 'www.ifes.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Universidade Federal do Espirito Santo', 'UFES', 'ES', '2732458607', 'www.ufes.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Universidade Federal de Minas Gerais', 'UFMG', 'MG', '2732458678', 'www.ufmg.edu.br');
INSERT INTO instituicao (nome, abreviacao, estado, telefone, site) VALUES ('Faesa', 'FAESA', 'ES', '2732458690', 'www.faesa.com.br');  

INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nível 1', 90, 'Programação Básica');
INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nível 2', 180, 'Programação Intermediária');
INSERT INTO nivel (nome, pontuacaototal, descricaotema) VALUES ('Nível 3', 250, 'Programação Avançada');

INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Newbie', 30, 0, 'Nesta Etapa você vai encontra atividades que abordam conceitos gerais sobre a programação', 1);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Amador', 60, 30, 'Nesta Etapa você vai encontra atividades sobre operadores lógicos', 1);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Lenda', 90, 60, 'Nesta etapa você colocará seu conhecimento em prova com exercícios das tarefas anteriores ! ', 1);

INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Newbie', 120, 90, 'Nesta Etapa você vai encontrar desafios envolvendo testes de mesa', 2);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Amador', 150, 120, 'Agora é hora de solucionar algumas questões de lógica !', 2);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Lenda', 180, 150, 'Esta preparado para resolver questões mais dificies sobre teste de mesa e lógica ?', 2);

INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Newbie', 200, 180, 'Nesta etapa você encontrara questões sobre estruturas de condicionais', 3);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Amador', 220, 200, 'Nesta etapa você encontrara questões sobre estruturas de repetição', 3);
INSERT INTO tarefa (nome, pontuacao_max, pontuacao_min, descricao, id_nivel) VALUES ('Lenda', 260, 240, 'Nesta etapa é hora de provar que é a Lenda do CodinPlay! ', 3);

INSERT INTO patente (nome,pontuacao_min , pontuacao_max) VALUES ('Júnior', 0, 80);
INSERT INTO patente (nome,pontuacao_min, pontuacao_max) VALUES ('Pleno', 80, 130);
INSERT INTO patente (nome,pontuacao_min, pontuacao_max) VALUES ('Senior',130,200);

INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Você conhece as Variáveis?','Qual das seguintes variáveis só aceita números inteiros?',15,'Single','String','Integer','Double','Long',3,1,1,1);
INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Você conhece essa propriedade?','Qual das seguintes variáveis só aceita valores verdadeiro ou falso?',15,'Boolean','String','Integer','Double','Long',1,1,1,1);
INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Você conhece o tamanho desse tipo?','Qual é o tamanho de um inteiro?',15,'1','5','4','6','12',1,1,1,1);
INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Manja das Variáveis?','Qual nome de variável abaixo não é valido ?',20,'Tempo','nota_fiscal','vetTeste','char','OlA MuNdO',4,1,2,1);
INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Então você conhece as constantes ...','Indique qual número abaixo é uma constante inteira válida ?',20,'100','3.0','-234','0L','2 345 24',4,1,2,1);
INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Então você conhece as constantes 2','Indique qual número abaixo é uma constante inteira errada ?',20,'0L','0L','-234','0L','0L',3,1,2,1);
INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Hora do teste','Qual a saída esperada para o codigo abaixo ? <br /> a = 10 <br /> b = 7 <br /> c = a <br /> a = a + b + c <br /> print(a)" ',30,'27','30','5','7','10',1,1,3,1);
INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Hora do teste','Qual a saída esperada para o codigo abaixo ? <br /> a = 20 <br /> b = 5 <br /> c = a - (a + b) <br /> print(c)" ',20,'20','30','5','7','10',1,1,4,2);
INSERT INTO atividade (nome, descricaoproblema, apontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Hora do teste','Qual a saída esperada para o codigo abaixo ? <br /> a = 10 <br /> b = 5 <br /> c = a - (a + b) <br /> print(c)" ',20,'20','30','15','7','10',1,1,4,2);
INSERT INTO atividade (nome, descricaoproblema, pontuacao, respostaa, respostab, respostac, respostad, respostae, respostacerta, tipo, id_tarefa, id_nivel ) VALUES ('Hora do teste','Qual a saída esperada para o codigo abaixo ? <br /> a = 5 <br /> b = 10 <br /> c = a <br /> c = b <br /> print(c)" ',5,'10','15','5','30','25',1,2,1,1);

INSERT INTO usuario (nome, email, telefone, tipo, sexo, senha, id_patente, pontuacao, id_instituicao, id_nivel, datanascimento) VALUES ('Paulo', 'paulo@kdeanota','99990909' ,0, 2, '123', 1, 0, 1, 1, '24/04/1990');