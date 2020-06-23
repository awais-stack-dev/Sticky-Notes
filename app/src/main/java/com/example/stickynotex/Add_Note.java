package com.example.stickynotex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Add_Note extends AppCompatActivity {
    EditText writtenText;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__note);
        writtenText = (EditText) findViewById(R.id.written_text);
        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okClicked();
            }
        });
    }

    private void okClicked() {

        String Loadtext = "";
        try {
            InputStreamReader myfile = new InputStreamReader(getApplicationContext().openFileInput("records.txt"));
            BufferedReader reader = new BufferedReader(myfile);
            while (true) {
                try {
                    String temp = reader.readLine();
                    if (temp == null) {
                        break;
                    }
                    Loadtext += temp;
                    Loadtext += "\n";
                    temp = "";
                } catch (IOException e) {
                    break;
                }
            }
        }catch (FileNotFoundException e){
            return;
        }
            String text = writtenText.getText().toString();
            try {
                OutputStreamWriter file = new OutputStreamWriter(getApplicationContext().openFileOutput("records.txt", Context.MODE_PRIVATE));
                file.write(Loadtext + "\n" + text);
                file.close();
                Toast toast = Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } catch (FileNotFoundException e) {
                writtenText.setText("...");
            } catch (IOException e) {
                writtenText.setText("IOException");
            }
        }
    }