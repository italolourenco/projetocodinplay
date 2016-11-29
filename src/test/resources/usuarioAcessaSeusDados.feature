
@tag
Feature: Testar Informações do Usuario
##Historia 003 - Usuário Visualiza o painel de informações e progresso
##Eu como usuário gostaria de visualizar meus dados para avaliar meu progresso

@tag1
Scenario: Usuario Visualiza Informacoes
Given Usuario logado no sistema
When  Usuario seleciona a opcao Home
Then  Usuario deve ser direcionado para tela principal usuario
And   Usuario visualiza as informações do site com os seus dados
