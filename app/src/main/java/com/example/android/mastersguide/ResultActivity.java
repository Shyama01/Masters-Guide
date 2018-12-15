package com.example.android.mastersguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private String country,dName,uniName,cost,duration,department,subDepartment,url,image;
    private ArrayList<Course>courses = new ArrayList<>();
    private String iCountry,iDepartment;
    private RecyclerView courselist;
    private CustomCourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        courselist = findViewById(R.id.courselist_recycler);
        courselist.setHasFixedSize(true);
        courselist.setLayoutManager(new LinearLayoutManager(ResultActivity.this));


        iCountry = getIntent().getStringExtra("country");
        iDepartment = getIntent().getStringExtra("department");

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("Disc_List");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                department = jo_inside.getString("dName");

                JSONArray subArray = jo_inside.getJSONArray("Sub_Disc");

                if (department.contains(iDepartment)){


                    for (int j = 0; j < subArray.length(); j++) {

                        JSONObject details = subArray.getJSONObject(j);

                        subDepartment = details.getString("sdName");

                        JSONArray info = details.getJSONArray("Dept");

                        getDetailinfo(info);

                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new CustomCourseAdapter(courses);
        courselist.setAdapter(adapter);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("results.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    void getDetailinfo(JSONArray info) throws JSONException {
        for (int k = 0; k < info.length(); k++) {

            JSONObject infos = info.getJSONObject(k);

            country = infos.getString("Inst_Location");
            uniName = infos.getString("Inst_Name");
            cost = infos.getString("Inst_Cost");
            duration = infos.getString("Inst_Duration");
            dName = infos.getString("sddName");
            url = infos.getString("url");
            image = infos.getString("Inst_ImgLink");

            if (iCountry.contains(country)) {
                courses.add(new Course(dName, country, cost, uniName, duration,url,image));
            }


        }
    }
}

