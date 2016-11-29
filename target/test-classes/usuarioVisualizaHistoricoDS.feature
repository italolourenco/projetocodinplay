
@tag
Feature: Testar as informações do Historico do Desafio Semanal
##Historia 008 - Usuário Visualiza Histórico do Desafio Semanal
##Eu como usuário desejo visualizar meu histórico de desafios semanais, para verificar quais eu realizei ou não

@tag1
Scenario: Usuario Realiza Visualiza Informações do Historico do Desafio Semanal
Given Usuario logado no sistema
When  Usuario seleciona a opcao Home
Then  Deve ser direcionado para tela principal usuario
And   Na area Desafio Semanal, deve ser exibida os dados do historico do desafio semanal para o usuário
