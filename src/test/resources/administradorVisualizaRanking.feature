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
Feature: Testar as informações do ranking para o Administrador
##Historia 015 - Administrador Visualiza Ranking
##Eu como Administrador desejo verificar informações gerais do sistema, como pontuação de instituições, cursos para gerar relatórios mais detalhados

@tag1
Scenario: Administrador Acessa Tela de Ranking
Given Administrador logado no sistema
When  Administrador seleciona a opcao RANKING
Then  Administrador deve ser direcionado para tela Ranking Administrador
And   Deve ser exibido os dados do ranking do sistema para o Administrador

@tag2
Scenario: Administrador Pesquisa dados de outro usuario com sucesso
Given Administrador logado no sistema
And   Administrador na tela Ranking
When  Administrador preenche o campo nome com "Gustavo"
And   Administrador escolhe um filtro
And   Usuario pesquisado existe
And   Administrador seleciona a opcao Pesquisar
Then  Deve ser exibido o ranking com as informacoes do usuario pesquisado para o Administrador

@tag3
Scenario: Administrador Pesquisa dados de outro usuario sem sucesso
Given Administrador logado no sistema
And   Administrador na tela Ranking
When  Administrador preenche o campo nome com "Gustavo"
And   Usuario pesquisado nao existe
And   Administrador seleciona a opcao Pesquisar
Then  Deve ser exibido uma mensagem de para o Administrador informando que o usuario pesquisado nao existe
