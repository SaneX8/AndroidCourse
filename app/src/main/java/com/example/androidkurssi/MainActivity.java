package com.example.androidkurssi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    //private AppBarConfiguration appBarConfiguration;

    public static final String TAG ="MyAppMessage";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.e(TAG, "activating play view");

        final Button button = findViewById(R.id.about_button);
        final View someText = findViewById(R.id.textView2);
        final Button gameButton = findViewById(R.id.gameStart);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (someText.getVisibility() == View.VISIBLE){
                    someText.setVisibility(View.INVISIBLE);
                } else {
                    someText.setVisibility(View.VISIBLE);
                }


            }
        });


        gameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(i);


            }
        });











    }


}