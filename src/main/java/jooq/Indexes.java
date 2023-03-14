/*
 * This file is generated by jOOQ.
 */
package jooq;


import javax.annotation.processing.Generated;

import jooq.tables.Teacher;
import jooq.tables.TeacherDetails;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>Student</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index TEACHER_PRIMARY = Indexes0.TEACHER_PRIMARY;
    public static final Index TEACHER_DETAILS_PRIMARY = Indexes0.TEACHER_DETAILS_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index TEACHER_PRIMARY = Internal.createIndex("PRIMARY", Teacher.TEACHER, new OrderField[] { Teacher.TEACHER.ID }, true);
        public static Index TEACHER_DETAILS_PRIMARY = Internal.createIndex("PRIMARY", TeacherDetails.TEACHER_DETAILS, new OrderField[] { TeacherDetails.TEACHER_DETAILS.TEACHER_ID }, true);
    }
}
