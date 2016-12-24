package com.leumas.typingdevelopment.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.leumas.typingdevelopment.R;
import com.leumas.typingdevelopment.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    private FrameLayout flContainer;
    private int backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flContainer = (FrameLayout) findViewById(R.id.flContainer);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContainer, new MainFragment());
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
        //reset
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backButtonCount = 0;
            }
        }, 1000);
    }

}
