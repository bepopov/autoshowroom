## Getting Started

Download sources:
```
git clone https://github.com/BogdanKFU/autoshowroom.git
```
Create database and add your properties to application.properties.
Build server by using build task. Run it. Use:
```
java -jar
```
Build soapclient or restclient. Run it.
Connect to the server by using (available names are in the client.csv file):
```
connect <name>
```
There are few commands:
```
create-order --order "<make_name> <model_name> <options_names separated by AND"
edit-order --order <order_id> --make <make_name> --options <options_names separated by AND>
delete-order <order_id>
get-orders
get-orders-by-status --status <status>
```
All values are completable (press TAB)

## Running server using Docker

Go to the server module path.

Run:
```
gradle build
```
Then run:
```
docker-compose up
```
Check server is running by going to the page: localhost:9000
## Deploy all
Before starting to deploy create cluster in some platform (I used Google Cloud) and ensure that
you have installed gcloud, kubectl, helm in your machine. Don't forget configure the tools.
- build the applications and Docker images
([_jenkins-slave_](https://github.com/bepopov/autoshowroom/tree/master/server/opt/jenkins/slave), 
[_autoshowroom-server_](https://github.com/bepopov/autoshowroom/tree/master/server),
[_autoshowroom-groovy-server_](https://github.com/bepopov/autoshowroom/tree/master/groovy_server)):
```
gradle build
docker build -t [SOURCE_IMAGE] .
```
- push the images to the docker registry;
```
docker tag [SOURCE_IMAGE] [HOSTNAME]/[PROJECT-ID]/[IMAGE]
docker push [HOSTNAME]/[PROJECT-ID]/[IMAGE]
```
- apply `tiller-rbac.yml`:
```
kubectl apply -f tiller-rbac.yml
helm init --service-account tiller --history-max 200
```
- deploy
[cassandra](https://github.com/bepopov/autoshowroom/tree/master/groovy_server/ops/cassandra)
by running ```install.sh```
- apply
[autoshowroom-server.yml](https://github.com/bepopov/autoshowroom/blob/master/server/autoshowroom-server.yml)
and
[autoshowroom-groovy-server.yml](https://github.com/bepopov/autoshowroom/blob/master/groovy_server/autoshowroom-groovy-server.yml)
(change image names to yours before)

To use locally deployed applications you can run ```kubectl port-forward``` in your terminal.