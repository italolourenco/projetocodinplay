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
Feature: Testar o login do Administrador no sistema


@tag1
Scenario: Testar Login Valido
Given  Admin esta na pagina incial
When  Preenche os campos login com "igor@condiplay", senha com 1234 e clica em entrar
And   login e senha sao validos
Then  Deve ser exibida a página principal do administrador
  
@tag2
Scenario: Testar Login Invalido
Given Admin esta na pagina incial
When Preenche os campos login com "igor@condiplay", senha com 12345 e clica em entrar
And   login ou senha sao invalidos
Then Deve ser exibida uma mensagem de erro para o adminstrador
