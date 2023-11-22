# Lucas Rodrigues Monteiro da Silva

**Introdução**

Olá, é um prazer recebe-lo em meu portifólio. Sou o Lucas Rodrigues, graduando em Banco de Dados pela FATEC - Prof. Jessen Vidal.

Aqui, contará com uma descrição sobre os meus projetos semestrais e como atuei em cada um, durante a minha formação. 
## Projeto 1

## PLIINB

Assistente Pessoal Virtual vinculado à API Google Calendar, no qual o usuário usará comandos de voz para acessar a Agenda Google através da API, executar os comandos de consultar, editar e visualizar compromissos da agenda. Além de consumir API do sexto semestre.

**Lista de comandos possíveis:**

-   Consultar agenda;
-   Ler compromissos do dia;
-   Incluir compromisso;
-   Editar compromisso;
-   Excluir compromisso;
-   Fechar agenda.

-   ## Tecnologias utilizadas 🔍

  **Python 3.6 (com Flask)** 🛠️
* Ao optar por empregar o Flask no desenvolvimento da interface web da assistente virtual, os usuários serão beneficiados por uma interação intuitiva e acessível. Adicionalmente, o framework proporciona a flexibilidade essencial para ampliar e aprimorar as funcionalidades da assistente ao longo do tempo, adaptando-se às mudanças e requisitos que possam surgir durante a evolução do projeto. Essa abordagem oferece uma base sólida para aperfeiçoar a experiência do usuário e expandir as capacidades da assistente conforme as demandas do contexto.

**Spyder (IDE)** 👾
* O Spyder se destaca como uma ferramenta essencial para a criação da assistente virtual. Como uma IDE dedicada ao Python, oferece um ambiente familiar e intuitivo para os desenvolvedores. Sua interface amigável simplifica o processo de escrita, oferecendo não apenas uma interface amigável, mas também ferramentas poderosas e integradas que facilitam desde a manipulação de dados até a depuração e personalização do código Python.
    
**Agenda do Google (API Google Calendar)** 💻 
* A integração da Agenda do Google, por meio da API Google Calendar, desempenha um papel fundamental no projeto da assistente virtual. Essa integração permite que a assistente acesse, gerencie e utilize informações diretamente a partir do Google Calendar.

* A assistente pode agendar compromissos, criar lembretes, verificar disponibilidade de horários e até mesmo realizar confirmações ou cancelamentos de eventos na agenda dos usuários. Isso se traduz em uma experiência mais conveniente e personalizada, onde a assistente pode fornecer informações relevantes sobre eventos agendados, lembretes de compromissos, e até mesmo automatizar algumas interações relacionadas à agenda.
    
**Trello** 📊 
* A utilização do trello serviu para dividir as tarefas em cartões, que podem representar desde funcionalidades específicas da assistente até atividades de desenvolvimento, testes, e revisões

 **StackEdit** 📁
 * A funcionalidade de colaboração em tempo real do StackEdit é valiosa para equipes distribuídas ou que trabalham remotamente. Vários membros podem editar um documento simultaneamente, visualizando as alterações em tempo real, o que facilita a revisão, o compartilhamento de ideias e a tomada de decisões colaborativas.

  ## Contribuições pessoais 🎓

 <details>
 
<summary>Função autenticacao_google()</summary>
<br>
Esta função verifica a existência do arquivo token.pickle, que contém as credenciais de acesso. Caso as credenciais não existam, sejam inválidas ou estejam expiradas, o código inicia um processo de renovação, verificando se há um token de atualização disponível (refresh_token) para as credenciais expiradas. Em caso positivo, as credenciais são renovadas. Caso contrário, o código inicia um novo fluxo de autenticação utilizando o arquivo credentials.json. Ao finalizar esses procedimentos, a função retorna um serviço autenticado, pronto para interagir com a API do Google Calendar de forma segura e autorizada.

```python
def autenticacao_google():
    creds = None
    if os.path.exists('token.pickle'):
        with open('token.pickle', 'rb') as token:
            creds = pickle.load(token)

    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(
                'credentials.json', SCOPES)
            creds = flow.run_local_server(port=0)

        with open('token.pickle', 'wb') as token:
            pickle.dump(creds, token)

    service = build('calendar', 'v3', credentials=creds)
    return service
    
 ```
 </details>

