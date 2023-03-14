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

public class JooqQueryExmple {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/learn-jooq?serverTimezone=GMT%2B8&useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "pass";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            // fetch方法可以返回一个结果集对象 Result
            // jOOQ的Result对象实现了List接口，可以直接当做集合使用
            Result<Record> recordResult = dslContext.select().from(TEACHER).fetch();
            recordResult.forEach(record -> {
                Integer age = record.getValue(TEACHER.AGE);
                String name = record.getValue(TEACHER.NAME);
                System.out.println("age: " + age + " , name: " + name);
            });

            List<TeacherRecord> userRecordResult = dslContext.select().from(TEACHER).fetch().into(TEACHER);
            userRecordResult.forEach(record -> {
                Integer age = record.getAge();
                String name = record.getName();
                System.out.println("into S1UserRecord   id: " + age + " , username: " + name);
            });

            List<TeacherRecord> fetchIntoClassResultList = dslContext.select().from(TEACHER).fetchInto(TeacherRecord.class);
            Result<TeacherRecord> fetchIntoTableResultList = dslContext.select().from(TEACHER).fetchInto(TEACHER);

            System.out.println("fetchIntoClassResultList: \n" + fetchIntoClassResultList.toString());
            System.out.println("fetchIntoTableResultList: \n" + fetchIntoTableResultList.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}