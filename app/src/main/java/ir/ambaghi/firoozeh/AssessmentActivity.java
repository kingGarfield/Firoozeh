package ir.ambaghi.firoozeh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Model.TeacherAndCourseAssessment;

public class AssessmentActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    TextView noDefineTextView;
    String student_stno;
    String partion[];
    ArrayList<TeacherAndCourseAssessment> teacherAndCourseAssessmentArrayList = new ArrayList<>();

    public String getStudent_stno() {
        return student_stno;
    }

    public void setStudent_stno(String student_stno) {
        this.student_stno = student_stno;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        setStudent_stno(getIntent().getStringExtra("student_stno"));

        toolbar = findViewById(R.id.activityAssessmentToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        noDefineTextView = findViewById(R.id.noDifine);
        teacherAndCourseAssessmentArrayList = getTeacherAndCourseFromFile();
        //Toast.makeText(AssessmentActivity.this, teacherAndCourseAssessmentArrayList.size()+""
        //,Toast.LENGTH_LONG).show();
        AssessmentAdapter adapter = new AssessmentAdapter(AssessmentActivity.this,R.layout.list_of_assessment, teacherAndCourseAssessmentArrayList);

        listView = findViewById(R.id.activityAssessmentListView);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private ArrayList<TeacherAndCourseAssessment> getTeacherAndCourseFromFile() {
        ArrayList<TeacherAndCourseAssessment> teacherAndCourseAssessmentList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("files/TeachersAndCourseInfo.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                partion = line.split("-");
                if (partion[0].equals(student_stno)) {
                    TeacherAndCourseAssessment teacherAndCourseAssessment = new TeacherAndCourseAssessment();
                    teacherAndCourseAssessment.setStudent_stno(partion[0]);
                    teacherAndCourseAssessment.setTeacher_tno(partion[1]);
                    teacherAndCourseAssessment.setCourse_cno(partion[2]);
                    teacherAndCourseAssessment.setTeachersName(partion[3]);
                    teacherAndCourseAssessment.setCourseName(partion[4]);
                    teacherAndCourseAssessmentList.add(teacherAndCourseAssessment);
                }
            }
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teacherAndCourseAssessmentList;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
