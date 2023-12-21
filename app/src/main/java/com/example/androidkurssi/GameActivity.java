package com.example.androidkurssi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    int rand_int1;
    public static final String TAG ="MyAppMessage";


    int gameScore;
    SharedPreferences myPreferences;
    private static final String KEY_HS = "HighestScore";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        gameScore = 0;


        Random rand = new Random();
        rand_int1 = rand.nextInt(3);

        Log.e(TAG, "activating play view "+rand_int1);


        final FloatingActionButton refreshBtn = findViewById(R.id.refreshButtoni);
        final ImageButton diamondbtn = findViewById(R.id.imgbtn1);
        final ImageButton diamondbtn2 = findViewById(R.id.imgbtn2);
        final ImageButton diamondbtn3 = findViewById(R.id.imgbtn3);
        final ImageButton diamondbtn4 = findViewById(R.id.imgbtn4);
        final TextView score = findViewById(R.id.scoretxt);





        diamondbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(rand_int1 == 0){
                    diamondbtn.setVisibility(View.GONE);
                    diamondbtn.setImageResource(R.drawable.timu);
                    diamondbtn.setVisibility(View.VISIBLE);
                    gameScore = gameScore + 1;
                    score.setText(getString(R.string.scoretxt)+" "+String.valueOf(gameScore));


                } else {
                    diamondbtn.setVisibility(View.INVISIBLE);
                }



            }
        });

        diamondbtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(rand_int1 == 1){
                    diamondbtn2.setVisibility(View.GONE);
                    diamondbtn2.setImageResource(R.drawable.timu);
                    diamondbtn2.setVisibility(View.VISIBLE);
                    gameScore = gameScore + 1;
                    score.setText(getString(R.string.scoretxt)+" "+String.valueOf(gameScore));
                } else{
                    diamondbtn2.setVisibility(View.INVISIBLE);
                }

            }
        });

        diamondbtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(rand_int1 == 2){
                    diamondbtn3.setVisibility(View.GONE);
                    diamondbtn3.setImageResource(R.drawable.timu);
                    diamondbtn3.setVisibility(View.VISIBLE);
                    gameScore = gameScore + 1;
                    score.setText(getString(R.string.scoretxt)+" "+String.valueOf(gameScore));
                } else{
                    diamondbtn3.setVisibility(View.INVISIBLE);
                }

            }
        });

        diamondbtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(rand_int1 == 3) {
                    diamondbtn4.setVisibility(View.GONE);
                    diamondbtn4.setImageResource(R.drawable.timu);
                    diamondbtn4.setVisibility(View.VISIBLE);
                    gameScore = gameScore + 1;
                    score.setText(getString(R.string.scoretxt)+" "+String.valueOf(gameScore));

                } else{
                    diamondbtn4.setVisibility(View.INVISIBLE);
                }
            }
        });


        refreshBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(i);


            }
        });






    }
}