docker-compose down  

mvn clean install -DskipTests

docker image prune -a -f

docker-compose up -d   