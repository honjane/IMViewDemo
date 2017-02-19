package com.honjane.imviewdemo.msg;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.honjane.imviewdemo.Model.ChatMessage;
import com.honjane.imviewdemo.Model.MessageWrapper;
import com.honjane.imviewdemo.R;


/**
 * 图片消息类型holder
 * Created by honjane on 2017/2/7.
 */

public class PicMsgHolder extends BaseMsgHolder {
    private ImageView mImageMsgIv;

    public PicMsgHolder(Context context) {
        super(context);
    }

    @Override
    protected void initChildView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_message_pic, mMessageContainerFl);
        mImageMsgIv = (ImageView) rootView.findViewById(R.id.msg_content_image);
    }

    @Override
    protected void bindChildView(MessageWrapper wrapper, final int position) {

        if (wrapper == null || wrapper.msg == null || mImageMsgIv == null) {
            return;
        }
        final ChatMessage message = wrapper.msg;

        mImageMsgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击了，图片_" + position, Toast.LENGTH_SHORT).show();

            }
        });

        mImageMsgIv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mCommClickListener != null) {
                    mCommClickListener.onLongClick(mMessageContainerFl, position);
                }
                return true;
            }
        });

        Uri uri = Uri.parse("file://" + message.localPath);
        mImageMsgIv.setImageURI(uri);

    }


}
