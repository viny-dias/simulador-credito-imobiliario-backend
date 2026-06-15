# --- ETAPA 1: COMPILAÇÃO (BUILD) ---
# Baixa uma imagem do Maven com Java 25 para compilar o projeto
FROM maven:3-eclipse-temurin-25-alpine AS build
WORKDIR /app

# Copia o arquivo de dependências (pom.xml) e baixa as bibliotecas
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia a pasta de código-fonte inteira para dentro do Docker
COPY src ./src

# Executa o comando do Maven para compilar o código e gerar o arquivo .jar (pulando os testes de unidade por enquanto)
RUN mvn clean package -DskipTests

# --- ETAPA 2: EXECUÇÃO (RUN) ---
# Baixa uma imagem limpa e leve contendo apenas o ambiente de execução do Java 25
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Copia o arquivo .jar gerado na Etapa 1 para dentro desta nova imagem limpa
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080 do contêiner para o mundo exterior
EXPOSE 8080

# Comando que será executado quando o contêiner ligar
ENTRYPOINT ["java", "-jar", "app.jar"]
