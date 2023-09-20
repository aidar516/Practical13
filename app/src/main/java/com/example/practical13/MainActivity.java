package com.example.practical13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText dateTxt, timeTxt;
    ImageButton dateBtn, timeBtn;
    Button applyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTxt = findViewById(R.id.date_txt);
        timeTxt = findViewById(R.id.time_txt);
        dateBtn = findViewById(R.id.date_pick_btn);
        timeBtn = findViewById(R.id.time_pick_btn);
        applyBtn = findViewById(R.id.apply_btn);


        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedYear = 2020;
                int selectedMonth = 0;
                int selectedDay = 1;

                DatePickerDialog.OnDateSetListener datePick = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dateTxt.setText(""+i2+"."+(i+1)+"."+i1);
                    }
                };

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, datePick, selectedYear, selectedMonth, selectedDay);
                datePickerDialog.show();
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedHours = 12;
                int selectedMinute = 30;
                boolean is24Format = true;

                TimePickerDialog.OnTimeSetListener timePick = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        timeTxt.setText(i + ":" + i1);
                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, timePick, selectedHours, selectedMinute, is24Format);
                timePickerDialog.show();
            }
        });

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                builder.setView(dialogView);

                TextView title = dialogView.findViewById(R.id.title);
                TextView message = dialogView.findViewById(R.id.message);
                Button positiveBtn = dialogView.findViewById(R.id.positive_button);
                Button negativeBtn = dialogView.findViewById(R.id.negative_button);

                title.setText("Подтверждение записи");
                message.setText("Вы точно хотите подтвердить запись?");

                positiveBtn.setText("Да");
                negativeBtn.setText("Нет");

                AlertDialog dialog = builder.create();

                positiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Вы подтвердили запись",
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                negativeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Вы отменили подтверждение",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}