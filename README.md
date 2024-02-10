# Desafio Movieflix - Casos de Uso

Este repositório apresenta a implementação das funcionalidades essenciais para atender aos casos de uso do projeto Movieflix. O objetivo principal é garantir que todos os testes TDD sejam bem-sucedidos, assegurando a qualidade e a funcionalidade do sistema. 

## Casos de Uso

### Listar Filmes
- **Outros**: O sistema apresenta uma listagem dos nomes de todos os gêneros, bem como uma listagem paginada com título, subtítulo, ano e imagem dos filmes, ordenada alfabeticamente por título.
- **Entrada**: O usuário visitante ou membro seleciona, opcionalmente, um gênero.
- **Saída**: O sistema apresenta a listagem atualizada, restringindo somente ao gênero selecionado.

### Visualizar Detalhes do Filme
- **Entrada**: O usuário visitante ou membro seleciona um filme.
- **Saída**: O sistema informa título, subtítulo, ano, imagem e sinopse do filme, e também uma listagem dos textos das avaliações daquele filme juntamente com nome do usuário que fez cada avaliação.
- **Entrada**: O usuário membro informa, opcionalmente, um texto para avaliação do filme.
- **Saída**: O sistema apresenta os dados atualizados, já aparecendo também a avaliação feita pelo usuário.

### Exceção 3.1 - Texto Vazio
- **Cenário**: O sistema apresenta uma mensagem de que não é permitido texto vazio na avaliação.
