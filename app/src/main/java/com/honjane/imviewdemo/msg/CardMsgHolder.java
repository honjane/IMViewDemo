package com.honjane.imviewdemo.msg;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.honjane.imviewdemo.Model.CardMessageInfo;
import com.honjane.imviewdemo.Model.ChatMessage;
import com.honjane.imviewdemo.Model.Constant;
import com.honjane.imviewdemo.Model.MessageWrapper;
import com.honjane.imviewdemo.R;

/**
 * 卡片消息类型holder
 * Created by honjane on 2017/2/7.
 */

public class CardMsgHolder extends BaseMsgHolder {

    private View mNormalLayoutRl;
    private View mTitleLayoutLl;
    private TextView mCardCategoryTv;
    private TextView mCardIdTv;
    private TextView mCardDescTv;
    private TextView mToDetailTv;
    private ImageView mCardIconTiv;

    public CardMsgHolder(Context context) {
        super(context);
    }

    @Override
    protected void initChildView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_message_card, mMessageContainerFl);
        mNormalLayoutRl = rootView.findViewById(R.id.rl_normal);
        mTitleLayoutLl = rootView.findViewById(R.id.ll_title);
        mCardCategoryTv = (TextView) rootView.findViewById(R.id.tv_card_category);
        mCardIdTv = (TextView) rootView.findViewById(R.id.tv_card_id);
        mCardIconTiv = (ImageView) rootView.findViewById(R.id.im_card_icon);
        mCardDescTv = (TextView) rootView.findViewById(R.id.tv_card_desc);
        mToDetailTv = (TextView) rootView.findViewById(R.id.tv_to_detail);

    }

    @Override
    protected void bindChildView(MessageWrapper wrapper, int position) {
        if (wrapper == null || wrapper.msg == null || mMessageContainerFl == null) {
            return;

        }
        ChatMessage message = wrapper.msg;
        if (message.msgType == Constant.MessageType.PRODUCT) {
            bindProduct(message, position);
        } else {
            //其他卡片
        }
    }


    private void bindProduct(ChatMessage message, final int position) {
        if (message == null) {
            return;
        }
        setViewData(message.cardInfo);
        mMessageContainerFl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了，卡片_" + position, Toast.LENGTH_SHORT).show();

            }
        });

        mMessageContainerFl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mCommClickListener != null) {
                    mCommClickListener.onLongClick(mMessageContainerFl, position);
                }
                return true;
            }
        });
    }


    private void setViewData(CardMessageInfo cardMessageInfo) {

        if (cardMessageInfo == null) {
            return;
        }
        mNormalLayoutRl.setVisibility(View.VISIBLE);
        mTitleLayoutLl.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(cardMessageInfo.typeName)) {
            mCardCategoryTv.setVisibility(View.GONE);
        } else {
            mCardCategoryTv.setVisibility(View.VISIBLE);
            mCardCategoryTv.setText(cardMessageInfo.typeName);
        }

        if (TextUtils.isEmpty(cardMessageInfo.idDesc)) {
            mCardIdTv.setVisibility(View.GONE);
        } else {
            mCardIdTv.setVisibility(View.VISIBLE);
            mCardIdTv.setText(cardMessageInfo.idDesc);
        }

        mCardIconTiv.setImageResource(R.mipmap.icon_image_default);
        mCardDescTv.setText(cardMessageInfo.nameDesc.trim());

        if (!TextUtils.isEmpty(cardMessageInfo.viewMoreText)) {
            mToDetailTv.setText(cardMessageInfo.viewMoreText);
            mToDetailTv.setVisibility(View.VISIBLE);
        } else {
            mToDetailTv.setVisibility(View.GONE);
        }

    }


}
