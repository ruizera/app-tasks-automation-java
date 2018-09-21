#language: pt
#utf-8 
@busca @appVersion
Funcionalidade: Tarefas
  Como cliente
  Eu quero manter minhas tarefas
  Para organizar e visualizar a minha execução das mesmas

Contexto: Acessar o site
  Dado que eu esteja na home do site

@positivo @regressao @smokeTest 
Esquema do Cenário: Validar adição de tarefa
  Quando eu adicionar <quantidade> tarefa valida
  Entao as tarefas retornadas devem ser exibidas corretamente

  Exemplos:
  | quantidade |
  | 1 |
  | 2 |
  | 10|

@negativo @regressao
Cenário: Validar que não é possível adicionar tarefas inválidas
  Quando eu adicionar 1 tarefa inválida
  Entao não será adicionado nenhuma tarefa

@negativo @regressao
Cenário: Validar que não é possível adicionar tarefas inválidas após ter adicionado tarefas válidas
  Quando eu adicionar 2 tarefa valida
  E eu adicionar 1 tarefa inválida
  Entao a quantidade de tarefas deve ser igual a 2

@positivo @regressao @smokeTest
Esquema do Cenário: Validar remocao de tarefa
  Quando eu adicionar <quantidade_adicionar> tarefa valida
  E eu remover <quantidade_remover> tarefa
  Entao as tarefas retornadas devem ser exibidas corretamente

  Exemplos:
  | quantidade_adicionar | quantidade_remover |
  | 1 | 1 |
  | 3 | 1 |
  | 3 | 2 |
  | 3 | 3 |

@positivo @regressao @smokeTest
Esquema do Cenário: Validar tarefas concluídas
  Quando eu adicionar <quantidade_adicionar> tarefa valida
  E eu marcar <quantidade_concluir> tarefas como concluidas
  Entao as tarefas marcadas como concluidas devem estar corretas

  Exemplos:
  | quantidade_adicionar | quantidade_concluir |
  | 1 | 1 |
  | 3 | 1 |
  | 3 | 2 |
  | 3 | 3 |