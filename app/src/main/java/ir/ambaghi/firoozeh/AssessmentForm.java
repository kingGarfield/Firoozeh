package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import Model.TeacherAndCourseAssessment;

public class AssessmentForm extends AppCompatActivity implements Button.OnClickListener{

    RadioGroup q1_radiogroup,q2_radiogroup,q3_radiogroup;
    TextView courseName,teacherName;
    int res1 = -1;
    int res2 = -1;
    int res3 = -1;
    TeacherAndCourseAssessment teacherAndCourseAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_form);

        courseName = findViewById(R.id.courseNameAssessment);
        teacherName = findViewById(R.id.teacherNameAssessment);

        Intent i = getIntent();
        teacherAndCourseAssessment = (TeacherAndCourseAssessment)i.getSerializableExtra("teacherAndCourse");
        courseName.setText(teacherAndCourseAssessment.getCourseName().toString());
        teacherName.setText(teacherAndCourseAssessment.getTeachersName().toString());

        q1_radiogroup = findViewById(R.id.q1_radiogroup);
        q2_radiogroup = findViewById(R.id.q2_radiogroup);
        q3_radiogroup = findViewById(R.id.q3_radiogroup);
        q1_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id1 = group.getCheckedRadioButtonId();
                RadioButton radioButton1 = group.findViewById(id1);
                int which1 = group.indexOfChild(radioButton1);
                res1 = 4-which1;
                Toast.makeText(AssessmentForm.this, 4-which1+"",Toast.LENGTH_SHORT).show();
            }
        });

        q2_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id2 = group.getCheckedRadioButtonId();
                RadioButton radioButton2 = group.findViewById(id2);
                int which2 = group.indexOfChild(radioButton2);
                res2 = 4-which2;
                Toast.makeText(AssessmentForm.this, res2+"",Toast.LENGTH_SHORT).show();
            }
        });

        q3_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id3 = group.getCheckedRadioButtonId();
                RadioButton radioButton3 = group.findViewById(id3);
                int which3 = group.indexOfChild(radioButton3);
                res3 = 4-which3;
                Toast.makeText(AssessmentForm.this, res3+"",Toast.LENGTH_SHORT).show();
            }
        });



    }


    @Override
    public void onBackPressed() {

    }


    @Override
    public void onClick(View v) {
        Button button = (Button)v;
        switch (button.getId()) {
            case R.id.btnNextQ1:
                if(res1 == -1) {
                    Toast.makeText(AssessmentForm.this, R.string.select_one_item,
                            Toast.LENGTH_LONG).show();
                } else {
                    Button button1 = findViewById(R.id.btnNextQ1);
                    button1.setVisibility(View.INVISIBLE);
                    button1 = findViewById(R.id.btnNextQ2);
                    button1.setVisibility(View.VISIBLE);
                    RadioGroup radioGroup1 = findViewById(R.id.q1_radiogroup);
                    radioGroup1.setVisibility(View.INVISIBLE);
                    radioGroup1 = findViewById(R.id.q2_radiogroup);
                    radioGroup1.setVisibility(View.VISIBLE);
                    TextView textView1 = findViewById(R.id.assessment_q1);
                    textView1.setVisibility(View.INVISIBLE);
                    textView1 = findViewById(R.id.assessment_q2);
                    textView1.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.btnNextQ2:
                if(res2 == -1) {
                    Toast.makeText(AssessmentForm.this, R.string.select_one_item,
                            Toast.LENGTH_LONG).show();
                } else {
                    Button button2 = findViewById(R.id.btnNextQ2);
                    button2.setVisibility(View.INVISIBLE);
                    button2 = findViewById(R.id.btnNextQ3);
                    button2.setVisibility(View.VISIBLE);
                    RadioGroup radioGroup2 = findViewById(R.id.q2_radiogroup);
                    radioGroup2.setVisibility(View.INVISIBLE);
                    radioGroup2 = findViewById(R.id.q3_radiogroup);
                    radioGroup2.setVisibility(View.VISIBLE);
                    TextView textView2 = findViewById(R.id.assessment_q2);
                    textView2.setVisibility(View.INVISIBLE);
                    textView2 = findViewById(R.id.assessment_q3);
                    textView2.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.btnNextQ3:
                if(res3 == -1) {
                    Toast.makeText(AssessmentForm.this, R.string.select_one_item,
                            Toast.LENGTH_LONG).show();
                } else {

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(teacherAndCourseAssessment.getStudent_stno()+"-");
                    stringBuilder.append(teacherAndCourseAssessment.getTeacher_tno()+"-");
                    stringBuilder.append(teacherAndCourseAssessment.getCourse_cno()+"-");
                    stringBuilder.append(res1+"-");
                    stringBuilder.append(res2+"-");
                    stringBuilder.append(res3+"-");
                    int result = res1+res2+res3;
                    stringBuilder.append(result);

                    try {
                        OutputStreamWriter writer =
                                new OutputStreamWriter(openFileOutput("Assessment.txt",MODE_PRIVATE));
                        writer.write(stringBuilder.toString());
                        writer.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(AssessmentForm.this, AssessmentActivity.class);
                    i.putExtra("student_stno",teacherAndCourseAssessment.getStudent_stno());
                    startActivity(i);
                    finish();
                }
                break;
        }
    }
}
