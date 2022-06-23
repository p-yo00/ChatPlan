package org.techtown.chatplan;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import static android.content.Context.MODE_PRIVATE;

import org.techtown.chatplan.Command;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Chatting {
    final int EMPTY = 0;
    final int CALENDAR = 1;
    final int WEEK = 2;
    final int DDAY = 3;
    final int ERROR = 4;

    private static Chatting instance;
    private Command command;

    private Chatting(){
        command = command.getInstance();
    }

    public static Chatting getInstance(){
        if(instance==null){
            instance=new Chatting();
        }
        return instance;
    }

    public int chat(String text){
        int count=0;
        int commandTemp=0;
        if(isContains(text, command.getC_Calendar())){
            count++;
            commandTemp=CALENDAR;
        }
        if(isContains(text,command.getC_Week())){
            count++;
            commandTemp=WEEK;
        }
        if(isContains(text,command.getC_Dday())){
            count++;
            commandTemp=DDAY;
        }
        if(count>1)
            return ERROR;
        return commandTemp;
    }

    private boolean isContains(String text, ArrayList<String> list){
        for(int i=0; i<list.size();i++){
            if(text.contains(list.get(i)))
                return true;
        }
        return false;
    }


}
