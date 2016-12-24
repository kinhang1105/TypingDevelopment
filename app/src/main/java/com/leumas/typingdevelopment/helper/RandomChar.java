package com.leumas.typingdevelopment.helper;

import android.util.Log;

import java.util.Random;

/**
 * Created by Samuel on 2016/12/22.
 */

public class RandomChar {

    public static String getRandomText(int length){
        String output = "";
        Random rnd = new Random();
        String randomLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int n=0; n<length; n++){
            output+=randomLetters.charAt(rnd.nextInt(randomLetters.length()));
        }
        return output;
    }
}
