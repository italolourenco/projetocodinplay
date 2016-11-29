
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
