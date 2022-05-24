# 🏢 Sistema de Informação de Edifícios Residenciais
O S.I.E.R. é um sistema utilizado na disciplina **2CIN056** de Ciência da Informação da Universidade Estadual de Londrina [**UEL**](http://www.uel.br/ceca/cin/index.html).


Terminologia na construção de vocabulários
controlados para o registro terminológico dos edifícios residenciais da cidade de Londrina e região.

<p align="center">
  <img src="https://i.imgur.com/mLzFCnJ.png">
</p>

# Requisitos
 - MariaDB: `^10.6.1`
 - Java: `^17`

# Docker commands

docker compose for development
```bash
docker-compose -p sier-development -f docker/docker-compose.dev.yml up -d
```

Building image for production
```bash
DOCKER_BUILDKIT=1 docker build -f docker/Dockerfile.prod -t sier:3.0.0 .
```

docker compose for production
```bash
cp docker/.env.example docker/.env

docker-compose -p sier -f docker/docker-compose.prod.yml up -d
```