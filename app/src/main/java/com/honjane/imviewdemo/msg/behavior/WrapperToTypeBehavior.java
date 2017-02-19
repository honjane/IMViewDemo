package com.honjane.imviewdemo.msg.behavior;

/**
 * 消息类型view行为接口
 * Created by tanghongjian on 2017/2/9.
 */

public interface WrapperToTypeBehavior<T> {

    /**
     *
     * @param wrapper 数据包装类
     * @return int
     */
    int getViewTypeByWrapper(T wrapper);

}
