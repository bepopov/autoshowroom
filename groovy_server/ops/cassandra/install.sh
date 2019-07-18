#!/bin/bash
kubectl create configmap cassandra-config --from-file=data/data.cql
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install --name cassandra bitnami/cassandra --set initDBConfigMap=cassandra-config