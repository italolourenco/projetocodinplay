
@tag
Feature: Testar cadastro de instituicao
##Historia 016 - Administrador Cria Nova Instituição
##Eu como Administrador desejo criar uma nova instituiçao no sistema para melhorar a qualidade do cadastro dos usuários

@tag1
Scenario: Administrador Acessa Cadastro de Insituicao
Given Administrador logado no sistema
When  Administrador seleciona a opcao NOVA INSTITUICAO
Then  Administrador deve ser direcionado para tela nova instituicao

@tag2
Scenario: Administrador Cadastra Instituicao com Sucesso
Given Administrador logado no sistema
And   Administrador na tela Nova Instituicao
When  Administrador preenche os dados
And   Administrador seleciona a opcao salvar
And   Dados validos
Then  Deve ser exibido para o adminstrador uma mensagem de sucesso informando que a instituicao foi cadastrada

@tag3
Scenario: Administrador Cadastra Instituicao sem Sucesso
Given Administrador logado no sistema
And   Administrador na tela Nova Instituicao
When  Administrador preenche os dados
And   Administrador seleciona a opcao salvar
And   Dados invalidos
Then  Deve ser exibido para o adminstrador uma mensagem de erro informando que a instituicao foi nao cadastrada
