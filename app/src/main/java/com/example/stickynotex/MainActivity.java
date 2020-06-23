package com.example.stickynotex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView list;
    Button add;
    Button clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(TextView) findViewById(R.id.list);
        add=(Button) findViewById(R.id.add_btn);
        clear=(Button) findViewById(R.id.clear);
        loadData();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBtnClicked();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });
    }
    private void clearData(){
        try {
            OutputStreamWriter file = new OutputStreamWriter(getApplicationContext().openFileOutput("records.txt", Context.MODE_PRIVATE));
            file.write("");
            file.close();
            Toast toast = Toast.makeText(getApplicationContext(), "Notes clear", Toast.LENGTH_LONG);
            loadData();
            toast.show();
        }catch (FileNotFoundException e){
            return;
        }catch (IOException e){
            return;
        }
    }
    private void loadData() {
        String text="";
        try {
            InputStreamReader file = new InputStreamReader(getApplicationContext().openFileInput("records.txt"));
            BufferedReader reader=new BufferedReader(file);
            while (true){
                try {
                    String temp = reader.readLine();
                    if (temp==null){
                        break;
                    }
                    text+=temp;
                    text+="\n";
                    temp="";
                }catch (IOException e){
                    break;
                }
            }
            list.setText(text);
        } catch (FileNotFoundException e) {
            Toast toast= Toast.makeText(getApplicationContext(),"No data available",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    private void addBtnClicked(){
        Intent intent=new Intent(this,Add_Note.class);
        startActivity(intent);
    }
}