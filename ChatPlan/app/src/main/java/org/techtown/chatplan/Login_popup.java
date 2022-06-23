package org.techtown.chatplan;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_popup extends Activity {

    Button btLogin;
    EditText etID;
    EditText etPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_popup);

        btLogin=findViewById(R.id.btLogin);
        etID=findViewById(R.id.etID);
        etPasswd=findViewById(R.id.etPasswd);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check=true;
                if(TextUtils.isEmpty(etID.getText())){
                    Toast.makeText(getApplicationContext(),"닉네임을 입력해야합니다.",Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(TextUtils.isEmpty(etPasswd.getText())){
                    Toast.makeText(getApplicationContext(),"비밀번호를 입력해야합니다.",Toast.LENGTH_SHORT).show();
                    check=false;
                }
                if(check){
                    AccountDB.db.setID(etID.getText().toString());
                    AccountDB.db.setPasswd(etPasswd.getText().toString());
                    SharedPreferences prefInfo= getSharedPreferences("INFO", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor= prefInfo.edit();

                    editor.putString("ID",etID.getText().toString());
                    editor.putString("Passwd",etPasswd.getText().toString());
                    editor.commit();

                    Intent intent=getIntent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }

}