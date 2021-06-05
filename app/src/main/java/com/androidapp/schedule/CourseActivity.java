package com.androidapp.schedule;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    Button searchBtn, addBtn;
    TextView tv;

    private Spinner yearSpinner, termSpinner, areaSpinner, majorSpinner;
    private ArrayAdapter<String> arrayAdapter;
    public static final String SELECTED_SUBJECT = "subject";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        termSpinner = (Spinner) findViewById(R.id.termSpinner);
        areaSpinner = (Spinner) findViewById(R.id.areaSpinner);
        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);

        searchBtn = (Button) findViewById(R.id.searchBtn);
        addBtn = (Button) findViewById(R.id.addBtn);

        searchBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.spinner_year));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.spinner_year_term));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.spinner_year_term_subjectArea));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.spinner_year_term_subjectMajor_major));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(arrayAdapter);

        initSubjectSpinner();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.searchBtn) {
            if (yearSpinner.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "해당년도를 입력하세요.", Toast.LENGTH_SHORT).show();
            } else if (termSpinner.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "해당학기를 입력하세요.", Toast.LENGTH_SHORT).show();
            } else if (areaSpinner.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "전공여부를 입력하세요.", Toast.LENGTH_SHORT).show();
            } else if (majorSpinner.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "학과를 입력하세요.", Toast.LENGTH_SHORT).show();
            } else {
                Intent data = new Intent();

                if (majorSpinner.getSelectedItem() != null) {
                    String subject = yearSpinner.getSelectedItem().toString() + " " + termSpinner.getSelectedItem().toString() + " " + areaSpinner.getSelectedItem().toString() + " " + majorSpinner.getSelectedItem().toString();
                    data.putExtra(SELECTED_SUBJECT, subject);
                } else {
                    String subject = yearSpinner.getSelectedItem().toString() + " " + termSpinner.getSelectedItem().toString() + " " + areaSpinner.getSelectedItem().toString();
                }
                setResult(RESULT_OK, data);
                finish();
            }
        } else if(view.getId() == R.id.addBtn){
                finish();
            }

//    public void addButton(View view) {
//        AssetManager assetManager = getAssets();
//
//        try {
//            InputStream is = assetManager.open("json/timetable.json");
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader reader = new BufferedReader(isr);
//
//            StringBuffer buffer = new StringBuffer();
//            String line= reader.readLine();
//            while (line!=null){
//                buffer.append(line+"\n");
//                line=reader.readLine();
//            }
//
//            String jsonData= buffer.toString();
//
//            JSONObject jsonObject = new JSONObject(jsonData);
//            String courseID = jsonObject.getString("courseID");
//            int courseYear = jsonObject.getInt("courseYear");
//            String courseTerm = jsonObject.getString("courseTerm");
//            String courseArea = jsonObject.getString("courseArea");
//            String courseMajor = jsonObject.getString("courseMajor");
//            String courseGrade = jsonObject.getString("courseGrade");
//            String courseTitle = jsonObject.getString("courseTitle");
//            int courseCredit = jsonObject.getInt("courseCredit");
//            int courseDivide = jsonObject.getInt("courseDivide");
//            String courseProfessor = jsonObject.getString("courseProfessor");
//            String courseTime = jsonObject.getString("courseTime");
//            String courseClass = jsonObject.getString("courseClass");
//
//            tv.setText("학수번호: " +"courseID" + "년도: " + "courseYear");
//
//        } catch (IOException e) {e.printStackTrace();} catch (JSONException e) {e.printStackTrace(); }
//
//    }
    }

    private void initSubjectSpinner() {
//        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //년도에 대한 학기 스피너 초기화
//                switch (position){
//                    case 0 :
//                        termSpinner.setAdapter(null);
//                        break;
//                    default :
//                        setTermSpinnerAdapterItem(R.array.spinner_year_term);
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

//        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            // 학기에 대한 전선/교필.... 스피너 초기화
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //2021년 선택시
//                switch (position){
//                    case 0 :
//                        termSpinner.setAdapter(null);
//                        break;
//                    default :
//                        setTermSpinnerAdapterItem(R.array.spinner_year_term);
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }
//    private void setTermSpinnerAdapterItem(int array_resource) {
//        if (arrayAdapter != null) {
//            termSpinner.setAdapter(null);
//            arrayAdapter = null;
//        }
//
//        if (yearSpinner.getSelectedItemPosition() > 1) {
//            areaSpinner.setAdapter(null);
//        }
//
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[])getResources().getStringArray(array_resource));
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        termSpinner.setAdapter(arrayAdapter);
//    }
}