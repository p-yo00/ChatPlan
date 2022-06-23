package org.techtown.chatplan;

import java.util.ArrayList;
import java.util.Calendar;

public class PlanDB {
    private static PlanDB db=null;
    private static ArrayList<Plan> planList;

    private PlanDB(){

    }

    public static PlanDB getInstance(){
        if(planList==null){
            planList=new ArrayList<Plan>();
        }
        if(db==null){
            db=new PlanDB();
        }
        return db;
    }

    public void AddPlan(Calendar Caldate, String date, String title, String time, String memo){
        Plan plan = new Plan(Caldate,date,title,time,memo);
        planList.add(plan);
    }

    public ArrayList<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(ArrayList<Plan> planList) {
        this.planList = planList;
    }
}
