#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios 
#<> (placeholder)
#""
## (Comments)

#Sample Feature Definition Template
@tag
Feature: Testar criacao de Desafio Semanal
##Historia 017 - Administrador Cria Nova Atividade
##Eu como Administrador desejo criar um novo desafio semanal para manter o sistema sempre atualizado

@tag1
Scenario: Administrador Acessa De Cadastro de Desafio Semanal
Given Administrador logado no sistema
When  Administrador seleciona a opcao NOVA TAREFA
Then  Administrador deve ser direcionado para tela nova tarefa

@tag2
Scenario: Administrador Cadastra Desafio com Sucesso
Given Administrador logado no sistema
And   Administrador na tela Cadastro de Tarefas
When  Administrador seleciona o tipo da atividade "Desafio Semanal"
And   Administrador preenche a Descricao
And   Administrador seleciona o nivel
And   Administrador preenche os campos das alternativas e escolhe a resposta correta
And   Administrador Seleciona a opcao Salvar
And   Campos validos
Then  Deve ser exibido para o adminstrador uma mensagem de sucesso informando que o desafio foi cadastrado

@tag3
Scenario: Administrador Cadastra Desafio sem Sucesso
Given Administrador logado no sistema
And   Administrador na tela Cadastro de Tarefas
When  Administrador seleciona o tipo da atividade "Desafio Semanal"
And   Administrador preenche a Descricao
And   Administrador seleciona o nivel
And   Administrador preenche os campos das alternativas e escolhe a resposta correta
And   Administrador Seleciona a opcao Salvar
And   Campos invalidos
Then  Deve ser exibido para o adminstrador uma mensagem de erro informando que o desafio nao foi cadastrado
