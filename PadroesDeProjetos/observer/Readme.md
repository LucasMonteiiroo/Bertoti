# Observer
## Neste diagrama, temos as cinco classes: Observer, ConcreteObserverA, ConcreteObserverB, Subject e ConcreteSubject.

* A classe Observer é a interface que define o método update(value), que será implementado pelas classes ConcreteObserverA e ConcreteObserverB. Essas classes são os observadores concretos que implementam a lógica de atualização específica.

* A classe Subject é a interface que define os métodos attach(observer), detach(observer) e notifyObservers(), que serão implementados pela classe ConcreteSubject. Esses métodos são responsáveis por gerenciar a lista de observadores e notificar os observadores sobre as atualizações.

* A classe ConcreteSubject é a implementação concreta do sujeito. Ela possui um atributo value para representar o valor a ser observado e uma lista de observadores (observers). Essa classe implementa os métodos attach(observer), detach(observer) e notifyObservers() para adicionar, remover e notificar os observadores, respectivamente. Além disso, possui o método setValue(value) para atualizar o valor e notificar os observadores sobre a alteração.

* A classe Test possui o método main, que é o ponto de entrada do programa. Ele cria uma instância de ConcreteSubject, instâncias de ConcreteObserverA e ConcreteObserverB, anexa os observadores ao sujeito, atualiza o valor do sujeito e, em seguida, remova um dos observadores e atualiza novamente o valor do sujeito.

