# Lucas Rodrigues Monteiro da Silva

**Introdução**

Olá, é um prazer recebe-lo em meu portifólio. Sou o Lucas Rodrigues, graduando em Banco de Dados pela FATEC - Prof. Jessen Vidal.

Minha trajetória na área de tecnologia começou com o curso técnico em Desenvolvimento de Sistemas na Etec Machado de Assis, uma instituição pertencente ao renomado grupo do Centro Paula Souza, que também inclui a Fatec. Foi nessa etapa inicial que desenvolvi minha paixão pela tecnologia e obtive os conhecimentos fundamentais que me impulsionariam na carreira.

Durante o curso, uma das matérias que mais me cativou foi a de banco de dados. Desde então, decidi aprofundar meus estudos nessa área e seguir esse caminho profissionalmente, iniciando então o curos de Banco de Dados. Ao concluir o sexto semestre na Fatec, consigo refletir sobre toda a minha evolução, tanto acadêmica quanto profissional.

Nesses anos de estudo, tive a oportunidade de aplicar meus conhecimentos em diversos projetos, o que me permitiu consolidar e expandir minhas habilidades técnicas (hard skills) e interpessoais (soft skills). Essa experiência prática, aliada ao contínuo desenvolvimento de competências, me preparou para enfrentar os desafios do mercado de trabalho e me proporcionou um sólido crescimento profissional na área de tecnologia.

Aqui, contará com uma descrição sobre os meus projetos semestrais e como atuei em cada um, durante a minha formação. 

<p align="center">
<img src="https://github.com/LucasMonteiiroo/Bertoti/blob/main/Metodologia/Logos/EU.jfif" width="300" height="300">
</p>
<p align="center">
<a href="lucasmonteiror.lm@gmail.com"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
<a href="https://www.linkedin.com/in/lucas-monteiro-56585117b/" target="_blank"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
</p>

## Projeto 1

## PLIINB

Assistente Pessoal Virtual vinculado à API Google Calendar, no qual o usuário usará comandos de voz para acessar a Agenda Google através da API, executar os comandos de consultar, editar e visualizar compromissos da agenda. Além de consumir API do sexto semestre.

**Lista de comandos possíveis:**

-   ## Tecnologias utilizadas 🔍

  **Python 3.6 (com Flask)** 
* Ao optar por empregar o Flask no desenvolvimento da interface web da assistente virtual, os usuários serão beneficiados por uma interação intuitiva e acessível. Adicionalmente, o framework proporciona a flexibilidade essencial para ampliar e aprimorar as funcionalidades da assistente ao longo do tempo, adaptando-se às mudanças e requisitos que possam surgir durante a evolução do projeto. Essa abordagem oferece uma base sólida para aperfeiçoar a experiência do usuário e expandir as capacidades da assistente conforme as demandas do contexto.

**Spyder (IDE)** 
* O Spyder se destaca como uma ferramenta essencial para a criação da assistente virtual. Como uma IDE dedicada ao Python, oferece um ambiente familiar e intuitivo para os desenvolvedores. Sua interface amigável simplifica o processo de escrita, oferecendo não apenas uma interface amigável, mas também ferramentas poderosas e integradas que facilitam desde a manipulação de dados até a depuração e personalização do código Python.
    
**Agenda do Google (API Google Calendar)** 
* A integração da Agenda do Google, por meio da API Google Calendar, desempenha um papel fundamental no projeto da assistente virtual. Essa integração permite que a assistente acesse, gerencie e utilize informações diretamente a partir do Google Calendar.

* A assistente pode agendar compromissos, criar lembretes, verificar disponibilidade de horários e até mesmo realizar confirmações ou cancelamentos de eventos na agenda dos usuários. Isso se traduz em uma experiência mais conveniente e personalizada, onde a assistente pode fornecer informações relevantes sobre eventos agendados, lembretes de compromissos, e até mesmo automatizar algumas interações relacionadas à agenda.
    
