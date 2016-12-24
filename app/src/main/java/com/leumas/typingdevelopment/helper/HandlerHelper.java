package com.leumas.typingdevelopment.helper;

import android.os.Handler;

/**
 * Created by Samuel on 2016/12/24.
 */

public class HandlerHelper {

    public static void postDelay(int time, final HandlerListener handlerListener){
        if(handlerListener==null) return;
        if(time < 0) time =1000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                handlerListener.run();
            }
        }, time);
    }

    public interface HandlerListener{
        void run();
    }

}
