package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Data.HttpClient;
import Util.Utils;

public class MenuPage extends AppCompatActivity {

    TextView student_fullname, student_stno;
    Button student_information_button, exitButton, assessmentButton, studentProfileButton;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    View navigationHeader;
    String partition[];
    ImageView student_image;
    ArrayList<String> studentInformation = null;
    String token = null;

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation_view);
        navigationHeader = navigationView.getHeaderView(0);
        drawerLayout = findViewById(R.id.navigation_drawer);
        student_fullname = navigationHeader.findViewById(R.id.studentFullName);
        student_stno = navigationHeader.findViewById(R.id.studentNumber);
        studentProfileButton = findViewById(R.id.studen_profile_button);
        student_image = navigationHeader.findViewById(R.id.studentImage);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        Intent i = getIntent();

        if (i != null) {
            studentInformation = new ArrayList<>();
            Bundle bundle = new Bundle();
            bundle = i.getExtras();

            studentInformation = bundle.getStringArrayList("studentInformation");
            token = studentInformation.get(4);
            student_fullname.setText(studentInformation.get(2) + " " + studentInformation.get(3));
            student_stno.setText(studentInformation.get(0));
            if (student_stno.getText().toString().equals("9421983")) {
                student_image.setImageResource(R.drawable.baqinejad);
            } else if (student_stno.getText().toString().equals("9444444")) {
                student_image.setImageResource(R.drawable.hejazi);
            } else {
                student_image.setImageResource(R.drawable.dehghani);
            }
        }

        student_information_button = findViewById(R.id.student_information_button);
        student_information_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentInformation != null) {

                    GetStudentInfo info = new GetStudentInfo();
                    info.execute(token);
                }
            }
        });

        studentProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPage.this, StudentProfile.class);
                i.putExtra("token", token);
                startActivity(i);
            }
        });
        exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


        assessmentButton = (Button) findViewById(R.id.assessment_button);
        assessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(new Intent(MenuPage.this, AssessmentActivity.class));
                i.putExtra("student_stno", student_stno.getText().toString());
                startActivity(i);
            }
        });
    }

    public boolean onNavigationItemSelecte(MenuItem item) {
        return true;
    }


    class GetStudentInfo extends AsyncTask<String, Void, String> {

        String token = null;

        @Override
        protected String doInBackground(String... strings) {
            token = strings[0];
            String data1 = new HttpClient().getInformation(Utils.STUDENT_AVG_RECEIVE_PASS_URL, token);
            String data2 = new HttpClient().getInformation(Utils.ANY_TERM_INFORMATION, token);
            String data = data1 + "!" + data2;
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("null!null")) {
                Toast.makeText(MenuPage.this, "خطا در ارتباط با سرور",
                        Toast.LENGTH_LONG).show();
            } else {
                ArrayList<String> student_avg_receive_pass = new ArrayList<>();
                ArrayList<String> student_terms_information = new ArrayList<>();
                String data[] = s.split("!");

                try {
                    JSONObject object1 = new JSONObject(data[0]);
                    student_avg_receive_pass.add(token);
                    student_avg_receive_pass.add(Utils.getString(object1, "numberOfCourses"));
                    student_avg_receive_pass.add(Utils.getString(object1, "numberOfPassedCourses"));
                    student_avg_receive_pass.add(Utils.getString(object1, "average of marks"));


//                    JSONObject object2 = new JSONObject(data[1]);
//                    JSONArray jsonArray = object2.getJSONArray("studentCourses");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject subObject = jsonArray.getJSONObject(i);
//                        student_terms_information.add(token);
//                        student_terms_information.add(Utils.getString(subObject, "term"));
//                        student_terms_information.add(Utils.getString(subObject, "name"));
//                        student_terms_information.add(Utils.getString(subObject, "value"));
//                        student_terms_information.add(Utils.getString(subObject, "mark"));
//                        student_terms_information.add(Utils.getString(subObject, "status"));
//                        student_terms_information.add(Utils.getString(subObject, "markType"));
//                        student_terms_information.add(Utils.getString(subObject, "registerType"));
//                        student_terms_information.add(Utils.getString(subObject, "type"));
//                        student_terms_information.add(Utils.getString(subObject, "registerType"));
//                    }


                    JSONObject object2 = new JSONObject(data[1]);
                    JSONArray jsonArray = object2.getJSONArray("studentCourses");
                    JSONObject subObject = jsonArray.getJSONObject(0);
                    student_terms_information.add(token);
                    student_terms_information.add(Utils.getString(subObject, "term"));
                    student_terms_information.add("18");

                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("student_avg_receive_pass", student_avg_receive_pass);
                    bundle.putStringArrayList("student_terms_information", student_terms_information);
                    Intent i = new Intent(MenuPage.this, StudentInformation.class);
                    i.putExtras(bundle);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
