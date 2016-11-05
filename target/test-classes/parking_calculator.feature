#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios 
#<> (placeholder)
#""
## (Comments)

# language: pt
#Sample Feature Definition Template
@tag
Funcionalidade: Belly

@tag1
Cenario: Um pouco de pepinos
  Dado Eu tenho 42 pepinos na minha barriga
  Quando Eu espero 1 hora
  Entao Minha barriga deve rosnar


