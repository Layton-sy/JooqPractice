package example;

import jooq.tables.records.TeacherRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jooq.tables.Teacher.TEACHER;

public class UpdateExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/learn-jooq?serverTimezone=GMT%2B8&useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = "pass";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
            //单次更新 sql方式
            dslContext.update(TEACHER)
                    .set(TEACHER.NAME, "updated name")
                    .set(TEACHER.AGE, 30)
                    .where(TEACHER.ID.eq(2))
                    .execute();
            //单次更新record方式
            TeacherRecord record = dslContext.newRecord(TEACHER);
            record.setId(1);
            record.setName("teacher name 2");
            record.setAge(33);
            record.update();

            //批量更新
            TeacherRecord record1 = dslContext.newRecord(TEACHER);
            record1.setId(1);
            record1.setName("teacher name 1");
            record1.setAge(33);

            TeacherRecord record2 = dslContext.newRecord(TEACHER);
            record2.setId(2);
            record2.setName("teacher name 2");
            record2.setAge(33);

            List<TeacherRecord> records = new ArrayList<>();
            records.add(record1);
            records.add(record2);

            dslContext.batchUpdate(records).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
