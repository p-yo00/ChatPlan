package org.techtown.chatplan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int EMPTY = 0;
    final int CALENDAR = 1;
    final int DDAY = 3;
    final int ERROR = 4;

    private ListView listView;
    private static ArrayList<User> chatlist=new ArrayList<>();
    private MessageAdapter listAdapter=new MessageAdapter(chatlist);

    private Button btSend;

    private SharedPreferences pref;
    private ArrayList<User> checkUser;

    User user;
    User chatbot;
    boolean check=false;
    String tempData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btSend=findViewById(R.id.send);
        checkUser=new ArrayList<>();

        listView=(ListView)findViewById(R.id.listView);
        listAdapter=new MessageAdapter(chatlist);
        AccountDB.db.setLogin(false);

        Intent chatIntent = new Intent(getApplicationContext(), AddPlan.class);
        String text = chatIntent.getStringExtra("msg");
        if(text!=null){
            chatlist.add(new User(1,R.drawable.bot,text));
        }


        listView.setAdapter(listAdapter);

        pref = getSharedPreferences("First", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        boolean first = pref.getBoolean("First", false);
        if(first==false) {
            chatlist.add(new User(0,R.drawable.bot,"이름과 비밀번호를 정하세요!"));

            Intent intent = new Intent(this, Login_popup.class);
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() { public void run() {
                startActivityForResult(intent,1);

            } }, 3000);

            editor.putBoolean("First", true);
            first=true;
            editor.commit();
        }

        User temp=new User(0, R.drawable.bot, "비밀번호를 입력해주세요");
        chatlist.add(temp);
        listAdapter.notifyDataSetChanged();


        SharedPreferences prefInfo= getSharedPreferences("INFO", Activity.MODE_PRIVATE);
        AccountDB.db.setID(prefInfo.getString("ID",""));
        AccountDB.db.setPasswd(prefInfo.getString("Passwd",""));

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText message=findViewById(R.id.inputMessage);
                String msg=message.getText().toString();
                if(msg.equals("")){
                    Log.d("빈 문자열","");
                }

                else if(!AccountDB.db.isLogin()){
                    User temp=new User(1,R.drawable.bot,msg);
                    chatlist.add(temp);
                    Log.d("값:",msg+"  "+ AccountDB.db.getPasswd());
                    if(msg.equals(AccountDB.db.getPasswd())){
                        chatlist.add(new User(0,R.drawable.bot,"로그인 성공"));
                        AccountDB.db.setLogin(true);
                    }
                    else{
                        chatlist.add(new User(0,R.drawable.bot,"로그인 실패"));
                    }
                    listAdapter.notifyDataSetChanged();
                }

                else {
                    chatlist.add(new User(1, R.drawable.bot, msg));

                    tempData = msg;
                    check = true;
                    Chatting chatSystem = Chatting.getInstance();
                    int option = chatSystem.chat(msg);
                    Intent intent;

                    switch (option) {
                        case CALENDAR:
                            intent = new Intent(view.getContext(), CalendarActivity.class);
                            startActivity(intent);
                            break;
                        case DDAY:
                            intent = new Intent(view.getContext(), Dday.class);
                            startActivity(intent);
                            break;
                        case EMPTY:
                            chatlist.add(new User(0, R.drawable.bot, "알 수 없는 말이에요"));
                            break;
                        case ERROR:
                            chatlist.add(new User(0, R.drawable.bot, "헷갈려요"));
                            break;
                        default:
                            chatlist.add(new User(0, R.drawable.bot, "네"));
                            break;
                    }
                    listAdapter.notifyDataSetChanged();

                }
                message.setText(null);
            }
        });

    }

    public void addList(String msg){
        if(msg!=null) {
            User temp=new User(0, R.drawable.bot, msg);
            chatlist.add(temp);
            for(int i=0; i<chatlist.size();i++){
                System.out.println(chatlist.get(i).getMessage());
            }
        }
    }

}