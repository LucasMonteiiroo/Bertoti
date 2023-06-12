# Facade

## No diagrama, temos cinco classes: SubsystemA, SubsystemB, SubsystemC, Facade e FacadePatternExample.

* As classes SubsystemA, SubsystemB e SubsystemC são classes que representam subsistemas ou componentes internos. Cada uma delas possui um método de operação específico, como operationA(), operationB() e operationC().

* A classe Facade é a fachada que fornece uma interface simplificada para o cliente interagir com os subsistemas. Ela possui instâncias dos subsistemas (subsystemA, subsystemB e subsystemC). No construtor da classe Facade, são criadas as instâncias dos subsistemas. O método operation() na classe Facade chama os métodos de operação dos subsistemas, fornecendo uma interface única e simplificada para o cliente interagir com os subsistemas.

* A classe Test é a classe de teste que contém o método main. Dentro desse método, é criada uma instância de Facade chamada facade, e então é chamado o método operation() da fachada para executar as operações nos subsistemas de forma transparente para o cliente.

<div align="center">
  <img src="https://github.com/LucasMonteiiroo/Bertoti/blob/main/PadroesDeProjetos/facade/Facade.png">
 </div> 
