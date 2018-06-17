package Model;

import java.io.Serializable;

public class TeacherAndCourseAssessment implements Serializable {
    private String teachersName;
    private String courseName;
    private String student_stno;
    private String teacher_tno;
    private String course_cno;

    public String getTeacher_tno() {
        return teacher_tno;
    }

    public void setTeacher_tno(String teacher_tno) {
        this.teacher_tno = teacher_tno;
    }

    public String getCourse_cno() {
        return course_cno;
    }

    public void setCourse_cno(String course_cno) {
        this.course_cno = course_cno;
    }

    public String getStudent_stno() {
        return student_stno;
    }

    public void setStudent_stno(String student_stno) {
        this.student_stno = student_stno;
    }

    public String getTeachersName() {
        return teachersName;
    }

    public void setTeachersName(String teachersName) {
        this.teachersName = teachersName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
