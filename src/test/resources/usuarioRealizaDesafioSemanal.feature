
@tag
Feature: Testar a realização do Desafio Semanal
##Historia 009 - Usuário Realiza Desafio Semanal
##Eu como usuário desejo solucionar o desafio semanal para obter pontuação extra.

@tag1
Scenario: Usuario Realiza Desafio semanal com sucesso
Given Usuario logado no sistema
And   Usuario na tela principal
When  Usuario seleciona a opcao Iniciar Desafio Semanal
And   Desafio Semanal Desbloqueado
And   Usuario Seleciona a opcao Correta
And   Usuario Seleciona a opcao enviar
Then  Deve ser exibido para o usuario uma mensagem de sucesso informando que ele escolheu a respota certa
And   Deve ser feita a somatoria da pontuacao do desafio com a pontuacao atual do usuario

@tag2
Scenario: Desafio Semanal Bloqueado
Given Usuario logado no sistema
And   Usuario na tela principal
When  Usuario seleciona a opcao Iniciar Desafio Semanal
And   Desafio Semanal Bloqueado
Then  Deve ser exibido para o usuario uma mensagem de erro informando que o desafio semanal esta bloqueado

@tag3
Scenario: Usuario Realiza Desafio Semanal sem sucesso
Given Usuario logado no sistema
And   Usuario na tela principal
When  Usuario seleciona a opcao Iniciar Desafio Semanal
And   Desafio Semanal Desbloqueado
And   Usuario Seleciona a opcao Incorreta
And   Usuario Seleciona a opcao enviar
Then  Deve ser exibido para o usuario uma mensagem de erro informando que ele escolheu a respota errada