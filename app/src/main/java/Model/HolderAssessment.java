package Model;

import android.widget.ImageButton;
import android.widget.TextView;

public class HolderAssessment {
    private TextView teachersName;
    private TextView cName;
    private ImageButton goButton;
    private TextView idDoneTextView;

    public TextView getIdDoneTextView() {
        return idDoneTextView;
    }

    public void setIdDoneTextView(TextView idDoneTextView) {
        this.idDoneTextView = idDoneTextView;
    }

    private TeacherAndCourseAssessment teacherAndCourseAssessment = new TeacherAndCourseAssessment();

    public TeacherAndCourseAssessment getTeacherAndCourseAssessment() {
        return teacherAndCourseAssessment;
    }

    public void setTeacherAndCourseAssessment(TeacherAndCourseAssessment teacherAndCourseAssessment) {
        this.teacherAndCourseAssessment = teacherAndCourseAssessment;
    }

    public TextView getTeachersName() {
        return teachersName;
    }

    public void setTeachersName(TextView teachersName) {
        this.teachersName = teachersName;
    }

    public TextView getcName() {
        return cName;
    }

    public void setcName(TextView cName) {
        this.cName = cName;
    }

    public ImageButton getGoButton() {
        return goButton;
    }

    public void setGoButton(ImageButton goButton) {
        this.goButton = goButton;
    }
}
