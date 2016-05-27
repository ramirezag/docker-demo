FROM java:8-jdk
RUN \
        apt-get update && \
	apt-get upgrade && \
        apt-get install -y --no-install-recommends software-properties-common wget zip && \
        apt-get clean && \
        rm -rf /var/cache/* /var/lib/apt/lists/*
env TOOLS_DIR /tools
RUN mkdir $TOOLS_DIR
RUN \
        wget http://mirror.rise.ph/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.zip && \
        unzip apache-maven-3.3.9-bin.zip -d $TOOLS_DIR
env PATH $TOOLS_DIR/apache-maven-3.3.9/bin:$PATH
RUN \
	wget http://mirror.rise.ph/apache/tomcat/tomcat-8/v8.0.35/bin/apache-tomcat-8.0.35.zip && \
	unzip apache-tomcat-8.0.35.zip -d $TOOLS_DIR && \
        chmod +x $TOOLS_DIR/apache-tomcat-8.0.35/bin/*.sh
COPY . /app
WORKDIR /app
RUN mvn clean install
#RUN cp target/student-api.war $TOOLS_DIR/apache-tomcat-8.0.35/webapps/api.war
WORKDIR $TOOLS_DIR/apache-tomcat-8.0.35
RUN mv /app/docker_entrypoint.sh . && chmod +x docker_entrypoint.sh
ENTRYPOINT ["./docker_entrypoint.sh"]
