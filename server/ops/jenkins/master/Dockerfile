FROM groovy:2.5.7-jdk8 as groovy
COPY --from=groovy /opt/groovy /opt/groovy
COPY --from=groovy /usr/bin/groovy /usr/bin/groovy
FROM jenkins/jenkins
USER root
ARG KUBECTL_VERSION=v1.13.2
ARG HELM_VERSION=v2.12.3
RUN apt-get update \
    && apt-get -y install ca-certificates gettext-base certbot python3-pip \
    && pip3 install certbot-dns-route53 ansible \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* \
    && curl -LO https://storage.googleapis.com/kubernetes-release/release/$KUBECTL_VERSION/bin/linux/amd64/kubectl \
    && chmod +x ./kubectl \
    && mv ./kubectl /usr/local/bin/kubectl \
    && curl -LO https://storage.googleapis.com/kubernetes-helm/helm-$HELM_VERSION-linux-amd64.tar.gz \
    && tar -zxvf helm-$HELM_VERSION-linux-amd64.tar.gz \
    && mv ./linux-amd64/helm /usr/local/bin/helm
ARG DOCKER_VERION=18.03
RUN rm -rf ./linux-amd64 \
    && wget -qO- https://get.docker.com/ | sh

VOLUME ["/data/app"]
# Allow the host to use gradle cache, otherwise gradle will always download plugins & artifacts on every build
VOLUME ["/root/.gradle/caches/"]
RUN \
    cd /usr/local && \
    curl -L https://services.gradle.org/distributions/gradle-5.5.1-bin.zip -o gradle-5.5.1-bin.zip && \
    unzip gradle-5.5.1-bin.zip && \
    rm gradle-5.5.1-bin.zip
ENV GRADLE_HOME=/usr/local/gradle-5.5.1
ENV PATH=$PATH:$GRADLE_HOME/bin