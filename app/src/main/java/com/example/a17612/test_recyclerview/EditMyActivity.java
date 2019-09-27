package com.example.a17612.test_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
/**
 *查看和修改个人信息界面
 *
 */
public class EditMyActivity extends AppCompatActivity {
    private EditText editName;//登录名
    private EditText editPhoneNum;//手机号


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        editName = findViewById(R.id.editName);
        editPhoneNum = findViewById(R.id.editPhoneNum);

        //取得启动我时传来的数据
        String Name = getIntent().getStringExtra("en");
        String phoneNum=null;

        if(getIntent().getStringExtra("pn")==null){
            phoneNum="17854255407";
        }else{
            phoneNum = getIntent().getStringExtra("pn");
        }

        editName.setText(Name);
        editPhoneNum.setText(phoneNum);
        //点击响应
        findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ep=getIntent().getStringExtra("ep");
                Intent intent = new Intent(EditMyActivity.this,MainActivity.class);
                intent.putExtra("en",editName.getText().toString());
                intent.putExtra("pn",editPhoneNum.getText().toString());
                intent.putExtra("ep",ep);
                startActivity(intent);
                finish();
            }
        });
    }
}
