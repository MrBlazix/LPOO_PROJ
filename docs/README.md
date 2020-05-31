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
### Problem in Context
No início houve alguns problemas relativos com o trabalho simultâneo de ambos os membros do grupo.
Mesmo trabalhando em partes diferentes do código, havia diversas alterações que alteravam vários componentes.

### The Pattern
Decidimos então implementar o MVC Design Pattern. Permitindo-nos dividir o programa em 3 camadas, permitindo que a alteração de uma camada, não influencia-se o resto do código.

### Implementation
![Designed Classes](/docs/images/lpoo_uml.png)

### Consequences
Esta implemantação permitiu-nos registar os seguintes benefícios:
* Permite-nos desenvolver partes diferentes do código em paralelo.
* Facilidade nas alterações da interface gráfica.
* Maior facilidade em testar cada camada e cada componente dessa camada.


### Problem in Context
No caso do Pac, temos um exemplo de uma caso em que só pretendemos ter exatamente uma instancia de uma dada classe.

### The Pattern
Para evitar usar o Singleton Pattern, acabámos por inicializar uma instancia apenas do pac, e passar esse obejto como um parâmetro.

### Implementation
![Designed Classes](/docs/images/lpoo_uml_2.png)

### Consequences
Esta implementação permitiu-nos registar os seguintes benefícios:
* Maior facilidade em realizar alterações no código.
* Maior facilidade em testar.

## Known code smells and refactoring sugestions
### Não Corrigidos
**Arena.java**
* Large Class - Criar uma nova classe para substituir partes da classe original, aumentando a flexibilidade.

### Corrigidos
**Game.java**  
* Uso excessivo de chamadas a métodos de outras classes - Feita apenas uma chamada e guardados os resultados em variáveis.
* Dead Code - L.73 - Removida váriavel não usada.
* Dead Code - L.102 - Removida linha que inicializava objeto não usado.
* Dead Code - L.132 - Parâmetro desnecessário removido.
* Dead Code - L.145 - Removida váriavel não usada.

**Arena.java**  
* Uso excessivo de chamadas a métodos de outras classes - Feita apenas uma chamada e guardados os resultados em variáveis.
* Dead Code - L.3 - Eliminados *imports* não usados.
* Dead Code - L.78 - Removida linha que inicializava objeto não usado.

**Dot.java**
* Dead Code - L.3 - Eliminados *imports* não usados.


## Testing
Implementados Unit Tests a nível individual das funções.
Resultados:

![Designed Classes](/docs/images/lpoo_tests.PNG)


## Self Evaluation
Luís Marques:50 %
Pedro Pacheco:50 %
