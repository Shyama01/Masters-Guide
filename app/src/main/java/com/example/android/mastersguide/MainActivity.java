package com.example.android.mastersguide;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity {
    public static final String EmployeeNamearray = "EmpName";
    public static final String EmployeeName  = "EmpName";
    public static final String CountryNamearray = "CountName";
    public static final String CountryName  = "CountName";
    public static final String EmployeeMailid = "EmpMailid";
    public static final String JSON_ARRAY = "result";
    private JSONArray result;
    Spinner spinner;
    Spinner spinner1;
    private Button btnRes;

    String  country;
    String  department;

    private ArrayList<String> disciplinelist;
    private ArrayList<String> countrylist;
    TextView employeename,mailid,countryTV;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner= (Spinner) findViewById(R.id.spnrEmployee);
        spinner1 = (Spinner) findViewById(R.id.spnrCountry);
        mailid= (TextView) findViewById(R.id.tvmailid);
        btnRes = findViewById(R.id.btn_result);
        disciplinelist = new ArrayList<String>();
        countrylist = new ArrayList<String>();
        //getdata();
        getCountryAndDepart();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Setting the values to textviews for a selected item
//                employeename.setText(getemployeeName(position));
//                mailid.setText(getmailid(position));
//                countryTV.setText(getcountryName(position));
                department = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                employeename.setText("");
                mailid.setText("");
                countryTV.setText("");
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Setting the values to textviews for a selected item
//                employeename.setText(getemployeeName(position));
//                mailid.setText(getmailid(position));
//                countryTV.setText(getcountryName(position));
                country = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                employeename.setText("");
                mailid.setText("");
                countryTV.setText("");
            }
        });

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("country",country);
                intent.putExtra("department",department);
                startActivity(intent);
            }
        });


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
    @RequiresApi(api = Build.VERSION_CODES.N)
    void getCountryAndDepart(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("Disc_List");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                //department = jo_inside.getString("dName");
                disciplinelist.add(jo_inside.getString("dName"));

                JSONArray subArray = jo_inside.getJSONArray("Sub_Disc");

                for (int j = 0; j < subArray.length(); j++) {

                    JSONObject details = subArray.getJSONObject(j);

                    JSONArray info = details.getJSONArray("Dept");

                    for (int k = 0; k < info.length(); k++) {

                        JSONObject infos = info.getJSONObject(k);

                        countrylist.add(infos.getString("Inst_Location"));

                    }


                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        disciplinelist = (ArrayList<String>) disciplinelist.stream().distinct().collect(Collectors.<String>toList());
        spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, disciplinelist));
        countrylist = (ArrayList<String>) countrylist.stream().distinct().collect(Collectors.<String>toList());
        spinner1.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, countrylist));
    }

}
