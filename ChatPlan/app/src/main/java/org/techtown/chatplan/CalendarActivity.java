package org.techtown.chatplan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    private ListView listView;
    private PlanListAdapter listAdapter;
    private ArrayList<Plan> planList;
    String today;
    Button btAdd;
    TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView=findViewById(R.id.calendarView);
        btAdd=findViewById(R.id.addPlan);
        planList=new ArrayList<Plan>();
        txtTime=findViewById(R.id.txtPlanTime);

        listView=(ListView)findViewById(R.id.planListView);

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(calendarView.getDate());
        today=formatter.format(date);

        System.out.println(today);

        listAdapter=new PlanListAdapter(today);
        listView.setAdapter(listAdapter);

        for(int i = 0; i< PlanDB.getInstance().getPlanList().size(); i++){
            System.out.println(PlanDB.getInstance().getPlanList().get(i));
        }


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth ){
                String day;
                day=year+"-"+(month+1)+"-"+dayOfMonth;
                today=day;

                listAdapter=new PlanListAdapter(today);
                listView.setAdapter(listAdapter);
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddPlan.class);
                intent.putExtra("date",today);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            if (resultCode == RESULT_OK) {
                int hour = data.getIntExtra("hour",0);
                int minute = data.getIntExtra("minute",0);
                listAdapter=new PlanListAdapter(today);
                listView.setAdapter(listAdapter);
            }
        }
    }
}