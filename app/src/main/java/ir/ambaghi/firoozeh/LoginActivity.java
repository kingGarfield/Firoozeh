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

import java.util.ArrayList;

import Data.LoginHttpClient;
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
    }

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
            String token = null;
            if(message == null) {
                Toast.makeText(getApplicationContext(), "عدم اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
            else if (message.equals("signed in successfully.")) {
                if(data  != null) {
                    try {
                        JSONObject object = new JSONObject(data);
                        firstName = Utils.getString(object, "first name");
                        lastName = Utils.getString(object, "last name");
                        token = Utils.getString(object, "token");
                    }catch (JSONException e) {

                    }
                }
                ArrayList<String> studentInformation = new ArrayList<>();
                studentInformation.add(stno);
                studentInformation.add(pass);
                studentInformation.add(firstName);
                studentInformation.add(lastName);
                studentInformation.add(token);
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