<details>
<summary>função eventos(day, service)</summary>
<br>
O código da função eventos(day, service) em Python é responsável por listar os eventos do Google Calendar para um determinado dia. Ele começa combinando a data inicial e final do dia especificado para criar um intervalo de tempo para a busca de eventos. Em seguida, utiliza o serviço da API do Google Calendar para buscar os eventos nesse intervalo de tempo, utilizando os parâmetros de tempo timeMin e timeMax.

Se não existirem eventos para o dia especificado, o código imprime uma mensagem informando que não há compromissos e espera por uma entrada para continuar.

Caso existam eventos, o código limpa a tela, lista o intervalo de tempo e itera sobre cada evento encontrado. Para cada evento, imprime o título e a data/horário de início. Além disso, chama a função editarEventos(events, service) para possibilitar a edição dos eventos listados. Esta função auxiliar permite a manipulação dos eventos presentes no Google Calendar.
 
```python
def eventos(day, service):
    date = datetime.datetime.combine(day, datetime.datetime.min.time())
    end_date = datetime.datetime.combine(day, datetime.datetime.max.time())
    utc = pytz.UTC
    date_utc = date.astimezone(utc)
    end_date_utc = end_date.astimezone(utc)
    
    events_result = service.events().list(calendarId='primary', 
                                          timeMin=date_utc.isoformat(),
                                          timeMax=end_date_utc.isoformat(),
                                          singleEvents=True,
                                          orderBy='startTime').execute()
    events = events_result.get('items', [])

    if not events:
        print('Não existem compromissos neste dia.')
        input("Pressione uma tecla para continuar...")
    else:
        clear()
        lin()
        print("Listando Eventos ")
        print("Inicio:", date)
        print("Fim:", end_date)
        for event in events:
            lin()
            print("Titulo:"+ event['summary'])
            print("Data e Horário: "+ event['start'].get('dateTime'))
            lin()
            print("")

        editarEventos(events, service)

```
 </details>

****

## Projeto 2

**Empresa parceira**

