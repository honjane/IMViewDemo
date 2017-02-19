package com.honjane.imviewdemo.msg.behavior;

import android.content.Context;

import com.honjane.imviewdemo.Model.Constant;
import com.honjane.imviewdemo.msg.BaseMsgHolder;
import com.honjane.imviewdemo.msg.PicMsgHolder;
import com.honjane.imviewdemo.msg.TextMsgHolder;

/**
 * 处理viewHolder与viewType的对应关系
 * <p>
 * Created by honjane on 2017/2/13.
 */

public class TypeToHolderImpl implements TypeToHolderBehavior {
    protected Context mContext;

    public TypeToHolderImpl(Context context) {
        mContext = context;
    }

    @Override
    public BaseMsgHolder getViewHolder(int msgViewType) {
        BaseMsgHolder holder = null;
        switch (msgViewType) {
            case Constant.ViewType.TEXT:
                holder = new TextMsgHolder(mContext);
                break;
            case Constant.ViewType.PIC:
                holder = new PicMsgHolder(mContext);
                break;
            //其他各种类型
            default:
                break;
        }
        return holder;
    }
}
