# 🏢 Sistema de Informação de Edificios Residenciais
O S.I.E.R. é um sistema utilizado na disciplina **2CIN056** de Ciência da Informação da Universisade Estadual de Londrina [**UEL**](http://www.uel.br/ceca/cin/index.html).


Terminologia na construção de vocabularios
controlados para o registro terminológico dos edifícios residenciais da cidade de Londrina e região.

<p align="center">
  <img src="https://i.imgur.com/rE5XP6Z.png">
</p>

# Requisitos
 - MariaDB: `^10.3.11`
 - Java: `^11`

# Guia rapido de instalação
> para mais informações sobre o deploy confira o [guia completo de instalação](./deploy#instruções-de-deploy)

## para executar a aplicação
> SIER-3.0.0.BETA [DOWNLOAD](./deploy/dist/saier-2.0.0-beta.jar)

utilizando o comando:
`java -jar sier-3.0.0.RELEASE.jar`.
_________________________
> O sistema sera iniciado utilizando as configurações padrão [**confira a tabela**](#quando-um-parametro-não-é-definido-na-execução-ele-recebe-o-valor-padrão).

- Depois de Iniciado, ao acessar a url `/settings` é exibido um formulario para a configuração inicial do usuario administrador.

## executando o sistema com configurações diferentes

O S.I.E.R. suporta parametros via terminal em sua execução.
> basta colocar o valor do parametro junto com o comando de execução: `java -jar saier-2.0.0-beta.jar --<nome do parametro>=<valor>`.

> O sistema tambem suporta arquivos externos de configuração `em construção` [**confira no guia completo de deploy**](./deploy#externalizando-as-configurações).


**Por exemplo mudar a porta para 9000:**


```shell
java -jar saier-3.0.0.RELEASE.jar --port=9000
```

### Quando um parametro não é definido na execução, ele recebe o valor padrão.

|      **Descrição**        |  **parametro**  |              **Valor padrão**             |
|---------------------------|-----------------|-------------------------------------------|
| Porta                     | `--port`        | 8080                                      |
| Url do banco              | `--db-url`      | localhost:3306/sier                      |
| Nome de usuario (banco)   | `--db-username` | saier                                     |
| Senha do usuario (banco)  | `--db-password` | saier                                     |
