#!/bin/bash
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install --name cassandra bitnami/cassandra