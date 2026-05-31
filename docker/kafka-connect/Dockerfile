ARG DEBEZIUM_VERSION=3.5
FROM quay.io/debezium/connect:${DEBEZIUM_VERSION}

ARG OJDBC_VERSION=23.26.0.0.0

USER root

RUN curl -fsSL \
    "https://repo1.maven.org/maven2/com/oracle/database/jdbc/ojdbc11/${OJDBC_VERSION}/ojdbc11-${OJDBC_VERSION}.jar" \
    -o /kafka/libs/ojdbc11.jar

USER kafka
