
@tag
Feature: Testar contato com o Administrador
##Historia 013 - Usuário Realiza Contato com Admin
##Eu como usuário desejo enviar uma mensagem para o administrador para informar duvidas, sugestoes ou reclamações

@tag1
Scenario: Usuario Acessa Opcao de Contato com Admin
Given Usuario logado no sistema
When  Usuario seleciona a opcao CONTATO
Then  Usuario deve ser direcionado para tela de contato

@tag2
Scenario: Usuario Envia mensagem com sucesso
Given Usuario logado no sistema
And   Usuario na tela de contato
And   Usuario seleciona o tipo de mensagem e preenche o campo mensagem com "oi eu sou o goku" 
And   Usuario seleciona a opcao enviar
Then  Deve ser exibido uma mensagem para o usuario informando que a mensagem foi enviada com sucesso

@tag3
Scenario: Usuario Envia mensagem em branco
Given Usuario logado no sistema
And   Usuario na tela de contato
And   Usuario seleciona o tipo de mensagem e preenche o campo mensagem com " " 
And   Usuario seleciona a opcao enviar
Then  Deve ser exibido uma mensagem de erro para o usuario informando que a mesangem esta vazia