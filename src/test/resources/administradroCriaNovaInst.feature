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
Feature: Testar cadastro de instituicao
##Historia 016 - Administrador Cria Nova Instituição
##Eu como Administrador desejo criar uma nova instituiçao no sistema para melhorar a qualidade do cadastro dos usuários

@tag1
Scenario: Administrador Acessa Cadastro de Insituicao
Given Administrador logado no sistema
When  Administrador seleciona a opcao NOVA INSTITUICAO
Then  Administrador deve ser direcionado para tela nova instituicao

@tag2
Scenario: Administrador Cadastra Instituicao com Sucesso
Given Administrador logado no sistema
And   Administrador na tela Nova Instituicao
When  Administrador preenche os dados
And   Administrador seleciona a opcao salvar
And   Dados validos
Then  Deve ser exibido para o adminstrador uma mensagem de sucesso informando que a instituicao foi cadastrada

@tag3
Scenario: Administrador Cadastra Instituicao sem Sucesso
Given Administrador logado no sistema
And   Administrador na tela Nova Instituicao
When  Administrador preenche os dados
And   Administrador seleciona a opcao salvar
And   Dados invalidos
Then  Deve ser exibido para o adminstrador uma mensagem de erro informando que a instituicao foi nao cadastrada