**Trello** 
* A utilização do trello serviu para dividir as tarefas em cartões, que podem representar desde funcionalidades específicas da assistente até atividades de desenvolvimento, testes, e revisões

 **StackEdit**
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

 ## Aprendizados efetivos 🎓

 HARD SKILLS

 <details>
 <summary> Linguagem Python </summary>
 <br>
Esses trechos de código me permitiram aprofundar meu conhecimento em manipulação de arquivos, datas e interação com APIs externas, particularmente a API do Google Calendar. 
</details>

 SOFT SKILLS

 <details>
 <summary> Comunicação 
 </summary>
 <br>
Foi necessário aprimorar minha comunicação com os membros do grupo para facilitar a compreensão e colaboração entre todos os membros da equipe.
</details>

[GitHub](https://github.com/BrunoGDF/PLIINB).


## Projeto 2

**Empresa parceira**

## TecSUS
<p align="center">
<img src="https://github.com/LucasMonteiiroo/Bertoti/blob/main/Metodologia/Logos/logo_tecsus_horizontal.png" width="260" height="80">
</p>
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
<summary> Conexão com o banco de dados </summary>
<br>
 A classe ConexaoBD que é responsável por gerenciar conexões com um banco de dados MySQL em nosso projeto.

 Essa classe fornece três principais funcionalidades:

Conexão com o Banco de Dados: O método conexao() estabelece uma conexão com o banco de dados MySQL utilizando as informações fornecidas, como o URL de conexão, nome de usuário e senha. Ele utiliza o driver JDBC para se conectar ao banco de dados.

Execução de Consultas SQL: O método executaSql(String sql) recebe uma consulta SQL como parâmetro e a executa no banco de dados. Ele cria um objeto Statement e executa a consulta, armazenando os resultados em um objeto ResultSet.

Desconexão: O método desconecta() é responsável por fechar a conexão com o banco de dados quando ela não é mais necessária.

 ```python
package modeloConnection;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConexaoBD {

	private String driver = "DriverManager.getConnection";
	private String caminho = "jdbc:mysql://localhost:3306/projetointegrador?&Timezone=true&serverTimezone=UTC";
	private String usuario = "root";
	private String senha = "Amor041612#";

	public Connection con;
	public Statement stm;
	public ResultSet rs;

	public void conexao() {
		try {
			System.setProperty("jdbc.Drivers", driver);
			con = DriverManager.getConnection(caminho, usuario, senha);
			//JOptionPane.showMessageDialog(null, "Conex�o Efetuada");

		} catch

		(SQLException e) {
			JOptionPane.showMessageDialog(null, "Conex�o com Erro \n" + e);

		}
	}
	
	public void executaSql(String sql) {
		try {
			stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ExecutaSQL: \n " + e.getMessage());

		}

	}

	public void desconecta() {

		try {
			con.close();
			//JOptionPane.showMessageDialog(null, "Desconectado");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao fechar a conex�o" + e);

		}
	}

	
}

```
</details>
<details>
<summary> Relatorio </summary>
<br>
Esta classe é responsável por criar e exibir uma janela de relatório em nossa aplicação.

A classe possui um método main que inicia a aplicação, criando uma instância da classe TelaRelatorio e tornando a janela visível. Além disso, no método initialize, configuramos a janela com o título "RELATÓRIO", dimensões de 960x720 pixels, cor de fundo branca e a ação de fechamento padrão ao clicar no botão de fechar.

Essa classe é uma parte importante do nosso sistema, pois nos permite visualizar relatórios de forma clara e organizada.

 ```python
package DigiCont;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;

public class TelaRelatorio {

	JFrame frmRelatrio;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelatorio window = new TelaRelatorio();
					window.frmRelatrio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaRelatorio() {
		initialize();
	}

	private void initialize() {
		frmRelatrio = new JFrame();
		frmRelatrio.setTitle("RELATÓRIO");
		frmRelatrio.getContentPane().setBackground(Color.WHITE);
		frmRelatrio.setBounds(100, 100, 960, 720);
		frmRelatrio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRelatrio.setLocationRelativeTo(null);
	}
}


```
 </details>


 ## Aprendizados efetivos 🎓

 HARD SKILLS

 <details>
 <summary> Gerenciamento de Conexões </summary>
 <br>
Trabalhando com o código neste projeto, aprendi a estabelecer conexões seguras e eficientes com um banco de dados MySQL em Java. Além disso, dominei técnicas para gerenciar recursos críticos, como statements e result sets, garantindo seu fechamento adequado para evitar vazamentos de memória e maximizar o desempenho do sistema.
</details>

 SOFT SKILLS

 <details>
 <summary> Trabalho em Equipe
 </summary>
 <br>
Capacidade de colaborar eficazmente com outras pessoas, combinando habilidades e esforços para alcançar objetivos comuns e solucionar problemas coletivamente.
</details>

[GitHub](https://github.com/assenvitor/ProjetoTecSUS).


## Projeto 3

**Empresa parceira**

## IACIT soluções tecnológicas S.A.
<p align="center">
<img src="https://github.com/LucasMonteiiroo/Bertoti/assets/65603418/f5b6e3b7-b3d5-41f5-9747-7b3c42605942" width="200" height="80">

</p>
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
 
 
 
   <details>
 <summary> Modelagem </summary>
 <br>
Colaborei também com a implementação da regra de negocio a nossa modelagem de dados, onde demos o inicio do banco e estruturamos o projeto.    
    
Nesse caso, a cidade em si serve como a estação que desempenha o papel de coordenar e distribuir as informações entre as diversas entidades envolvidas. Essa estrutura permite que as entidades dependam da cidade/estação para acessar e compartilhar as informações necessárias para suas atividades. A cidade/estação atua como um ponto central de referência, garantindo a integridade e consistência das informações, e facilitando a colaboração eficiente entre as partes envolvidas. Essa abordagem permite uma gestão mais eficaz e uma melhor organização dos dados, contribuindo para o sucesso e eficiência geral das operações.
 
 <br>
 <img src="https://github.com/LucasMonteiiroo/Bertoti/blob/main/Metodologia/Logos/modelagemIACIT.png" width="600" height="500">

 </details>
 
 
 ## Aprendizados efetivos 🎓

 HARD SKILLS

 <details>
 <summary> Gestão de Projetos </summary>
 <br>
Planejamento de Sprints: Capacidade de planejar, executar e encerrar sprints de forma eficiente. 

Utilização de ferramentas e métricas para monitorar o progresso do projeto e da equipe.

</details>

 SOFT SKILLS

 <details>
 <summary> Ética e Responsabilidade
 </summary>
 <br>
no papel de Scrum Master envolvem a atuação com integridade e transparência, assegurando que a equipe siga os princípios ágeis e os padrões éticos, e facilitando um ambiente de trabalho que priorize a honestidade, o respeito e o cumprimento de compromissos.
</details>

[GitHub](https://github.com/CarcaraTec/IACIT).

 
 ## Projeto 4

**Empresa parceira**

## Embraer 
<p align="center">
<img src="https://github.com/LucasMonteiiroo/Bertoti/blob/main/Metodologia/Logos/embraer.png" width="220" height="100">

</p>
Atualmente, muitos pilotos trabalham como "freelancers" e não operam apenas uma aeronave/frota, o que dificulta o reconhecimento de determinados equipamentos e versões de software instalados na aeronave. Além disso, o piloto nem sempre tem acesso rápido a todos os manuais e documentos da aeronave, o que dificulta o seu conhecimento sobre a aeronave e as suas capacidades.

**Requisitos Funcionais**
* Utilização de APIs para futura integração com sistemas de plataformas móveis
- Acesso à base de dados de clientes (utilizador, password e equipamento)
- Os utilizadores devem ter perfis diferentes (administrador, edição e consulta)
- Possibilidade de aplicação de filtros de seleção (por categoria de artigo)
- Visualização de ficheiros PDF na App e possibilidade de exportação no mesmo formato, com registo da data de geração do ficheiro e do utilizador.

## Tecnologias utilizadas 🔍

**Java e Spring**

* O back-end da aplicação foi desenvolvido em Java juntamente com o framework Spring, onde foi realizada a lógica das regras de negócio para determinação de quais itens eram considerados instalados, instaláveis ou não instaláveis de acordo com os chassis que fossem pesquisados. Através do Spring Data foi possível realizar consultas de forma mais eficiente no banco de dados para tratamento das lógicas e condições no back-end.

**Oracle Autonomous Database**

* Para armazenamento dos dados, foi utilizado o Oracle Autonomous Database, um banco relacional em nuvem. Foi escolhido devido a possibilidade de consultar o banco através da internet, proporcionando uma melhor acessibilidade, praticidade e flexibilidade. Além do mais, também proporcionou maior segurança, tanto no controle de acesso ao banco, quanto na consistência dos dados, pois eram gerados backups automáticos que protegiam contra a perda de dados e facilitavam a recuperação em caso de falhas de operação.

**Vue.js**

* A interface do usuário foi construída com Vue.js, um framework JavaScript que oferece uma arquitetura organizada através da criação de componentes reutilizáveis. Isso simplificou o desenvolvimento da plataforma, já que diversas partes visuais da interface compartilhavam semelhanças.

**AWS**

* Para hospedar a aplicação em um servidor, foi empregada uma instância da AWS, uma das principais provedoras de infraestrutura em nuvem para aplicações online. Utilizando a AWS, foi possível hospedar a aplicação na nuvem, possibilitando o acesso ao sistema através do endereço do servidor AWS. Isso viabilizou o acesso por diversos dispositivos, como smartphones, tablets e outros dispositivos com acesso à internet.


## Contribuições pessoais 🎓

 <details>
 <summary> Definição de rotas </summary>
 <br>

Essa constante MainRoutes define as principais rotas da nossa aplicação. Vamos dar uma olhada em alguns pontos-chave:

path: Define o caminho da rota, que será acessado na barra de endereço do navegador. Aqui, definimos que nossas rotas principais estarão sob o caminho /main.

meta: { requiresAuth: true }: Esta é uma meta-informação que indica que essas rotas requerem autenticação para serem acessadas. Isso é importante para garantir a segurança da nossa aplicação.

redirect: Redireciona para /main caso o usuário acesse a rota principal.

component: Define o componente Vue que será renderizado quando a rota for acessada. Utilizamos uma função de importação dinâmica (import()) para carregar o componente sob demanda, o que ajuda a melhorar o desempenho da aplicação.

children: Define rotas filhas dentro da rota principal. Cada rota filha segue a mesma estrutura que a rota principal, incluindo o nome, caminho e componente associado.

Essas rotas definem a estrutura da nossa aplicação frontend, permitindo que os usuários naveguem entre diferentes páginas e recursos de forma eficiente.


 
```python
const MainRoutes = {
    path: '/main',
    meta: {
        requiresAuth: true
    },
    redirect: '/main',
    component: () => import('@/layouts/full/FullLayout.vue'),
    children: [
        {
            name: 'Dashboard',
            path: '/',
            component: () => import('@/views/dashboard/index.vue')
        },
        {
            name: 'Typography',
            path: '/ui/typography',
            component: () => import('@/views/components/Typography.vue')
        },
        {
            name: 'Shadow',
            path: '/ui/shadow',
            component: () => import('@/views/components/Shadow.vue')
        },
        {
            name: 'Icons',
            path: '/icons',
            component: () => import('@/views/pages/Icons.vue')
        },
        {
            name: 'UserTable',
            path: '/userTable',
            component: () => import('@/components/user/ConsultaTable.vue')
        },
        {
            name: 'Statistic',
            path: '/statistic',
            component: () => import('@/views/pages/Statistics.vue')
        },
        {
            name: 'UserManager',
            path: '/userManager',
            component: () => import('@/views/pages/UserManager.vue')
        },
        {
            name: 'EditorTable',
            path: '/editorTable',
            component: () => import('@/views/pages/EditorTable.vue')
        },
        {
            name: 'Item',
            path: '/item/:id',
            component: () => import('@/views/pages/ItemCraft.vue')
          },
          {
            name: 'Chassi',
            path: '/chassi/:id',
            component: () => import('@/views/pages/ChassiProfile.vue')
          },
          
    ]
};

export default MainRoutes;

```
 </details>



 ## Aprendizados efetivos 🎓

 HARD SKILLS

 <details>
 <summary> Java </summary>
 <br>
Importante para desenvolvimento de todo o projeto, pude aprender e aperfeiçoar com os meus colegas
</details>

 SOFT SKILLS

 <details>
 <summary> Autonomia
 </summary>
 <br>
Proatividade em aprender novas tecnologias e metodologias, e auto-gestão de tarefas.
</details>

[GitHub](https://github.com/CarcaraTec/Embraer).


## Projeto 5

**Empresa parceira**

## Oracle

<p align="center">
<img src="https://github.com/LucasMonteiiroo/Bertoti/blob/main/Metodologia/Logos/Oracle_Logo.jpg" width="200" height="80">

O desafio consistiu em criar uma plataforma online abrangente para a gestão eficiente de um restaurante, contemplando funcionalidades como controle de estoque e fornecedores, análise de desempenho de funcionários e pratos, e a visualização de dados através de gráficos e relatórios. O objetivo principal era auxiliar os proprietários na administração eficaz do restaurante, facilitando a gestão de custos, inventário e pessoal, e fornecendo uma visão clara das operações.

A solução foi desenvolvida como um sistema web, organizado em diversas seções, que realiza a coleta e processamento de dados da base de dados do restaurante. Os resultados são apresentados de maneira intuitiva por meio de gráficos, tabelas e cards, proporcionando insights detalhados sobre estoque, vendas, pratos e desempenho de funcionários. Com isso, a plataforma oferece uma ferramenta poderosa e prática para a tomada de decisões estratégicas pelos gestores do estabelecimento.


## Tecnologias utilizadas 🔍

**Java e Spring**

* Para o desenvolvimento do back-end, utilizamos Java em conjunto com o framework Spring para criar uma API com arquitetura REST. Esta API é responsável por gerenciar o mapeamento das rotas e possibilitar a comunicação entre o sistema e o banco de dados. Foram criados endpoints que suportam operações CRUD (Create, Read, Update, Delete) para interagir eficientemente com os dados de pratos, funcionários e estoque, facilitando o consumo dessas informações pelo front-end. Com essa abordagem, garantimos uma estrutura flexível e eficiente para a gestão das operações do restaurante.

**Oracle Autonomous Database**

* O banco de dados foi hospedado na nuvem utilizando o Oracle Autonomous Database, permitindo o acesso aos dados de qualquer lugar com conexão à internet. A escolha desse banco de dados foi influenciada pela segurança oferecida, incluindo acesso restrito através de wallet, o que garantiu maior proteção e confiabilidade das informações armazenadas.

**Vue.js**

* Com o framework Vue.js, conseguimos modularizar os elementos do front-end, permitindo sua reutilização em diferentes partes da aplicação. Isso facilitou significativamente a organização e manutenção do código, além de melhorar a escalabilidade e a legibilidade da interface.


## Contribuições pessoais 🎓

 <details>
 <summary> Modelagem do banco de dados </summary>
 <br>

 </details>

  <details>
 <summary> Criação de Views  </summary>
 <br>
Simplificação de Consultas: Views permitem encapsular consultas SQL complexas em uma estrutura simples e reutilizável. Ao invés de escrever uma consulta longa e complicada toda vez, uma view permite acessar essa lógica complexa de forma direta e simplificada.

 </details>

   <details>
 <summary> Criação de índices  </summary>
 <br>
Busca Rápida: Índices permitem que o banco de dados localize rapidamente as linhas desejadas sem precisar escanear a tabela inteira, acelerando significativamente a execução das consultas.

Foram criados diversos indices nas colunas que buscavam datas. 
 </details>


 ## Aprendizados efetivos 🎓

 HARD SKILLS

 <details>
 <summary> Conhecimento em DevOps</summary>
 <br>
 Integração contínua, entrega contínua e operações automatizadas.
</details>

 SOFT SKILLS

 <details>
 <summary> Criatividade
 </summary>
 <br>
A capacidade de gerar ideias inovadoras e soluções únicas, aplicando novas abordagens e perspectivas para resolver problemas de maneira eficaz e original. Brainstorming de novas ideias.
</details>

[GitHub](https://github.com/CarcaraTec/Cloud-Kitchen-Oracle).


## Projeto 6

**Empresa parceira**

## Imagem

<p align="center">
<img src="https://github.com/LucasMonteiiroo/Bertoti/blob/main/Metodologia/Logos/imagem.jpg" width="220" height="140">

O desafio consistiu em criar uma plataforma que analisa e visualiza os sentimentos dos clientes a partir de avaliações online, utilizando tecnologia de ponta para fornecer insights contextualizados geograficamente.

Desenvolvemos uma solução com inteligência artificial para analisar sentimentos em avaliações de clientes de hotéis, utilizando um banco de dados não relacional. O software fornece insights através de mapas interativos, gráficos de tendências, cards informativos e um sistema de gerenciamento de acesso integrado.

## Tecnologias Utilizadas 🔍

**Java e Spring Boot**

* A linguagem Java foi escolhida pela sua robustez e capacidade de lidar com grandes volumes de transações. Em conjunto com o framework Spring Boot, que facilita a criação de aplicações Java, foi desenvolvida a camada de segurança da aplicação. O Spring Boot oferece módulos como Spring Security, que garantem autenticação e autorização eficientes e seguras. Ele permite a criação de serviços RESTful seguros, assegurando que os dados sensíveis dos usuários estejam protegidos contra acessos não autorizados.

**Python e Flask**

* Python foi selecionado pela sua simplicidade e eficiência no desenvolvimento rápido de aplicações. Utilizando o framework Flask, conhecido pela sua leveza e flexibilidade, foi desenvolvida a camada web e de API's REST da aplicação. Flask facilita a criação de endpoints RESTful e a integração com outros serviços, possibilitando uma comunicação fluida entre o frontend e o backend. A escolha por Flask também se deve ao seu suporte robusto para bibliotecas de machine learning e análise de dados, que são fundamentais para a parte de IA da solução.

**MongoDB**

* MongoDB, um banco de dados não relacional, foi escolhido para armazenar os dados não estruturados do nosso dataset, como as avaliações dos clientes. Sua arquitetura baseada em documentos permite uma grande flexibilidade no armazenamento e na recuperação de dados complexos e variados, que são comuns em análises de sentimentos. A escalabilidade horizontal do MongoDB garante que o sistema possa crescer de acordo com a demanda, mantendo a performance eficiente.

**MySQL**

* MySQL foi utilizado como o sistema de gerenciamento de banco de dados relacional para armazenar informações estruturadas, como dados de usuários e suas interações com a aplicação. Conhecido por sua confiabilidade e robustez, o MySQL facilita a execução de operações complexas de busca e manipulação de dados, garantindo a integridade e a consistência dos dados armazenados.

**Vue.js**

* Para o frontend, o framework JavaScript Vue.js foi escolhido por sua simplicidade e capacidade de criar interfaces de usuário reativas e dinâmicas. Vue.js facilita a criação de componentes reutilizáveis e a gestão do estado da aplicação, permitindo uma experiência de usuário fluida e interativa. Sua integração com outras tecnologias e ferramentas de desenvolvimento web assegura que a interface da aplicação seja moderna e responsiva, proporcionando uma excelente usabilidade.


## Contribuições pessoais 🎓

 <details>
 <summary> Desenvolvimento do gráfico sobre o a origem das avaliações </summary>
 <br>

Component
 
```python
<template>
    <div class="card flex justify-content-center">
        <Chart type="doughnut" :data="chartData" :options="chartOptions" class="w-full md:w-30rem" />
    </div>
</template>

<script setup lang="ts">
import { chartData, popularGrafico8 } from "../interfaces/GraphicManagedTypeAvaliation";
import { onMounted } from "vue";

// Chamando a função popularGrafico8 ao montar o componente com parâmetros vazios
onMounted(async () => {
    await popularGrafico8('', '', '');
});
</script>

<style scoped>
.card {
    padding: 2em;
    margin-bottom: 2em;
}

.flex {
    display: flex;
    justify-content: center;
    align-items: center;
}

.w-full {
    width: 100%;
}

.md\:w-30rem {
    width: 30rem;
}
</style>


```

TypeScript
 
```python
import { ref } from "vue";
import axios from "axios";

// Referências reativas para os dados e opções do gráfico
export const chartData = ref<any>(null);
export const chartOptions = ref<any>(null);

// Função para configurar as opções do gráfico
const setChartOptions = () => {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');

    return {
        plugins: {
            legend: {
                labels: {
                    color: textColor
                }
            }
        }
    };
};

// Função para configurar os dados do gráfico
const setChartData = (data: any) => {
    const documentStyle = getComputedStyle(document.body);

    return {
        labels: ['Mobile', 'Others'],
        datasets: [
            {
                data: [data.mobile, data.others],
                backgroundColor: [
                    documentStyle.getPropertyValue('--blue-500'),
                    documentStyle.getPropertyValue('--gray-500')
                ],
                hoverBackgroundColor: [
                    documentStyle.getPropertyValue('--blue-400'),
                    documentStyle.getPropertyValue('--gray-400')
                ]
            }
        ]
    };
};

// Função para popular o gráfico com dados da API
export async function popularGrafico8(data_ini: string, data_fin: string, cidade: string) {
    try {
        // Fazendo requisição à API com parâmetros vazios permitidos
        const response = await axios.get(`http://localhost:5000/graficos/mobile`, {
            params: {
                data_inicio: data_ini,
                data_fim: data_fin,
                cidade: cidade
            }
        });
        // Configurando os dados do gráfico
        chartData.value = setChartData(response.data);
        // Configurando as opções do gráfico
        chartOptions.value = setChartOptions();
    } catch (error) {
        console.error('Erro ao fazer requisição:', error);
    }
}

export default { chartData, popularGrafico8 };


```
 </details>

 <details>
 <summary> Desenvolvimento do gráfico sobre a % dos tipos de viajantes </summary>
 <br>

Component
 
```python
<template>
    <div class="card flex justify-content-center">
        <Chart type="doughnut" :data="chartData" :options="chartOptions" class="w-full md:w-30rem" />
    </div>
</template>

<script setup>
import {chartData, popularGrafico} from "../interfaces/GraphicManaged.ts"
import { onMounted } from "vue";





onMounted(async () => {
    popularGrafico('','','');
});





// Função para configurar as opções do gráfico

</script>

```

TypeScript
 
```python
import { ref } from "vue";
import axios from "axios";

export const chartData = ref();
const chartOptions = ref();

const setChartOptions = () => {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');

    return {
        plugins: {
            legend: {
                labels: {
                    color: textColor
                }
            }
        }
    };
};

const setChartData = (data : any) => {
    const documentStyle = getComputedStyle(document.body);
    console.log('Setting chart data with:', data);

    return {
        labels: ['Business', 'Leisure', 'Others'],
        datasets: [
            {
                data: [data.business, data.leisure, data.outros],
                backgroundColor: [
                    documentStyle.getPropertyValue('--orange-500'), 
                    documentStyle.getPropertyValue('--blue-500'), 
                    documentStyle.getPropertyValue('--gray-500')
                ],
                hoverBackgroundColor: [
                    documentStyle.getPropertyValue('--orange-400'), 
                    documentStyle.getPropertyValue('--blue-400'), 
                    documentStyle.getPropertyValue('--gray-400')
                ]
            }
        ]
    };
};

export async function popularGrafico(data_ini : any, data_fin: any,cidade: any){
    try {
        const response = await axios.get('http://127.0.0.1:5000/graficos/tipos-viagens?data_inicio='+data_ini+'&data_fim='+data_fin+'&cidade='+cidade);
        chartData.value = setChartData(response.data);
        chartOptions.value = setChartOptions();
    } catch (error) {
        console.error('Erro ao fazer requisição:', error);
    }
}

export default {chartData, popularGrafico};


```
 </details>
 
 <details>
 <summary> Desenvolvimento da aba menu para comportar os gráficos sobre os viajantes </summary>
 <br>

Menu
 
```python
<template>
  <div class="grid">
    <div class="col-12">
      <SearchFilterBar/>
      <div class="card p-fluid">
        <h2 style="font-size: 1.5em; margin-bottom: 20px;">Type Traveler Dashboard</h2>
        
        <!-- Título do gráfico CharTypeTraveler -->
        <h3 style="font-size: 1.2em; margin-bottom: 10px;">Comparative Sentiment Analysis by Travel Type (%)</h3>
        <!-- Gráfico do CharTypeTraveler -->
        <CharTypeTraveler />

        <!-- Título do gráfico ChartTypeTraveler -->
        <h3 style="font-size: 1.2em; margin-top: 30px; margin-bottom: 10px;">Comparative Analysis by Travel Type (%)</h3>
        <!-- Gráfico do ChartTypeTraveler -->
        <ChartTypeTraveler />

      
        <!-- Título do gráfico ChartTypeTraveler -->
        <h3 style="font-size: 1.2em; margin-top: 30px; margin-bottom: 10px;">Comparative Analysis by Type Guest (%)</h3>
        <!-- Gráfico do ChartTypeTraveler -->
        <ChartTypesGuests />

        <h3 style="font-size: 1.2em; margin-top: 30px; margin-bottom: 10px;">Analysis of the Origin of Evaluation</h3>
        <!-- Gráfico do ChartTypeTraveler -->
        <ChartTypeAvaliation />
      </div>
    </div>
  </div>
</template>

<script setup>
import SearchFilterBar from '../../../components/SearchFilterBar.vue';
import CharTypeTraveler from '@/components/CharTypeTraveler.vue';
import ChartTypeTraveler from '@/components/ChartTypeTraveler.vue';
import ChartTypesGuests from '../../../components/ChartTypesGuests.vue';
import ChartTypeAvaliation from '../../../components/ChartTypeAvaliation.vue';
</script>

<style scoped>
.grid {
  display: flex;
  flex-wrap: wrap;
}

.col-12 {
  width: 100%;
}

.card {
  padding: 2em;
  margin-bottom: 2em;
}

h2 {
  font-size: 1.5em;
  margin-bottom: 1em;
}

h3 {
  font-size: 1.2em;
  margin-bottom: 10px;
}
</style>



```


 </details>

 ## Aprendizados efetivos 🎓

 HARD SKILLS

 <details>
 <summary> Inteligencia Artificial</summary>
 <br>
 Conhecer sobre o treinamento de IA trouxe uma amplitude muito boa sobre o assunto. 
</details>

 SOFT SKILLS

 <details>
 <summary> Resiliência
 </summary>
 <br>
A capacidade de gerar ideias inovadoras e soluções únicas, aplicando novas abordagens e perspectivas para resolver problemas de maneira eficaz e original. Brainstorming de novas ideias.
</details>

[GitHub](https://github.com/CarcaraTec/Imagem-api6sem).






 
 
