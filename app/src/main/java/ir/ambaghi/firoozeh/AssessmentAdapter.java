package ir.ambaghi.firoozeh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.HolderAssessment;
import Model.TeacherAndCourseAssessment;

public class AssessmentAdapter extends ArrayAdapter<TeacherAndCourseAssessment> {

    public static final String PREFS_NAME = "myPrefsFile";
    private Activity activity;
    private int resource;
    private ArrayList<TeacherAndCourseAssessment> teacherAndCourseAssessmentArrayList = new ArrayList<>();
    HolderAssessment holder;
    SharedPreferences preferences;

    public AssessmentAdapter(@NonNull Activity activity, int resource, @NonNull ArrayList<TeacherAndCourseAssessment> teacherAndCourseAssessmentArrayList) {
        super(activity, resource, teacherAndCourseAssessmentArrayList);
        setActivity(activity);
        setResource(resource);
        setTeacherAndCourseAssessmentArrayList(teacherAndCourseAssessmentArrayList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public int getCount() {
        return getTeacherAndCourseAssessmentArrayList().size();
    }

    @Nullable
    @Override
    public TeacherAndCourseAssessment getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable TeacherAndCourseAssessment item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        holder = new HolderAssessment();
        if (view == null || view.getTag() == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            view = inflater.inflate(getResource(), null);
            holder.setTeachersName((TextView) view.findViewById(R.id.teachersName));
            holder.setcName((TextView) view.findViewById(R.id.cName));
            holder.setGoButton((ImageButton) view.findViewById(R.id.showAssessment));
            holder.setIdDoneTextView((TextView) view.findViewById(R.id.isDoeneTextView));
            view.setTag(holder);
        } else {
            holder = (HolderAssessment) view.getTag();
        }

        holder.setTeacherAndCourseAssessment(getTeacherAndCourseAssessmentArrayList().get(position));

        holder.getTeachersName().setText(holder.getTeacherAndCourseAssessment().getTeachersName());
        holder.getcName().setText(holder.getTeacherAndCourseAssessment().getCourseName());
//        preferences = getActivity().getSharedPreferences(PREFS_NAME, 0);
//
//        if (preferences.contains(holder.getTeacherAndCourseAssessment().getStudent_stno() +
//                holder.getTeacherAndCourseAssessment().getTeacher_tno() +
//                holder.getTeacherAndCourseAssessment().getCourse_cno())) {
//
//            holder.getGoButton().setVisibility(View.INVISIBLE);
//            holder.getIdDoneTextView().setVisibility(View.VISIBLE);
//        }
//        holder.getGoButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString(holder.getTeacherAndCourseAssessment().getStudent_stno() +
//                        holder.getTeacherAndCourseAssessment().getTeacher_tno() +
//                                holder.getTeacherAndCourseAssessment().getCourse_cno(),
//                                holder.getTeacherAndCourseAssessment().getStudent_stno() +
//                                holder.getTeacherAndCourseAssessment().getTeacher_tno() +
//                                holder.getTeacherAndCourseAssessment().getCourse_cno());
//                editor.commit();
//
//
//                Intent i = new Intent(getActivity(), AssessmentForm.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("teacherAndCourse", holder.getTeacherAndCourseAssessment());
//                i.putExtras(bundle);
//                getActivity().startActivity(i);
//                getActivity().finish();
//            }
//        });



        holder.getGoButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                String str = (preferences.getString(position + "", null));
                if (str != null) {
                    Toast.makeText(getActivity(),"انجام شده",Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences.Editor preferences1 = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
                    preferences1.putString(position + "", position + "");
                    preferences1.commit();
                    Intent i = new Intent(getActivity(), AssessmentForm.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("teacherAndCourse", getTeacherAndCourseAssessmentArrayList().get(position));
                    i.putExtras(bundle);
                    getActivity().startActivity(i);
                    getActivity().finish();
                }

            }
        });
        return view;
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

    public ArrayList<TeacherAndCourseAssessment> getTeacherAndCourseAssessmentArrayList() {
        return teacherAndCourseAssessmentArrayList;
    }

    public void setTeacherAndCourseAssessmentArrayList(ArrayList<TeacherAndCourseAssessment> teacherAndCourseAssessmentArrayList) {
        this.teacherAndCourseAssessmentArrayList = teacherAndCourseAssessmentArrayList;
    }


}
