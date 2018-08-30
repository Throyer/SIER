# 🏢 Sistema de Arquitetura da Informação de Edificios Residenciais
O S.A.I.E.R. é um sistema utilizado na disciplina **2CIN056** de Ciência da Informação da [**UEL**](http://www.uel.br/ceca/cin/index.html).
Terminologia na construção de vocabularios
controlados para o registro terminológico dos edifícios residenciais da cidade de Londrina e região.

# Requisitos
 - MySQL ou MariaDB
 - Java 8

# Guia rapido de instalação
> `em contrução` [Guia completo de instalação (**deploy guide**)](#)
## para executar a aplicação

utilizando o comando:
`java -jar saier-2.x.x.RELEASE.jar`.
> O sistema sera iniciado utilizando as configurações [**padrão**](#quando-um-parametro-não-é-definido-na-execução-ele-recebe-o-valor-padrão).

- Depois de Iniciado, ao acessar a url `/install` é exibido um formulario para a
criação do usuario administrador.

## executando o sistema com configurações diferentes

O S.A.I.E.R. suporta parametros via terminal em sua execução.
> basta colocar o valor do parametro junto com o comando de `java -jar`.

> O sistema tambem suporta arquivos externos de configuração `em construção` [**confira no guia completo de deploy**]().


**Por exemplo mudar a porta para 9000:**


```shell
java -jar saier-2.x.x.RELEASE.jar --port=9000
```

### Quando um parametro não é definido na execução, ele recebe o valor padrão.

|      **Descrição**        |  **parametro**  |              **Valor padrão**             |
|---------------------------|-----------------|-------------------------------------------|
| Porta                     | `--port`        | 8080                                      |
| url do banco              | `--db-url`      | localhost:3306/saier                      |
| nome de usuario (banco)   | `--db-username` | saier                                     |
| senha do usuario (banco)  | `--db-password` | saier                                     |
| mostrar sql na saida      | `--show-sql`    | false                                     |
| criar as tabelas no banco | `--ddl-auto`    | update                                    |

> ### Configuração padrão
> ao ser executado o sistema os valores padão para o banco são:
> banco`saier` usuario `saier` e senha `saier`
> e caso as tablas não existam, ira gerar as tabelas de forma automatica pois o [**Hibernate**](http://hibernate.org/orm/) ddl-auto é `update`.
