# Simple GRPc client written on Groovy
It is a groovy application running with Cassandra database. There runs the REST-service which provides saving the
order received from _restclient_. After saving in Cassandra _groovy_server_ sends the order to the _autoshowroom_ server
using **gRPC**.
## Running using Docker
Firstly, build the application:
```
gradle build
```
Then build the Docker image and run the container from this image:
```
docker build -t groovy_server .
docker run -p 8080:8080 -d groovy_server
```