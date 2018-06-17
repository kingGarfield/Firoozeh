package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
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
import Model.AnyTermInformation;
import Util.Utils;

public class AnyTermInformationActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<AnyTermInformation> anyTermInformationArrayList = new ArrayList<>();
    String partition[];
    Toolbar toolbar;
    AnyTermInformationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_term_information);
        Intent i = getIntent();
        if(i != null) {
            String stno = i.getStringExtra("stno");
            String termNumber = i.getStringExtra("termNumber");
            GetAnyTermInfo info = new GetAnyTermInfo();
            info.execute(stno);
        }

        toolbar = (Toolbar)findViewById(R.id.activity_any_term_information_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    class GetAnyTermInfo extends AsyncTask<String,Void,String> {

        String token = null;
        @Override
        protected String doInBackground(String... strings) {
            token = strings[0];
            String data = new HttpClient().getInformation(Utils.ANY_TERM_INFORMATION, token);
            return data;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<AnyTermInformation> anyTermInf = new ArrayList<>();
            if (s.equals("null!null")) {
                Toast.makeText(AnyTermInformationActivity.this, "خطا در ارتباط با سرور",
                        Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject object2 = new JSONObject(s);
                    JSONArray jsonArray = object2.getJSONArray("studentCourses");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject subObject = jsonArray.getJSONObject(i);
                        AnyTermInformation information = new AnyTermInformation();

                        information.setCourseName(Utils.getString(subObject, "name"));
                        information.setNumOfUnits(Integer.parseInt(Utils.getString(subObject, "value")));
                        information.setScore(Double.parseDouble(Utils.getString(subObject, "mark")));
                        information.setScoreResult(Utils.getString(subObject, "status"));
                        information.setScoreStatus(Utils.getString(subObject, "markType"));
                        information.setLectureStatus(Utils.getString(subObject, "registerType"));
                        information.setCourseType(Utils.getString(subObject, "type"));
                        information.setRegistrationType(Utils.getString(subObject, "registerType"));

                        anyTermInf.add(information);
                    }

                    listView = (ListView) findViewById(R.id.any_term_list);
                    adapter = new AnyTermInformationAdapter(AnyTermInformationActivity.this,
                            R.layout.row_of_any_term_information_list,
                            anyTermInf);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                } catch (JSONException e) {

                }
            }
        }
    }
}
