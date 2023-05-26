# Define a imagem base
FROM adoptopenjdk/openjdk11:latest

# Define o diretório de trabalho na imagem
WORKDIR /app

# Copia o arquivo JAR da aplicação para o diretório de trabalho na imagem
COPY target/fluxo-caixa-0.0.1-SNAPSHOT.jar .

# Define o comando de execução da aplicação
CMD ["java", "-jar", "fluxo-caixa-0.0.1-SNAPSHOT.jar"]
