#если при старте из под винды, дичь с путями, то пропиши в poweshell $Env:COMPOSE_CONVERT_WINDOWS_PATHS=1
FROM maven:3.3-jdk-8

COPY ./ /app
WORKDIR /app

# Command line для скачивания всех зависимостей в образ а mvn у нас жадный ска... так что 2 комманды
RUN mvn compile -s settings.xml 
RUN mvn package -s settings.xml -DskipTests

# Command line to execute the test
ENTRYPOINT ["mvn", "test", "-s", "settings.xml"]