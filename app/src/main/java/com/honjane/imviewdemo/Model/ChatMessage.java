package com.honjane.imviewdemo.Model;

/**
 * Created by honjane on 2017/2/15.
 */
public class ChatMessage {

    public long msgId;
    public int msgType;
    public long sendTime;
    public long groupId;
    public int duration;
    public int groupType;
    public String msgKey;
    public String content;
    public String senderPic;
    public String senderNickName;
    public String senderTourStatus;
    public String localPath;

    public CardMessageInfo cardInfo;
}
