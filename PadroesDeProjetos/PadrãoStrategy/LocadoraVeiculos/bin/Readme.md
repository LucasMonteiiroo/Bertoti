
# No diagrama acima, temos as seguintes classes:

* Desconto: A classe abstrata base para todos os descontos. Ela possui um atributo proximo do tipo Desconto para encadear descontos em uma sequência. A classe também define o método abstrato calcular(orcamento: Orcamento): BigDecimal que será implementado pelas subclasses concretas.

* DescontoValorMaiorQueMil: Uma subclasse de Desconto que implementa o desconto para valores maiores que mil. Ela verifica se o valor do Orcamento é maior que mil e retorna um desconto de 8% do valor, caso contrário, chama o método calcular() do próximo desconto na sequência.

* SemDesconto: Uma subclasse de Desconto que representa a situação em que não há desconto. Ela retorna zero como resultado do cálculo do desconto.

* Orcamento: Representa um orçamento de veículos com os atributos valor (do tipo BigDecimal) e quantidadeDias (do tipo int). Possui métodos para acessar esses atributos.

* CalculadoraDeTaxas: Classe responsável por calcular as taxas com base no tipo de taxa selecionado. Possui o método calcular(orcamento: Orcamento, tipoTaxa: TipoTaxa): BigDecimal que recebe o orçamento

<div align="center">
  <img src="https://github.com/LucasMonteiiroo/Bertoti/blob/main/PadroesDeProjetos/Padr%C3%A3oStrategy/Strategyy.png">
 </div> 
