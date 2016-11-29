@tag
Feature: Cadastro de usuário
##Historia 001 - Usuário realiza Cadastro
##Eu como usuário gostaria de me cadastrar no sistema para ter acesso ao conteúdo da plataforma

@tag1
Scenario: Testar acesso a pagina
Given Usuario esta na pagina incial
When  Usuario clica na opcao de cadastro
Then  Usuario deve ser redicerionado para a tela de cadastro
  
@tag2
Scenario: Testar Cadastro Valido
Given Usuario esta na tela de cadastro
When  Preenche os campos nome com "Italo", instituicao com "IFES", sexo com "Masculino", data de nascimento com "30/03/1994", email com "italolt10@gmail.com", telefone com "999999999", senha com "1234", confirmar senha com "1234"
And   Usuario clica em confirmar
And   campos validos
And   Usuario não existe no sistema
Then  Deve ser exibida uma mensagem de cadastro efetuado com sucesso

@tag3
Scenario: Testar Cadastro de usuario ja existente
Given Usuario esta na tela de cadastro
When  Preenche os campos nome com "Italo", instituicao com "IFES", sexo com "Masculino", data de nascimento com "30/03/1994", email com "italolt10@gmail.com", telefone com "999999999", senha com "1234", confirmar senha com "1234"
And   Usuario clica em confirmar
And   campos validos
And   Usuario ja existe no sistema
Then  Deve ser exibida uma mensagem de erro informando que ja existe um usuario com esse email

@tag4
Scenario: Testar Erro de Cadastro
Given Usuario esta na tela de cadastro
When  Preenche os campos nome com "Italo", instituicao com "IFES", sexo com "Masculino", data de nascimento com "30/03/1994", email com "italolt10@gmail.com", telefone com "9999999999", senha com "1234", confirmar senha com "1234"
And   Usuario clica em confirmar
And   campos invalidos
Then  Deve ser exibida uma mensagem de erro informando o(s) campo(s) com dado invalido

@tag1
Scenario: Testar Cancelamento de Cadastro
Given Usuario esta na tela de cadastro
When  Usuario clica na opcao de cancelar
Then  Usuario deve ser redicerionado para a tela de login
