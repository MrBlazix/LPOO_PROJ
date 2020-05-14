# LPOO_<T03><G34> - <Pac-Man>

Neste antigo mas divertido jogo poderás desfrutar da nostalgia do Pac-Man.
Foge dos fantasmas e tenta alcançar o máximo de pontos enquanto o fazes!
Projeto desenvolvido por Luís Marques e Pedro Pacheco @FEUP for LPOO 19/20.

## Implemented Features
### Move
* O Pac move-se unidirecionalmente aquando input. Os fantasmas movem-se aleatoriamente.
### Score
* Aquando colisão do Pac com os pontos amarelos, o score aumenta.

## Planned Features
* Está no plano implementar os menus (principal, instruções e pausa), inteligência artificial nos fantasmas, estado de invencibilidade no Pac, vidas e testes unitários de forma a cobrir o bom funcionamento do jogo.

## Design
No relevant problems were found.
![Designed Classes](/images/lpoo_uml.png)

## Known code smells and refactoring sugestions
###Classe excessivamente grande
Large Class - Criar uma nova classe para substituir partes da classe original, aumentando a flexibilidade.
###Uso excessivo de chamadas a métodos de outras classes
Fazer apenas uma chamada e guardar resultados em variáveis.
###Método excessivamente grande
Long Method - Usar Extract Method

## Testing
No tests were implemented yet.

## Self Evaluation
Luís Marques:50 %
Pedro Pacheco:50 %

