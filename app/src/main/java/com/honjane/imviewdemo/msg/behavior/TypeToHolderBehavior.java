package com.honjane.imviewdemo.msg.behavior;


import com.honjane.imviewdemo.msg.BaseMsgHolder;

/**
 * Created by honjane on 2017/2/13.
 */

public interface TypeToHolderBehavior {

    BaseMsgHolder getViewHolder(int msgViewType);
}
