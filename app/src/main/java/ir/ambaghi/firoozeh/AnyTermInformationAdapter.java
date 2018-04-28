package ir.ambaghi.firoozeh;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import Model.AnyTermInformation;

/**
 * Created by iWin_64bit on 4/28/2018.
 */

public class AnyTermInformationAdapter extends ArrayAdapter<AnyTermInformation> {

    private ArrayList<AnyTermInformation> anyTermInformationArrayList = new ArrayList<>();
    private Activity activity;
    private int resource;

    public AnyTermInformationAdapter(@NonNull Activity activity, int resource, @NonNull ArrayList<AnyTermInformation> anyTermInformations) {
        super(activity, resource, anyTermInformations);
        setAnyTermInformationArrayList(anyTermInformations);
        setActivity(activity);
        setResource(resource);
        notifyDataSetChanged();
    }

    public ArrayList<AnyTermInformation> getAnyTermInformationArrayList() {
        return anyTermInformationArrayList;
    }

    public void setAnyTermInformationArrayList(ArrayList<AnyTermInformation> anyTermInformationArrayList) {
        this.anyTermInformationArrayList = anyTermInformationArrayList;
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


    @NonNull
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public int getCount() {
        return getAnyTermInformationArrayList().size();
    }

    @Nullable
    @Override
    public AnyTermInformation getItem(int position) {
        return super.getItem(position);
    }

    @Nullable


    @Override
    public int getPosition(@Nullable AnyTermInformation item) {
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

        if(row == null || row.getTag() == null) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            row = inflater.inflate(getResource(), null);
            holder.setCourseName((TextView)row.findViewById(R.id.courseName));
            holder.setCourseType((TextView)row.findViewById(R.id.courseType));
            holder.setLectureStatus((TextView)row.findViewById(R.id.lectureStatus));
            holder.setNumOfUnits((TextView)row.findViewById(R.id.numOfUnits));
            holder.setRegistrationType((TextView)row.findViewById(R.id.registrationType));
            holder.setScore((TextView)row.findViewById(R.id.score));
            holder.setScoreResult((TextView)row.findViewById(R.id.scoreResult));
            holder.setScoreStatus((TextView)row.findViewById(R.id.scoreStatus));
            row.setTag(holder);
        } else  {
            holder = (Holder)row.getTag();
        }

        holder.setAnyTermInformation(getAnyTermInformationArrayList().get(position));
        holder.getCourseName().setText(holder.getAnyTermInformation().getCourseName());
        holder.getCourseType().setText(holder.getAnyTermInformation().getCourseType());
        holder.getLectureStatus().setText(holder.getAnyTermInformation().getLectureStatus());
        holder.getNumOfUnits().setText(holder.getAnyTermInformation().getNumOfUnits() + "");
        holder.getRegistrationType().setText(holder.getAnyTermInformation().getRegistrationType());
        holder.getScore().setText(holder.getAnyTermInformation().getScore() + "");
        holder.getScoreResult().setText(holder.getAnyTermInformation().getScoreResult());
        holder.getScoreStatus().setText(holder.getAnyTermInformation().getScoreStatus());

        return row;
    }

    private class Holder {
        private TextView courseName;
        private TextView numOfUnits;
        private TextView score;
        private TextView scoreResult;
        private TextView scoreStatus;
        private TextView lectureStatus;
        private TextView courseType;
        private TextView registrationType;
        AnyTermInformation anyTermInformation = new AnyTermInformation();

        public TextView getCourseName() {
            return courseName;
        }

        public void setCourseName(TextView courseName) {
            this.courseName = courseName;
        }

        public TextView getNumOfUnits() {
            return numOfUnits;
        }

        public void setNumOfUnits(TextView numOfUnits) {
            this.numOfUnits = numOfUnits;
        }

        public TextView getScore() {
            return score;
        }

        public void setScore(TextView score) {
            this.score = score;
        }

        public TextView getScoreResult() {
            return scoreResult;
        }

        public void setScoreResult(TextView scoreResult) {
            this.scoreResult = scoreResult;
        }

        public TextView getScoreStatus() {
            return scoreStatus;
        }

        public void setScoreStatus(TextView scoreStatus) {
            this.scoreStatus = scoreStatus;
        }

        public TextView getLectureStatus() {
            return lectureStatus;
        }

        public void setLectureStatus(TextView lectureStatus) {
            this.lectureStatus = lectureStatus;
        }

        public TextView getCourseType() {
            return courseType;
        }

        public void setCourseType(TextView courseType) {
            this.courseType = courseType;
        }

        public TextView getRegistrationType() {
            return registrationType;
        }

        public void setRegistrationType(TextView registrationType) {
            this.registrationType = registrationType;
        }

        public AnyTermInformation getAnyTermInformation() {
            return anyTermInformation;
        }

        public void setAnyTermInformation(AnyTermInformation anyTermInformation) {
            this.anyTermInformation = anyTermInformation;
        }
    }
}
