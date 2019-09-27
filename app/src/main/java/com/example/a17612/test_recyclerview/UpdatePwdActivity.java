package com.example.a17612.test_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 *修改密码界面
 *
 */
public class UpdatePwdActivity extends AppCompatActivity {
    private TextView textName;//用户名
    private EditText editOld;//原密码
    private EditText editNew;//新密码
    private EditText editNewTwo;//再次输入新密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
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
        //获取控件输入值
        textName=findViewById(R.id.textName);
        editOld=findViewById(R.id.editOld);
        editNew=findViewById(R.id.editNew);
        editNewTwo=findViewById(R.id.editNewTwo);
        //传到本页面的值
        String name = getIntent().getStringExtra("en");


        textName.setText(name);
        //点击响应
        findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getIntent().getStringExtra("ep").equals(editOld.getText().toString())){
                    Toast toast=Toast.makeText(getApplicationContext(), "密码错误，请重新输入！", Toast.LENGTH_SHORT);
                    toast.show();
                }else if(!editNew.getText().toString().equals(editNewTwo.getText().toString())){
                    Toast toast=Toast.makeText(getApplicationContext(), "两次新密码输入不一致，请重新输入！", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    String pn=getIntent().getStringExtra("pn");
                    Intent intent = new Intent(UpdatePwdActivity.this, LoginActivity.class);
                    intent.putExtra("en",textName.getText().toString());
                    intent.putExtra("ep",editNew.getText().toString());
                    intent.putExtra("pn",pn);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    }
