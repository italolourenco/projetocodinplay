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
Feature: Testar escolha de atividade
##Historia 005 - Usuário Escolhe Atividade
##Eu como usuário gostaria de visualizar as atividades de um nível para verificar quais já foram realizadas e quais estão desbloqueadas para fazer

@tag1
Scenario: Usuario Escolhe atividade com sucesso
Given Usuario logado no sistema
When  Usuario seleciona a opcao JOGAR
And   Usuario Seleciona um nível
And   Nivel Desbloqueado
And   Usuario Escolhe uma Atividade
And   Atividade Desbloqueada
Then  Usuario deve ser direcionado para tela de Resolucao de Atividade

@tag2
Scenario: Usuario Escolhe nivel bloqueado
Given Usuario logado no sistema
When  Usuario seleciona a opcao JOGAR
And   Usuario Seleciona um nível
And   Nivel Bloqueado
Then  Deve ser exibida uma mensagem de erro para o usuario informando que o nivel esta bloqueado

@tag3
Scenario: Usuario Escolhe Atividade Bloqueada
Given Usuario logado no sistema
When  Usuario seleciona a opcao JOGAR
And   Usuario Seleciona um nível
And   Nivel Desbloqueado
And   Usuario Escolhe uma Atividade
And   Atividade Bloqueada
Then  Deve ser exibida uma mensagem de erro para o usuario informando que a ativdade esta bloqueado


