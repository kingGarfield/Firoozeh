package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenuPage extends AppCompatActivity {

    TextView student_fullname, student_stno;
    Button student_information_button;
    String partition[];
    ArrayList<String> studentInformation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        student_fullname = (TextView) findViewById(R.id.student_fullname);
        student_stno = (TextView) findViewById(R.id.student_stno);
        Intent i = getIntent();
        if (i != null) {
            studentInformation = new ArrayList<>();
            Bundle bundle = new Bundle();
            bundle = i.getExtras();
            studentInformation = bundle.getStringArrayList("studentInformation");
            student_fullname.setText(studentInformation.get(2) + " " + studentInformation.get(3));
            student_stno.setText(studentInformation.get(0));
        }

        student_information_button = (Button) findViewById(R.id.student_information_button);
        student_information_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentInformation != null) {


                    ArrayList<String> student_avg_receive_pass = readStudentAvgReceivePassFromFile(studentInformation.get(0));
                    ArrayList<String> student_terms_information = readStudentTermsInformationFromFile(studentInformation.get(0));



                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("student_avg_receive_pass",student_avg_receive_pass);
                    bundle.putStringArrayList("student_terms_information",student_terms_information);
                    Intent i = new Intent(MenuPage.this, StudentInformation.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        });
    }


    private ArrayList<String> readStudentAvgReceivePassFromFile(String stno) {
        ArrayList<String> student_avg_receive_pass = null;
        try {
            InputStream is = getAssets().open("files/student_inf_avg_pass_receive.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                partition = line.split("-");
                if (stno.equals(partition[0])) {
                    student_avg_receive_pass = new ArrayList<>();
                    for (int i = 0; i < partition.length; i++) {
                        student_avg_receive_pass.add(partition[i]);
                    }
                    break;
                }
            }
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student_avg_receive_pass;
    }


    private ArrayList<String> readStudentTermsInformationFromFile(String stno) {
        ArrayList<String> student_terms_information = null;
        try {
            InputStream is = getAssets().open("files/terms_information.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                partition = line.split("-");
                if (stno.equals(partition[0])) {
                    student_terms_information = new ArrayList<>();
                    for (int i = 0; i < partition.length; i++) {
                        student_terms_information.add(partition[i]);
                    }
                    break;
                }
            }
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student_terms_information;
    }
}
