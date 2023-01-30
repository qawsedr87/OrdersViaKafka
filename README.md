# orders-api-with-kafka

Simple APIs of Java Spring Boot application using Spring for Apache Kafka and PostgreSQL.

## Technologies Used
- Java 17 or higher
- Java Spring Boot v3.0.x
- Spring for Apache Kafka Version v3.0.x
- PostgreSQL v14.x
- Docker
- Docker Compose

## Configuration
The configuration for the application and its dependencies (PostgreSQL, Apache Kafka) can be found in the `.docker/docker-compose-dev.yaml` file.

## Getting Started
1. Clone the repository to your local machine and Navigate to the project directory.
```shell
git clone https://github.com/qawsedr87/orders-api-with-kafka.git

cd orders-api-with-kafka
```

2. Start the Docker containers. 

docker containers would be including one zookeeper, two kafka servers, kafka-manager and postgresql...
```shell
docker-compose ./docker/docker-compose-dev.yaml up
## 
```

3. Run the application
```shell
./gradlew bootrun
```

4. Check that the api is running by visiting http://localhost:8080.

5. To stop the containers, press CTRL + C.

## Kafka Operation via Docker
1. List running containers
```shell
$ docker ps
CONTAINER ID   IMAGE                              COMMAND                  CREATED          STATUS          PORTS                                         NAMES
3c654bb61f4f   confluentinc/cp-kafka:latest       "/etc/confluent/dock…"   19 minutes ago   Up 19 minutes   9092/tcp, 0.0.0.0:29092->29092/tcp            docker-kafka-server-1-1
2977901bcd96   confluentinc/cp-kafka:latest       "/etc/confluent/dock…"   19 minutes ago   Up 19 minutes   9092/tcp, 0.0.0.0:39092->39092/tcp            docker-kafka-server-2-1
14ac4d6ff21c   sheepkiller/kafka-manager          "./start-kafka-manag…"   19 minutes ago   Up 19 minutes   0.0.0.0:9000->9000/tcp                        docker-manager-1
798686f4de59   confluentinc/cp-zookeeper:latest   "/etc/confluent/dock…"   19 minutes ago   Up 19 minutes   2888/tcp, 3888/tcp, 0.0.0.0:22181->2181/tcp   docker-zookeeper-1
f2e61ce0665a   postgres:14.1-alpine               "docker-entrypoint.s…"   2 hours ago      Up 19 minutes   0.0.0.0:5432->5432/tcp                        docker-db-1
```

2. Run Kafka Commands inside the container
```shell
$ docker exec -ti docker-kafka-server-2-1 /bin/bash
[appuser@2977901bcd96 ~]$
```

3. Receive the messages 
```shell
[appuser@2977901bcd96 ~]$ cd ..
[appuser@2977901bcd96 home]$ cd ..
[appuser@2977901bcd96 /]$ bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic order --from-beginning
{"createdAt":1675031917770,"updatedAt":1675031917770,"id":52,"totalPrice":8.74,"memo":"Kyraton Kitchen Scissors Heavy Duty 2 Pieces, Stainless Steel Sharp Cooking Shears with Cover, Multipurpose Cooking Scissors For Meat Chicken Bone Veg Poultry Fish. Dishwasher Safe Food Scissors","action":"add"}
...
```

## Documentation
Additional documentation on the APIs and Entity Diagram can be found in the `docs` directory.


