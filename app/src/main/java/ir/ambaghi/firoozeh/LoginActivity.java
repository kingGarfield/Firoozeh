//package ir.ambaghi.firoozeh;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class LoginActivity extends AppCompatActivity {
//
//    EditText stnoEditText,passEditText;
//    Button login_button;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//
//        stnoEditText = (EditText)findViewById(R.id.stnoEditText);
//        passEditText = (EditText)findViewById(R.id.passEditText);
//        login_button = (Button)findViewById(R.id.login_button);
//        login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (stnoEditText.getText().toString().equals("") ||
//                        passEditText.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), R.string.do_not_insert,
//                            Toast.LENGTH_LONG).show();
//                } // else_if(dont exist in database)
//                else {
//                    startActivity(new Intent(LoginActivity.this,MenuPage.class));
//                    stnoEditText.setText("");
//                    passEditText.setText("");
//                }
//            }
//        });
//    }
//}


package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Data.JSONLoginParser;
import Data.LoginHttpClient;
import Model.Student;
import Util.Utils;

public class LoginActivity extends AppCompatActivity {

    EditText stnoEditText, passEditText;
    TextView up_border;
    Button login_button;
    ProgressBar progressBar;
    String partition[];
    ArrayList<String> studentInformation = new ArrayList<>();

    //ProgressBar progressBar;
    //Connection con;
    //String un,pass,db,ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Saleem_Quran.ttf");
        up_border = (TextView) findViewById(R.id.up_border);
        up_border.setTypeface(typeface);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        stnoEditText = (EditText) findViewById(R.id.stnoEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);
        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (stnoEditText.getText().toString().equals("") || passEditText.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.do_not_insert, Toast.LENGTH_LONG).show();
                } else {
                    CheckLogin checkLogin = new CheckLogin();
                    checkLogin.execute(new String[]{stnoEditText.getText().toString(),
                            passEditText.getText().toString()});
                }

            }
        });
//        login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (stnoEditText.getText().toString().equals("") ||
//                        passEditText.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), R.string.do_not_insert,
//                            Toast.LENGTH_LONG).show();
//                } // else_if(dont exist in database)
//
//                else {
//                    studentInformation = loginCheck(stnoEditText.getText().toString(),
//                            passEditText.getText().toString());
//                    if (studentInformation.size() > 0) {
//                        Intent i = new Intent(LoginActivity.this, MenuPage.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putStringArrayList("studentInformation", studentInformation);
//                        i.putExtras(bundle);
//                        startActivity(i);
//                        stnoEditText.setText("");
//                        passEditText.setText("");
//                        LoginActivity.this.finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), R.string.no_login, Toast.LENGTH_LONG).show();
//                    }
//                }
////                CheckLogin checkLogin = new CheckLogin();
////                checkLogin.execute("");
//            }
//        });
    }

//    private ArrayList<String> loginCheck(String stno, String pass) {
//        ArrayList<String> studentInformation = new ArrayList<>();
//        try {
//            InputStream is = getAssets().open("files/studentsInformation.txt");
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader br = new BufferedReader(isr);
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                partition = line.split("-");
//                if (stno.equals(partition[0]) && pass.equals(partition[1])) {
//                    for (int i = 0; i < partition.length; i++) {
//                        studentInformation.add(partition[i]);
//                    }
//                    break;
//                }
//            }
//            is.close();
//            isr.close();
//            br.close();
//        } catch (IOException e) {
//            e.getStackTrace();
//        }
//        return studentInformation;
//    }

    private class CheckLogin extends AsyncTask<String, Void, String> {

        String stno;
        String pass;
        String data;
        @Override
        protected String doInBackground(String... strings) {
            stno = strings[0];
            pass = strings[1];
            String message = null;
            data = (new LoginHttpClient()).getLoginData(strings[0], strings[1]);
            JSONObject object = null;
            if (data != null) {
                try {
                    object = new JSONObject(data);
                    message = Utils.getString(object, "message");
                    Log.v("messagee", message);

                } catch (JSONException e) {
                    //Toast.makeText(getApplicationContext(), "عدم اتصال به اینترنت", Toast.LENGTH_LONG).show();
                }
            } else {
                message = null;
            }
            return message;
        }

        @Override
        protected void onPostExecute(String message) {
            super.onPostExecute(message);
            String firstName = null;
            String lastName = null;
            if(message == null) {
                Toast.makeText(getApplicationContext(), "عدم اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
            else if (message.equals("signed in successfully.")) {
                if(data  != null) {
                    try {
                        JSONObject object = new JSONObject(data);
                        firstName = Utils.getString(object, "first name");
                        lastName = Utils.getString(object, "last name");
                    }catch (JSONException e) {

                    }
                }
                ArrayList<String> studentInformation = new ArrayList<>();
                studentInformation.add(stno);
                studentInformation.add(pass);
                studentInformation.add(firstName);
                studentInformation.add(lastName);
                Intent i = new Intent(LoginActivity.this, MenuPage.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("studentInformation", studentInformation);
                i.putExtras(bundle);

                startActivity(i);
                LoginActivity.this.finish();
            } else {
                Toast.makeText(getApplication(), "شماره دانشجویی یا رمز عبور ناصحیح است.",
                        Toast.LENGTH_LONG).show();
            }

        }
    }


}

