package org.techtown.chatplan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Dday extends AppCompatActivity {

    private ListView listView;
    private DdayAdapter listAdapter;
    private ArrayList<Plan> planList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dday);

        listView = findViewById(R.id.dDayListView);

        PlanDB planDB = PlanDB.getInstance();
        listAdapter=new DdayAdapter(planDB.getPlanList());
        listView.setAdapter(listAdapter);

    }
}