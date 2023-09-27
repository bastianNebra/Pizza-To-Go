FROM openliberty/open-liberty:full-java11-openj9-ubi

COPY --chown=1001:0 src/main/liberty/config/ /config/

COPY --chown=1001:0 target/pizza_delivery.war /config/apps/

EXPOSE 9080

RUN configure.sh

CMD ["/opt/ol/wlp/bin/server","run"]

