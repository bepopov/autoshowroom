# Run Jenkins on cluster using Kubernetes
Firstly, apply `tiller-rbac.yml`:
```
kubectl apply -f tiller-rbac.yml
helm init --service-account tiller --history-max 200
```
You should push the custom image of jenkins-slave to the docker registry in your cloud to add `envsubst` command that is necessary to replace
environment variables like `${BUILD_NUMBER}` in deployment script. Use next commands:
```
docker build -t [SOURCE_IMAGE] .
docker tag [SOURCE_IMAGE] [HOSTNAME]/[PROJECT-ID]/[IMAGE]
docker push [HOSTNAME]/[PROJECT-ID]/[IMAGE]
```
Then push the `jenkins/stable` chart using Helm:

`helm install --name jenkins -f opt/jenkins/jenkins-settings.yml stable/jenkins`

To use Jenkins with Google Cloud you need to add credentials for Service Account to the Jenkins. Name credentials
as <i>autoshowroom</i>. Likewise you need to add Kubeconfig as Jenkins credential (type: _Secret File_). Name
of credential should be _cluster_. Also there should be set _GOOGLE_APPLICATION_CREDENTIALS_ environment
variable as Google API token changes periodically. Setting this variable sets default token. Ensure that token's
service account has all necessary permissions for managing Kubernetes.\
[_Disable Agent → Master Security Access Control_](https://wiki.jenkins.io/display/JENKINS/Slave+To+Master+Access+Control#SlaveToMasterAccessControl-kill-switch)
to allow agents access to the master.

## Useful information
[Stackoverflow - How to configure docker for jenkins in Helm](https://github.com/springfox/springfox)\
Docker needed for building the image of the application. Jenkins there are configured to using Kubernetes'es Docker. 
But in fact there are many ways to configure Docker for Jenkins: \
[Medium - Building Docker Images inside Kubernetes](https://medium.com/hootsuite-engineering/building-docker-images-inside-kubernetes-42c6af855f25)\
Also there might be a problem with build performance in Kubernetes because Jenkins creates there short-lived
agent pods that always download all used tools (such as Gradle) and all required dependencies. 
To fix this there might use [stateful agents](https://medium.com/@jutley/running-persistent-jenkins-agents-on-kubernetes-c2c6e3c51988) or
mount [docker volumes](https://jenkins.io/doc/book/pipeline/docker/#caching-data-for-containers) (this way described in Jenkins docs).
In this case is used [jobcacher plugin](https://wiki.jenkins.io/display/JENKINS/Job+Cacher+Plugin).\
Maybe the job performance problem is because of [kubernetes](https://wiki.jenkins.io/display/JENKINS/Kubernetes+Plugin) 
plugin usage or *agent any* directive in pipeline.