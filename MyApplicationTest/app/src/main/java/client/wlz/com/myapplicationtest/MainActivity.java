package client.wlz.com.myapplicationtest;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<WXMessage> data = new ArrayList<WXMessage>();
    private SwipeListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();
    }

    private void initData() {

        for(int i=0;i<50;i++){
            WXMessage msg = null;
            if(i%3==0){
                msg = new WXMessage("腾讯新闻", "人民日报刊文：习近平对评价毛泽东提6个重要观点", "早上8:44");
                msg.setIcon_id(R.drawable.qq_icon);
            }else if(i%3==1){
                msg = new WXMessage("订阅号", "CSDN：2013年国内最具技术影响力公司","早上8:49");
                msg.setIcon_id(R.drawable.wechat_icon);
            }else{
                msg = new WXMessage("微博阅读", "美女演各款妹子跟男朋友打电话","昨天晚上");
                msg.setIcon_id(R.drawable.qq_icon);
            }

            data.add(msg);
        }
    }

    /**
     * 初始化界面
     */
    private void initView() {

        mListView = (SwipeListView)findViewById(R.id.listview);
        final SwipeAdapter mAdapter = new SwipeAdapter(this,data,mListView.getRightViewWidth());

        mAdapter.setOnRightItemClickListener(new SwipeAdapter.onRightItemClickListener() {

            @Override
            public void onRightItemClick(View v, int position) {
                mListView.deleteItem(mListView.getChildAt(position));
                data.remove(position);
                mAdapter.notifyDataSetChanged();
                CustomToast.showToast(MainActivity.this,  "删除第  " + (position+1)+" 对话记录", 1000);
            }
        });

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomToast.showToast(MainActivity.this,  "item onclick " + position, 1000);
            }
        });
    }
}
