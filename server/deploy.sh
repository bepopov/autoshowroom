#!/bin/bash

FILE=deployment.yml
envsubst < ${FILE} | kubectl --namespace=default apply -f -