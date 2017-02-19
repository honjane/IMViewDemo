package com.honjane.imviewdemo.msg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.honjane.imviewdemo.Listener.OnCommClickListener;
import com.honjane.imviewdemo.Listener.OnExtraListener;
import com.honjane.imviewdemo.Model.ChatMessage;
import com.honjane.imviewdemo.Model.MessageWrapper;
import com.honjane.imviewdemo.R;


/**
 * 消息类型holder基类 (有用头像基类)
 * <p>
 * Created by honjane on 2017/2/7.
 */

public abstract class BaseMsgHolder {

    protected Context mContext;
    protected TextView mNickNameTv;
    protected ImageView mAvatarIv;
    protected FrameLayout mMessageContainerFl;
    protected OnExtraListener mExtraListener;
    protected OnCommClickListener mCommClickListener;
    private boolean mSelfSend;

    public BaseMsgHolder(Context context) {
        mContext = context;
    }

    public void setListener(OnExtraListener listener, OnCommClickListener commClickListener) {
        mExtraListener = listener;
        mCommClickListener = commClickListener;
    }


    public View initView(boolean selfSend) {
        mSelfSend = selfSend;
        View view;
        if (mSelfSend) {
            view = initSendView();
        } else {
            view = initRecView();
        }

        initChildView();
        return view;
    }

    protected abstract void initChildView();

    private View initSendView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_msg_send, null);
        mMessageContainerFl = (FrameLayout) rootView.findViewById(R.id.fl_send_msg_container);
        initCommView(rootView);
        return rootView;
    }

    private View initRecView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_msg_recv, null);
        mMessageContainerFl = (FrameLayout) rootView.findViewById(R.id.fl_recv_msg_container);
        initCommView(rootView);
        return rootView;
    }

    private void initCommView(View rootView) {
        mNickNameTv = (TextView) rootView.findViewById(R.id.nick_name);
        mAvatarIv = (ImageView) rootView.findViewById(R.id.avatar);
    }


    public void bindView(MessageWrapper wrapper, int position, boolean isSend) {
        if (wrapper == null || wrapper.msg == null || mMessageContainerFl == null) {
            return;
        }
        ChatMessage message = wrapper.msg;
        mNickNameTv.setText(message.senderNickName);
        //没有使用远程图片message.senderPic
        //mAvatarIv.setImageUrl(message.senderPic);
        if (isSend) {
            bindSendView(message, wrapper.sendStatus, position);
        } else {
            bindRecView(message, position);
        }

        bindChildView(wrapper, position);
    }

    private void bindRecView(ChatMessage message, int position) {

//        mMessageContainerFl.setBackgroundResource(R.drawable.icon_bg_msg_recv);
        mNickNameTv.setText(mContext.getString(R.string.auctar_name));
        mAvatarIv.setImageResource(R.mipmap.icon_avatar);

        bindCommView(message, position);
    }

    private void bindSendView(ChatMessage message, final int sendStatus, final int position) {

//        mMessageContainerFl.setBackgroundResource(R.drawable.icon_bg_msg_send);

        bindCommView(message, position);
    }

    private void bindCommView(final ChatMessage message, final int position) {
        mAvatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCommClickListener != null) {
                    mCommClickListener.onAvatarClick(position);
                }
            }
        });

        mAvatarIv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mCommClickListener != null) {
                    mCommClickListener.onAvatarLongClick(position, message.senderNickName);
                }
                return true;
            }

        });
    }

    protected abstract void bindChildView(MessageWrapper wrapper, int position);


}
