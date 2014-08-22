
if [ $1 = 'all' ]; then

  git pull
  mvn clean package

fi

java -jar target/atmosphere.jar
