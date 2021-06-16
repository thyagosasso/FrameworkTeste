@Cadastro @Funcionais
Feature: Cadastro Cliente

  Background: Entrar na plataforma
    Given que um usuário acessou a opção de cadastro

  @cadastroClienteLexisPass @regressivo
  Scenario Outline: CT01 - [LEXIS PASS] Cadastro Novo Cliente
    When digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de cadastro via <email/sms>
    And cadastro uma senha
    And clico no botao de "Cadastrar"
    And sou direcionado para a pagina de tudo certo com cadastro
    And clico no botao de "Acessar"
    Then sou direcionado para a pagina de inserir senha

    Examples: 
      | cpf/cnpj      | email/sms                       |
      | "82991476036" | "cadastrovalidacao@yopmail.com" |

  @cadastroClienteLexisReview @regressivo
  Scenario Outline: CT02 - [LEXIS REVIEW] Cadastro Novo Cliente
    When digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de cadastro via <email/sms>
    And cadastro uma senha
    And clico no botao de "Cadastrar"
    And sou direcionado para a pagina de tudo certo com cadastro
    And clico no botao de "Acessar"
    Then sou direcionado para a pagina de inserir senha

    Examples: 
      | cpf/cnpj      | email/sms                              |
      | "14103396024" | "automacaologinportocom11@yopmail.com" |

  @cadastroClienteLexisReject
  Scenario Outline: CT03 – [LEXIS REJECT] Cadastro Novo Cliente
    When digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    Then sou direcionado para a tela de bloqueio do lexis
    And clico em voltar
    And sou direcionado para a homepage

    Examples: 
      | cpf/cnpj      | email/sms                                |
      | "44763691031" | "automacaocadastroportocom1@yopmail.com" |

  #		| "algum cnpj aqui" |
  @cadastroSenhasDiferentes @regressivo
  Scenario Outline: CT04 - Cadastro Senhas Não Coincidem
    Given digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de cadastro via <email/sms>
    When cadastro senhas que nao coincidem
    Then recebo uma mensagem informando que as senhas não coincidem

    Examples: 
      | cpf/cnpj      | email/sms                       |
      | "82991476036" | "cadastrovalidacao@yopmail.com" |

  @cadastroClienteAtivoContaAtiva @regressivo
  Scenario Outline: CT05 - Cadastro Cliente Ativo Ja Possui Conta
    When digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    And sou direcionado para a pagina de ja possui conta
    And clico no botao de "Acessar"
    Then sou direcionado para a pagina de inserir senha

    # Massa 1 = Cliente Ativo Conta Ativa
    # Massa 2 = Aguardando Primeiro Login
    Examples: 
      | cpf/cnpj      | msg                                      |
      | "08276884318" | "Você já possui uma conta Porto Seguro." |
      | "66881025017" | "Você já possui uma conta Porto Seguro." |

  @cadastroInvalido @regressivo
  Scenario Outline: CT07 - Cadastro Cpf e Cnpj Inválidos
    When digito o <cpf/cnpj>
    Then recebo uma <msg> de falha

    Examples: 
      | cpf/cnpj         | msg              |
      | "11111111111"    | "CPF inválido."  |
      | "11111111111111" | "CNPJ inválido." |

  @cadastroTentativaBloqueado
  Scenario Outline: CT08 - Cadastro Bloquear Usuario Token Inválido
    When digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And adiciono o token invalido
    Then sou direcionado para a tela de bloqueio por tentativa
    And clico em voltar
    And sou direcionado para a homepage

    Examples: 
      | cpf/cnpj      | email/sms                       |
      | "17380112082" | "cadastrovalidacao@yopmail.com" |

  @cadastroReenviarPin @regressivo
  Scenario Outline: CT09 - Cadastro Reenviar o PIN
    When digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o primeiro pin de cadastro via <email/sms>
    And clico em enviar pin novamente
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de cadastro via <email/sms>
    And cadastro uma senha

    # And clico no botao de "Cadastrar"
    #Then sou direcionado para a pagina de tudo certo com cadastro
    Examples: 
      | cpf/cnpj      | email/sms                       |
      | "82991476036" | "cadastrovalidacao@yopmail.com" |

  #		| "83561915051"  	 	| "algum sms aqui"				  	|
  #		| "algum cnpj aqui" | "testeportocom" 					  |
  #		| "algum cnpj aqui"	| "algum sms aqui" 					  |
  @cadastroTrocarEnvioDePin @regressivo
  Scenario Outline: CT10 - Cadastro Alternar Dados de Envio de PIN
    When digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o primeiro pin de cadastro via <email/sms>
    And clico em enviar pin por email
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de cadastro via <trocar envio>
    And cadastro uma senha

    # And clico no botao de "Cadastrar"
    #Then sou direcionado para a pagina de tudo certo com cadastro
    Examples: 
      | cpf/cnpj      | email/sms        | trocar envio                    |
      | "82991476036" | "(11) 947212770" | "cadastrovalidacao@yopmail.com" |

  # | "46949261012" | "automacaologinportocom1@yopmail.com" |"(11) 947212770"                       |
  @cadastroClienteAtivoContaAtivaMSG @regressivo
  Scenario Outline: CT11 - Cadastro Não Cliente
    When digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    Then recebo uma <msg> de falha

    Examples: 
      | cpf/cnpj      | msg                      |
      | "08780141013" | "Opa! Dados incorretos." |

  @cadastroTokenLegado @regressivo
  Scenario Outline: CT12 – Cliente já possui token no portal
    When digito o <cpf/cnpj>
    And clico no botao de "Entrar"
    And recebo o pin de recuperacao via <email/sms>
    And clico no botao de "Continuar"
    And digito a senha de recuperacao
    And clico no botao de "Cadastrar"
    Then sou direcionado para a pagina de tudo certo com cadastro

    Examples: 
      | cpf/cnpj      | email/sms                              |
      | "95394213062" | "automacaologinportocom12@yopmail.com" |

  @cadastroBloqueioBotaoVoltar @regressivo
  Scenario: CT13 - Bloqueio botão cancelar
   Given acesso a URL de bloqueio de nova conta com token expirado
    And sou direcionao a pagina de bloqueio de nova conta direto do navegador
    And clico em voltar
    And sou direcionado para a homepage

  @cadastroBloqueioTokenReutilizado @regressivo
  Scenario Outline: CT14 - Cadastro, bloqueio de conta e token reutilizado
    When digito o <cpf/cnpj>
    And clico no botao de "Cadastrar"
    And seleciono validacao por <email/sms>
    And clico no botao de "Enviar"
    And receberei uma mensagem de sucesso "Pronto!"
    And recebo o pin de cadastro via <email/sms>
    And cadastro uma senha
    And clico no botao de "Cadastrar"
    And sou direcionado para a pagina de tudo certo com cadastro
    And recebo o link de bloqueio via <email/sms>
    And sou direcionao a pagina de bloqueio de nova conta
    And clico no botao de "Bloquear conta"
    And sou direcionado para a tela de bloqueio por tentativa
    And recebo o segundo link de bloqueio via <email/sms>
    And sou direcionao a pagina de bloqueio de nova conta
    And clico no botao de "Bloquear conta"
    And sou direcionado a pagina de bloqueio de token reutilizado
    And clico em voltar
    Then sou direcionado para a homepage

    Examples: 
      | cpf/cnpj      | email/sms                             |
      | "15701007057" | "automacaologinportocom5@yopmail.com" |

  @cadastroBloqueioTokenExpirado @regressivo
  Scenario: CT15 - Validação de Tela de Bloqueio de token expirado
    Given acesso a URL de bloqueio de nova conta com token expirado
    And sou direcionao a pagina de bloqueio de nova conta direto do navegador
    When clico no botao de "Bloquear conta"
    And sou direcionado para a tela de bloqueio por token expirado
    And clico em voltar
    Then sou direcionado para a homepage
    
  @cadastroBloqueioTokenNaoEncontrado @regressivo
  Scenario: CT16 - Validação de Tela de Bloqueio de token não encontrado
    Given acesso a URL de bloqueio de nova conta com token não encontrado
    And sou direcionao a pagina de bloqueio de nova conta direto do navegador
    When clico no botao de "Bloquear conta"
    And receberei uma mensagem de sucesso "Ops..."
    And clico em voltar
    Then sou direcionado para a homepage
