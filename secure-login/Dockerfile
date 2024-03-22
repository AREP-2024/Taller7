FROM openjdk:17

EXPOSE 8080

WORKDIR /usrapp/bin

COPY /certs /usrapp/bin/certs
COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.arep.ASE.app.App"]