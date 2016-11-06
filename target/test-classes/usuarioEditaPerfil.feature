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
Feature: Testar a opcao editar perfil
##Historia 012 - Usuário Edita suas informacoes
##Eu como usuário gostaria de editar meus dados para manter o sistema sempre atualizado

@tag1
Scenario: Usuario Acessa opcao editar Perfil
Given Usuario logado no sistema
And   Usuario na tela pricipal usuario
When  Usuario seleciona a opcao Editar Perfil
Then  Usuario deve ser direcionado para tela editar Perfil

@tag2
Scenario: Usuario Edita Perfil Com sucesso
Given Usuario logado no sistema
And   Usuario na tela Editar Perfil
When  Usuario preenche os campos desejados
And   Seleciona a opcao Enviar
And   Dados validos
Then  Deve ser exibido uma mensagem para o usuario informando que a edicao foi realizada com sucesso

@tag3
Scenario: Usuario Edita Perfil sem sucesso
Given Usuario logado no sistema
And   Usuario na tela Editar Perfil
When  Usuario preenche os campos desejados
And   Seleciona a opcao Enviar
And   Dados invalidos
Then  Deve ser exibido uma mensagem para o usuario informando que a edicao nao foi realizada informando os campos com erro
