# Mutantes Aplicación

test 
###### Description
`Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.
Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.
Para eso te ha pedido crear un programa en donde recibirás como parámetro un array de Strings que representan cada fila de una 
tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representan cada base nitrogenada del ADN.`

##### Para ejecutar la aplicación, debe tener configurado docker

`docker-compose up`

##### Validar que esta desplegada la aplicación

###### Request:

`curl --location --request GET 'http://ALB-1280084128.us-east-2.elb.amazonaws.com/mercadolibre-mutant-svc/actuator/health'`

<img src="/images/healthcheck.PNG"/>


##### Para consultar ADN Mutante

###### Request:

`curl --location --request POST 'http://ALB-1280084128.us-east-2.elb.amazonaws.com/mercadolibre-mutant-svc/mutant' \
--header 'Content-Type: application/json' \
--data-raw '{
"dna": ["ATGAAT", "CAATGA", "TAGCAT", "ATCAGG", "CCCGTA", "CCACTG"]
}'`

<img src="/images/post_mutants.PNG"/>

##### Para consultar las estadisticas

###### Request:

`curl --location --request GET 'http://ALB-1280084128.us-east-2.elb.amazonaws.com/mercadolibre-mutant-svc/stats'`

<img src="/images/stats_mutants.PNG"/>

##### Swagger
<img src="/images/swagger.PNG"/>

#####Jacoco
<img src="/images/jacoco.PNG"/>

##### AWS

##### Esta aplicacion esta configurada en aws
<img src="/images/ecs.PNG"/>

