#!/bin/bash

INSTALL_GCLOUD=true
INSTALL_KUBECTL=true
CLUSTER_EXISTS=true
APP_REPLICAS=1
JENKINS=true
GROOVY_APP=true

# gcloud installation
echo "Hi, welcome to the script of the deployment the app to the Google Cloud!"
echo
echo "Determining existence of gcloud tool:"
"$gcloud"
if [[ "$?" -eq 127 ]] ; then
        echo "Downloading Google Cloud SDK..."
        echo
        echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | sudo tee -a /etc/apt/sources.list.d/google-cloud-sdk.list
        apt-get install apt-transport-https ca-certificates
        "$curl"
        if [[ "$?" -eq 127 ]] ; then
            sudo apt-get install curl
        fi
        curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key --keyring /usr/share/keyrings/cloud.google.gpg add -
        sudo apt-get update && sudo apt-get install google-cloud-sdk
fi
echo
echo "Determining existence of kubectl tool:"
# kubectl installation
"$kubectl"
if [[ "$?" -eq 127 ]] ; then
    echo
    echo "kubectl installation..."
    sudo apt-get install kubectl
    echo
    echo "kubectl installed"
    echo
fi
echo
# connect to the cluster
echo "Type next properties to connect to the cluster:"
echo
read -p 'Project (identifier): ' project
read -p 'Cluster: ' cluster
read -p 'Zone (e.g.: us-west1-a): ' zone
gcloud container clusters get-credentials $cluster --zone $zone --project $project

#gcloud projects create $project --name=$project
#gcloud container clusters create $cluster --zone $zone
# curl -fsSL https://get.docker.com/ | sh
# curl -L https://git.io/get_helm.sh | bash
# helm installation
# https://medium.com/@timhberry/deploy-jenkins-to-google-kubernetes-engine-with-helm-60e0a4d7de93
curl -L https://git.io/get_helm.sh | bash
kubectl apply -f tiller-rbac.yml
helm init --service-account tiller --history-max 200
helm install --name jenkins -f opt/jenkins/jenkins-settings.yml stable/jenkins
printf $(kubectl get secret --namespace default jenkins -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode);echo
kubectl get services --namespace jenkins
kubectl port-forward service/jenkins --namespace=jenkins 8080
kubectl create clusterrolebinding jenkins --clusterrole cluster-admin --serviceaccount=jenkins:default