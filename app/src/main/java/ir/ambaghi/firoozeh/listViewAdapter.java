package ir.ambaghi.firoozeh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ali on 4/26/2018.
 */

public class listViewAdapter extends ArrayAdapter<listViewAdapter.data>{
    public static class data{
        String pfName;
        String courseName;

        public data(String pfName, String courseName) {
            this.pfName = pfName;
            this.courseName = courseName;
        }
    }

    private ArrayList<data> dataArrayList;

    public listViewAdapter(@NonNull Context context, int resource, ArrayList<data> dataArrayList) {
        super(context, resource, dataArrayList);
        this.dataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.fragment_professor_recylcer_view_item,parent,false);
        ((TextView) v.findViewById(R.id.pfNameTextView)).setText(dataArrayList.get(position).pfName);
        ((TextView) v.findViewById(R.id.courseNameTextView)).setText(dataArrayList.get(position).courseName);
        return v;
    }
}
