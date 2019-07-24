# Alternative deploying Jenkins
This deployment has better performance because it doesn't create new pods like it works in
first deployment. There all jobs runs on the Jenkins master.\
To deploy jenkins-master run commands:
```
helm package myjenkins
helm install --name jenkins myjenkins-1.4.1.tgz -f ../alternative-settings.yml
```
To push the image to the Google Container Registry and use 
[_Google Kubernetes Engine_](https://plugins.jenkins.io/google-kubernetes-engine) plugin you need to add credentials.
Create Service Account in IAM menu in Google Cloud (or use default), create and download private key in JSON format.
In Jenkins'es credentials menu (in the left side) choose global scope and click on the "Add credentials". Use type Google
Service Account with private key, add there file and save (name should be **autoshowroom**).