package org.techtown.chatplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanListAdapter extends BaseAdapter {

    PlanDB db = PlanDB.getInstance();
    ArrayList<Plan> planList;
    CalendarView calendarView;

    public PlanListAdapter(String today){
        if(planList==null){
            planList=new ArrayList<Plan>();
        }
        for(int i=0; i<db.getPlanList().size();i++){
            if(db.getPlanList().get(i).getDate2().equals(today)){
                planList.add(db.getPlanList().get(i));
            }
        }
    }

    public void setList(ArrayList<Plan> list){
        planList=list;
    }

    @Override
    public int getCount() {
        if(planList!=null)
            return planList.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int i) {
        return planList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        if(convertView==null){
            convertView = inflater.inflate(R.layout.plan_list,parent, false);

            calendarView = convertView.findViewById(R.id.calendarView);
            Plan plan = planList.get(i);
            TextView txtTitle = convertView.findViewById(R.id.txtTitle);
            TextView txtTime = convertView.findViewById(R.id.txtPlanTime);

            txtTitle.setText(plan.getTitle());
            txtTime.setText(plan.getTime());
        }
        return convertView;
    }
}
