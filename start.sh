#!/bin/bash

# Build del proyecto principal
cd Tarea1
mvn clean package

# Ejecutar el JAR creado
nombre_jar="Tarea1-1.0-jar-with-dependencies.jar"
java -jar target/$nombre_jar &

# Esperar unos segundos para asegurarse de que la aplicación haya arrancado completamente
sleep 10

# Instalar y desplegar la parte dinámica en Tomcat
cd ../Tarea2.1
mvn clean install

nombre_war="Tarea2.1-1.0.war"
war_cp_dir="target/$nombre_war"
cp $war_cp_dir "~/apache-tomcat-10.1.15/webapps"

# Reiniciar Apache Tomcat
apache_dir="~/apache-tomcat-10.1.15/bin"
cd $apache_dir
sh startup.sh
