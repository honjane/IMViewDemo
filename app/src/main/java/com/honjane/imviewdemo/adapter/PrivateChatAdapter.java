package com.honjane.imviewdemo.adapter;

import android.content.Context;

import com.honjane.imviewdemo.Model.Constant;
import com.honjane.imviewdemo.demo.MyTypeToHolderImpl;
import com.honjane.imviewdemo.demo.MyWrapperToTypeImpl;

/**
 * Created by honjane on 2017/2/19.
 */

public class PrivateChatAdapter extends BaseChatAdapter {
    public PrivateChatAdapter(Context context) {
        super(context);
        //动态的添加card类型消息
        registerBehavior(Constant.ViewType.CARD, new MyWrapperToTypeImpl(), new MyTypeToHolderImpl(context));
    }
}
