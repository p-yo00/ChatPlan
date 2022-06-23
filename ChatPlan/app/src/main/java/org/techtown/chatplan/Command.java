package org.techtown.chatplan;


import java.util.ArrayList;

public class Command {
    final int CALENDAR = 1;
    final int WEEK = 2;
    final int DDAY = 3;

    private static Command instance;
    private static ArrayList<String> C_Calendar;
    private static ArrayList<String> C_Week;
    private static ArrayList<String> C_Dday;

    private Command(){
        C_Calendar = new ArrayList<String>();
        C_Week = new ArrayList<String>();
        C_Dday = new ArrayList<String>();

        C_Calendar.add("캘린더");
        C_Calendar.add("달력");
        C_Calendar.add("Cal");
        C_Week.add("주간");
        C_Week.add("week");
        C_Dday.add("dday");
        C_Dday.add("디데이");

    }

    public static Command getInstance(){
        if(instance==null){
            instance=new Command();
        }
        return instance;
    }

    public void addCommand(int option, String command){
        switch(option){
            case CALENDAR:
                C_Calendar.add(command);
                break;
            case WEEK:
                C_Week.add(command);
                break;
            case DDAY:
                C_Dday.add(command);
                break;
        }
    }

    public ArrayList<String> getC_Calendar() {
        return C_Calendar;
    }

    public ArrayList<String> getC_Week() {
        return C_Week;
    }

    public ArrayList<String> getC_Dday() {
        return C_Dday;
    }
}
