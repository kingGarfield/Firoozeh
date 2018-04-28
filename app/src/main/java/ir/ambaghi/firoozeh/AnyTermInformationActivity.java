package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Model.AnyTermInformation;

public class AnyTermInformationActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<AnyTermInformation> anyTermInformationArrayList = new ArrayList<>();
    String partition[];
    AnyTermInformationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_term_information);
        Intent i = getIntent();
        if(i != null) {
            String stno = i.getStringExtra("stno");
            String termNumber = i.getStringExtra("termNumber");
            Log.v("aaaaaa",stno);
            Log.v("aaaaaa",termNumber);
            anyTermInformationArrayList = readAnyTermInfFromFile(stno, termNumber);
        }
        listView = (ListView) findViewById(R.id.any_term_list);
        adapter = new AnyTermInformationAdapter(AnyTermInformationActivity.this,
                                                    R.layout.row_of_any_term_information_list,
                                                    anyTermInformationArrayList);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    public ArrayList<AnyTermInformation> readAnyTermInfFromFile(String stno, String termNumber) {
        ArrayList<AnyTermInformation> anyTermInf = new ArrayList<>();

        try {
            InputStream is = getAssets().open("files/anyTermInformation.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine()) != null){
                partition = line.split("-");
                if(partition[0].equalsIgnoreCase(stno) && partition[1].equalsIgnoreCase(termNumber)) {
                    AnyTermInformation information = new AnyTermInformation();
                    information.setCourseName(partition[2]);
                    information.setNumOfUnits(Integer.parseInt(partition[3]));
                    Log.v("bbbb",partition[4]);
                    information.setScore(Double.parseDouble(partition[4]));
                    information.setScoreResult(partition[5]);
                    information.setScoreStatus(partition[6]);
                    information.setLectureStatus(partition[7]);
                    information.setCourseType(partition[8]);
                    information.setRegistrationType(partition[9]);
                    anyTermInf.add(information);
                }
            }
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return anyTermInf;
    }
}
