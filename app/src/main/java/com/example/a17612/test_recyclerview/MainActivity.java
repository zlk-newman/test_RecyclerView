package com.example.a17612.test_recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 *主页界面
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public final static int REQCODE_EDIT = 100;

    private List<Object> listData = new ArrayList<>();
    //配置连接界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        //登陆成功界面
        String en = getIntent().getStringExtra("en");
        Toast toast=Toast.makeText(getApplicationContext(), "您好："+en+",欢迎光临！", Toast.LENGTH_LONG);
        toast.show();

        recyclerView = findViewById(R.id.listView);
        //设置适配器
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listData.add(new String("Group1"));
        listData.add(new MailInfo("王随便","15633589854",1));
        listData.add(new MailInfo("迈克尔杰克孙","18758699852",5));
        listData.add(new String("Group2"));
        listData.add(new MailInfo("xxxxx","17896585242",10));
        listData.add(new MailInfo("巩林那","18697586429",11));
    }
    //调用menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //配置菜单响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {//修改密码

                    String en = getIntent().getStringExtra("en");
                    String ep = getIntent().getStringExtra("ep");
                    String pn = getIntent().getStringExtra("pn");
                    Intent intent = new Intent(MainActivity.this, UpdatePwdActivity.class);
                    intent.putExtra("en",en);
                    intent.putExtra("ep",ep);
                    intent.putExtra("pn",pn);
                    startActivity(intent);
            return true;
        }else if(id == R.id.action_add_item){//添加新联系人
            //向列表中添加一条
            listData.add(1,new MailInfo("",
                    "",3));
            //通知RecyclerView，重新加载数据
            recyclerView.getAdapter().notifyItemInserted(1);
            return true;
        }else if(id == R.id.Item){//查看和修改个人信息
            String en = getIntent().getStringExtra("en");
            String ep = getIntent().getStringExtra("ep");
            String pn = getIntent().getStringExtra("pn");
            Intent intent = new Intent(MainActivity.this, EditMyActivity.class);
            intent.putExtra("en",en);
            intent.putExtra("ep",ep);
            intent.putExtra("pn",pn);
            startActivity(intent);
            return true;
        }if(id == R.id.Exit){//退出登录
            String en = getIntent().getStringExtra("en");
            String ep = getIntent().getStringExtra("ep");
            String pn = getIntent().getStringExtra("pn");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra("en",en);
            intent.putExtra("ep",ep);
            intent.putExtra("pn",pn);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //设置适配器单条格式
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        if(requestCode == REQCODE_EDIT){
            if(resultCode == RESULT_OK){
                String Name = data.getStringExtra("Name");
                String phoneNum = data.getStringExtra("phoneNum");
                int pos = data.getIntExtra("pos",-1);

                MailInfo mailInfo = (MailInfo) listData.get(pos);
                mailInfo.setName(Name);
                mailInfo.setPhoneNum(phoneNum);
                //更新那一条
                recyclerView.getAdapter().notifyItemChanged(pos);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //设置联系人的类
    class MailInfo {
        private String Name;
        private String phoneNum;
        private int like;

        public MailInfo(String Name, String phoneNum, int like) {
            this.Name = Name;
            this.phoneNum = phoneNum;
            this.like = like;
        }

        public String getName() {
            return Name;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public int getLike() {
            return like;
        }
    }

    //仅为了向Adapter中传范型参数而设立的共同父类，没什么实际作用
    class BaseViewHolder extends RecyclerView.ViewHolder{

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    //设置分组配置
    class GroupViewHolder extends BaseViewHolder{
        TextView textGroup ;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            textGroup = itemView.findViewById(R.id.textGroup);
        }
    }
    //取值和跳转方法
    class MyViewHolder extends BaseViewHolder{
        TextView text;
        TextView textPhoneNum;
        RatingBar ratingBar;
        TextView textDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.textName);
            textPhoneNum = itemView.findViewById(R.id.textPhoneNum);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            textDelete = itemView.findViewById(R.id.textDelete);

            //响应点击一行
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //取得这一行的数据
                    int pos = getAdapterPosition();
                    MailInfo mailInfo = (MailInfo) listData.get(pos);

                    //打开编辑页面
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("Name",mailInfo.getName());
                    intent.putExtra("phoneNum",mailInfo.getPhoneNum());
                    intent.putExtra("pos",pos);
                    startActivityForResult(intent,REQCODE_EDIT);


                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();
                    MailInfo mailInfo = (MailInfo) listData.get(pos);
                    Intent intent1 = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" +mailInfo.getPhoneNum());
                    intent1.setData(data);
                    startActivity(intent1);
                    return false;
                }
            });


            textDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点了X号，需删除这一行
                    //取得所在Item的序号
                    int pos = getAdapterPosition();
                    listData.remove(pos);
                    recyclerView.getAdapter().notifyItemRemoved(pos);
                }
            });
        }
    }
    //配置MyAdapter
    class MyAdapter extends RecyclerView.Adapter<BaseViewHolder>{

        @NonNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            //RecyclerView调用此方法，获取某一条展示数据所用的View
            if(viewType == 0){
                //Group
                View view = getLayoutInflater().inflate(R.layout.list_item_layout2,
                        viewGroup,false);
                return new GroupViewHolder(view);
            }else if(viewType == 1) {
                View view = getLayoutInflater().inflate(R.layout.list_item_layout,
                        viewGroup, false);
                return new MyViewHolder(view);
            }else{
                return null;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull BaseViewHolder myViewHolder, int position) {
            //RecyclerView调用此方法，绑定一条的数据与对应的View
            Object data = listData.get(position);
            if(data instanceof String){
                String str = (String) data;
                //Group
                GroupViewHolder vh = (GroupViewHolder) myViewHolder;
                vh.textGroup.setText(str);
            }else if(data instanceof MailInfo) {
                MailInfo musicInfo = (MailInfo) data;
                MyViewHolder vh = (MyViewHolder) myViewHolder;
                vh.text.setText(musicInfo.getName());
                vh.textPhoneNum.setText(musicInfo.getPhoneNum());
                vh.ratingBar.setRating(musicInfo.getLike());
            }
        }
        //获取表尺寸
        @Override
        public int getItemCount() {
            return listData.size();
        }

        @Override
        public int getItemViewType(int position) {
            //跟据行号，返回不同类型的View
            Object data = listData.get(position);
            if(data instanceof String){
                return 0;
            }else if (data instanceof MailInfo){
                return 1;
            }else {
                return super.getItemViewType(position);
            }
        }
    }
}
