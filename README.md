# Build
mvn clean package && docker build -t com.github.basbuurman.oose.dea/spotitube .

# RUN

docker rm -f spotitube || true && docker run -d -p 8080:8080 -p 4848:4848 --name spotitube com.github.basbuurman.oose.dea/spotitube 