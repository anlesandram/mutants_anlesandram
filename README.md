# Mutantes Aplicación

##### Para ejecutar la aplicación, debe tener configurado docker

`docker-compose up`

##### Validar que esta desplegada la aplicación

###### Request:

`curl --location --request GET 'localhost:8080/mercadolibre-mutant-svc/actuator/health'`

##### Response:

`{
     "status": "UP"
 }`

##### Para consultar ADN Mutante

###### Request:

`curl --location --request POST 'localhost:8080/mercadolibre-mutant-svc/mutant' \
 --header 'Authorization: BEARER b60c9d27c9b5404d94842fc2f7948c55' \
 --header 'Content-Type: application/json' \
 --data-raw '{
   "dna": ["ATGAAT", "CAATGA", "TAGCAT", "ATCAGG", "CCCGTA", "CCACTG"]
 }'`

##### Para consultar las estadisticas

`curl --location --request GET 'localhost:8080/mercadolibre-mutant-svc/stats'`

Swagger
<img src="/images/swagger.PNG"/>



