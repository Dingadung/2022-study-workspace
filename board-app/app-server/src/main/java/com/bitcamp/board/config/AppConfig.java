package com.bitcamp.board.config;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

// 1) DB 커넥션 관리자 준비: DataSource
// 2) 트랜잭션 관리자 준비: TransactionManager
public class AppConfig {

  public DataSource createDataSource() {
    System.out.println("createDataSource() 호출됨!");

    DriverManagerDataSource ds = new  DriverManagerDataSource();
    ds.setDriverClassName("org.mariadb.jdbc.Driver");
    ds.setUrl( "jdbc:mariadb://localhost:3306/studydb");
    ds.setUsername("study");
    ds.setPassword("1111");
    return ds;
  }

  public PlatformTransactionManager createTransactionManager(DataSource ds) {
    System.out.println("createTransactionManager() 호출됨!");

    return new DataSourceTransactionManager(ds);
  }
}
