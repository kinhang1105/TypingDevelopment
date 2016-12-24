package com.leumas.typingdevelopment.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leumas.typingdevelopment.R;

/**
 * Created by Samuel on 2016/12/20.
 */

public class ChatBox extends LinearLayout {
    private static final String TAG = "ChatBox";

    private TextView tvContent;

    public ChatBox(Context context) {
        super(context, null);
    }

    public ChatBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.chatbox_topic, this);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
    }

    public void setContent(String text){
        if(tvContent!=null) tvContent.setText(text);
    }



}
