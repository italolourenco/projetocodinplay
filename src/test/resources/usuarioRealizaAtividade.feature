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
Feature: Testar a realização de uma atividade
##Historia 006 - Usuário Realiza Atividade
##Eu como usuário gostaria de realizar uma atividade para conquistar pontos

@tag1
Scenario: Usuario Realiza Atividade com sucesso
Given Usuario logado no sistema
And   Usuario na tela de Resolucao de Atividade
When  Usuario seleciona a alternativa correta
And   Usuario seleciona a opcao enviar
Then  Deve ser exibido para o usuario uma mensagem de sucesso
And   Deve ser realizado a soma da pontuacao do usuario com a pontuacao da atividade

@tag2
Scenario: Usuario Realiza Atividade sem sucesso
Given Usuario logado no sistema
And   Usuario na tela de Resolucao de Atividade
When  Usuario seleciona a alternativa incorreta
And   Usuario seleciona a opcao enviar
Then  Deve ser exibido para o usuario uma mensagem de erro informando que a resposta esta errada
And   Usuario deve ser direcionado para tela de informações sobre o nivel selecionado
