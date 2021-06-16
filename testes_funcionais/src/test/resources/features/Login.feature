@Login @Funcionais
Feature: Login Cliente

  Background: Entrar na plataforma
    Given que um usuário acessou a opção de login

  @loginClienteAtivoLexisPass @regressivo
  Scenario Outline: CT01 – [LEXIS PASS] Login Cliente Ativo
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And digito a senha
    And clico no botao de "Entrar"
   # And clico no link de politica de privacidade
    #And sou direcionado para a pagina de politica de privacidade  
    #And aceito os termos de politica de privacidade         
   # And clico no botao de "Entrar"                                 
    And sou direcionado para a home logada

    Examples: 
      | cpf/cnpj          |
    # | "15701007057"     |
  	 	| "56721347008"     |
  #		| "algum cnpj aqui" |
  
  @loginClienteAtivoLexisReview @regressivo
  Scenario Outline: CT02 – [LEXIS REVIEW] Login Cliente Ativo
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And digito a senha
    And clico no botao de "Entrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de login via <email/sms>
    And clico no botao de "Continuar"
    And sou direcionado para a home logada
  # And sou direcionado para a pagina de politica de privacidade  
  # And aceito os termos de politica de privacidade 
  # And aceito os termos de politica de privacidade
  #	And clico no botao de "Entrar"
    
    Examples: 
      | cpf/cnpj          | email/sms                        			  |
      | "83561915051"     | "automacaologinportocom2@yopmail.com"   |
  #		| "83561915051"  	 	| "algum sms aqui"				  	|
  #		| "algum cnpj aqui" | "testeportocom" 					  |
  #		| "algum cnpj aqui"	| "algum sms aqui" 					  |
  
  @loginClienteAtivoLexisReject 
  Scenario Outline: CT03 – [LEXIS REJECT] Login Cliente Ativo
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And digito a senha
    And clico no botao de "Entrar"
    Then sou direcionado para a tela de bloqueio do lexis
    And clico em voltar
    And sou direcionado para a homepage

    Examples: 
      | cpf/cnpj          | email/sms                  |
      | "84847701097"     | "automacaologinportocom11@yopmail.com" |
  #		| "algum cnpj aqui" |

  @loginContaBloqueada @regressivo
  Scenario Outline: CT04 – Login Conta Bloqueada
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    Then sou direcionado para a pagina de conta bloqueada

    Examples: 
      | cpf/cnpj          |
      | "49347876070"     |
  #		| "algum cnpj aqui"	|
  
  @loginBotaoVoltar @regressivo
  Scenario Outline: CT05 – Login Botão Voltar
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And clico no botao de voltar
    And clico no botao de voltar
    Then sou direcionado para a homepage

    Examples: 
      | cpf/cnpj      |
      | "46949261012" |

  @loginSenhaInvalida 
  Scenario Outline: CT06 – Login Senha Inválida, Senha Curta e Bloqueio por Tentativa
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And digito uma senha invalida
    And clico no botao de "Entrar"
    Then recebo uma <msg> senha invalida
    And eu excedo as tentativas de login
    Then sou direcionado para a pagina de recuperar conta bloqueada

    Examples: 
      | cpf/cnpj          | msg                                |
      | "99738496047"     | "Opa! Dados incorretos."           |
  #		| "algum cnpj aqui"	| "Poxa! Não conseguimos localizar." |
  
  
  @loginProspectNaoAtivo @regressivo
  Scenario Outline: CT07 – Login Prospect Não Ativo
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    Then sou direcionado para o fluxo de cadastro

    Examples: 
      | cpf/cnpj          |
      | "35416297034"     |
  #		| "algum cnpj aqui"	| #CONTA EXCLUÍDA
      | "78262133048"     |
  #		| "algum cnpj aqui"	| #AGUARDANDO VALIDAÇÃO
      | "80340644087"     |
  #		| "algum cnpj aqui"	| #CONTA NÃO EXISTE
      | "52070114058"     |
	#		| "algum cnpj aqui"	| #TENTATIVA DE CADASTRO
	
  @loginDadosInvalidos @regressivo
  Scenario Outline: CT08 – Login Cpf e Cnpj Inválidos
    When digito o <cpf/cnpj>
    Then recebo uma <msg> de falha

    Examples: 
      | cpf/cnpj         | msg              |
      | "11111111111"    | "CPF inválido."  |
      | "11111111111111" | "CNPJ inválido." |
      
  @loginLinkCrieUmaConta @regressivo
  Scenario: CT09 - Login Clicar no Botão de Criar Nova Conta
  	When eu clico no link de criar nova conta
  	Then sou direcionado para a pagina inicial de cadastro
  	
  @loginReenviarPin @regressivo
  Scenario Outline: CT10 - Login Reenviar o PIN
  	When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And digito a senha
    And clico no botao de "Entrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
   	And recebo o primeiro pin de login via <email/sms>
    And clico em enviar pin novamente
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de login via <email/sms>
    And clico no botao de "Continuar"
    Then sou direcionado para a homepage

  	    Examples: 
      | cpf/cnpj          | email/sms                   |
      | "46949261012"     | "automacaologinportocom1@yopmail.com"   |
  #		| "83561915051"  	 	| "algum sms aqui"				  	|
  #		| "algum cnpj aqui" | "testeportocom" 					  |
  #		| "algum cnpj aqui"	| "algum sms aqui" 					  |
  
   @loginTrocarEnvioDePin @regressivo
  Scenario Outline: CT11 - Login Alternar Dados de Envio de PIN
  	When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And digito a senha
    And clico no botao de "Entrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o primeiro pin de login via <email/sms>
    And clico em enviar pin por email
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de login via <trocar envio>
    And clico no botao de "Continuar"
    Then sou direcionado para a homepage

  	    Examples: 
      | cpf/cnpj          | email/sms                   |  trocar envio             |
      | "46949261012"     | "(11) 947212770"            | "automacaologinportocom1@yopmail.com" |
  #		| "83561915051"  	 	| "automacaologinportocom1"  	| "(11) 947212770"          |
  #		| "algum cnpj aqui" | "testeportocom" 					  |                           |
  #		| "algum cnpj aqui"	| "algum sms aqui" 					  |                           |
  
  @loginTokenBloqueioTentativa
  Scenario Outline: CT12 - Login Bloquear Usuario Token Inválido
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And digito a senha
    And clico no botao de "Entrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And adiciono o token invalido
    Then sou direcionado para a pagina de recuperar conta bloqueada

    Examples: 
      | cpf/cnpj      | email/sms                       			|
      | "83561915051" | "automacaologinportocom2@yopmail.com" |
  