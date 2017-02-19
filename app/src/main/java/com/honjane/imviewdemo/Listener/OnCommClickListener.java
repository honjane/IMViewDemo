package com.honjane.imviewdemo.Listener;

import android.view.View;

/**
 * Created by honjane on 2017/2/15.
 */

public interface OnCommClickListener {
    void onAvatarClick(int position);

    void onAvatarLongClick(int position, String nickName);

    void onLongClick(View view, int position);
}
