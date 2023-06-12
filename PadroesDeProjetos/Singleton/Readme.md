# Singleton

## No diagrama, temos duas classes: Singleton e Test.

* A classe Singleton  possui um atributo estático instance que é a única instância da classe. O construtor é declarado como privado para evitar a criação de instâncias fora da classe. O método getInstance() é o método estático responsável por retornar a única instância existente, garantindo que apenas uma instância seja criada quando chamada pela primeira vez. A classe também possui outros métodos, como doSomething(), que representa o comportamento da instância singleton.

* A classe Test é a classe de teste que contém o método main. Dentro desse método, é obtida a instância do Singleton chamando o método getInstance() e, em seguida, é chamado o método doSomething() para demonstrar o uso da instância singleton.
