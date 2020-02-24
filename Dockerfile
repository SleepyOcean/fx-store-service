FROM java:8
COPY fx-store-service-1.0.0.jar /so-deploy/fx-store-service-1.0.0.jar
EXPOSE 9050
CMD ["java", "-jar", "/so-deploy/fx-store-service-1.0.0.jar", "--rootDir=/home/sleepy/res/"]