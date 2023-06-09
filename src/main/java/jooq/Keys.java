/*
 * This file is generated by jOOQ.
 */
package jooq;


import javax.annotation.processing.Generated;

import jooq.tables.Teacher;
import jooq.tables.TeacherDetails;
import jooq.tables.records.TeacherDetailsRecord;
import jooq.tables.records.TeacherRecord;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>school</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<TeacherRecord, Integer> IDENTITY_TEACHER = Identities0.IDENTITY_TEACHER;
    public static final Identity<TeacherDetailsRecord, Integer> IDENTITY_TEACHER_DETAILS = Identities0.IDENTITY_TEACHER_DETAILS;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<TeacherRecord> KEY_TEACHER_PRIMARY = UniqueKeys0.KEY_TEACHER_PRIMARY;
    public static final UniqueKey<TeacherDetailsRecord> KEY_TEACHER_DETAILS_PRIMARY = UniqueKeys0.KEY_TEACHER_DETAILS_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<TeacherRecord, Integer> IDENTITY_TEACHER = Internal.createIdentity(Teacher.TEACHER, Teacher.TEACHER.ID);
        public static Identity<TeacherDetailsRecord, Integer> IDENTITY_TEACHER_DETAILS = Internal.createIdentity(TeacherDetails.TEACHER_DETAILS, TeacherDetails.TEACHER_DETAILS.TEACHER_ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<TeacherRecord> KEY_TEACHER_PRIMARY = Internal.createUniqueKey(Teacher.TEACHER, "KEY_teacher_PRIMARY", Teacher.TEACHER.ID);
        public static final UniqueKey<TeacherDetailsRecord> KEY_TEACHER_DETAILS_PRIMARY = Internal.createUniqueKey(TeacherDetails.TEACHER_DETAILS, "KEY_teacher_details_PRIMARY", TeacherDetails.TEACHER_DETAILS.TEACHER_ID);
    }
}
