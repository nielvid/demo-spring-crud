package com.example.demo.datasource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresDatasource {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource hikariDataSource(){
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();

    }
}


//package com.example.demo.datasource;
//
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TomcatDataSourceConfig {
//
//    @Bean
//    @ConfigurationProperties("app.datasource")
//    public DataSource dataSource() {
//        return new DataSource();
//    }
//}

//Add this to your pom.xml:
//<dependency>
//    <groupId>org.apache.tomcat</groupId>
//    <artifactId>tomcat-jdbc</artifactId>
//</dependency>

//tomcat:
//max-active: 50
//max-idle: 20
//min-idle: 5
//initial-size: 10
