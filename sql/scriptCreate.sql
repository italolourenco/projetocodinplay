CREATE TABLE IF NOT EXISTS instituicao (
  id_instituicao SERIAL,
  nome VARCHAR(45) NULL,
  abreviacao VARCHAR(45) NULL,
  estado VARCHAR(45) NULL,
  telefone VARCHAR(45) NULL,
  site VARCHAR(45) NULL,
  PRIMARY KEY (id_instituicao));

CREATE TABLE IF NOT EXISTS Patente(
  id_patente SERIAL,
  nome VARCHAR(45) NULL,
  pontuacao_min INT NULL,
  pontuacao_max INT NULL,
  PRIMARY KEY (id_patente));
  
  
CREATE TABLE IF NOT EXISTS nivel(
  id_nivel SERIAL,
  nome VARCHAR(45) NULL,
  pontuacaototal INT NULL,
  descricaotema TEXT NULL,
  PRIMARY KEY (id_nivel));

  
CREATE TABLE IF NOT EXISTS tarefa (
  id_tarefa SERIAL,
  nome VARCHAR(45) NULL,
  pontuacao_max INT NULL,
  pontuacao_min INT NULL,
  descricao TEXT NULL,
  id_nivel INT NOT NULL,
  PRIMARY KEY (id_tarefa),
  FOREIGN KEY (id_nivel)
  REFERENCES nivel(id_nivel));


CREATE TABLE IF NOT EXISTS atividade (
  id_atividade SERIAL,
  nome VARCHAR(45) NULL,
  descricaoproblema TEXT NULL,
  pontuacao INTEGER NOT NULL,
  respostaA VARCHAR(45) NULL,
  respostaB VARCHAR(45) NULL,
  respostaC VARCHAR(45) NULL,
  respostaD VARCHAR(45) NULL,
  respostaE VARCHAR(45) NULL,
  respostaCerta INT NULL,
  tipo INT NULL,
  id_tarefa INT NOT NULL,
  id_nivel INT NOT NULL,
  PRIMARY KEY (id_atividade),
  FOREIGN KEY (id_nivel) REFERENCES nivel(id_nivel),
  FOREIGN KEY (id_tarefa) REFERENCES tarefa(id_tarefa));
  
   
CREATE TABLE IF NOT EXISTS usuario(
  id_usuario SERIAL,
  nome VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  datanascimento VARCHAR(45),
  telefone VARCHAR(45) NULL,
  tipo INT,
  sexo INT,
  senha VARCHAR(45) NULL,
  id_patente INT NOT NULL,
  pontuacao INT NULL,
  id_instituicao INT NOT NULL,
  id_nivel INT NOT NULL,
  PRIMARY KEY (id_usuario),
  FOREIGN KEY (id_patente ) REFERENCES Patente(id_patente),
  FOREIGN KEY (id_instituicao) REFERENCES instituicao(id_instituicao),
  FOREIGN KEY (id_nivel) REFERENCES nivel(id_nivel));

  CREATE TABLE IF NOT EXISTS usuario_atividade (
  id_usuario INT NOT NULL,
  id_atividade INT NOT NULL,
  data_realizada VARCHAR(45) NOT NULL,
  status INT NULL,
  PRIMARY KEY (id_usuario, id_atividade, data_realizada),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
  FOREIGN KEY (id_atividade) REFERENCES atividade(id_atividade));