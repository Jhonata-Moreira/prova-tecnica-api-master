# language: pt
# encoding: UTF-8
Funcionalidade: Validação de consulta, alteração, deleção e restrições

 Esquema do Cenário: Consulta restrição de usuário
  Dado que deseje realizar uma consulta de usuário no endpoint de restricoes
  Quando busco pelo "<massasCPF>"
  Então recebo o status code 200 na consulta de restricao
  E a mensagem de "O CPF {cpf} tem problema"

 Exemplos:
  | massasCPF |
  | UsuarioRestrito1 |
  | UsuarioRestrito2 |
  | UsuarioRestrito3 |
  | UsuarioRestrito4 |
  | UsuarioRestrito5 |
  | UsuarioRestrito6 |
  | UsuarioRestrito7 |
  | UsuarioRestrito8 |
  | UsuarioRestrito9 |
  | UsuarioRestrito10 |

  Cenário: Consultar usuário sem restrição
   Dado que deseje realizar uma consulta de usuário no endpoint de restricoes
   Quando consulto pelo usuário "UsuarioSemRestricao"
   Então recebo o status code 204 na consulta de restricao
  
  Cenário: Realizar criação de simulação
   Dado que deseje criar uma simulação no endpoint "/simulacoes"
   Quando envio os dados necessários do cliente "UsuarioSemRestricao"
   """
   {
    "nome": "Fulano de Tal",
    "cpf": "31851491066",
    "email": "email@email.com",
    "valor": 1200,
    "parcelas": 3,
    "seguro": true
   }
   """
   Então recebo o status code 201 ao enviar a simulacao
   E visualizo um response body com o id criado para o usuário

  Cenário: Realizar criação de simulação de um usuário restrito
   Dado que deseje criar uma simulação no endpoint "/simulacoes"
   Quando envio os dados necessários do cliente "UsuarioRestrito1"
   """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
   Então recebo o status code 201 ao enviar a simulacao
   E visualizo um response body com o id criado para o usuário

  Cenário: Realizar criação de simulação de um usuário já cadastrado
   Dado que deseje criar uma simulação no endpoint "/simulacoes"
   Quando envio os dados necessários do cliente "UsuarioSemRestricao"
   """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
   Então recebo o status code 400 ao enviar a simulacao
   E visualizo a mensagem "CPF duplicado"

  Cenário: Realizar criação de simulação de um usuário com CPF vazio
   Dado que deseje criar uma simulação no endpoint "/simulacoes"
   Quando envio os dados necessários do cliente "UsuarioCpfVazio"
   """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
   Então recebo o status code 400 ao enviar a simulacao
   E visualizo a mensagem "CPF não pode ser vazio"

  Cenário: Realizar criação de simulação de um usuário com CPF nulo
   Dado que deseje criar uma simulação no endpoint "/simulacoes"
   Quando envio os dados necessários do cliente "UsuarioCpfNulo"
   """
   {
    "nome": "{nome}",
    "cpf": null,
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
   Então recebo o status code 400 ao enviar a simulacao
   E visualizo a mensagem "CPF não pode ser vazio"

 Cenário: Realizar criação de simulação de um usuário com CPF mascarado
  Dado que deseje criar uma simulação no endpoint "/simulacoes"
  Quando envio os dados necessários do cliente "UsuarioCpfMascarado"
  """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
  Então recebo o status code 400 ao enviar a simulacao

 Cenário: Realizar criação de simulação de um usuário sem enviar nome
  Dado que deseje criar uma simulação no endpoint "/simulacoes"
  Quando envio os dados necessários do cliente "UsuarioSemNome"
  """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
  Então recebo o status code 400 ao enviar a simulacao
  E visualizo a mensagem "Nome não pode ser vazio"

 Cenário: Realizar criação de simulação de um usuário enviando dados vazios
  Dado que deseje criar uma simulação no endpoint "/simulacoes"
  Quando envio os dados necessários do cliente "UsuarioComDadosVazios"
  """
   {
    "nome": "{nome}",
    "cpf": null,
    "email": "{email}",
    "valor": null,
    "parcelas": null,
    "seguro": {seguro}
   }
   """
  Então recebo o status code 400 ao enviar a simulacao
  E visualizo a mensagem "Parcelas não pode ser vazio"
  E visualizo a mensagem "CPF não pode ser vazio"
  E visualizo a mensagem "Valor não pode ser vazio"
  E visualizo a mensagem "E-mail deve ser um e-mail válido"
  
 Cenário: Realizar criação de simulação de um usuário passando valor acima do limite
  Dado que deseje criar uma simulação no endpoint "/simulacoes"
  Quando envio os dados necessários do cliente "UsuarioComValorAcima"
  """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
  Então recebo o status code 400 ao enviar a simulacao
  E visualizo a mensagem "Valor deve ser menor ou igual a R$ 40.000"

 Cenário: Realizar criação de simulação de um usuário passando valor menor que 1,000
  Dado que deseje criar uma simulação no endpoint "/simulacoes"
  Quando envio os dados necessários do cliente "UsuarioComValorMenor"
  """
   {
    "nome": "Fulano de Tal",
    "cpf": "",
    "email": "email@email.com",
    "valor": 1200,
    "parcelas": 3,
    "seguro": true
   }
   """
  Então recebo o status code 400 ao enviar a simulacao
  E visualizo a mensagem "Valor dever ser maior ou igual a R$ 1.000"

  Cenário: Realizar criação de simulação de um usuário com 1 parcela
   Dado que deseje criar uma simulação no endpoint "/simulacoes"
   Quando envio os dados necessários do cliente "UsuarioComParcelaMenor"
   """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
   Então recebo o status code 400 ao enviar a simulacao
   E visualizo a mensagem "Parcelas deve ser igual ou maior que 2"

  Cenário: Realizar criação de simulação de um usuário com 49 parcelas
   Dado que deseje criar uma simulação no endpoint "/simulacoes"
   Quando envio os dados necessários do cliente "UsuarioComParcelaMaior"
   """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
   Então recebo o status code 400 ao enviar a simulacao
   E visualizo a mensagem "Parcelas deve ser igual ou menor que 48"

  Cenário: Realizar crição de simulação de um usuário sem seguro
   Dado que deseje criar uma simulação no endpoint "/simulacoes"
   Quando envio os dados necessários do cliente "UsuarioSemSeguro"
   """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
   Então recebo o status code 201 ao enviar a simulacao
   E visualizo um response body com o id criado para o usuário
   E visualizo também o campo seguro com valor "false"

  Cenário: Realizar alteração de uma simulação
   Dado que deseje alterar uma simulação
   E informo o CPF do cliente "UsuarioSemRestricao" na URL
   Quando envio os dados necessários do cliente "UsuarioAlteracao" para alteracao
   """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
   Então recebo o status code 200 ao enviar a alteracao da simulacao
   E visualizo um response body com os dados do usuário alterado

  Cenário: Realizar alteração de uma simulação não existente
   Dado que deseje alterar uma simulação
   Quando informo o CPF do cliente "UsuarioSemSimulação" na URL
   E envio os dados necessários do cliente "UsuarioSemSimulação" para alteracao
   """
   {
    "nome": "{nome}",
    "cpf": "{cpf}",
    "email": "{email}",
    "valor": {valor},
    "parcelas": {parcelas},
    "seguro": {seguro}
   }
   """
   Então recebo o status code 400 ao enviar a alteracao da simulacao