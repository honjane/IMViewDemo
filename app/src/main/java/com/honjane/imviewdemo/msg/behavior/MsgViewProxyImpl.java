package com.honjane.imviewdemo.msg.behavior;

import android.content.Context;
import android.view.View;

import com.honjane.imviewdemo.Listener.OnCommClickListener;
import com.honjane.imviewdemo.Listener.OnExtraListener;
import com.honjane.imviewdemo.Model.Constant;
import com.honjane.imviewdemo.Model.MessageWrapper;
import com.honjane.imviewdemo.R;
import com.honjane.imviewdemo.msg.BaseMsgHolder;
import java.util.ArrayList;


/**
 * 聊天消息类型View代理类
 * Created by honjane on 2017/2/6.
 */
public class MsgViewProxyImpl implements MsgViewBehavior<MessageWrapper, OnExtraListener> {
    protected OnExtraListener mExtraListener;
    protected OnCommClickListener mCommClickListener;
    private ArrayList<Integer> mViewTypes;
    private TypeToHolderBehavior mTypeToHolderBehavior;

    public MsgViewProxyImpl(Context context) {
        mViewTypes = new ArrayList<>();
        mTypeToHolderBehavior = new TypeToHolderImpl(context);
        initViewType();
    }

    private void initViewType() {
        mViewTypes.add(Constant.ViewType.TEXT);
        mViewTypes.add(Constant.ViewType.PIC);
    }


    public View getBindView(View convertView, int position, MessageWrapper wrapper, int viewType) {
        if (wrapper == null || wrapper.msg == null) {
            return convertView;
        }
        BaseMsgHolder holder;
        if (shouldCache(convertView, viewType, wrapper.isSend) && convertView.getTag() instanceof BaseMsgHolder) {
            holder = (BaseMsgHolder) convertView.getTag();
        } else {
            holder = mTypeToHolderBehavior.getViewHolder(viewType);
            if (holder == null) {
                return convertView;
            }
            holder.setListener(mExtraListener, mCommClickListener);
            convertView = holder.initView(wrapper.isSend);
            convertView.setTag(holder);
        }
        holder.bindView(wrapper, position, wrapper.isSend);
        //解决复用错位
        convertView.setTag(R.id.selfsend, wrapper.isSend);
        convertView.setTag(R.id.msgviewtype, viewType);
        return convertView;
    }

    /**
     * 判断是否使用缓存的holder
     *
     * @param convertView 缓存view
     * @param msgViewType view类型
     * @param selfSend    是否自己发送
     * @return boolean
     */


    private boolean shouldCache(View convertView, int msgViewType, boolean selfSend) {
        if (convertView == null) {
            return false;
        }
        Object typeTag = convertView.getTag(R.id.msgviewtype);
        Object selfSendTag = convertView.getTag(R.id.selfsend);

        return typeTag instanceof Integer && msgViewType == (Integer) typeTag
                && selfSendTag instanceof Boolean && selfSend == (Boolean) selfSendTag;
    }


    /**
     * 注册HolderBehavior
     * <p>
     * holderBehavior 需要继承BaseAuthorMsgHolder或者BaseMsgHolder
     *
     * @param msgViewType 消息view类型 { ChatConstant.MsgViewType }
     */
    public void registerHolderBehavior(int msgViewType, TypeToHolderBehavior typeToHolderBehavior) {
        if (!mViewTypes.contains(msgViewType)) {
            mViewTypes.add(msgViewType);
        }

        if (typeToHolderBehavior != null) {
            mTypeToHolderBehavior = typeToHolderBehavior;
        }
    }

    public void setExtraListener(OnExtraListener extraListener) {
        mExtraListener = extraListener;
    }

    @Override
    public void setCommClickListener(OnCommClickListener listener) {
        mCommClickListener = listener;
    }



    @Override
    public int getViewTypeCount() {
        if (mViewTypes == null) {
            return 0;
        }
        return mViewTypes.size();
    }


}