## TecSUS
![image](https://github.com/LucasMonteiiroo/Bertoti/blob/main/Metodologia/Logos/logo_tecsus_horizontal.png)

A TecSUS realiza a gestão de contas de utilidades (água e energia) dos seus clientes. Todos os meses milhares de contas devem ser digitadas manualmente no sistema para a realização de análises de contratos e análises de consumo. Desta forma, para facilitar a digitação das contas precisamos de um sistema que permita o cadastro de cada conta de forma rápida e ágil, sem a necessidade de utilização do mouse, apenas por comandos do teclado.

## Tecnologias utilizadas 🔍

**Eclipse**
* No contexto específico da TecSUS, onde é necessário criar um sistema para facilitar a digitação das contas de utilidades (água e energia) dos clientes, o Eclipse pode ser uma excelente escolha para desenvolver essa aplicação. Ele permite criar uma interface amigável e eficiente, adaptada para facilitar o cadastro rápido e ágil das contas, priorizando a entrada de dados por meio de comandos do teclado, sem depender do uso do mouse.

**MySQL**
* O MySQL é um sistema de gerenciamento de banco de dados relacional muito popular, conhecido por ser de código aberto, confiável e amplamente utilizado em muitas aplicações e sistemas em todo o mundo.

* No contexto da TecSUS, onde é necessário gerenciar e armazenar dados relacionados às contas de utilidades dos clientes (como dados de consumo de água e energia), o MySQL pode desempenhar um papel fundamental como um banco de dados confiável.

**BrModelo 2.0**
* No contexto da TecSUS, onde é essencial gerenciar e organizar os dados das contas de utilidades dos clientes, o BRModelo pode ser empregado para criar um modelo ER representando a estrutura das informações necessárias para o armazenamento no banco de dados, como detalhes das contas, informações de consumo, informações dos clientes, entre outros.

## Contribuições pessoais 🎓
<details>
<summary>função eventos(day, service)</summary>
<br>

</details>

****


## Projeto 3

**Empresa parceira**

## IACIT soluções tecnológicas S.A.

![image](https://github.com/LucasMonteiiroo/Bertoti/assets/65603418/f5b6e3b7-b3d5-41f5-9747-7b3c42605942)


Foi proposto um desafio de desenvolver um sistema que possa importar dados meteorológicos de arquivos CSV fornecidos pela empresa IACIT. O sistema deve armazenar esses dados em um banco de dados e permitir a geração de relatórios solicitados pelos clientes da empresa. A aplicação web deve ser capaz de filtrar os registros com base em regiões, estados, estações, tipos de dados e intervalos de datas. Além disso, ela deve exibir as informações em forma de gráficos e cards. Por fim, o sistema deve permitir a geração de relatórios com base nas pesquisas realizadas pelos usuários.

* Requisitos funcionais exigidos: Cadastro de estações, cadastro de estados e regiões, importação de dados e geração de relatórios.

* Requisitos não funcionais exigidos: Sistema Web, linguagem java, banco de dados relacional e documentações.

## Tecnologias utilizadas 🔍

**HTML, CSS e Javascript**  💻 

* Para criar a interface gráfica da aplicação, foram combinadas as linguagens de marcação HTML e CSS com a linguagem de programação JavaScript. O HTML é responsável pela estrutura básica da página da web, enquanto o CSS define o estilo visual e a aparência da página. Já o JavaScript é utilizado para adicionar interatividade à página, permitindo que os usuários realizem ações e que os dados sejam exibidos e atualizados dinamicamente. Ao utilizar o HTML, CSS e JavaScript em conjunto, foi possível desenvolver uma interface amigável e intuitiva para os usuários da aplicação. Isso permite que eles visualizem os dados meteorológicos e gerem relatórios de forma fácil e eficiente. Além disso, a aplicação fez uso da biblioteca de JavaScript chamada Charts, que possibilitou a apresentação dos dados em forma de gráficos de maneira adequada.

**Java** 🛠️

* Para desenvolver o back-end da aplicação, foi utilizada a linguagem Java em conjunto com o framework Spring Boot. O Spring Boot é uma estrutura que agiliza a criação de aplicativos web em Java, oferecendo um conjunto de ferramentas e bibliotecas que facilitam o desenvolvimento. Com o uso do Java e do Spring Boot, foi possível criar o back-end da aplicação de forma eficiente, aproveitando as vantagens proporcionadas por essa combinação de tecnologias.

**PostgresSQL** 🐘

* Para armazenar todos os dados meteorológicos, dados de estações e regiões, foi utilizado o banco de dados PostgreSQL, que é um banco de dados relacional. O PostgreSQL foi escolhido devido à sua versatilidade, interface simplificada e funcionalidades intuitivas, o que facilitou o uso e o gerenciamento do banco de dados. Sua eficiência e desempenho contribuíram para o armazenamento tranquilo dos milhões de dados meteorológicos importados, garantindo um bom desempenho do sistema.

## Contribuições pessoais 🎓

 Durante o projeto, trabalhando como PO, pude aprimorar minhas habilidades nessa função. Em um ambiente ágil e colaborativo, a metodologia Scrum desempenha um papel fundamental na gestão de projetos. O Product Owner (PO) é responsável por definir as prioridades e guiar a equipe para alcançar os objetivos do produto. Neste contexto, a priorização de tarefas é uma atividade crucial para garantir o sucesso do projeto.
 
 Abaixo é possivel visualizar alguns tópicos que do que precisiei trabalhar com o time. 

 <details>
 
<summary>Compreensão das necessidades do cliente</summary>
 <br>
 O primeiro passo para o PO é entender profundamente as necessidades do cliente e os objetivos do produto. Isso envolve a comunicação efetiva com os stakeholders e a coleta de feedback contínuo. Ao ter uma visão clara das expectativas, o PO pode definir prioridades com base na entrega de valor para o cliente.
 
 </details>
 
 <details>
 <summary> Definição do Product Backlog </summary>
 <br>
 O Product Backlog é uma lista ordenada de itens que representam as funcionalidades, requisitos e melhorias do produto. O PO, em colaboração com a equipe, é responsável por criar e manter esse backlog. A priorização das tarefas é feita atribuindo um valor de negócio a cada item, considerando seu impacto no sucesso do produto
 
 </details>
 
  <details>
 <summary> Reuniões de Planejamento </summary>
 <br>
 Durante as reuniões de planejamento do Sprint, o PO trabalha em conjunto com a equipe para definir as tarefas que serão incluídas no Sprint Backlog. Com base na priorização do Product Backlog, o PO seleciona as tarefas mais relevantes para o Sprint atual. A equipe discute os detalhes e estima o esforço necessário para concluir cada tarefa
 
 </details>
   
   <details>
 <summary> Colaboração contínua </summary>
 <br>
 Durante o Sprint, o PO mantém uma comunicação aberta com a equipe. Ele fornece esclarecimentos sobre os requisitos, responde a perguntas e ajusta a prioridade, se necessário. A colaboração contínua é essencial para garantir que as tarefas estejam alinhadas com as expectativas e que a equipe possa trabalhar de forma eficiente.
 
 </details>
 
   <details>
 <summary> Adpatação ao longo do tempo </summary>
 <br>
A priorização de tarefas é um processo dinâmico. À medida que o projeto evolui, novas informações e feedback podem surgir, o que pode exigir ajustes nas prioridades. O PO deve estar aberto a essas mudanças e ser capaz de adaptar-se às necessidades em constante mudança.
 
 </details>
 
 Colaborei também com a implementação da regra de negocio a nossa modelagem de dados, onde demos o inicio do banco e estruturamos o projeto. 
 
   <details>
 <summary> Modelagem </summary>
 <br>
Nesse caso, a cidade em si serve como a estação que desempenha o papel de coordenar e distribuir as informações entre as diversas entidades envolvidas. Essa estrutura permite que as entidades dependam da cidade/estação para acessar e compartilhar as informações necessárias para suas atividades. A cidade/estação atua como um ponto central de referência, garantindo a integridade e consistência das informações, e facilitando a colaboração eficiente entre as partes envolvidas. Essa abordagem permite uma gestão mais eficaz e uma melhor organização dos dados, contribuindo para o sucesso e eficiência geral das operações.
 <br>
 <img src="caminho_da_imagem.png" alt="Texto alternativo" width="300" height="200">

 </details>
 
 
 ## Projeto 4

**Empresa parceira**

## Embraer 
Atualmente, muitos pilotos trabalham como "freelancers" e não operam apenas uma aeronave/frota, o que dificulta o reconhecimento de determinados equipamentos e versões de software instalados na aeronave. Além disso, o piloto nem sempre tem acesso rápido a todos os manuais e documentos da aeronave, o que dificulta o seu conhecimento sobre a aeronave e as suas capacidades.

**Requisitos Funcionais**
* Utilização de APIs para futura integração com sistemas de plataformas móveis
- Acesso à base de dados de clientes (utilizador, password e equipamento)
- Os utilizadores devem ter perfis diferentes (administrador, edição e consulta)
- Possibilidade de aplicação de filtros de seleção (por categoria de artigo)
- Visualização de ficheiros PDF na App e possibilidade de exportação no mesmo formato, com registo da data de geração do ficheiro e do utilizador.

## Tecnologias utilizadas 🔍

**Java e Spring**
O back-end da aplicação foi desenvolvido em Java juntamente com o framework Spring, onde foi realizada a lógica das regras de negócio para determinação de quais itens eram considerados instalados, instaláveis ou não instaláveis de acordo com os chassis que fossem pesquisados. Através do Spring Data foi possível realizar consultas de forma mais eficiente no banco de dados para tratamento das lógicas e condições no back-end.

**Oracle Autonomous Database**
Para armazenamento dos dados, foi utilizado o Oracle Autonomous Database, um banco relacional em nuvem. Foi escolhido devido a possibilidade de consultar o banco através da internet, proporcionando uma melhor acessibilidade, praticidade e flexibilidade. Além do mais, também proporcionou maior segurança, tanto no controle de acesso ao banco, quanto na consistência dos dados, pois eram gerados backups automáticos que protegiam contra a perda de dados e facilitavam a recuperação em caso de falhas de operação.








 
 
