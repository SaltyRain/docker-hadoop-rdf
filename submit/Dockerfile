FROM bde2020/hadoop-base:2.0.0-hadoop3.2.1-java8

COPY CSVtoRDF.jar /opt/hadoop/applications/CSVtoRDF.jar

ENV JAR_FILEPATH="/opt/hadoop/applications/CSVtoRDF.jar"
ENV CLASS_TO_RUN="RDFJobDriver"
ENV PARAMS="/input /output"

ADD run.sh /run.sh
RUN chmod a+x /run.sh

CMD ["/run.sh"]
