package org.example;

import jooq.tables.records.TeacherRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static jooq.Tables.TEACHER;

public class JooqInsertExmple {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/learn-jooq?serverTimezone=GMT%2B8&useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "pass";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
            //普通地插入
            dslContext.insertInto(TEACHER,TEACHER.AGE,TEACHER.NAME)
                    .values(18,"test insert")
                    .values(19,"test insert2")
                    .execute();
            //拿到自增的主键
            Integer id = dslContext.insertInto(TEACHER,TEACHER.AGE,TEACHER.NAME)
                    .values(18,"test insert")
                    .values(19,"test insert2")
                    .returning(TEACHER.ID)
                    .fetchOne().getId();
            //主键重复时忽略
            dslContext.insertInto(TEACHER,TEACHER.ID,TEACHER.AGE,TEACHER.NAME)
                    .values(1,18,"test insert")
                    .values(2,19,"test insert2")
                    .onDuplicateKeyIgnore()
                    .execute();
            //主键重复时进行更新操作
            dslContext.insertInto(TEACHER)
                    .set(TEACHER.ID,1)
                    .set(TEACHER.NAME,"teacher insert")
                    .set(TEACHER.AGE,19)
                    .onDuplicateKeyUpdate()
                    .set(TEACHER.NAME,"teacher insert")
                    .set(TEACHER.AGE,19)
                    .execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}