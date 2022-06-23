package org.techtown.chatplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DdayAdapter extends BaseAdapter {

    ArrayList<Plan> planList;

    public DdayAdapter(ArrayList<Plan> planList){
        this.planList=planList;
    }

    @Override
    public int getCount() {
        return planList.size();
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

        convertView = inflater.inflate(R.layout.d_day,parent, false);

        TextView txtTitle = convertView.findViewById(R.id.txtDdayTitle);
        TextView txtdDay = convertView.findViewById(R.id.txtdDay);
        TextView txtDate = convertView.findViewById(R.id.txtdDayDate);

        Plan plan = planList.get(i);

        Calendar dDayCal = plan.getCalDate();
        Calendar todayCal = Calendar.getInstance();
        long today = todayCal.getTimeInMillis()/(24*60*60*1000);
        long dDay = dDayCal.getTimeInMillis()/(24*60*60*1000);

        txtTitle.setText(plan.getTitle());
        txtdDay.setText("D-"+(dDay-today+1));

        txtDate.setText(plan.getDate2());

        return convertView;
    }

}
