package dev.ranggalabs.dbcache.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
public class MySQLConfig {

    @Value("${spring.mysql-datasource.url}")
    private String mysqlUrl;
    @Value("${spring.mysql-datasource.driver-class-name}")
    private String mysqlDriver;
    @Value("${spring.mysql-datasource.username}")
    private String mysqlUsername;
    @Value("${spring.mysql-datasource.password}")
    private String mysqlPassword;

    @Bean(name = "mysql")
    public Sql2o sql2oMySQL(@Qualifier("mysql.datasource")DataSource mysqlDataSource){
        Sql2o sql2o = new Sql2o(mysqlDataSource);
        return sql2o;
    }

    @Bean(name = "mysql.datasource")
    public DataSource mysqlDataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(mysqlDriver);
        hikariConfig.setJdbcUrl(mysqlUrl);
        hikariConfig.setUsername(mysqlUsername);
        hikariConfig.setPassword(mysqlPassword);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

}
