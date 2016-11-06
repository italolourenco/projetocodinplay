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
Feature: Testar as informações do Historico do Desafio Semanal
##Historia 008 - Usuário Visualiza Histórico do Desafio Semanal
##Eu como usuário desejo visualizar meu histórico de desafios semanais, para verificar quais eu realizei ou não

@tag1
Scenario: Usuario Realiza Visualiza Informações do Historico do Desafio Semanal
Given Usuario logado no sistema
When  Usuario seleciona a opcao Home
Then  Deve ser direcionado para tela principal usuario
And   Na area Desafio Semanal, deve ser exibida os dados do historico do desafio semanal para o usuário
