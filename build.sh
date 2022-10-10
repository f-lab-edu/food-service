cd common && mvn clean package &&
cd ../user && mvn clean package &&
cd ../auth && mvn clean package &&
cd ../store && mvn clean package &&
cd ../order && mvn clean package &&
cd .. && mvn clean package
