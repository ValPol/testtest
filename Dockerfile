#если при старте из под винды, дичь с путями, то пропиши в poweshell $Env:COMPOSE_CONVERT_WINDOWS_PATHS=1
FROM maven:3.3-jdk-8

COPY ./ /app
WORKDIR /app
# Command line to execute the test
RUN ls

ENTRYPOINT ["mvn", "test"]