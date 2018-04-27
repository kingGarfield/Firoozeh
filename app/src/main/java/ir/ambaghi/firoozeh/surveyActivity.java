package ir.ambaghi.firoozeh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class surveyActivity extends AppCompatActivity {
    ArrayList<listViewAdapter.data> dataArrayList;
    ListView listView;
    SwipeRefreshLayout refreshLayout;
    private listViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        listView = findViewById(R.id.listView);
        refreshLayout = findViewById(R.id.swiperefresh);
        refreshLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        dataArrayList = new ArrayList<>();
        dataArrayList.add(new listViewAdapter.data("Ali Ahmadi","how to ..."));
        listViewAdapter = new listViewAdapter(this, -1, dataArrayList);
        listView.setAdapter(listViewAdapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataArrayList.add(new listViewAdapter.data("refresh","refresh"));
                listViewAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
    }
}
