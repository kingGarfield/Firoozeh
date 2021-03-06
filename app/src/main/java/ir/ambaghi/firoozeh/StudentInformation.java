package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.TermAndAvgForRow;

public class StudentInformation extends AppCompatActivity {

    Toolbar toolbar;
    TextView passedUnits,receivedUnits,average;
    ListView termList;
    ArrayList<String> student_avg_receive_pass = new ArrayList<>();
    ArrayList<String> student_terms_information = new ArrayList<>();
    ArrayList<TermAndAvgForRow> termAndAvgForRows = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        toolbar = (Toolbar) findViewById(R.id.activity_student_information_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        passedUnits = (TextView) findViewById(R.id.passedUnits);
        receivedUnits = (TextView) findViewById(R.id.receivedUnits);
        average = (TextView) findViewById(R.id.average);
        termList = (ListView) findViewById(R.id.termList);

        Intent i = getIntent();
        if(i != null) {
            Bundle bundle = i.getExtras();
            student_avg_receive_pass = bundle.getStringArrayList("student_avg_receive_pass");
            student_terms_information = bundle.getStringArrayList("student_terms_information");
            passedUnits.setText(student_avg_receive_pass.get(1));
            receivedUnits.setText(student_avg_receive_pass.get(2));
            average.setText(student_avg_receive_pass.get(3));

            for (int j = 1; j < student_terms_information.size(); j=j+2) {
                TermAndAvgForRow stuInfForRow = new TermAndAvgForRow();
                stuInfForRow.setStno(student_terms_information.get(0));
                stuInfForRow.setTermNumber(student_terms_information.get(j));
                stuInfForRow.setTermAverage(student_terms_information.get(j+1));
                termAndAvgForRows.add(stuInfForRow);
            }
        }

        TermAndAvgForRowAdapter adapter = new TermAndAvgForRowAdapter(
                                StudentInformation.this, R.layout.row_of_list_in_student_information,
                                    termAndAvgForRows);

        termList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
