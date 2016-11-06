
Feature: Testar as informações do Desafio Semanal
##Historia 007 - Usuário Visualiza Desafio Semanal
##Eu como usuário desejo visualizar o desafio semanal para verificar se ele esta disponivel para realização

@tag1
Scenario: Usuario Realiza Visualiza Informações do Desafio Semanal
Given Usuario logado no sistema
When  Usuario seleciona a opcao Home
Then  Deve ser direcionado para tela principal usuario
And   Na area Desafio Semanal, deve ser exibida os dados do desafio semanal para o usuário

