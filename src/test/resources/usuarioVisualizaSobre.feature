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
Feature: Testar as informacoes do sonre
##Historia 014 - Usuário Visualiza Sobre
##Eu como usuário gostaria de visualizar mais informações sobre o sistema para solucionar dúvidas

@tag1
Scenario: Usuario Visualiza Informacoes do Ranking
Given Usuario logado no sistema
When  Usuario seleciona a opcao SOBRE
Then  Usuario deve ser direcionado para tela Sobre
And   Deve ser exibido os dados do Sobre a plataforma

