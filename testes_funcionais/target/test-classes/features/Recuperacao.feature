@Recuperacao @Funcionais
Feature: Recuperacao Cliente

  Background: Entrar na plataforma
    Given que um usuário acessou a opção de login

   @recuperacaoLexisPass @regressivo
  Scenario Outline: CT01 – [LEXIS PASS] Recuperação de Conta
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And clico no link de esqueceu senha
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de recuperacao via <email/sms>
    And clico no botao de "Continuar"
    And digito a senha de recuperacao
    And clico no botao de "Cadastrar"
		And sou direcionado para a pagina de tudo certo com cadastro
    And clico no botao de "Acessar"
    Then sou direcionado para a pagina de inserir senha

    Examples: 
      | cpf/cnpj          | email/sms                               |
      | "95394213062"     | "automacaologinportocom12@yopmail.com"  |
  #		| "algum cnpj aqui"	|"automacaologinportocom11"               |
  
  @recuperacaoLexisReview @regressivo
  Scenario Outline: CT02 – [LEXIS REVIEW] Recuperação de Conta
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And clico no link de esqueceu senha
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de recuperacao via <email/sms>
    And clico no botao de "Continuar"
    And digito a senha de recuperacao
    And clico no botao de "Cadastrar"
		And sou direcionado para a pagina de tudo certo com cadastro
    And clico no botao de "Acessar"
    Then sou direcionado para a pagina de inserir senha

    Examples: 
      | cpf/cnpj          | email/sms                  						 |
      | "83561915051"     | "automacaologinportocom2@yopmail.com" |
#			| "algum cnpj aqui"	| "algum email aqui"			 				    	 |
  
  @recuperacaoLexisReject 
  Scenario Outline: CT03 – [LEXIS REJECT] Recuperação de Conta
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And clico no link de esqueceu senha
    Then sou direcionado para a tela de bloqueio do lexis

    Examples: 
      | cpf/cnpj          | email/sms                 						 |
      | "84847701097"     | "automacaologinportocom13@yopmail.com" |
  #		| "algum cnpj aqui"	|  																			 |
  
  @recuperacaoReenviarPin @regressivo
  Scenario Outline: CT04 - Recuperação Reenviar o PIN
  	When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And clico no link de esqueceu senha
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
   	And recebo o primeiro pin de recuperacao via <email/sms>
    And clico em enviar pin novamente
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de recuperacao via <email/sms>
    And clico no botao de "Continuar"
    And digito a senha de recuperacao
    And clico no botao de "Cadastrar"
		And clico no botao de "Acessar"
    Then sou direcionado para a pagina de inserir senha

  	    Examples: 
    | cpf/cnpj          | email/sms                                 |
      | "46949261012"     | "automacaologinportocom1@yopmail.com"   |
  #		| "83561915051"  	 	| "algum sms aqui"				  							|
  #		| "algum cnpj aqui" | "testeportocom" 					  						|
  #		| "algum cnpj aqui"	| "algum sms aqui" 					  						|
  
   @recuperacaoTrocarEnvioDePin @regressivo
  Scenario Outline: CT05 - Recuperação Alternar Dados de Envio de PIN
  	When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And clico no link de esqueceu senha
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And clico em enviar pin por email
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de recuperacao via <trocar envio>
    And clico no botao de "Continuar"
    And digito a senha de recuperacao
    And clico no botao de "Cadastrar"
		And clico no botao de "Acessar"
    Then sou direcionado para a pagina de inserir senha

  	    Examples: 
      | cpf/cnpj          | email/sms                   |  trocar envio           						  |
      | "46949261012"     | "(11) 947212770"            | "automacaologinportocom1@yopmail.com" |
  #		| "83561915051"  	 	| "automacaologinportocom1"  	| "(11) 947212770"      						    |
	#		| "algum cnpj aqui" | "testeportocom" 						|                     				          |
  #		| "algum cnpj aqui"	| "algum sms aqui" 					  |                    							      |
  
  @recuperacaoTentativaBloqueio
  Scenario Outline: CT06 - Recuperação Bloquear Usuario Token Inválido
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And clico no link de esqueceu senha
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And adiciono o token invalido
    Then sou direcionado para a tela de bloqueio por tentativa
    And clico em voltar
    And sou direcionado para a homepage

    Examples: 
      | cpf/cnpj      | email/sms                       |
      | "46949261012" | "cadastrovalidacao@yopmail.com" |
