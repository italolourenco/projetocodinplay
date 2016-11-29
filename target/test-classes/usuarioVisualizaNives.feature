
@tag
Feature: Testar as informações de nivel do usuario
##Historia 004 - Usuário Visualiza Níveis
##Eu como usuário gostaria de visualizar os níveis e verificar quais eu já realizei e quais estão desbloqueados

@tag1
Scenario: Usuario Visualiza Informacoes
Given Usuario logado no sistema
When  Usuario seleciona a opcao JOGAR
Then  Usuario deve ser direcionado para tela progressao do usuario
And   Deve ser exibido os dados de progressão do usuario
And   Deve ser exibido os níveis e seu status de acordo com a progressão do usuario
