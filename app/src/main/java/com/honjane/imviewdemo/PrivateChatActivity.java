package com.honjane.imviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.honjane.imviewdemo.Listener.OnExtraListener;
import com.honjane.imviewdemo.Model.MessageWrapper;
import com.honjane.imviewdemo.adapter.PrivateChatAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by honjane on 2017/2/19.
 */

public class PrivateChatActivity extends Activity implements OnExtraListener {


    private ListView mMessageContentLv;
    private PrivateChatAdapter mPrivateChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        initHeader();
        initView();

    }

    private void initHeader() {
        ((TextView) findViewById(R.id.tv_title)).setText("私聊");
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        mMessageContentLv = (ListView) findViewById(R.id.lv_content);
        mPrivateChatAdapter = new PrivateChatAdapter(this);
        mPrivateChatAdapter.setExtraListener(this);
        mPrivateChatAdapter.setData(test());
        mMessageContentLv.setAdapter(mPrivateChatAdapter);
    }

    private List<MessageWrapper> test() {

        List<MessageWrapper> list = new ArrayList<>();

        return list;
    }

    @Override
    public void onExtraClick() {

    }
}
