mvn package
mvn install javadoc:jar
mvn install:install-file -Dfile=target/CGGE2-2.0.jar -DgroupId=de.johndee -DartifactId=CGGE2 -Dversion=2.0 -Dpackaging=jar
