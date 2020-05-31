# LPOO_T03G34 - Pac-Man

Neste antigo mas divertido jogo poderás desfrutar da nostalgia do Pac-Man.
Foge dos fantasmas e tenta alcançar o máximo de pontos enquanto o fazes!

Projeto desenvolvido por Luís Marques e Pedro Pacheco @FEUP for LPOO 19/20.

## Implemented Features
### Pac
* O Pac move-se unidirecionalmente de acordo com o input.
* Na ausência de input, o pac continua com a direção atual.
* No caso de atingir um obstáculo, o Pac pára à espera de novo input.
* No caso de comer um dos pontos maiores, o Pac entra em "Power State", onde pode "comer" os ghosts.

### Ghosts
* Os ghosts usam um algoritmo simples para se movimentarem.
* Consiste em garantir a redução da Manhattan Distance relativa ao Pac.
* No caso do Pac estar em "Power State", este algoritmo inverte-se, e eles tentam aumentar a distância.
* Dois ghosts não podem estar na mesma posição ao mesmo tempo.
* Cada ghost tem uma posição inicial definida.
* No caso de ser "comido", cada ghost volta para a sua posição inicial.

### Score
O Score aumenta:
* No momento em que o Pac atinge/come um dos pontos amarelos.
* No momento em que o Pac atinge/come um fantasma após ativar o "power state".

### Map
* O mapa está implementado com todos os componentes previstos.
* É gerado a partir de um ficheiro "map.txt".

### Menu
* O menu apresenta-se completamente funcional.
* Permite começar o jogo.
* Permite ver as instruções.
* Permite sair

### Lives
* O sistema de vidas está implementado e a funcionar.
* Cada jogo começa com 3 vidas.
* Quando o Pac é "comido", perde uma vida.
* Se o numero de vidas chegar a 0, o jogo acaba.

### Power State
* Como já mecionámos, o Power State ativa-se quando o Pac come um dos pontos amarelos maiores.
* O mapa fica azul.
* Os ghosts começam a fugir do Pac.
* O Pac ganha a abilidade de comer os ghosts.

## Planned Features
* Está no plano implementar os menus (principal, instruções e pausa), inteligência artificial nos fantasmas, estado de invencibilidade no Pac, vidas e testes unitários de forma a cobrir o bom funcionamento do jogo.

## Design
No relevant problems were found.
![Designed Classes](/docs/images/lpoo_uml.png)

## Known code smells and refactoring sugestions
### Classe excessivamente grande
Large Class - Criar uma nova classe para substituir partes da classe original, aumentando a flexibilidade.
### Uso excessivo de chamadas a métodos de outras classes
Fazer apenas uma chamada e guardar resultados em variáveis.
### Método excessivamente grande
Long Method - Usar Extract Method

## Testing
No tests were implemented yet.

## Self Evaluation
Luís Marques:50 %
Pedro Pacheco:50 %
