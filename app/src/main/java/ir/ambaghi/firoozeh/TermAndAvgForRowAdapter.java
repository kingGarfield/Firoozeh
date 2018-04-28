package ir.ambaghi.firoozeh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Model.AnyTermInformation;
import Model.TermAndAvgForRow;

/**
 * Created by iWin_64bit on 4/25/2018.
 */

public class TermAndAvgForRowAdapter extends ArrayAdapter<TermAndAvgForRow> {

    private Activity activity;
    private int resource;
    private ArrayList<TermAndAvgForRow> termAndAvgForRowArrayList = new ArrayList<>();
    String partition[];
    ArrayList<AnyTermInformation> anyTermInformationArrayList = new ArrayList<>();

    public TermAndAvgForRowAdapter(@NonNull Activity activity, int resource, @NonNull ArrayList<TermAndAvgForRow> termAndAvgForRowArrayList) {
        super(activity, resource, termAndAvgForRowArrayList);
        setActivity(activity);
        setResource(resource);
        setTermAndAvgForRowArrayList(termAndAvgForRowArrayList);
        notifyDataSetChanged();
    }


    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public ArrayList<TermAndAvgForRow> getTermAndAvgForRowArrayList() {
        return termAndAvgForRowArrayList;
    }

    public void setTermAndAvgForRowArrayList(ArrayList<TermAndAvgForRow> termAndAvgForRowArrayList) {
        this.termAndAvgForRowArrayList = termAndAvgForRowArrayList;
    }


    @NonNull
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public int getCount() {
        return getTermAndAvgForRowArrayList().size();
    }

    @Nullable
    @Override
    public TermAndAvgForRow getItem(int position) {
        return getTermAndAvgForRowArrayList().get(position);
    }

    @Override
    public int getPosition(@Nullable TermAndAvgForRow item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        Holder holder = new Holder();
        if(convertView == null || convertView.getTag() == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(getResource(), null);
            holder.setNumberOfTerm((TextView)row.findViewById(R.id.numberOfTerm));
            holder.setAverage((TextView)row.findViewById(R.id.average));
            //holder.setStudentInformationForRow(getRowInStudentInformationList().get(position));

            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

        holder.setTermAndAvgForRow(getTermAndAvgForRowArrayList().get(position));
        holder.getAverage().setText(holder.getTermAndAvgForRow().getTermAverage());
        holder.getNumberOfTerm().setText(holder.getTermAndAvgForRow().getTermNumber());


        final Holder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stno = finalHolder.getTermAndAvgForRow().getStno();
                String termNumber = finalHolder.getTermAndAvgForRow().getTermNumber();
                Intent i = new Intent(activity, AnyTermInformationActivity.class);
                i.putExtra("stno",stno);
                i.putExtra("termNumber",termNumber);
                activity.startActivity(i);


            }
        });
        return row;
    }

    public ArrayList<String> readAnyTermInfFromFile(String stno, String termNumber) {
        ArrayList<String> anyTermInf = new ArrayList<>();

        try {
            InputStream is = activity.getAssets().open("files/anyTermInformation.txt.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine()) != null){
                partition = line.split("-");
                if(partition[0].equalsIgnoreCase(stno) && partition[1].equalsIgnoreCase(termNumber)) {
                    AnyTermInformation information = new AnyTermInformation();
                    information.setCourseName(partition[2]);
                    information.setNumOfUnits(Integer.parseInt(partition[3]));
                    information.setScore(Double.parseDouble(partition[4]));
                    information.setScoreResult(partition[5]);
                    information.setScoreStatus(partition[6]);
                    information.setLectureStatus(partition[7]);
                    information.setCourseType(partition[8]);
                    information.setRegistrationType(partition[9]);
                    anyTermInformationArrayList.add(information);
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

    private class Holder {

        private TextView numberOfTerm,average;

        private TermAndAvgForRow termAndAvgForRow = new TermAndAvgForRow();

        public TermAndAvgForRow getTermAndAvgForRow() {
            return termAndAvgForRow;
        }

        public void setTermAndAvgForRow(TermAndAvgForRow studentInformationForRow) {
            this.termAndAvgForRow = studentInformationForRow;
        }

        public TextView getNumberOfTerm() {
            return numberOfTerm;
        }

        public void setNumberOfTerm(TextView numberOfTerm) {
            this.numberOfTerm = numberOfTerm;
        }

        public TextView getAverage() {
            return average;
        }

        public void setAverage(TextView average) {
            this.average = average;
        }
    }
}
