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
Feature: Testar o login do usuario no sistema
##Historia 002 - Usuário Realiza Login
##Eu como usuário gostaria de logar no sistema para realizar as atividades propostas e melhorar minhas habilidades de programação

@tag1
Scenario: Testar Login Valido
Given  Usuario esta na pagina incial
When  Preenche os campos login com <login>, senha com <senha> e clica em entrar
And   login e senha são validos
Then  Deve ser exibida a página principal usuario
  
@tag2
Scenario: Testar Login Invalido
Given Usuario esta na pagina incial
When Preenche os campos login com <login>, senha com <senha> e clica em entrar
And   login ou senha são invalidos
Then Deve ser exibida uma mensagem de erro para o usuário
