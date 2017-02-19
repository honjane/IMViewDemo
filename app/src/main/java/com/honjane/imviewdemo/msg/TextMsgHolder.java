package com.honjane.imviewdemo.msg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.honjane.imviewdemo.Model.MessageWrapper;
import com.honjane.imviewdemo.R;

/**
 * Created by honjane on 2017/2/7.
 */

public class TextMsgHolder extends BaseMsgHolder {
    public TextView mContentView;

    public TextMsgHolder(Context context) {
        super(context);
    }

    @Override
    protected void initChildView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_message_text, mMessageContainerFl);
        mContentView = (TextView) rootView.findViewById(R.id.msg_content_text);
    }

    @Override
    protected void bindChildView(MessageWrapper wrapper, int position) {

        if (mContentView == null || wrapper == null || wrapper.msg == null || wrapper.msg.content == null) {
            return;
        }

        showTextMessage(wrapper.msg.content, position);
    }


    private void showTextMessage(String content, final int position) {

        mContentView.setText(content);

        mContentView.setOnClickListener(null);

        mContentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mCommClickListener != null) {
                    mCommClickListener.onLongClick(mMessageContainerFl, position);
                }
                return true;
            }
        });

    }
}

