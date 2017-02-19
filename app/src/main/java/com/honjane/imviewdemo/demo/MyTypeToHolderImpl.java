package com.honjane.imviewdemo.demo;

import android.content.Context;

import com.honjane.imviewdemo.Model.Constant;
import com.honjane.imviewdemo.msg.BaseMsgHolder;
import com.honjane.imviewdemo.msg.CardMsgHolder;
import com.honjane.imviewdemo.msg.behavior.TypeToHolderImpl;

/**
 * Created by honjane on 2017/2/19.
 *
 * 继承TypeToHolderImpl 实现新加样式类型type与holder的绑定
 */

public class MyTypeToHolderImpl extends TypeToHolderImpl {
    public MyTypeToHolderImpl(Context context) {
        super(context);
    }


    @Override
    public BaseMsgHolder getViewHolder(int msgViewType) {
        switch (msgViewType) {
            case Constant.ViewType.CARD:
                return new CardMsgHolder(mContext);
            //其他各种类型
            default:
                break;
        }
        return super.getViewHolder(msgViewType);
    }

}
