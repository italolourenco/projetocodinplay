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
Feature: Testar as informações do ranking de usuário
##Historia 011 - Usuário Visualiza Seu Ranking
##Eu como usuário gostaria de visualizar minha pontuação e a pontuação de outros usuarios para verificar meu desempenho

@tag1
Scenario: Usuario Visualiza Informacoes do Ranking
Given Usuario logado no sistema
When  Usuario seleciona a opcao RANKING
Then  Usuario deve ser direcionado para tela Ranking
And   Deve ser exibido os dados do ranking do usuario

@tag2
Scenario: Usuario Pesquisa dados de outro usuario com sucesso
Given Usuario logado no sistema
And   Usuario na tela Ranking
When  Usuario preenche o campo nome com "Gustavo"
And   Usuario seleciona a opcao Pesquisar
And   Usuario pesquisado existe
Then  Deve ser exibido o ranking com as informacoes do usuario pesquisado

@tag3
Scenario: Usuario Pesquisa dados de outro usuario sem sucesso
Given Usuario logado no sistema
And   Usuario na tela Ranking
When  Usuario preenche o campo nome com "Gustavo"
And   Usuario seleciona a opcao Pesquisar
And   Usuario pesquisado nao existe
Then  Deve ser exibido uma mensagem para o usuario informando que o usuario pesquisado nao existe
