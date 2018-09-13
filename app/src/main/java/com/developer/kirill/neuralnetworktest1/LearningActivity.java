package com.developer.kirill.neuralnetworktest1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LearningActivity extends Activity {
    private EditText salary;
    private EditText age;
    private EditText labor_term;
    private EditText children;
    private EditText marriage;
    private EditText desired;
    private Button send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_lay);
        salary = findViewById(R.id.salaryview);
        age = findViewById(R.id.ageview);
        labor_term = findViewById(R.id.labview);
        children = findViewById(R.id.chview);
        marriage = findViewById(R.id.marview);
        desired = findViewById(R.id.editText6);
        send = findViewById(R.id.buttonlearn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NeuralNetwork_Sweet_Cheese.network.learnNetwork(Integer.parseInt(salary.getText().toString()),
                        Integer.parseInt(age.getText().toString()),
                        Integer.parseInt(labor_term.getText().toString()),
                        Integer.parseInt(children.getText().toString()),
                        Integer.parseInt(marriage.getText().toString()),
                        Integer.parseInt(desired.getText().toString()));
                salary.setText("");
                age.setText("");
                labor_term.setText("");
                children.setText("");
                marriage.setText("");
                desired.setText("");
                Toast toast = Toast.makeText(getApplicationContext(), "Сеть приняла обучение", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
