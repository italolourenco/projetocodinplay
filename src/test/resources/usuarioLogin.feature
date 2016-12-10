
@tag
Feature: Testar o login do usuario no sistema
##Historia 002 - Usuário Realiza Login
##Eu como usuário gostaria de logar no sistema para realizar as atividades propostas e melhorar minhas habilidades de programação

@tag1
Scenario: Testar Login Valido
Given Usuario entra com "italotog_@hotmail.com" e senha "123" validos
When  Usuario clica na opcao entrar
Then  Usuario deve estar logado no sistema
  
@tag2
Scenario: Testar Login Invalido
Given Usuario esta na pagina incial
When Preenche os campos login com <login>, senha com <senha> e clica em entrar
And   login ou senha são invalidos
Then Deve ser exibida uma mensagem de erro para o usuário
