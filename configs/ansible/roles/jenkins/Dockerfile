FROM jenkins/jenkins:lts

MAINTAINER Anton Oleynikov (yurolejniko@yandex.ru)

USER root

# Здесь должен быть ID группы docker хостовой машины, чтобы появился докер внутри контейнера дженкинса
ARG DOCKER_HOST_GROUP_ID
RUN groupadd -g $DOCKER_HOST_GROUP_ID docker
# RUN groupadd -g 998 docker

# дать права докера -- дженкинсу
RUN usermod -a -G docker jenkins

#установить ансибл
RUN apt-get update && apt-get install -y python-setuptools python-pip build-essential libssl-dev libffi-dev python-dev && pip install ansible
# так же необходим мавен внутри контейнера, иначе дженкинс ничего не соберет
RUN apt-get install -y maven


USER jenkins

COPY templates/plugins/plugins.txt /usr/share/jenkins/plugins.txt

RUN /usr/local/bin/install-plugins.sh $(cat /usr/share/jenkins/plugins.txt | tr '\n' ' ')

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false




