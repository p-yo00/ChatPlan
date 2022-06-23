package org.techtown.chatplan;
import java.util.Calendar;

public class Plan {
    private Calendar Caldate;
    private String date;
    private String title;
    private String time;
    private String memo;

    public Plan(Calendar Caldate, String date, String title, String time, String memo) {
        this.Caldate=Caldate;
        this.date=date;
        this.title = title;
        this.time = time;
        this.memo = memo;
    }

    public String getDate2() {return date;}

    public Calendar getCalDate() {
        return Caldate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
