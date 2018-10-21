# 🏢 Sistema de Arquitetura da Informação de Edificios Residenciais
O S.A.I.E.R. é um sistema utilizado na disciplina **2CIN056** de Ciência da Informação da [**UEL**](http://www.uel.br/ceca/cin/index.html).
Terminologia na construção de vocabularios
controlados para o registro terminológico dos edifícios residenciais da cidade de Londrina e região.

<p align="center">
  <img src="https://i.imgur.com/3qQ4j1V.png">
</p>

# Requisitos
 - MySQL ou MariaDB
 - Java 8

# Guia rapido de instalação
> para mais informações sobre o deploy confira o [guia completo de instalação](./deploy#instruções-de-deploy)

## para executar a aplicação
> SAIER-2.0.0-BETA [DOWNLOAD](./deploy/dist/saier-2.0.0-beta.jar)

utilizando o comando:
`java -jar saier-2.0.0-beta.jar`.
_________________________
> O sistema sera iniciado utilizando as configurações padrão [**confira a tabela**](#quando-um-parametro-não-é-definido-na-execução-ele-recebe-o-valor-padrão).

- Depois de Iniciado, ao acessar a url `/install` é exibido um formulario para a
criação do usuario administrador.

## executando o sistema com configurações diferentes

O S.A.I.E.R. suporta parametros via terminal em sua execução.
> basta colocar o valor do parametro junto com o comando de execução: `java -jar saier-2.0.0-beta.jar --<nome do parametro>=<valor>`.

> O sistema tambem suporta arquivos externos de configuração `em construção` [**confira no guia completo de deploy**](./deploy#externalizando-as-configurações).


**Por exemplo mudar a porta para 9000:**


```shell
java -jar saier-2.0.0-beta.jar --port=9000
```

### Quando um parametro não é definido na execução, ele recebe o valor padrão.

|      **Descrição**        |  **parametro**  |              **Valor padrão**             |
|---------------------------|-----------------|-------------------------------------------|
| Porta                     | `--port`        | 8080                                      |
| Contenxto                 | `--contexto`    | /                                         |
| Url do banco              | `--db-url`      | localhost:3306/saier                      |
| Nome de usuario (banco)   | `--db-username` | saier                                     |
| Senha do usuario (banco)  | `--db-password` | saier                                     |
| Mostrar sql na saida      | `--show-sql`    | false                                     |
| Criar as tabelas no banco | `--ddl-auto`    | update                                    |

> ### Configuração padrão
> ao ser executado o sistema os valores padão para o banco são:
> banco`saier` usuario `saier` e senha `saier`
> e caso as tabelas não existam, ira gerar as tabelas de forma automatica pois o [**Hibernate**](http://hibernate.org/orm/) ddl-auto é `update`.
