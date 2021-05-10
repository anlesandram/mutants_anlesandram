FROM tomcat:9.0

ADD target/mercadolibre-mutant-svc.war $CATALINA_HOME/webapps/

EXPOSE 8080
