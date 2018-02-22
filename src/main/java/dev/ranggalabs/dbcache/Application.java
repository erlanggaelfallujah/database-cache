package dev.ranggalabs.dbcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // https://javabeat.net/spring-boot-sql-databases/
        // https://www.dineshonjava.com/working-with-sql-databases-in-spring-boot-application/
        // http://hsqldb.org/doc/guide/running-chapt.html

        // java -cp ..\lib\hsqldb.jar org.hsqldb.Server -database.0 mem:mydb -dbname.0 xdb
        SpringApplication.run(Application.class, args);
    }
}
