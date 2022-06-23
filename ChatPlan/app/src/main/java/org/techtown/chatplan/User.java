package org.techtown.chatplan;

import android.widget.ImageView;
import android.widget.TextView;

public class User {

    private int type;
    private int profile;
    private String message;


    public User(int type, int profile, String message) {
        this.type=type;
        this.profile = profile;
        this.message = message;
    }



    public int getProfile() {
        return profile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg){
        this.message=msg;
    }

    public int getType() {
        return type;
    }
}
