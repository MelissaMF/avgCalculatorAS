package com.example.averagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etfirstGrade, etsecondGrade, etthirdGrade, etattendance, etexamGrade;
    TextView tvpreExamGrade, tvfinalAverage, tvfinalSituation;
    ImageView imgState;
    Double preExamGrade, finalAverage, examGrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etfirstGrade = findViewById(R.id.etFirstGrade);
        etsecondGrade = findViewById(R.id.etSecondGrade);
        etthirdGrade = findViewById(R.id.etThirdGrade);
        etattendance = findViewById(R.id.etAttendance);
        etexamGrade = findViewById(R.id.etExamGrade);
        tvpreExamGrade = findViewById(R.id.tvPreExamGrade);
        tvfinalAverage = findViewById(R.id.tvFinalAverage);
        tvfinalSituation = findViewById(R.id.tvFinalSituation);
        imgState = findViewById(R.id.imgState);
        imgState.setVisibility(View.INVISIBLE);
    }
    public void setFinalResults(Double finalAverage){
        tvfinalAverage.setText("Nota final: " + finalAverage);
        if(finalAverage >=4){
            tvfinalSituation.setText("Situación Final: Aprobado");
            imgState.setImageResource(R.drawable.aprobado);
            imgState.setVisibility(View.VISIBLE);
        } else{
            tvfinalSituation.setText("Situación Final: Reprobado");
            imgState.setImageResource(R.drawable.reprobado);
            imgState.setVisibility(View.VISIBLE);
        }
    }

    public void calculateAverage(View view){
        try {
            boolean isValid = true;
            double firstGrade = Double.parseDouble(etfirstGrade.getText().toString());
            double secondGrade = Double.parseDouble(etsecondGrade.getText().toString());
            double thirdGrade = Double.parseDouble(etthirdGrade.getText().toString());
            int attendance = Integer.parseInt(etattendance.getText().toString());
            if (firstGrade < 1 || firstGrade > 7) {
                etfirstGrade.setError("Primera nota no es válida");
                isValid =false;
            }
            if (secondGrade < 1 || secondGrade > 7) {
                etsecondGrade.setError("Segunda nota no es válida");
                isValid =false;
            }
            if (thirdGrade < 1 || thirdGrade > 7) {
                etthirdGrade.setError("Tercera nota no es válida");
                isValid =false;
            }
            if(isValid){
                preExamGrade = firstGrade * 0.25 + secondGrade * 0.35 + thirdGrade *0.4;
                tvpreExamGrade.setText("Nota Presentación: " + preExamGrade.toString());
            }
            if (attendance < 0 || attendance > 100) {
                etattendance.setError("Porcentaje asistencia no válido");
            } else if (preExamGrade >= 5.5) {
                examGrade = preExamGrade;
                tvfinalSituation.setText("Situación Final: Eximido");
                etexamGrade.setText(preExamGrade.toString());
                etexamGrade.setEnabled(false);
            } else if (attendance >= 70 && firstGrade > 4 && secondGrade > 4 && thirdGrade > 4) {
                tvfinalSituation.setText("Situación Final: Eximido");
                finalAverage = preExamGrade;
                etexamGrade.setText(preExamGrade.toString());
                etexamGrade.setEnabled(false);
            } else if (preExamGrade < 2) {
                finalAverage = preExamGrade;
                etexamGrade.setText(preExamGrade.toString());
                etexamGrade.setEnabled(false);
            }else{
                etexamGrade.setEnabled(true);
            }
        } catch (Exception e){
            Toast.makeText(this, "Revisa errores y no dejar campos vacíos", Toast.LENGTH_SHORT).show();
        }

    }

    public void calculateFinalAverage(View view){
        try {
            examGrade = Double.parseDouble(etexamGrade.getText().toString());
            if (examGrade >= 1 && examGrade <= 7) {
                finalAverage = examGrade * 0.4 + preExamGrade * 0.6;
                setFinalResults(finalAverage);
            } else{
                etexamGrade.setError("Nota de examen no válida");
            }
        }catch(Exception e){
            Toast.makeText(this, "No pueden quedar campos vacíos", Toast.LENGTH_SHORT).show();
        }
    }
    public void clean(View view){
        etfirstGrade.setText("");
        etsecondGrade.setText("");
        etthirdGrade.setText("");
        etattendance.setText("");
        etexamGrade.setText("");
        tvpreExamGrade.setText("Nota Presentación: ");
        tvfinalAverage.setText("Nota final: ");
        tvfinalSituation.setText("Situación Final: ");
        imgState.setVisibility(View.INVISIBLE);
    }
}