@tag
Feature: Testar criacao de Atividade
##Historia 016 - Administrador Cria Nova Atividade
##Eu como Administrador desejo criar uma nova atividade para um nível/tarefa para manter o sistema sempre atualizado

@tag1
Scenario: Administrador Acessa De Cadastro de Tarefa
Given Administrador logado no sistema
When  Administrador seleciona a opcao NOVA TAREFA
Then  Administrador deve ser direcionado para tela nova tarefa

@tag2
Scenario: Administrador Cadastra Tarefa com Sucesso
Given Administrador logado no sistema
And   Administrador na tela Cadastro de Tarefas
When  Administrador seleciona o tipo da atividade "Tarefa"
And   Administrador preenche a Descricao
And   Administrador seleciona o nivel
And   Administrador preenche os campos das alternativas e escolhe a resposta correta
And   Administrador Seleciona a opcao Salvar
And   Campos validos
Then  Deve ser exibido para o adminstrador uma mensagem de sucesso informando que a atividade foi cadastrada

@tag3
Scenario: Administrador Cadastra Tarefa sem Sucesso
Given Administrador logado no sistema
And   Administrador na tela Cadastro de Tarefas
When  Administrador seleciona o tipo da atividade "Tarefa"
And   Administrador preenche a Descricao
And   Administrador seleciona o nivel
And   Administrador preenche os campos das alternativas e escolhe a resposta correta
And   Administrador Seleciona a opcao Salvar
And   Campos invalidos
Then  Deve ser exibido para o adminstrador uma mensagem de erro informando que a atividade nao foi cadastrada

