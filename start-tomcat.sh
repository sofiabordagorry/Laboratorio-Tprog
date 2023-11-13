#!/bin/bash

# Instalar y desplegar la parte dinámica en Tomcat
cd Tarea2.1
mvn clean install

nombre_war="Tarea2.1-1.0.war"
war_cp_dir="target/$nombre_war"
cp $war_cp_dir "/ens/home01/j/joaquin.corbo/apache-tomcat-10.1.13/webapps"

cd ../Mobile
mvn clean install

nombre_war="Mobile-1.0.war"
war_cp_dir="target/$nombre_war"
cp $war_cp_dir "/ens/home01/j/joaquin.corbo/apache-tomcat-10.1.13/webapps"

cd "/ens/home01/j/joaquin.corbo/apache-tomcat-10.1.13/webapps"
mv Tarea2.1-1.0.war Tarea2.1.war
mv Mobile-1.0.war Mobile.war
cd ..
