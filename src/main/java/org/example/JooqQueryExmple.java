package org.example;

import jooq.tables.Teacher;
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

public class Main {
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

            // 通过 Record.into 方法可以将默认Record对象，转换为表的Record对象，例如S1UserRecord
            // Result 接口也定义了into方法，可以将整个结果集转换为指定表Record的结果集
            // 通过 S1UserRecord 可以通过get方法直接获得表对象
            // 所有表的XXXRecord对象都是实现了 Record 对象的子类
            List<TeacherRecord> userRecordResult = dslContext.select().from(TEACHER).fetch().into(TEACHER);
            userRecordResult.forEach(record -> {
                Integer age = record.getAge();
                String name = record.getName();
                System.out.println("into S1UserRecord   id: " + age + " , username: " + name);
            });

            // fetchInto方法可以可以传入任意class类型，或者表常量
            // 会直接返回任意class类型的List集合，或者指定表Record的结果集对象
            List<TeacherRecord> fetchIntoClassResultList = dslContext.select().from(TEACHER).fetchInto(TeacherRecord.class);
            Result<TeacherRecord> fetchIntoTableResultList = dslContext.select().from(TEACHER).fetchInto(TEACHER);

            System.out.println("fetchIntoClassResultList: \n" + fetchIntoClassResultList.toString());
            System.out.println("fetchIntoTableResultList: \n" + fetchIntoTableResultList.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}