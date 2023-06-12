# Composite 

## A interface Component define a operação comum operation() que será implementada pelas classes Leaf e Composite.

* A classe Leaf representa as folhas do composite. Ela possui um atributo name para armazenar o nome da folha. A classe implementa o método operation(), que imprime uma mensagem informando a operação realizada pela folha.

* A classe Composite representa os componentes compostos, que podem conter outros componentes (sejam folhas ou outros composites). Ela possui um atributo components que é uma lista de componentes. A classe possui métodos para adicionar (addComponent()) e remover (removeComponent()) componentes. O método operation() do composite itera sobre todos os componentes e chama o método operation() de cada um.

* A classe Test é a classe de teste que contém o método main. Dentro desse método, são criadas instâncias de Leaf e Composite, e são realizadas operações nos composites e folhas para demonstrar o uso do padrão Composite.


<div align="center">
  <img src="https://github.com/LucasMonteiiroo/Bertoti/blob/main/PadroesDeProjetos/composite/Composite.png">
 </div> 
