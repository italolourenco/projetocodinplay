@tag
Feature: Testar o login do Administrador no sistema


@tag1
Scenario: Testar Login Valido
Given  Admin esta na pagina incial
When  Preenche os campos login com "igor@condiplay", senha com 1234 e clica em entrar
And   login e senha sao validos
Then  Deve ser exibida a página principal do administrador
  
@tag2
Scenario: Testar Login Invalido
Given Admin esta na pagina incial
When Preenche os campos login com "igor@condiplay", senha com 12345 e clica em entrar
And   login ou senha sao invalidos
Then Deve ser exibida uma mensagem de erro para o adminstrador
