package dev.ranggalabs.dbcache.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
public class HSQLDBConfig {

    @Value("${spring.hsqldb-datasource.url}")
    private String hsqldbUrl;
    @Value("${spring.hsqldb-datasource.driver-class-name}")
    private String hsqldbDriver;
    @Value("${spring.hsqldb-datasource.username}")
    private String hsqldbUsername;
    @Value("${spring.hsqldb-datasource.password}")
    private String hsqldbPassword;

    @Primary
    @Bean
    public Sql2o sql2oHSQLDB(@Qualifier("hsqldb.datasource") DataSource hsqldbDataSource){
        Sql2o sql2o = new Sql2o(hsqldbDataSource);
        return sql2o;
    }

    @Bean(name = "hsqldb.datasource")
    @Primary
    public DataSource hsqldbDataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(hsqldbDriver);
        hikariConfig.setJdbcUrl(hsqldbUrl);
        hikariConfig.setUsername(hsqldbUsername);
        hikariConfig.setPassword(hsqldbPassword);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

}
