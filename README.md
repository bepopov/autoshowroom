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
Build soapclient. Run it.
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
All values are completable except --options param in --edit-order (use <TAB>).
