package com.leumas.typingdevelopment.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.leumas.typingdevelopment.R;
import com.leumas.typingdevelopment.helper.DialogHelper;
import com.leumas.typingdevelopment.helper.HandlerHelper;
import com.leumas.typingdevelopment.helper.RandomChar;
import com.leumas.typingdevelopment.helper.ValidationHelper;
import com.leumas.typingdevelopment.widget.ChatBox;

import static com.leumas.typingdevelopment.helper.ValidationHelper.GENERAL_1S;

/**
 * Created by Samuel on 2016/12/20.
 */

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private ScrollView svContent;
    private LinearLayout llContent;
    private EditText etInput;
    private ImageView ivSubmit, ivMore;
    private ChatBox cbTest;
    private String topic;
    private int level = 5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        bindView(view);
        setUpView();
        return view;
    }

    private void bindView(View view){
        svContent = (ScrollView) view.findViewById(R.id.svContent);
        llContent = (LinearLayout) view.findViewById(R.id.llContent);
        etInput = (EditText) view.findViewById(R.id.etInput);
        ivSubmit = (ImageView) view.findViewById(R.id.ivSubmit);
        ivMore = (ImageView) view.findViewById(R.id.ivMore);
        cbTest = (ChatBox) view.findViewById(R.id.cbTest);
    }

    private void setUpView(){
        cbTest.setContent("JUST COPY WHAT YOU SEE");
        topic = genTopic();

        ivSubmit.setEnabled(false);
        ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genAnswer();

                //validate
                String answer = etInput.getText().toString();
                if(ValidationHelper.validate(topic, answer)){
                    HandlerHelper.postDelay(GENERAL_1S, new HandlerHelper.HandlerListener() {
                        @Override
                        public void run() {
                            topic = genTopic();
                            level++;
                        }
                    });
                }

                etInput.setText("");
            }
        });
        etInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            ivSubmit.performClick();
                            etInput.requestFocus();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        etInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToBottom();
            }
        });
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ivSubmit.setEnabled(s.length()>0);
                Log.d(TAG, "onTextChanged:setEnabled " + (s.length()>0?"T":"F"));
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogHelper.getDialog(getContext(), "Level:" + level);
            }
        });
    }


    private void scrollToBottom(){
        svContent.post(new Runnable() {
            @Override
            public void run() {
                svContent.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    private String genTopic(){
        View view = getActivity().getLayoutInflater().inflate(R.layout.chatbox_topic, null, false);
        TextView tvContent = (TextView) view.findViewById(R.id.tvContent);
        String topic = RandomChar.getRandomText(level);
        tvContent.setText(topic);
        llContent.addView(view);
        tvContent.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in));
        scrollToBottom();
        return topic;
    }

    private void genAnswer(){
        View view = getActivity().getLayoutInflater().inflate(R.layout.chatbox_answer, null, false);
        TextView tvContent = (TextView) view.findViewById(R.id.tvContent);
        tvContent.setText(etInput.getText().toString());
        llContent.addView(view);
        tvContent.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in));
        scrollToBottom();
    }

    private void autoReply(){
        HandlerHelper.postDelay(GENERAL_1S, new HandlerHelper.HandlerListener() {
            @Override
            public void run() {
                etInput.setText(topic);
                ivSubmit.performClick();
            }
        });
    }


}
