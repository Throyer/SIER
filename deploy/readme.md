# Instruções de deploy.
* 01 [Migrações do banco de dados](#migrações-do-banco-de-dados)
* 02 [configurando o proxy com nginx](#configurando-o-proxy-com-nginx)
* 03 [criando um serviço para o ubuntu](#criando-um-serviço-do-saier-no-ubuntu)
* 04 [Externalizando as configurações como _banco, porta http e etc_](#externalizando-as-configurações)

# Migrações do banco de dados
> _consulte o guia de_ [_**migrations**_](./migration#ainda-em-desenvolvimento) 

# Configurando o proxy com nginx

 - Criando arquivo
`sudo nano /etc/nginx/conf.d/saier.conf`

> exemplo de configuração baseado no do [_linode_](https://www.linode.com/docs/development/java/how-to-deploy-spring-boot-applications-nginx-ubuntu-16-04/)

```conf
server {
        listen 80;
        listen [::]:80;
        server_name example.com;
        location / {
             proxy_pass http://localhost:8080/;
             proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
             proxy_set_header X-Forwarded-Proto $scheme;
             proxy_set_header X-Forwarded-Port $server_port;
        }
}
```

# Criando um serviço do saier no Ubuntu
 - para criar o arquivo do serviço
`sudo nano /etc/systemd/system/saier.service`

```service
 [Unit]
 Description=Serviço ubuntu do SAIER
 After=syslog.target network.target mariadb.service
 
 [Service]
 User=saier
 Type=simple
 
 [Service]
 ExecStart=/usr/bin/java -jar /opt/saier/saier-2.0.0-beta.jar
 Restart=always
 StandardOutput=syslog
 StandardError=syslog
 SyslogIdentifier=saier

 [Install]
 WantedBy=multi-user.target
```
> - Para iniciar o serviço


> `sudo systemctl start saier`


> - Para checar o status


> `sudo systemctl status saier`


> - Para iniciar junto com o sistema operacional


> `sudo systemctl enable saier`


# Externalizando as configurações
você pode externalizar as configurações usando o parametro `--spring.config.location=`.
> **por exemplo:**  `java -jar saier-2.0.0-beta.jar --spring.config.location=/diretorio/exemplo/exemplo.properties`

> _O S.A.I.E.R tambem suporta "short line commands" consultar_ [_tabela de parametros no guia rapido de instalação_](https://github.com/Throyer/SAIER#executando-o-sistema-com-configurações-diferentes)

## exemplo de configuração
```properties
server.port=8080
server.servlet.context-path=/
spring.datasource.url=jdbc:mysql://localhost:3306/saier?useSSL=false
spring.datasource.username=saier
spring.datasource.password=saier
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update
upload.directory=
```
