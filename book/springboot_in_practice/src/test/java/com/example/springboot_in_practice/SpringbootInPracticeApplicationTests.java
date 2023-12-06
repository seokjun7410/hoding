package com.example.springboot_in_practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringbootInPracticeApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void 데이터소스_설정과_데이터소스_속성_값_일치_테스트() throws SQLException {
        String dataSourceClassName = dataSource.getClass().getName();
        String databaseProductName = dataSource.getConnection().getMetaData().getDatabaseProductName();

        assertThat(dataSourceClassName).isEqualTo("com.zaxxer.hikari.HikariDataSource");
        assertThat(databaseProductName).isEqualTo("MySQL");

    }

}
