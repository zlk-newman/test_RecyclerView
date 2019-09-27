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
 *查看和修改联系人界面
 */
public class EditActivity extends AppCompatActivity {
    private EditText editName;//联系人名
    private EditText editPhoneNum;//联系人手机
    private int pos;//设置星级

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
        String Name = getIntent().getStringExtra("Name");
        String phoneNum = getIntent().getStringExtra("phoneNum");
        pos = getIntent().getIntExtra("pos",-1);

        editName.setText(Name);
        editPhoneNum.setText(phoneNum);
        //点击响应
        findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Name",editName.getText().toString());
                intent.putExtra("phoneNum",editPhoneNum.getText().toString());
                intent.putExtra("pos",pos);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}
