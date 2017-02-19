package com.honjane.imviewdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.honjane.imviewdemo.Listener.OnCommClickListener;
import com.honjane.imviewdemo.Listener.OnExtraListener;
import com.honjane.imviewdemo.Model.MessageWrapper;
import com.honjane.imviewdemo.msg.behavior.MsgViewBehavior;
import com.honjane.imviewdemo.msg.behavior.MsgViewProxyImpl;
import com.honjane.imviewdemo.msg.behavior.TypeToHolderBehavior;
import com.honjane.imviewdemo.msg.behavior.WrapperToTypeBehavior;
import com.honjane.imviewdemo.msg.behavior.WrapperToTypeImpl;

import java.util.List;

/**
 * Created by honjane on 2017/2/19.
 */

public class BaseChatAdapter extends BaseAdapter {
    protected OnCommClickListener mMessageClickListener;
    protected MsgViewBehavior mMsgViewBehavior;
    protected WrapperToTypeBehavior mWrapperToTypeBehavior;
    protected Context mContext;
    protected List<MessageWrapper> mMessageWrappers;
    private boolean mChange;
    private OnExtraListener mExtraListener;

    public BaseChatAdapter(Context context) {
        mContext = context;
        mMsgViewBehavior = new MsgViewProxyImpl(mContext);
        mMsgViewBehavior.setCommClickListener(mMessageClickListener);
        mWrapperToTypeBehavior = new WrapperToTypeImpl();
    }


    public void setData(List<MessageWrapper> wrapperList) {
        mMessageWrappers = wrapperList;
    }

    @Override
    public int getCount() {
        if (mMessageWrappers != null && !mMessageWrappers.isEmpty()) {
            return mMessageWrappers.size();
        }
        return 0;
    }

    @Override
    public MessageWrapper getItem(int i) {
        if (i < 0 || i >= mMessageWrappers.size()) {
            return null;
        }
        return mMessageWrappers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return mMsgViewBehavior.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        MessageWrapper wrapper = getItem(position);
        if (wrapper == null) {
            return 0;
        }
        int type = mWrapperToTypeBehavior.getViewTypeByWrapper(wrapper);
        if (type >= 0 && type < getViewTypeCount()) {
            return type;
        }
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        MessageWrapper wrapper = getItem(position);
        if (wrapper == null || wrapper.msg == null) {
            return view;
        }
        if (mChange) {
            mChange = false;
            mMsgViewBehavior.setExtraListener(mExtraListener);
        }

        view = mMsgViewBehavior.getBindView(view, position, wrapper, getItemViewType(position));
        if (view == null) {
            return new View(mContext);
        }
        return view;
    }

    public void setExtraListener(OnExtraListener listener) {
        mExtraListener = listener;
        mChange = true;
    }

    public void registerProxyBehavior(MsgViewBehavior<MessageWrapper, OnExtraListener> behavior) {
        if (behavior != null) {
            mMsgViewBehavior = behavior;
        }
    }

    /**
     * 新加消息view类型注册
     *
     * @param msgViewType
     * @param behavior             处理wrapper与msgViewType对应关系，建议继承WrapperToTypeImpl已实现的逻辑
     * @param typeToHolderBehavior 处理holder与msgViewType对应关系，建议继承HolderToTypeImpl已实现的逻辑
     */
    public void registerBehavior(int msgViewType, WrapperToTypeBehavior<MessageWrapper> behavior, TypeToHolderBehavior typeToHolderBehavior) {
        if (behavior != null) {
            mWrapperToTypeBehavior = behavior;
        }
        if (typeToHolderBehavior != null && mMsgViewBehavior != null) {
            mMsgViewBehavior.registerHolderBehavior(msgViewType, typeToHolderBehavior);
        }
    }
}
