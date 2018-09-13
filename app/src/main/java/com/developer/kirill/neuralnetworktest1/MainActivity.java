package com.developer.kirill.neuralnetworktest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    public static TextView textView;
    private EditText salary;
    private EditText age;
    private EditText labor_term;
    private EditText children;
    private EditText marriage;
    public static Button result;
    public Button learn;
    NeuralNetwork_Sweet_Cheese network = NeuralNetwork_Sweet_Cheese.network;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        textView = findViewById(R.id.textView);
        salary = findViewById(R.id.salaryview);
        age = findViewById(R.id.ageview);
        labor_term = findViewById(R.id.labview);
        children = findViewById(R.id.chview);
        marriage = findViewById(R.id.marview);
        result = findViewById(R.id.button);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                network.collectData(Integer.parseInt(salary.getText().toString()),
                        Integer.parseInt(age.getText().toString()),
                        Integer.parseInt(labor_term.getText().toString()),
                        Integer.parseInt(children.getText().toString()),
                        Integer.parseInt(marriage.getText().toString()));
            }
        });
        learn = findViewById(R.id.button2);
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LearningActivity.class);
                startActivity(intent);
            }
        });
    }

}
