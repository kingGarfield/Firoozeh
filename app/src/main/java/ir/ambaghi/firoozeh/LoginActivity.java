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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText stnoEditText, passEditText;
    Button login_button;
    String partition[];
    ArrayList<String> studentInformation = new ArrayList<>();

    //ProgressBar progressBar;
    //Connection con;
    //String un,pass,db,ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        stnoEditText = (EditText) findViewById(R.id.stnoEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);
        login_button = (Button) findViewById(R.id.login_button);
//        progressBar = (ProgressBar)findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.GONE);
//        ip = "192.168.3.208";
//        db = "boostan";
//        un = "";
//        pass = "";
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stnoEditText.getText().toString().equals("") ||
                        passEditText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.do_not_insert,
                            Toast.LENGTH_LONG).show();
                } // else_if(dont exist in database)
                else {
                    studentInformation = loginCheck(stnoEditText.getText().toString(),
                            passEditText.getText().toString());
                    if (studentInformation.size() > 0) {
                        Intent i = new Intent(LoginActivity.this, MenuPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("studentInformation", studentInformation);
                        i.putExtras(bundle);
                        startActivity(i);
                        stnoEditText.setText("");
                        passEditText.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.no_login, Toast.LENGTH_LONG).show();
                    }
                }
//                CheckLogin checkLogin = new CheckLogin();
//                checkLogin.execute("");
            }
        });
    }

    private ArrayList<String> loginCheck(String stno, String pass) {
        ArrayList<String> studentInformation = new ArrayList<>();
        try {
            InputStream is = getAssets().open("files/studentsInformation.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                partition = line.split("-");
                Log.v("asdasd", partition[1]);
                if (stno.equals(partition[0]) && pass.equals(partition[1])) {
                    for (int i = 0; i < partition.length; i++) {
                        studentInformation.add(partition[i]);
                    }
                    break;
                }
            }
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return studentInformation;
    }

//    private class CheckLogin extends AsyncTask<String,String,String>{
//
//        String z = "";
//        Boolean isSuccessful = false;
//        @Override
//        protected String doInBackground(String... strings) {
//            String userName = stnoEditText.getText().toString();
//            String password = passEditText.getText().toString();
//            if(userName.trim().equals("") || password.trim().equals("")) {
//                z = "Please Enter Username and Password";
//            } else {
//                try {
//                    con = connectionclass(un,pass,db,ip);
//                    if(con == null) {
//                        z = "Check Your Internet Access!";
//
//                    } else {
//                        String query = "select * from login where usern_name = '"+userName.toString()+
//                                "' and pass_word = '"+password.toString()+"'";
//                        query = "select * from student";
//                        Statement stmt = con.createStatement();
//                        ResultSet rs = stmt.executeQuery(query);
//                        if(rs.next()) {
//                            z = "Login Successful";
//                            isSuccessful = true;
//                            con.close();
//                        } else {
//                            z = "Invalid Credentials!";
//                            isSuccessful = false;
//                        }
//                    }
//                }catch (Exception ex) {
//                    isSuccessful = false;
//                    z = ex.getMessage();
//                }
//            }
//            return z;
//        }
//
//        @Override
//        protected void onPostExecute(String r) {
//            progressBar.setVisibility(View.GONE);
//            Toast.makeText(LoginActivity.this, r, Toast.LENGTH_LONG).show();
//            if(isSuccessful) {
//                Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
//            }
//        }
//
//
//        @SuppressLint("NewApi")
//        public  Connection connectionclass(String user, String password, String database, String server) {
//
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//            Connection connection = null;
//            String connectionUrl;
//
//            try {
//                Class.forName("net.sourceforge.jtds.jdbc.Driver");
//                connectionUrl = "jdbc:jtds:sqlserver://"+server+database+";user="+user+";password="+password+";";
//                connection = DriverManager.getConnection(connectionUrl);
//            }catch (SQLException se) {
//                Log.e("error here 1: ",se.getMessage());
//            }catch (ClassNotFoundException e) {
//                Log.e("error here 2: ",e.getMessage());
//            }catch (Exception e) {
//                Log.e("error here 3: ",e.getMessage());
//            }
//            return  connection;
//        }
//    }


}

