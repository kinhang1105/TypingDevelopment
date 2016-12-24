package com.leumas.typingdevelopment.helper;

/**
 * Created by Samuel on 2016/12/24.
 */

public class ValidationHelper {

    public final static int GENERAL_1S = 1000;

    public static boolean validate(String topic, String answer){
        if(topic.toLowerCase().equals(answer.toLowerCase())){
            return true;
        }else{
            return false;
        }
    }

}
