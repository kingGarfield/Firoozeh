package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import Data.HttpClient;
import Model.Student;
import Util.Utils;

public class StudentProfile extends AppCompatActivity {

    TextView first, last, father, stno, national, email, phoneNumber, address;
    ProgressBar progressBar;
    String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);


        progressBar = findViewById(R.id.progressP);
        progressBar.setVisibility(View.VISIBLE);
        first = findViewById(R.id.firstNameP);
        last = findViewById(R.id.lastNameP);
        father = findViewById(R.id.fatherNameP);
        stno = findViewById(R.id.stnoP);
        national = findViewById(R.id.nationalIdP);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        address = findViewById(R.id.address);

        Intent i = getIntent();
        if (i != null) {
            token = i.getStringExtra("token");
            Profile profile = new Profile();
            profile.execute(token);
        }
    }


    class Profile extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String data = new HttpClient().getInformation(Utils.ACCUNT_SUMMARY_URL, strings[0]);
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            String data = s;
            JSONObject object;
            try {
                if (data == null) {
                    Toast.makeText(StudentProfile.this, "خطا در ارتباط با سرور",
                            Toast.LENGTH_LONG).show();
                } else {
                    object = new JSONObject(data);
                    first.setText(Utils.getString(object, "firstName"));
                    last.setText(Utils.getString(object, "lastName"));
                    father.setText(Utils.getString(object, "fatherName"));
                    stno.setText(Utils.getString(object, "username"));
                    national.setText(Utils.getString(object, "nationalID"));
                    email.setText(Utils.getString(object, "email"));
                    phoneNumber.setText(Utils.getString(object, "phoneNumber"));
                    address.setText(Utils.getString(object, "address"));
                    progressBar.setVisibility(View.INVISIBLE);
                }
            } catch (JSONException e) {
                Toast.makeText(StudentProfile.this, "خطا در ارتباط با سرور",
                        Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }
}
