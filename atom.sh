
if [ $1 = "all" ]; then

  git pull
  mvn clean package

elif [ $1 = "debug" ]; then

  java -Dcom.sun.management.jmxremote.port=9595 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -jar target/atmosphere.jar

else

  java -jar target/atmosphere.jar

fi
