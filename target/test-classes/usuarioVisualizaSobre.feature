
@tag
Feature: Testar as informacoes do sonre
##Historia 014 - Usuário Visualiza Sobre
##Eu como usuário gostaria de visualizar mais informações sobre o sistema para solucionar dúvidas

@tag1
Scenario: Usuario Visualiza Informacoes do Ranking
Given Usuario logado no sistema
When  Usuario seleciona a opcao SOBRE
Then  Usuario deve ser direcionado para tela Sobre
And   Deve ser exibido os dados do Sobre a plataforma

