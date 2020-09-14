FROM ubuntu:18.04

MAINTAINER Valentin Velichko <velichko1880@gmail.com>

RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
RUN apt-get install -y maven

ADD Task5 .

RUN cd NewMultithreadingTask1/

RUN mvn package

CMD ["java", "-cp", "./NewMultithreadingTask1/target/classes/", "by.epamlab.Runner"]
