package com.example.android.mastersguide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomCourseAdapter extends RecyclerView.Adapter<CustomCourseAdapter.CourseViewHolder>{

    private ArrayList<Course>  courses = new ArrayList<>();
    private Context context;

    public CustomCourseAdapter(ArrayList<Course> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CustomCourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);

        CourseViewHolder holder = new CourseViewHolder(v);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomCourseAdapter.CourseViewHolder holder, final int position) {

        holder.txtdepartment.setText(courses.get(position).getName());
        holder.txtcost.setText("cost : "+courses.get(position).getCost());
        holder.txtcountry.setText(courses.get(position).getCountry());
        holder.txtduration.setText("duration : "+courses.get(position).getDuration());
        holder.txtuniversity.setText(courses.get(position).getUniversity());
        holder.btnSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WebActivity.class);
                intent.putExtra("url",courses.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        Picasso.with(context).load(courses.get(position).getImage())
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder{

        public TextView txtdepartment,txtcost,txtduration,txtuniversity,txtcountry;
        public Button btnSite;
        public ImageView img;

        public CourseViewHolder(@NonNull View v) {
            super(v);

            txtcost = v.findViewById(R.id.txtCost);
            txtdepartment = v.findViewById(R.id.txtName);
            txtduration = v.findViewById(R.id.txtDuration);
            txtuniversity = v.findViewById(R.id.txtUniversity);
            txtcountry = v.findViewById(R.id.txtCountry);
            btnSite = v.findViewById(R.id.btnSite);
            img = v.findViewById(R.id.img);
        }
    }

}
