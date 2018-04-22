package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText stnoEditText,passEditText;
    Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        stnoEditText = (EditText)findViewById(R.id.stnoEditText);
        passEditText = (EditText)findViewById(R.id.passEditText);
        login_button = (Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stnoEditText.getText().toString().equals("") ||
                        passEditText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.do_not_insert,
                            Toast.LENGTH_LONG).show();
                } // else_if(dont exist in database)
                else {
                    startActivity(new Intent(LoginActivity.this,MenuPage.class));
                    stnoEditText.setText("");
                    passEditText.setText("");
                }
            }
        });
    }
}
