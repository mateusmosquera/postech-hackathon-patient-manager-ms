Feature: Gerenciamento de patients via HTTP

   @tagPost
   Scenario Outline: : Criar um novo patient
    Given que o patient envia uma solicitacao para criar um novo patient, com cpf "<cpf>", com nome <nome> e email <email>
    When patient recebe a solicitacao com code status <status>
    Then o patient recebe a resposta, com cpf "<cpf>", com nome <nome> e email <email>


     Examples:
     | cpf               | nome             | email              | status |
     |  686.524.400-18   | Patient Teste1   | patient1@teste.com | 201    |
     |  123.456.789-09   | Patient Teste    | patient@teste.com  | 400    |

  @tagGet
  Scenario Outline: Recuperar informacoes de um patient pelo CPF
    Given que o patient envia uma solicitacao para recuperar informacoes de um patient pelo CPF
    When  patient recebe as informacoes com code status <status>
    Then recebe as informacoes com a resposta

    Examples:
      | status |
      | 200    |