package com.honjane.imviewdemo.demo;


import com.honjane.imviewdemo.Model.Constant;
import com.honjane.imviewdemo.Model.MessageWrapper;
import com.honjane.imviewdemo.msg.behavior.WrapperToTypeImpl;

/**
 * Created by honjane on 2017/2/19.
 * 继承WrapperToTypeImpl，实现新加viewType与数据关系绑定
 */

public class MyWrapperToTypeImpl extends WrapperToTypeImpl {

    @Override
    public int getViewTypeByWrapper(MessageWrapper wrapper) {
        int viewType = 0;
        if (wrapper == null || wrapper.msg == null) {
            return viewType;
        }
        switch (wrapper.msg.msgType) {
            case Constant.MessageType.PRODUCT:
                return Constant.ViewType.CARD;
            //其他各种类型
            default:
                break;
        }
        return super.getViewTypeByWrapper(wrapper);
    }

}
