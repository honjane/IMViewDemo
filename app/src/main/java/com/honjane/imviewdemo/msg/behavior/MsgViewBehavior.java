package com.honjane.imviewdemo.msg.behavior;

import android.view.View;

import com.honjane.imviewdemo.Listener.OnCommClickListener;


/**
 * 消息类型view行为接口
 */

public interface MsgViewBehavior<T, D> {

    /**
     * @param convertView adapter的convertView
     * @param position    位置
     * @param wrapper     数据包装类
     * @param msgViewType view类型
     * @return
     */
    View getBindView(View convertView, int position, T wrapper, int msgViewType);

    /**
     *
     * @param extraListener 除开CommClickListener之外的Listener，建议一个类只注册一个统一D类Listener
     *
     */
    void setExtraListener(D extraListener);

    /**
     *
     * @param commClickListener  消息点击listener
     */
    void setCommClickListener(OnCommClickListener commClickListener);

    void registerHolderBehavior(int msgViewType, TypeToHolderBehavior holder);

    int getViewTypeCount();
}
