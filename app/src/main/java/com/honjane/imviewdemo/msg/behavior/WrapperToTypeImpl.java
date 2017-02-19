package com.honjane.imviewdemo.msg.behavior;

import com.honjane.imviewdemo.Model.Constant;
import com.honjane.imviewdemo.Model.MessageWrapper;


/**
 * 处理viewType与数据MessageWrapper的对应关系
 * <p>
 * Created by honjane on 2017/2/12.
 */

public class WrapperToTypeImpl implements WrapperToTypeBehavior<MessageWrapper> {

    @Override
    public int getViewTypeByWrapper(MessageWrapper wrapper) {
        int viewType = 0;
        if (wrapper == null || wrapper.msg == null) {
            return viewType;
        }

        switch (wrapper.msg.msgType) {
            case Constant.MessageType.TEXT:
                viewType = Constant.ViewType.TEXT;
                break;
            case Constant.MessageType.PIC:
                viewType = Constant.ViewType.PIC;
                break;

            default:
                break;
        }
        return viewType;
    }


}
