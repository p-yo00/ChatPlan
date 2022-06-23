package org.techtown.chatplan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.util.Calendar;


public class AddPlan extends AppCompatActivity {

    TextView txtDate;
    String date;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        txtDate=findViewById(R.id.txtDate);
        Button btTime = findViewById(R.id.timeSelect);
        Button btSave = findViewById(R.id.btSave);
        Button btCancel = findViewById(R.id.btCancel);
        EditText etTitle = findViewById(R.id.etName);
        EditText etMemo = findViewById(R.id.etMemo);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        String tempDate= date;
        txtDate.setText(date);

        System.out.println("date:"+date);

        Calendar dDay = Calendar.getInstance();;
        if(date!=null) {
            date += "-";
            StringBuffer newDate = new StringBuffer();
            int year = 0, month = 0, day = 0;
            int count = 0;
            for (int i = 0; i < date.length(); i++) {
                if (date.charAt(i) != '-') {
                    newDate.append(date.charAt(i));
                } else {
                    count++;
                    switch (count) {
                        case 1:
                            System.out.println("x:" + newDate.toString());
                            year = Integer.parseInt(newDate.toString());
                            break;
                        case 2:
                            month = Integer.parseInt(newDate.toString());
                        case 3:
                            day = Integer.parseInt(newDate.toString());
                    }
                    newDate.delete(0, newDate.length());
                }
            }
            dDay.set(year, month-1, day-1);
            System.out.println(year + "-" + month + "-" + day);
        }

        btTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), timeSelectPopup.class);
                startActivityForResult(intent,1);
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = hour+":"+minute;
                PlanDB db= PlanDB.getInstance();
                db.AddPlan(dDay, tempDate, etTitle.getText().toString(),time,etMemo.getText().toString());

                String msg = tempDate+" 에 "+etTitle.getText()+"이(가) 예정되어있습니다.";

                new MainActivity().addList(msg);

                Intent intent = new Intent();
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                intent.putExtra("title",etTitle.getText().toString());
                intent.putExtra("memo",etMemo.getText().toString());
                //intent.putExtra("msg",msg);
                setResult(RESULT_OK, intent);

                Intent intent2 = new Intent(view.getContext(),MainActivity.class);
                intent2.putExtra("msg",msg);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1){
            if(resultCode==RESULT_OK){
                hour = data.getIntExtra("hour",0);
                minute = data.getIntExtra("minute",0);
                EditText etTime = findViewById(R.id.etTime);
                Time time = new Time(hour,minute,0);
                etTime.setText(time.toString());
            }
        }
    }
}