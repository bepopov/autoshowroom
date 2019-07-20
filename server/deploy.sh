#!/bin/bash

FILE=autoshowroom-server.yml
envsubst < ${FILE} | kubectl --namespace=default apply -f -