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
import android.widget.Toast;
/**
 *登录界面
 * 总结以后要学习改正的问题：
 * 1.由于基础知识薄弱，用死数据进行数据的转换，重复的代码太多
 * 2.没有保存机制，app重启数据变回最初登录的数据
 * 3.专用方法了解的太少，还需多加学习、练习
 */

public class LoginActivity extends AppCompatActivity {
    public final static int REQCODE_EDIT = 100;
    private EditText editName;//用户名
    private EditText editPassword;//密码
    private Button buttonCommit;//设置按钮属性
    //配置连接界面方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //获取控件的text值
        editName=findViewById(R.id.editName);

        editPassword=findViewById(R.id.editPassword);

        buttonCommit=(Button) findViewById(R.id.buttonCommit);

        //设置点击响应
        buttonCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户名使用获取值还是默认值判定
            if (getIntent().getStringExtra("en")==null){
                //密码使用获取值还是默认值判定
                if(!"zlk".equals(editName.getText().toString())){
                    Toast toast=Toast.makeText(getApplicationContext(), "用户名错误，请重新登录！", Toast.LENGTH_SHORT);
                    toast.show();
                }else if (getIntent().getStringExtra("ep")==null){
                    if(!"123456".equals(editPassword.getText().toString())){

                        Toast toast=Toast.makeText(getApplicationContext(), "密码错误，请重新输入！", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        String pn = getIntent().getStringExtra("pn");
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("en",editName.getText().toString());
                        intent.putExtra("ep",editPassword.getText().toString());
                        intent.putExtra("pn",pn);
                        startActivityForResult(intent, REQCODE_EDIT);
                        finish();
                    }
                }else{
                    if(!getIntent().getStringExtra("ep").equals(editPassword.getText().toString())){

                        Toast toast=Toast.makeText(getApplicationContext(), "密码错误，请重新输入！", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        String pn = getIntent().getStringExtra("pn");
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("en",editName.getText().toString());
                        intent.putExtra("ep",editPassword.getText().toString());
                        intent.putExtra("pn",pn);
                        startActivityForResult(intent, REQCODE_EDIT);
                        finish();
                    }
                }
            }else{
                if(!getIntent().getStringExtra("en").equals(editName.getText().toString())){
                Toast toast=Toast.makeText(getApplicationContext(), "用户名错误，请重新登录！", Toast.LENGTH_SHORT);
                toast.show();
            }else if (getIntent().getStringExtra("ep")==null){
                if(!"123456".equals(editPassword.getText().toString())){

                    Toast toast=Toast.makeText(getApplicationContext(), "密码错误，请重新输入！", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    String pn = getIntent().getStringExtra("pn");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("en",editName.getText().toString());
                    intent.putExtra("ep",editPassword.getText().toString());
                    intent.putExtra("pn",pn);
                    startActivityForResult(intent, REQCODE_EDIT);
                    finish();
                }
            }else{
                if(!getIntent().getStringExtra("ep").equals(editPassword.getText().toString())){

                    Toast toast=Toast.makeText(getApplicationContext(), "密码错误，请重新输入！", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    String pn = getIntent().getStringExtra("pn");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("en",editName.getText().toString());
                    intent.putExtra("ep",editPassword.getText().toString());
                    intent.putExtra("pn",pn);
                    startActivityForResult(intent, REQCODE_EDIT);
                    finish();
                }
            }
            }


            }
        });
    }
}