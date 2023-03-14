package org.example;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static jooq.Tables.TEACHER;

public class JooqUpdateExmple {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/learn-jooq?serverTimezone=GMT%2B8&useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "pass";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
            dslContext.update(TEACHER)
                    .set(TEACHER.NAME, "updated name")
                    .set(TEACHER.AGE, 30)
                    .where(TEACHER.ID.eq(2))
                    .execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}