# OT297 - Java ONG

Alkemy acceleration project, ONG team 297.

Technology Java (Spring).

Year: 2022.

## Team

- Francisco Mastucci (Mentor)
- Guillermo Pintos
- Juan Ignacio Otturi
- L. Leonardo Mumbach

## Users

Normal users, password "12345"

```
gisel-email@gmail.com
ruth-email@gmail.com
fabi-email@gmail.com
mayra-email@gmail.com
diana-email@gmail.com
ariel-email@gmail.com
claudio-email@gmail.com
elton-email@gmail.com
tyrion-email@gmail.com
daenerys-email@gmail.com
```

Admin users, password "12345"

```
marta-email@gmail.com
guillermo-email@gmail.com
sara-email@gmail.com
fabian-email@gmail.com
esteban-email@gmail.com
erica-email@gmail.com
florencia-email@gmail.com
pablo-email@gmail.com
eddard-email@gmail.com
jon-email@gmail.com
```

## Docker

Commands to create docker for the application

Convert the wvnw file in executables

```
chmod ugo+x mvnw
```

Create the .jar file and run (complete the environment variables)

```
./mvnw package -DskipTests

java \
-DORGANIZATION_ID=ong.alkemy.somosmas@gmail.com \
-DS3_ACCESS_KEY= \
-DS3_BUCKET= \
-DS3_ENDPOINT= \
-DS3_REGION= \
-DS3_SECRET_KEY= \
-DSENDGRID_API_KEY= \
-DTEMPLATE_ID= \
-DTEMPLATE_ID_CONTACT= \
-jar target/ong-0.0.1-SNAPSHOT.jar
```

Create files: Dockerfile

```
FROM amazoncorretto:11-alpine3.16
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Create container

```
docker build -t ot297-0.1.0 .
```

Create files: docker-compose.yml (complete the environment variables)

```
version: "3.9"
services:
  db:
    image: mysql:8.0.31
    # command: --default-authentication-plugin=mysql_native_password
    restart: always
    ports:
    - 3306:3306
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: g297
  aap:
    image: ot297-0.1.0
    ports:
      - 8080:8080
    #volumes:
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/g297
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
      SPRING_DATASOURCE_INITIALIZATION-MODE: always
      SPRING_DATASOURCE_DRIVER-CLASS-NAME: com.mysql.cj.jdbc.Driver
      SPRING_JPA_HIBERNATE_DDL-AUTO: update
      ORGANIZATION_ID: ong.alkemy.somosmas@gmail.com
      S3_ACCESS_KEY:
      S3_BUCKET:
      S3_ENDPOINT:
      S3_REGION:
      S3_SECRET_KEY:
      SENDGRID_API_KEY:
      TEMPLATE_ID:
      TEMPLATE_ID_CONTACT:
```

Run containers

```
docker-compose up -d
```

Stop containers

```
docker-compose down
```
