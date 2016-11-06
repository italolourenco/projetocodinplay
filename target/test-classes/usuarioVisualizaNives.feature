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
Feature: Testar as informações de nivel do usuario
##Historia 004 - Usuário Visualiza Níveis
##Eu como usuário gostaria de visualizar os níveis e verificar quais eu já realizei e quais estão desbloqueados

@tag1
Scenario: Usuario Visualiza Informacoes
Given Usuario logado no sistema
When  Usuario seleciona a opcao JOGAR
Then  Usuario deve ser direcionado para tela progressao do usuario
And   Deve ser exibido os dados de progressão do usuario
And   Deve ser exibido os níveis e seu status de acordo com a progressão do usuario
