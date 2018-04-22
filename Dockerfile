FROM maven:3.5-jdk-8

RUN mkdir /fiuba
WORKDIR /fiuba

RUN echo "Descargando codigo del servidor" \
	&& git clone https://github.com/ivanpatos/tp_prof.git \
	&& echo "fin descarga"

COPY src/main/resources/application.properties /fiuba/tp_prof/hubin/src/main/resources/application.properties
	
RUN cd tp_prof/hubin \
	&& mvn clean package
	
EXPOSE 8085
	
CMD java -jar tp_prof/hubin/target/hubin-0.0.1-SNAPSHOT.jar